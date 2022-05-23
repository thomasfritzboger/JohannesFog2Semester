package dat.startcode.model.services;

import dat.startcode.model.dtos.LagerDTO;
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

    public static void saveMaterialLines(ConnectionPool connectionPool, int materialLineId, int carport_id,int product_id,int unit_lenght, int unit_quantity, double total_price) throws DatabaseException {
        ProductMapper productMapper = new ProductMapper(connectionPool);
        productMapper.saveMaterialLines(materialLineId,carport_id,product_id,unit_lenght,unit_quantity,total_price);
    }

}
