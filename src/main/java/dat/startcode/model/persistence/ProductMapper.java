package dat.startcode.model.persistence;

import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper implements IProductMapper {

    ConnectionPool connectionPool;

    public ProductMapper(ConnectionPool connectionPool) { this.connectionPool = connectionPool; }


    @Override
    public List<ProduktDTO> getAllProducts() throws DatabaseException {

        List<ProduktDTO> produktDTOList = new ArrayList<>();

        String sql = "SELECT * FROM produktdto";

        System.out.println("Inden SQL");
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int produktId = rs.getInt("product_id");
                    String produktDescription = rs.getString("product_description");
                    double unitPrice = rs.getDouble("unit_price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    double diameter = rs.getDouble("diameter");
                    String usement = rs.getString("description");
                    String scale = rs.getString("scale");

                    produktDTOList.add(new ProduktDTO(produktId,produktDescription,unitPrice,length,width,height,diameter,usement,scale));
                    System.out.println("SQL Create");
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle kunder fra database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne få forbindelse til databasen");
        }

        return produktDTOList;
    }
}
