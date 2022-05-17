package dat.startcode.model.services;

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

}
