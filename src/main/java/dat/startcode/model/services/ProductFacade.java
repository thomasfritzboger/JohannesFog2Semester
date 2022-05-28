package dat.startcode.model.services;

import dat.startcode.model.dtos.StockDTO;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.ProductMapper;

import java.util.List;

public class ProductFacade {

    public static List<ProductDTO> getProductDTOs(ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getAllProducts();
    }

    public static List<StockDTO> getStock(ConnectionPool connectionPool) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getStock();
    }

    public static void updateUnitPrice(ConnectionPool connectionPool, int id, double price) throws DatabaseException{
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.updateUnitPrice(id,price);
    }

    public static void updateProductDescription(ConnectionPool connectionPool, int id, String description) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.updateProductDescription(id,description);
    }

    public static void saveMaterialLines(ConnectionPool connectionPool, int carportId, int productId, int unitLength, int unitQuantity, double totalLinePrice) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.saveMaterialLines(carportId,productId,unitLength,unitQuantity,totalLinePrice);
    }

    public static List<OrderLineDTO> getMaterialLinesByCarportId (ConnectionPool connectionPool, int carportId) throws  DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        return productMapper.getMaterialLinesByCarportId(carportId);
    }
}
