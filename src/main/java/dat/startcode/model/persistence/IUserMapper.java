package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IUserMapper
{
    public User login(String email, String password) throws DatabaseException;
    public User createUser(String email, String password, String role, String phoneNumber, String address, int postalCode) throws DatabaseException;
    public void checkEmailIfExisting(String email) throws DatabaseException;
    public List<User> getCustomers() throws DatabaseException;

}
