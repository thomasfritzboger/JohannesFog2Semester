package dat.startcode.model.persistence;

import dat.startcode.model.dtos.StockDTO;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.exceptions.DatabaseException;
import java.util.List;

public interface IProductMapper {
    List<ProductDTO> getAllProducts() throws DatabaseException;
    List<StockDTO> getStock() throws DatabaseException;
    void updateUnitPrice(int id, double price) throws DatabaseException;
    void updateProductDescription(int id, String description) throws DatabaseException;
    void saveMaterialLines(int carportId, int productId, int unitLength, int unitQuantity, double totalPrice) throws DatabaseException;
    List<OrderLineDTO> getMaterialLinesByCarportId(int carportId) throws DatabaseException;
}

