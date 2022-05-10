package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IUserMapper
{
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
                if (rs.next())
                {
                    String role = rs.getString("role");
                    user = new User(email, password, role);
                } else
                {
                    throw new DatabaseException("Forkert email eller kodeord.");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Fejl opstået ved log ind. Noget gik galt med databasen.");
        }
        return user;
    }

    @Override
    public User createUser(String email, String password, String role, String phoneNumber, String address, int postalCode) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (email, password, role, phonenumber, address, postal_code) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, role);
                ps.setString(4, phoneNumber);
                ps.setString(5, address);
                ps.setInt(6, postalCode);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user = new User(email, password, role, phoneNumber, address, postalCode);
                } else
                {
                    throw new DatabaseException("Brugeren med email = " + email + " kunne ikke indsættes i databasen");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Kunne ikke indsætte brugeren i databasen");
        }
        return user;
    }


}
