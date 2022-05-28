package dat.startcode.model.persistence;

import dat.startcode.model.dtos.RequestDTO;
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

class AdminMapperTest {
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
                // Remove all rows from carport table
                stmt.execute("delete from carport");
                // Insert carports
                stmt.execute("insert into carport (carport_id, coverage_id, user_id, width, length, height, roof_type, hasShed, isConfirmed) " +
                        "values ('1','40','2','300','540','210','c','0','0'), " +
                        "('2','40','3','320','560','220','p','0','0'), " +
                        "('3','40','2','300','540','210','c','0','1')");
                // Remove all rows from usement table
                stmt.execute("delete from usement");
                // Insert usement descriptions
                stmt.execute("insert into usement (usement_id, usement_description) " +
                        "values ('1','Noget tekst')");
                // Remove all rows from unit_scale table
                stmt.execute("delete from unit_scale");
                // Insert unit scales
                stmt.execute("insert into unit_scale (unit_scale_id, unit_scale) " +
                        "values ('1', 'stk')");
                // Remove all rows from productvariant table
                stmt.execute("delete from productvariant");
                // Insert productvariant
                stmt.execute("insert into productvariant (productvariant_id) " +
                        "values ('1')");
                // Remove all rows from product_description table
                stmt.execute("delete from product_description");
                // Insert product descriptions
                stmt.execute("insert into product_description (product_description_id, product_description, unit_price) " +
                        "values ('1','Tekst','22')");
                // Remove all rows from product table
                stmt.execute("delete from product");
                // Insert products
                stmt.execute("insert into product (product_id, product_description_id, productvariant_id, unit_scale_id, usement_id) " +
                        "values ('1','1','1','1','1')");
                // Remove all rows from material_line table
                stmt.execute("delete from material_line");
                // Insert material lines
                stmt.execute("insert into material_line (material_line_id, carport_id, product_id, unit_length, unit_quantity, total_line_price) " +
                        "values ('1','1','1','360','3','100'), " +
                        "('2','2','1','270','5','90'), " +
                        "('3','3','1','360','2','200')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void getCustomers() throws DatabaseException {
        List<User> customerList = AdminFacade.getCustomerList(connectionPool);
        assertEquals(2,customerList.size());

        UserFacade.createUser("kunde4@fog.dk","1234","kunde","44332211","Femvej 5",3600,connectionPool);
        customerList = AdminFacade.getCustomerList(connectionPool);
        assertEquals(3,customerList.size());
    }

    @Test
    void getRequest() throws DatabaseException {
        List<RequestDTO> requestList = AdminFacade.getRequest(connectionPool);
        assertEquals(2,requestList.size());
    }

    @Test
    void getApprovedRequest() throws DatabaseException {
        List<RequestDTO> approvedList = AdminFacade.getApprovedRequest(connectionPool);
        assertEquals(1,approvedList.size());
    }

    @Test
    void newCoverageForCarport() throws DatabaseException {
        List<RequestDTO> carportCoverage = AdminFacade.getRequest(connectionPool);
        assertNotEquals(30,carportCoverage.get(0).getCoverage());

        AdminFacade.updateCarportPrice(30,carportCoverage.get(0).getCarportId(),connectionPool);
        carportCoverage = AdminFacade.getRequest(connectionPool);
        assertEquals(30,carportCoverage.get(0).getCoverage());
    }

    @Test
    void approveCarport() throws DatabaseException {
        List<RequestDTO> approvedList = AdminFacade.getApprovedRequest(connectionPool);
        assertNotEquals(2,approvedList.size());

        approvedList = AdminFacade.getRequest(connectionPool);
        AdminFacade.confirmCarportRequest(approvedList.get(1).getCarportId(),connectionPool);

        approvedList = AdminFacade.getApprovedRequest(connectionPool);
        assertEquals(2,approvedList.size());
    }

    @Test
    void deleteRequest() throws DatabaseException {
        List<RequestDTO> requestList = AdminFacade.getRequest(connectionPool);
        assertEquals(2,requestList.size());

        AdminFacade.deleteRequest(connectionPool,2);

        requestList = AdminFacade.getRequest(connectionPool);
        assertEquals(1,requestList.size());
    }
}