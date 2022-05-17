package dat.startcode.model.persistence;

import dat.startcode.model.entities.CarportDimension;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.ShedDimension;
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
    public CarportDimension makeCarportDimension(int length, int width, int height) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        CarportDimension carportDimension;
        String sql = "insert into dimensions (length, width, height) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, length);
                ps.setInt(2, width);
                ps.setInt(3, height);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    carportDimension = new CarportDimension(length, width, height);
                } else
                {
                    throw new DatabaseException("Carport med målene = " + length + "x " + width + "x " + height + " kunne ikke indsættes i databasen");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Kunne ikke indsætte carport i databasen.");
        }
        return carportDimension;
    }

    @Override
    public ShedDimension makeShedDimension(int width, int length, String placement) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        ShedDimension shedDimension;
        String sql = "insert into shed (width, length, placement) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, width);
                ps.setInt(2, length);
                ps.setString(3, placement);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    shedDimension = new ShedDimension(length, width, placement);
                } else
                {
                    throw new DatabaseException("Carport med placering = (" + placement + ") kunne ikke indsættes i databasen");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Kunne ikke indsætte carport i databasen.");
        }
        return shedDimension;
    }

    @Override
    public Request sendCarportRequest(int coverageId, int userId, int dimensionId, int shedId, boolean hasShed, boolean isConfirmed) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        Request request; //insert into user (email, password, role, phonenumber, address, postal_code) values (?,?,?,?,?,?)
        String sql = "insert into carport (coverage_id, user_id, dimensions_id, shed_id, hasShed, isConfirmed) values (?,?,?,?,?,?)";
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
