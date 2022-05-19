package dat.startcode.model.persistence;

import dat.startcode.model.dtos.RequestDTO;
import dat.startcode.model.entities.Carport;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

public interface IAdminMapper {

    public List<Carport> getDoneCarports() throws DatabaseException;
    public List<User> getCustomers() throws DatabaseException;
    //public List<Product> getProducts() throws DatabaseException;
    //TODO at gemme product som en classe.
    List<RequestDTO> getRequest() throws DatabaseException;

    Carport newCoverageForCarport( int newCoverage ,int carportId) throws DatabaseException;
}
