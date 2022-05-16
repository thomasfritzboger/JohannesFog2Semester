package dat.startcode.model.persistence;

import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.UsementDescriptionDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Denne mapper skal hente materiale data fra FogDB og sætte variabler i Carporcalculator og SkurCalculator
public class MaterialMapper implements iMaterialMapper{

    ConnectionPool connectionPool;

    public MaterialMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<UsementDescriptionDTO> getInfo() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<UsementDescriptionDTO> usementDescriptionlist = new ArrayList<>();
        String sql = "SELECT * FROM fog.usement ORDER BY usement_id desc";


        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String usementDescription = rs.getString("usement_description ");

                    int usementDescriptionId = rs.getInt("usement_id");

usementDescriptionlist.add(new UsementDescriptionDTO(usementDescriptionId,usementDescription));

                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Kunne ikke få alle beskrivelserne fra database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Kunne få forbindelse til databasen");
        }

        return usementDescriptionlist;
    }
}
