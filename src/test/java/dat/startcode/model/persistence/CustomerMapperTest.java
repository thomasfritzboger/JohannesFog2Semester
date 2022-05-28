package dat.startcode.model.persistence;

import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.AdminFacade;
import dat.startcode.model.services.CustomerFacade;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final static String USER = "root";
    private final static String PASSWORD = "Lægø3428@A";
    private final static String URL = "jdbc:mysql://localhost:3306/fog_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()){
            try (Statement stmt = testConnection.createStatement()){
                // Remove all rows from user table
                stmt.execute("delete from user");
                // Insert users
                stmt.execute("insert into user (user_id, email, password, role, phonenumber, address, postal_code) " +
                        "values ('1','admin@fog.dk','1234','admin','70707070','Envej 1','2800'), " +
                        "('2','kunde1@fog.dk','1234','kunde','80808080','Tovej 2','2800'), " +
                        "('3','kunde2@fog.dk','1234','kunde','90909090','Trevej 3','3600')");
                // Remove all rows from coverage table
                stmt.execute("delete from coverage");
                // Insert coverage
                stmt.execute("insert into coverage (coverage_id, coverage) " +
                        "values ('40','40'), " +
                        "('30','30')");
                // Remove all rows from shed table
                stmt.execute("delete from shed");
                // Insert sheds
                stmt.execute("insert into shed (shed_id, width, length, placement) " +
                        "values ('1','300','500','venstre'), " +
                        "('2','300','500','højre')");
                // Remove all rows from carport table
                stmt.execute("delete from carport");
                // Insert carports
                stmt.execute("insert into carport (carport_id, coverage_id, user_id, width, length, height, roof_type, shed_id, hasShed, isConfirmed) " +
                        "values ('1','40','2','300','540','210','p','1','1','0'), " +
                        "('2','40','3','320','560','220','c','1','0','0'), " +
                        "('3','40','2','300','540','210','p','2','1','0')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }


    @Test
    void createCarportRequest() throws DatabaseException {
        CustomerFacade.createCarportRequest(30,2,500,400,210,"c",false,-1,false,3000,connectionPool);
        List<RequestDTO> requests = AdminFacade.getRequest(connectionPool);
        assertEquals(4,requests.size());
    }

    @Test
    void getCarportRequestById() throws DatabaseException {
        List<Request> request = CustomerFacade.getCarportRequestById(2,connectionPool);
        assertEquals(2,request.size());
        List<Request> request2 = CustomerFacade.getCarportRequestById(3,connectionPool);
        assertEquals(1,request2.size());
    }

    @Test
    void getCarportById() throws DatabaseException {
        Carport carport = CustomerFacade.getCarportById(1,connectionPool);
        assertEquals(540,carport.getCarportLength());
        assertNotEquals(560,carport.getCarportLength());
    }

    @Test
    void updateEmail() throws DatabaseException {
        CustomerFacade.updateEmail(2,"a@a.dk",connectionPool);
        List<User> user = AdminFacade.getCustomerList(connectionPool);
        assertNotEquals("kunde1@fog.dk",user.get(0).getEmail());
        assertEquals("a@a.dk",user.get(0).getEmail());
    }

    @Test
    void updatePhoneNumber() throws DatabaseException {
        CustomerFacade.updatePhoneNumber(3,"55663322",connectionPool);
        List<User> user = AdminFacade.getCustomerList(connectionPool);
        assertNotEquals("90909090",user.get(1).getPhoneNumber());
        assertEquals("55663322",user.get(1).getPhoneNumber());
    }

    @Test
    void updatePassword() throws DatabaseException {
        User user = UserFacade.login("kunde1@fog.dk","1234",connectionPool);
        CustomerFacade.updatePassword(user.getUserId(), "3322",connectionPool);
        assertThrows(DatabaseException.class, () -> UserFacade.login(user.getEmail(), "1234", connectionPool));
        User user2 = UserFacade.login("kunde1@fog.dk","3322",connectionPool);
        assertEquals("kunde1@fog.dk",user2.getEmail());
    }
}