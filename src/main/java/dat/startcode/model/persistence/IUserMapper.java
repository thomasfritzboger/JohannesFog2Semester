package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import java.util.List;

public interface IUserMapper
{
    User login(String email, String password) throws DatabaseException;
    User createUser(String email, String password, String role, String phoneNumber, String address, int postalCode) throws DatabaseException;
    void checkEmailIfExisting(String email) throws DatabaseException;
    List<Carport> getCarportByUser(int userId) throws DatabaseException;

}
