package dat.startcode.model.persistence;

import dat.startcode.model.entities.Request;
import dat.startcode.model.exceptions.DatabaseException;

import java.time.LocalDate;
import java.util.List;

public interface ICustomerMapper
{

    public Request sendCarportRequest(int coverageId, int userId, int dimensionId, int shedId, boolean hasShed, boolean isConfirmed) throws DatabaseException;
    public List<Request> getAllCarportRequests(int userId) throws DatabaseException;
}
