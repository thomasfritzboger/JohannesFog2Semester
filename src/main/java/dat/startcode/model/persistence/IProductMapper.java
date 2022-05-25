package dat.startcode.model.persistence;

import dat.startcode.model.dtos.LagerDTO;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IProductMapper {
    List<ProduktDTO> getAllProducts() throws DatabaseException;
    List<LagerDTO> getLager() throws DatabaseException;
    void updateLagerPrice(int id, double price) throws DatabaseException;
    void updateLagerDescription(int id, String description) throws DatabaseException;
    void saveMaterialLines(int carport_id,int product_id,int unit_lenght, int unit_quantity, double total_price) throws DatabaseException;
    List<OrderLineDTO> getMaterialLinesByCarportId(int carportId) throws DatabaseException;
}

