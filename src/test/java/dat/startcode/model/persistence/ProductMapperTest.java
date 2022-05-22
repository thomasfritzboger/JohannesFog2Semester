package dat.startcode.model.persistence;

import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.ProductFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

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
                        "values ('1','1','1','1','1'), " +
                        "('2','1','1','1','1'), " +
                        "('3','1','1','1','1')");
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
    void getAllProducts() throws DatabaseException {
        List<ProduktDTO> productList = ProductFacade.getProduktDTOs(connectionPool);
        assertEquals(3,productList.size());
    }
}