package dat.startcode.model.persistence;

import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMapper implements IAdminMapper {

    ConnectionPool connectionPool;

    public AdminMapper(ConnectionPool connectionPool) { this.connectionPool = connectionPool; }

    @Override
    public List<User> getCustomers() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<User> customerList = new ArrayList<>();

        String sql = "SELECT user_id, email, phonenumber, address, postal_code FROM user WHERE role = 'kunde'";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phonenumber");
                    String address = rs.getString("address");
                    int postalCode = rs.getInt("postal_code");
                    customerList.add(new User(userId, email, phoneNumber, address, postalCode));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle kunder fra database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne få forbindelse til databasen");
        }

        return customerList;
    }

    @Override
    public List<RequestDTO> getRequest() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        List<RequestDTO> requestList = new ArrayList<>();

        String sql = "SELECT * FROM requestdto";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int carportId = rs.getInt("carport_id");
                    int userId = rs.getInt("user_id");
                    String date =  rs.getString("carport_created");
                    int coverage = rs.getInt("coverage");
                    double price = rs.getDouble("price");
                    requestList.add(new RequestDTO(carportId,userId,date,coverage,price));
                }
            } catch (SQLException sqlException) {
                throw new DatabaseException("Kunne ikke få alle carporte fra database");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }

        return requestList;
    }

    @Override
    public List<RequestDTO> getApprovedRequest() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        List<RequestDTO> requestList = new ArrayList<>();

        String sql = "SELECT carport_id, user_id, carport_created, isConfirmed " +
                "FROM carport " +
                "WHERE isConfirmed = 1";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int carportId = rs.getInt("carport_id");
                    int userId = rs.getInt("user_id");
                    String date = rs.getString("carport_created");
                    String accepted = rs.getString("isConfirmed");
                    requestList.add(new RequestDTO(carportId,userId,date,accepted));
                }
            } catch (SQLException sqlException) {
                throw new DatabaseException("Kunne ikke få alle carport");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return requestList;
    }

    @Override
    public Carport newCoverageForCarport(int newCoverage, int carportId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");
        Carport carport;
        String sql = "UPDATE carport " +
                "SET coverage_id = ? " +
                "WHERE carport_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,newCoverage);
                ps.setInt(2,carportId);
                ps.executeUpdate();
                carport = new Carport(carportId,newCoverage);
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Enten er carportens id forkert, eller det valgte dækningsbidrag findes ikke");
        }
        return carport;
    }

    @Override
    public Carport approveCarport(int carportId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");
        Carport carport;
        String sql = "UPDATE carport " +
                "SET isConfirmed = 1 " +
                "WHERE carport_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,carportId);
                ps.executeUpdate();
                carport = new Carport(carportId);
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Kunne bekræfte forspørgsel for carport: " + carportId);
        }
        return carport;
    }
}
