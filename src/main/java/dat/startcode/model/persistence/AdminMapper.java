package dat.startcode.model.persistence;

import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                throw new DatabaseException("Kunne ikke få alle kunder fra databasen");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
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
                throw new DatabaseException("Kunne ikke få alle forespørgsler");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return requestList;
    }

    @Override
    public void deleteRequest(int carportId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        String sql = "DELETE FROM carport " +
                "WHERE carport_id = ?";
        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1,carportId);
                ps.executeUpdate();
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Carportens id findes ikke");
        }
    }

    public Carport getCarportPriceAndCoverageById(int carportId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");

        Carport carport = null;

        String sql = "SELECT carport_price, coverage_id FROM carport WHERE carport_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){

                ps.setInt(1, carportId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    double carportPrice = rs.getDouble("carport_price");
                    int coverage = rs.getInt("coverage_id");

                    carport = new Carport(coverage, carportPrice);
                }
            } catch (SQLException sqlException) {
                throw new DatabaseException("Kunne ikke finde prisen på carport");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return carport;
    }

    @Override
    public Carport newCoverageForCarport(int newCoverage, int carportId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO,"");
        Carport carport;
        Carport carport1 = getCarportPriceAndCoverageById(carportId);
        String sql = "UPDATE carport SET coverage_id = ?, carport_price = ? WHERE carport_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){

                //get carportprice by carport id
                double getCarportPrice = carport1.getCarportPrice();

                //get coverage by carport id
                int oldCoverage = carport1.getCoverage();

                double newPrice = Math.round(((getCarportPrice/((double)oldCoverage/100+1))*((double)newCoverage/100+1))*100.0)/100.0;

                ps.setInt(1,newCoverage);
                ps.setDouble(2, newPrice);
                ps.setInt(3,carportId);
                ps.executeUpdate();
                carport = new Carport(carportId,newCoverage, newPrice);
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Enten er carportens id forkert, eller den valgte dækningsbidrag ikke tilgængelig");
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
            throw new DatabaseException("Kunne ikke bekræfte forespørgsel for carport med id: " + carportId);
        }
        return carport;
    }
}
