package dat.startcode.model.persistence;

import dat.startcode.model.entities.*;
import dat.startcode.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ICustomerMapper
{
    public Shed createNewShed(int width, int length, String placement) throws DatabaseException;
    public Shed getShedById(int shedId) throws DatabaseException;
    public Request createCarportRequest(int coverageId, int userId, int width, int length, int height, boolean hasShed, int shedId, boolean isConfirmed) throws DatabaseException;
    public List<Request> getCarportRequestById(int userId) throws DatabaseException;
    public Carport getCarportById(int carportId) throws DatabaseException;

}
