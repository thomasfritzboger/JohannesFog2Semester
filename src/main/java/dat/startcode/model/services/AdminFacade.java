package dat.startcode.model.services;

import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.AdminMapper;
import dat.startcode.model.persistence.ConnectionPool;
import java.util.List;

public class AdminFacade {

    public static List<User> getCustomerList(ConnectionPool connectionPool) throws DatabaseException {
        AdminMapper adminMapper = new AdminMapper(connectionPool);
        return adminMapper.getCustomers();
    }

    public static List<RequestDTO> getRequest(ConnectionPool connectionPool) throws DatabaseException {
        AdminMapper adminMapper = new AdminMapper(connectionPool);
        return adminMapper.getRequest();
    }

    public static List<RequestDTO> getApprovedRequest(ConnectionPool connectionPool) throws DatabaseException {
        AdminMapper adminMapper = new AdminMapper(connectionPool);
        return adminMapper.getApprovedRequest();
    }

    public static void deleteRequest (ConnectionPool connectionPool, int carportId) throws DatabaseException {
        AdminMapper adminMapper = new AdminMapper(connectionPool);
        adminMapper.deleteRequest(carportId);
    }

    public static Carport updateCarportPrice(int newCoverage, int carportId, ConnectionPool connectionPool) throws DatabaseException {
        AdminMapper adminMapper = new AdminMapper(connectionPool);
        return adminMapper.newCoverageForCarport(newCoverage,carportId);
    }

    public static Carport confirmCarportRequest(int carportId, ConnectionPool connectionPool) throws DatabaseException {
        AdminMapper adminMapper = new AdminMapper(connectionPool);
        return adminMapper.approveCarport(carportId);
    }
}
