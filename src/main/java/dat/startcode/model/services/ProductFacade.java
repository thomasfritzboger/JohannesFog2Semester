package dat.startcode.model.services;

import dat.startcode.model.dtos.LagerDTO;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

import java.util.List;

public class ProductFacade {

    public static List<ProduktDTO> getProduktDTOs (ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getAllProducts();
    }

    public static List<LagerDTO> getLager (ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getLager();
    }

    public static void updateLagerPrice (ConnectionPool connectionPool, int id, double price) throws DatabaseException{
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.updateLagerPrice(id,price);
    }

    public static void updateLagerDescription (ConnectionPool connectionPool, int id, String description) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.updateLagerDescription(id,description);
    }

    public static void saveMaterialLines(ConnectionPool connectionPool, int carport_id,int product_id,int unit_length, int unit_quantity, double total_line_price) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.saveMaterialLines(carport_id,product_id,unit_length,unit_quantity,total_line_price);
    }

    public static List<OrderLineDTO> getMaterialLinesByCarportId (ConnectionPool connectionPool, int carportId) throws  DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getMaterialLinesByCarportId(carportId);
    }
}
