package dat.startcode.model.persistence;

import dat.startcode.model.dtos.LagerDTO;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
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

    @Override
    public void updateLagerDescription(int id, String description) throws DatabaseException {

        String sql = "UPDATE product_description SET product_description = ? WHERE product_description_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,description);
                ps.setInt(2,id);
                ps.executeUpdate();
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Kunne ikke ændre beskrivelsen for produkt idet: "+ id);
        }

    }

    public void saveMaterialLines( int carport_id,int product_id,int unit_length, int unit_quantity, double total_line_price) throws DatabaseException {

        String sql = "insert into material_line (carport_id,product_id,unit_length,unit_quantity,total_line_price) values (?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql))  {
                ps.setInt(1, carport_id);
                ps.setInt(2, product_id);
                ps.setInt(3, unit_length);
                ps.setInt(4, unit_quantity);
                ps.setDouble(5, total_line_price);
                ps.executeUpdate();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke indsætte material line i databasen");
        }

    }

    @Override
    public List<OrderLineDTO> getMaterialLinesByCarportId(int carportId) throws DatabaseException {
        List<OrderLineDTO> materialLineList = new ArrayList<>();
        String sql = "SELECT p.product_description, m.unit_length, m.unit_quantity, p.scale, p.description from material_line as m " +
                "inner join produktdto as p " +
                "using (product_id) where m.carport_id = ? order by p.scale;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,carportId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String productDescription = rs.getString("product_description");
                    int unitLength = rs.getInt("unit_length");
                    int unitQuantity = rs.getInt("unit_quantity");
                    String scale = rs.getString("scale");
                    String description = rs.getString("description");
                    materialLineList.add(new OrderLineDTO(productDescription,unitLength,unitQuantity,scale,description));

                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle linjer fra database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne få forbindelse til databasen");
        }

        return materialLineList;
    }

}
