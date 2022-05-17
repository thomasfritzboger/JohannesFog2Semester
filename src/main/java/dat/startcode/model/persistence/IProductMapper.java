package dat.startcode.model.persistence;

import dat.startcode.model.entities.Produkt;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IProductMapper {
    List<Produkt> getAllProducts() throws DatabaseException;
}
