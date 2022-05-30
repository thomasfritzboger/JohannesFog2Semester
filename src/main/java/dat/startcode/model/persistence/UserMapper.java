package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IUserMapper {
    ConnectionPool connectionPool;

    public UserMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public User login(String email, String password) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("user_id");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");
                    int postalCode = rs.getInt("postal_code");
                    user = new User(id,email,password,role,phoneNumber,address,postalCode);
                } else {
                    throw new DatabaseException("Forkert email eller kodeord.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Fejl opstået ved log ind. Noget gik galt med databasen.");
        }
        return user;
    }

    @Override
    public User createUser(String email, String password, String role, String phoneNumber, String address, int postalCode) throws DatabaseException {
        checkEmailIfExisting(email);

        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (email, password, role, phonenumber, address, postal_code) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, role);
                ps.setString(4, phoneNumber);
                ps.setString(5, address);
                ps.setInt(6, postalCode);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    user = new User(email, password, role, phoneNumber, address, postalCode);
                } else {
                    throw new DatabaseException("Brugeren med email: " + email + " kunne ikke indsættes i databasen");
                }
            }
        }
        catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke indsætte brugeren i databasen");
        }
        return user;
    }

    @Override
    public void checkEmailIfExisting(String email) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;
        int result = 0;

        String sql = "SELECT EXISTS(SELECT * FROM user WHERE email = ?) AS TEST";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = Integer.parseInt(rs.getString("TEST"));

                    if(result == 1) {
                        throw new DatabaseException("En bruger findes allerede med denne email.");
                    }

                } else {
                    throw new DatabaseException("Noget gik galt.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Noget gik galt 2.");
        }
    }

    @Override
    public List<Carport> getCarportByUser(int loggedInUserId) throws DatabaseException {

        List<Carport> carportList = new ArrayList<>();

        String sql = "SELECT carport_id, user_id, shed_id, hasShed FROM carport WHERE user_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,loggedInUserId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int carportId = rs.getInt("carport_id");
                    int userId = rs.getInt("user_id");
                    int shedId = rs.getInt("shed_id");
                    int hasShed =  1;
                    carportList.add(new Carport(carportId, userId, shedId, hasShed));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke finde carporte til bruger med id " + loggedInUserId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return carportList;
    }
}
