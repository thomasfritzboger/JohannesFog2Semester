package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.CarportDimension;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.ShedDimension;
import dat.startcode.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ICustomerMapper
{

    public CarportDimension makeCarportDimension(int length, int width, int height) throws DatabaseException;
    public ShedDimension makeShedDimension(int width, int length, String placement) throws DatabaseException;
    public Request sendCarportRequest(int coverageId, int userId, int dimensionId, int shedId, boolean hasShed, boolean isConfirmed) throws DatabaseException;
    public List<Request> getAllCarportRequests(int userId) throws DatabaseException;
}
