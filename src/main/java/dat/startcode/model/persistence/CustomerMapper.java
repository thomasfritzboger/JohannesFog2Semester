package dat.startcode.model.persistence;

import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerMapper implements ICustomerMapper
{
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public Request sendCarportRequest(int coverageId, int userId, int dimensionId, int shedId, boolean hasShed, boolean isConfirmed) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Request request;
        String sql = "insert into carport (coverage_id, user_id, dimension_id, shed_id, hasShed, isCofirmed) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, coverageId);
                ps.setInt(2, userId);
                ps.setInt(3, dimensionId);
                ps.setInt(6, shedId);
                ps.setBoolean(7, hasShed);
                ps.setBoolean(8, isConfirmed);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    request = new Request(coverageId,userId,dimensionId,shedId,hasShed,isConfirmed);
                } else
                {
                    throw new DatabaseException("Carport til brugeren med id = " + userId + " kunne ikke indsættes i databasen");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Kunne ikke indsætte carport i databasen.");
        }
        return request;
    }

    @Override
    public List<Request> getAllCarportRequests(int userId) throws DatabaseException {
        return null;
    }
}
