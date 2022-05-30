package dat.startcode.model.persistence;

import dat.startcode.model.dtos.StockDTO;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProductDTO;
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
    public List<ProductDTO> getAllProducts() throws DatabaseException {

        List<ProductDTO> productDTOList = new ArrayList<>();

        String sql = "SELECT * FROM productdto order by product_id";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    String productDescription = rs.getString("product_description");
                    double unitPrice = rs.getDouble("unit_price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    double diameter = rs.getDouble("diameter");
                    String usement = rs.getString("description");
                    String scale = rs.getString("scale");

                    productDTOList.add(new ProductDTO(productId,productDescription,unitPrice,length,width,height,diameter,usement,scale));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle produkter fra databasen");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return productDTOList;
    }

    @Override
    public List<StockDTO> getStock() throws DatabaseException {

        List<StockDTO> stockDTOList = new ArrayList<>();

        String sql = "SELECT * FROM product_description";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int stockId = rs.getInt("product_description_id");
                    String productDescription = rs.getString("product_description");
                    double unitPrice = rs.getDouble("unit_price");
                    stockDTOList.add(new StockDTO(stockId,productDescription,unitPrice));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle product_descriptions fra databasen");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return stockDTOList;
    }

    @Override
    public void updateUnitPrice(int id, double price) throws DatabaseException {
        String sql = "UPDATE product_description SET unit_price = ? WHERE product_description_id = ?";

        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setDouble(1,price);
                ps.setInt(2,id);
                ps.executeUpdate();
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Kunne ikke ændre prisen for produkt med id: "+ id);
        }
    }

    @Override
    public void updateProductDescription(int id, String description) throws DatabaseException {
        String sql = "UPDATE product_description SET product_description = ? WHERE product_description_id = ?";
        try (Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,description);
                ps.setInt(2,id);
                ps.executeUpdate();
            }
        }catch (SQLException sqlException) {
            throw new DatabaseException("Kunne ikke ændre beskrivelsen for produkt med id: "+ id);
        }
    }

    public void saveMaterialLines(int carportId, int productId, int unitLength, int unitQuantity, double totalPrice) throws DatabaseException {

        String sql = "insert into material_line (carport_id,product_id,unit_length,unit_quantity,total_line_price) values (?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql))  {
                ps.setInt(1, carportId);
                ps.setInt(2, productId);
                ps.setInt(3, unitLength);
                ps.setInt(4, unitQuantity);
                ps.setDouble(5, totalPrice);
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
                "inner join productdto as p " +
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
            throw new DatabaseException("Kunne ikke få forbindelse til databasen");
        }
        return materialLineList;
    }

}
