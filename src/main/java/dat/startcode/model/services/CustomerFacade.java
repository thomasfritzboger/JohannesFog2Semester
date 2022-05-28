package dat.startcode.model.services;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.Shed;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CustomerMapper;

import java.util.List;

public class CustomerFacade
{
    public static Shed createNewShed(int width, int length, String placement, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.createNewShed(width, length, placement);
    }

    public static Request createCarportRequest(int coverageId, int userId, int width, int length, int height, String roofType, boolean hasShed, int shedId, boolean isConfirmed,double carportPrice, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.createCarportRequest(coverageId, userId, width, length, height, roofType, hasShed, shedId, isConfirmed, carportPrice);
    }

    public static List<Request> getCarportRequestById(int userId, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.getCarportRequestById(userId);
    }

    public static Carport getCarportById(int carportId, ConnectionPool connectionPool) throws DatabaseException{
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.getCarportById(carportId);
    }

    public static boolean updateEmail(int userId, String newEmail, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.updateEmail(userId,newEmail);
    }

    public static boolean updatePassword(int userId, String pass, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.updatePassword(userId,pass);
    }

    public static boolean updatePhoneNumber(int userId, String phoneNumber, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.updatePhoneNumber(userId,phoneNumber);
    }

}