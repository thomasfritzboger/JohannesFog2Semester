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
    public List<Carport> getDoneCarports() throws DatabaseException {

        List<Carport> carportList = new ArrayList<>();

        //TODO Create carport i database så der er noget at hente.
        //Temp data
        carportList.add( new Carport(1,1,500,560));
        carportList.add( new Carport(2,2,600,440));
        carportList.add( new Carport(3,3,380,600));

        return carportList;
    }
}
