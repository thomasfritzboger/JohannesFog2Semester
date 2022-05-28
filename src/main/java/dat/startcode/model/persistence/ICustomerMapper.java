package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface ICustomerMapper {
    Shed createNewShed(int width, int length, String placement) throws DatabaseException;
    Shed getShedById(int shedId) throws DatabaseException;
    Request createCarportRequest(int coverageId, int userId, int width, int length, int height, String roofType, boolean hasShed, int shedId, boolean isConfirmed, double carportPrice) throws DatabaseException;
    List<Request> getCarportRequestById(int userId) throws DatabaseException;
    Carport getCarportById(int carportId) throws DatabaseException;
    boolean updateEmail(int userId, String newEmail) throws DatabaseException;
    boolean updatePassword(int userId, String newPassword) throws DatabaseException;
    boolean updatePhoneNumber(int userId, String newPhoneNumber) throws DatabaseException;

}
