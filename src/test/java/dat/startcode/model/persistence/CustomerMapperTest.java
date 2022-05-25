package dat.startcode.model.persistence;

import dat.startcode.model.entities.Shed;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.CustomerFacade;
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
                        "values ('1','3','5','left'), " +
                        "('2','3','5','right')");
                // Remove all rows from carport table
                stmt.execute("delete from carport");
                // Insert carports
                stmt.execute("insert into carport (carport_id, coverage_id, user_id, width, length, height, shed_id,hasShed, isConfirmed) " +
                        "values ('1','40','2','300','540','210', '1','1','0'), " +
                        "('2','40','3','320','560','220','null','0','0'), " +
                        "('3','40','2','300','540','210','2','1','0')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

//    @Test
//    void createNewShed(){
//    }
//
//    @Test
//    void getShedById() {
//    }
//
//    @Test
//    void createCarportRequest() {
//    }
//
//    @Test
//    void getCarportRequestById() {
//    }
//
//    @Test
//    void getCarportById() {
//    }
}