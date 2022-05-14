package dat.startcode.model.persistence;

//Denne mapper skal hente materiale data fra FogDB og sætte variabler i Carporcalculator og SkurCalculator
public class MaterialMapper implements iMaterialMapper{

    ConnectionPool connectionPool;

    public MaterialMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
