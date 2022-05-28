package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerMapper implements ICustomerMapper {
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Shed createNewShed(int width, int length, String placement) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        boolean result = false;
        int newId = 0;
        Shed shed = null;
        String sql = "insert into shed (width, length, placement) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, width);
                ps.setInt(2, length);
                ps.setString(3, placement);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Carport med placering = (" + placement + ") kunne ikke indsættes i databasen");
                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);
                    shed = new Shed(newId, length, width, placement);
                } else {
                    shed = null;
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke indsætte carport i databasen.");
        }
        return shed;
    }

    @Override
    public Shed getShedById(int shedId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Shed shed = null;

        String sql = "SELECT width, length, placement FROM shed WHERE shed_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,shedId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    String placement = rs.getString("placement");
                    shed = new Shed(shedId, width,length,placement);
                }
            }
        } catch (Exception ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af shed tabellen fra databasen.");
        }
        return shed;
    }

    @Override
    public Request createCarportRequest(int coverageId, int userId, int width, int length, int height, String roofType, boolean hasShed, int shedId, boolean isConfirmed, double carportPrice) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Request request;
        String sql = "insert into carport (coverage_id, user_id, width, length, height, roof_type, shed_id, hasShed, isConfirmed,carport_price) values (?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, coverageId);
                ps.setInt(2, userId);
                ps.setInt(3, width);
                ps.setInt(4, length);
                ps.setInt(5, height);
                ps.setString(6, roofType);
                if(shedId == -1) {
                    ps.setNull(7, java.sql.Types.INTEGER);
                } else {
                    ps.setInt(7, shedId);
                }
                ps.setBoolean(8, hasShed);
                ps.setBoolean(9, isConfirmed);
                ps.setDouble(10,carportPrice);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    request = new Request(coverageId,userId,width, length, height,shedId,hasShed,isConfirmed,carportPrice);
                } else
                {
                    throw new DatabaseException("Carport til brugeren med id = " + userId + " kunne ikke indsættes i databasen");
                }
            }
        }
        catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke indsætte carport i databasen.");
        }
        return request;
    }

    @Override
    public List<Request> getCarportRequestById(int userId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        List<Request> userRequestList = new ArrayList<>();
        Shed shed = null;
        Request request = null;

        String sql = "SELECT carport_id, coverage_id, width, length, height, shed_id, hasShed, isConfirmed, carport_created FROM carport WHERE user_id = ? order by isConfirmed desc";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int carportId = rs.getInt("carport_id");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    int shedId = rs.getInt("shed_id");
                    boolean hasShed = rs.getBoolean("hasShed");
                    boolean isConfirmed = rs.getBoolean("isConfirmed");
                    Timestamp dateCreated = rs.getTimestamp("carport_created");

                    if(hasShed) shed = getShedById(shedId);

                    request = new Request(carportId, width, length, height, shedId, hasShed, shed, isConfirmed, dateCreated);
                    userRequestList.add(request);
                }
            }
        } catch (Exception ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af carport tabellen fra databasen.");
        }
        return userRequestList;
    }

    @Override
    public Carport getCarportById(int carportId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Carport carport = null;
        Shed shed = null;

        String sql = "SELECT width, length, height, roof_type, shed_id, hasShed FROM carport WHERE carport_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    String roofType = rs.getString("roof_type");
                    int shedId = rs.getInt("shed_id");
                    boolean hasShed = rs.getBoolean("hasShed");
                    shed = getShedById(shedId);
                    carport = new Carport(carportId, width, length, height, roofType, shed, hasShed);
                }
            }
        } catch (Exception ex) {
            throw new DatabaseException(ex, "Fejl under indlæsning af shed tabellen fra databasen.");
        }
        return carport;
    }

    @Override
    public boolean updateEmail(int userId, String newEmail) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "update user SET email = ? where user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, newEmail);
                ps.setInt(2, userId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke ændre email for bruger med " + userId +" i databasen.");
        }
        return true;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "update user SET password = ? where user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, newPassword);
                ps.setInt(2, userId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke ændre kodeord for bruger med " + userId +" i databasen.");
        }
        return true;
    }

    @Override
    public boolean updatePhoneNumber(int userId, String newPhoneNumber) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "update user SET phonenumber = ? where user_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, newPhoneNumber);
                ps.setInt(2, userId);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke ændre telefonnr. for bruger med " + userId +" i databasen.");
        }
        return true;
    }
}
