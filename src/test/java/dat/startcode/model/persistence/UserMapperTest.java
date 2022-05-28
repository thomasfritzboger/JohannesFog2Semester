package dat.startcode.model.persistence;

import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.AdminFacade;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
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
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from user table
                stmt.execute("delete from user");
                // Inset users
                stmt.execute("insert into user (user_id, email, password, role, phonenumber, address, postal_code) " +
                        "values ('1','admin@fog.dk','1234','admin','70707070','Envej 1','2800'), " +
                        "('2','kunde1@fog.dk','1234','kunde','80808080','Tovej 2','2800'), " +
                        "('3','kunde2@fog.dk','1234','kunde','90909090','Trevej 3','3600')");
                // Remove all rows from coverage table
                stmt.execute("delete from coverage");
                // Insert coverage
                stmt.execute("insert into coverage (coverage_id, coverage) " +
                        "values ('40','40')");
                // Remove all rows from carport table
                stmt.execute("delete from carport");
                // Insert carports
                stmt.execute("insert into carport (carport_id, coverage_id, user_id, width, length, height, roof_type, hasShed, isConfirmed) " +
                        "values ('1','40','2','300','540','210','c','0','0'), " +
                        "('2','40','3','320','560','220','p','0','0')");
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
    void testLogin() throws DatabaseException {
        User actualUser = UserFacade.login("kunde1@fog.dk","1234", connectionPool);
        assertEquals("kunde1@fog.dk", actualUser.getEmail());
        assertNotEquals("kunde2@fog.dk", actualUser.getEmail());
    }

    @Test
    void testCreateUser() throws DatabaseException {
        UserFacade.createUser("jill@fog.dk", "1234", "kunde", "10101010", "Firevej 4", 3600, connectionPool);
        List<User> userList = AdminFacade.getCustomerList(connectionPool);
        assertEquals(3,userList.size());
        assertEquals("jill@fog.dk", userList.get(2).getEmail());
    }

    @Test
    void checkEmailIfExisting() {
        assertThrows(DatabaseException.class, () -> UserFacade.login("kunde1@fog.dk","1233", connectionPool));
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob@fog.dk","1234", connectionPool));
    }

    @Test
    void getCarportByUser() throws DatabaseException {
        List<Carport> carportList = UserFacade.getCarportByUser(2,connectionPool);
        assertEquals(1,carportList.get(0).getCarportId());
        assertNotEquals(2,carportList.get(0).getCarportId());
    }

}
