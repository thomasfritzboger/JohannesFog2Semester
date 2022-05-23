package dat.startcode.model.persistence;

import dat.startcode.model.dtos.LagerDTO;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.entities.Carport;
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

        String sql = "SELECT * FROM produktdto order by product_id";

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
                throw new DatabaseException("Kunne ikke få alle produtker fra database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne få forbindelse til databasen");
        }

        return produktDTOList;
    }

    @Override
    public List<LagerDTO> getLager() throws DatabaseException {

        List<LagerDTO> lagerDTOList = new ArrayList<>();

        String sql = "SELECT * FROM product_description";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int lagerID = rs.getInt("product_description_id");
                    String lagerDescription = rs.getString("product_description");
                    double lagerPrice = rs.getDouble("unit_price");

                    lagerDTOList.add(new LagerDTO(lagerID,lagerDescription,lagerPrice));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle product_descriptions fra databasen");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne få forbindelse til databasen");
        }

        return lagerDTOList;
    }

    @Override
    public void updateLagerPrice(int id, double price) throws DatabaseException {

        String sql = "UPDATE product_description SET unit_price = ? WHERE product_description_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setDouble(1,price);
                ps.setInt(2,id);
                ps.executeUpdate();
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Kunne ikke ændre prisen for produkt idet: "+ id);
        }
    }

}
