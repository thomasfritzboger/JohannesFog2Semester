package dat.startcode.model.services;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.xml.crypto.Data;
import java.util.List;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);
        return userMapper.login(email, password);
    }

    public static User createUser(String email, String password, String role, String phoneNumber, String address, int postalCode, ConnectionPool connectionPool) throws DatabaseException
    {
        UserMapper userMapper = new UserMapper(connectionPool);
        return userMapper.createUser(email, password, role, phoneNumber, address, postalCode);
    }

    public static List<Carport> getCarportByUser(int userId, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper userMapper = new UserMapper(connectionPool);
        return userMapper.getCarportByUser(userId);
    }
}