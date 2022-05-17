package dat.startcode.model.persistence;

import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IProductMapper {
    List<ProduktDTO> getAllProducts() throws DatabaseException;
}
