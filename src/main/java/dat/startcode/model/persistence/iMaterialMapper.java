package dat.startcode.model.persistence;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.UsementDescriptionDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.util.List;

//Dette interface er til MaterialMapper
public interface iMaterialMapper {

    //Laves metode til at hente usementdescriptions product information mm
    public List<UsementDescriptionDTO> getInfo () throws DatabaseException;

    //Laves metoder der sætter klassevariabler i CarporCalculator klassen
    //Laves metoder der sætter klassevariabler i SkurCalculator klassen

}
