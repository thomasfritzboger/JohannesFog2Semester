package dat.startcode.model.services;

import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkurCalculatorTest {

    CarportCalculator calculator;
//    String user = "root";
//    String password = "DiskoT@ngo";
//    String url = "jdbc:mysql://localhost:3306/fog";
//    ConnectionPool connectionPool = new ConnectionPool(user,password,url);
//    List<ProduktDTO> pDTO;
    List<ProduktDTO> produktDTOList = new ArrayList<>();

//    {
//        try {
//            pDTO = ProductFacade.getProduktDTOs(connectionPool);
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//        }
//    }

    @BeforeEach
    void setUp() {


        //calculator = new CarportCalculator(pDTO);



        produktDTOList.add(new ProduktDTO(1,"25x200 mm. trykimp. Brædt",54.96,200,25,0,0,"understernbrædder til for & bag ende","stk"));
        produktDTOList.add(new ProduktDTO(2,"25x200 mm. trykimp. Brædt",54.96,200,25,0,0,"understernbrædder til siderne","stk"));
        produktDTOList.add(new ProduktDTO(3,"25x125 mm. trykimp. Brædt",35.68,125,25,0,0,"oversternbrædder til forenden","stk"));
        produktDTOList.add(new ProduktDTO(4,"25x125 mm. trykimp. Brædt",35.68,125,25,0,0,"oversternbrædder til siderne","stk"));
        produktDTOList.add(new ProduktDTO(5,"38x73 mm lægte ubh",35.68,73,38,0,0,"til z på bagside af dør","stk"));
        produktDTOList.add(new ProduktDTO(6,"45x95 mm. regulr ubh",59.91,95,45,0,0,"løsholter til skur gavle","stk"));
        produktDTOList.add(new ProduktDTO(7,"45x95 mm. regulr ubh",59.91,95,45,0,0,"løsholter til skur sider","stk"));
        produktDTOList.add(new ProduktDTO(8,"45x195 mm. spærtræ ubh",67.82,195,45,0,0,"remme i sider, sadles ned i stolper","stk"));
        produktDTOList.add(new ProduktDTO(9,"45x195 mm. spærtræ ubh",67.82,195,45,0,0,"spær, monteres på rem","stk"));
        produktDTOList.add(new ProduktDTO(10,"97x97 mm. trykimp. brædt",54.25,97,97,0,0,"stolper nedgraves 90 cm. i jord","stk"));
        produktDTOList.add(new ProduktDTO(11,"19x100 mm. trykimo brædt",21.39,100,19,0,0,"til beklædning af skur 1 på 2","stk"));
        produktDTOList.add(new ProduktDTO(12,"19x100 mm. trykimo brædt",21.39,100,19,0,0,"vandbrædt på stern i sider","stk"));
        produktDTOList.add(new ProduktDTO(13,"19x100 mm. trykimo brædt",21.39,100,19,0,0,"vandbrædt på stern i forende","stk"));
        produktDTOList.add(new ProduktDTO(14,"Plastmo Ecolite blåtonet",37.2,0,0,0,0,"tagplader monteres på spær","stk"));
        produktDTOList.add(new ProduktDTO(15,"Plastmo bundskruer 200stk.",282.11,0,0,0,0,"skruer til tagplader","pakke"));
        produktDTOList.add(new ProduktDTO(16,"Hulbånd 1x20mm. 10 mtr.",206.43,1,20,0,0,"til vindkryds på spær","rulle"));
        produktDTOList.add(new ProduktDTO(17,"Universal 190 mm højre",39.25,190,0,0,0,"til montering af spær på rem","stk"));
        produktDTOList.add(new ProduktDTO(18,"Universal 190 mm venstre",39.25,190,0,0,0,"til montering af spær på rem","stk"));
        produktDTOList.add(new ProduktDTO(19,"4,5 x 60 mm. skruer 200 stk.",71.39,60,0,0,4.5,"til montering af stern & vandbrædt","pakke"));
        produktDTOList.add(new ProduktDTO(20,"4,0 x 50 mm. beslagskruer 250 stk.",55.61,50,0,0,4,"til montering af universalbeslag + hulbånd","pakke"));
        produktDTOList.add(new ProduktDTO(21,"Bræddebolt 10 x 120 mm.",10.26,120,10,0,0,"til montering af rem på stolper","stk"));
        produktDTOList.add(new ProduktDTO(22,"Firkantskiver 40x40x11mm",6.41,40,40,11,0,"til montering af rem på stolper","stk"));
        produktDTOList.add(new ProduktDTO(23,"4,5 x 70 mm. skruer 400 stk.",142.14,70,0,0,4.5,"til montering af yderste beklædning","rulle"));
        produktDTOList.add(new ProduktDTO(24,"4,5 x 50 mm. skruer 300 stk.",77.86,50,0,0,4.5,"til montering af inderste beklædning","rulle"));
        produktDTOList.add(new ProduktDTO(25,"Stalddærsgreb 50x75",185,75,50,0,0,"til lås på dør i skur","sæt"));
        produktDTOList.add(new ProduktDTO(26,"T hængsel 390 mm",80.83,390,0,0,0,"til skurdør","stk"));
        produktDTOList.add(new ProduktDTO(27,"Vinkelbeslag 35",8.63,35,0,0,0,"til montering af løsholter i skur","stk"));

        calculator = new CarportCalculator(produktDTOList);
    }

    @Test //Udregner antal og længde på løsholter på dørside i kort skur
    void testBeregnAntalLøsholterDørSideKort() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",3);
        assertEquals(1, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideMedDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på dørside i mellemlangt skur
    void testBeregnLængdeLøsholterDørSideMellemLang() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",4);
        assertEquals(1, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(360, calculator.skur.løsholterLangsideMedDørLængde);

        calculator = new CarportCalculator(produktDTOList);
        calculator.beregnCarport(840,600,210,"y","p",
                "midt",6);
        System.out.println("Længde af hvert lille stykke mellem dør og stolpe "+(6-2)* calculator.skur.afstandMellemSpær);
        assertEquals(3, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideMedDørLængde);

        calculator = new CarportCalculator(produktDTOList);
        calculator.beregnCarport(840,600,210,"y","p",
                "midt",7);
        System.out.println("Længde af hvert lille stykke mellem dør og stolpe "+(7-2)* calculator.skur.afstandMellemSpær);
        assertEquals(3, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(300, calculator.skur.løsholterLangsideMedDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på dørside i langt skur
    void testBeregnAntalLøsholterDørSideLang() throws IllegalDimensionException {
        calculator.beregnCarport(1000,600,210,"y","p",
                "midt",10);
        System.out.println("Da der nu er væg>310 i dør side isættes ekstra stolpe, den samlede afstand deles afstand og hvert stykke skal være mindst "+((10-2)/2)* calculator.skur.afstandMellemSpær);
        assertEquals(6, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideMedDørLængde);
    }

    @Test //Udregner antal og længde på løsholter modsat dørside i kort skur
    void testBeregnAntalLøsholterModsatDørSideKort() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",3);
        assertEquals(3, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideUdenDørLængde);
    }

    @Test //Udregner antal og længde på løsholter modsat dørside i mellemlangt skur
    void testBeregnLængdeLøsholterModsatDørSideMellemLang() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",4);
        assertEquals(3, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideUdenDørLængde);


        calculator = new CarportCalculator(produktDTOList);
        calculator.beregnCarport(820,600,210,"y","p",
                "midt",5);
        assertEquals(3, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(300, calculator.skur.løsholterLangsideUdenDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på dørside i langt skur
    void testBeregnLængdeLøsholterModsatDørSideLang() throws IllegalDimensionException {
        calculator.beregnCarport(790,600,210,"y","p",
                "midt",6);
        assertEquals(6, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideUdenDørLængde);

        calculator = new CarportCalculator(produktDTOList);
        calculator.beregnCarport(960,600,210,"y","p",
                "midt",9);
        assertEquals(6, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(270, calculator.skur.løsholterLangsideUdenDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde < 240 og bredde op til 310)
    void testBeregnAntalLøsholterBredsideSmalt() throws IllegalDimensionException {
        calculator.beregnCarport(700,450,210,"y","p",
                "venstre",3);
        assertEquals(6, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);

        calculator = new CarportCalculator(produktDTOList);
        calculator.beregnCarport(700,540,210,"y","p",
                "venstre",3);
        assertEquals(6, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde mere end 310 og mindre end 480)
    void testBeregnAntalLøsholterBredsideMellemBred() throws IllegalDimensionException {
        calculator.beregnCarport(700,450,210,"y","p",
                "midt",3);
        assertEquals(12, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde mere end 480)
    void testBeregnAntalLøsholterBredsideBred() throws IllegalDimensionException {
        calculator.beregnCarport(700,500,210,"y","p",
                "midt",4);
        assertEquals(12, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);
    }

    @Test //Udregner længde og antal beklædningsbrædder
    void testBeregenAntalBeklædningsBrædder() throws IllegalDimensionException {
        //Da inder og yderbrædder er ens, regnes det samlede antal ud.
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(187, calculator.skur.beklædningsBrædderAntal);
        assertEquals(210, calculator.skur.skurHøjde);
    }

    @Test //Udregner hvor mange pakker skruer der skal til inderbeklædningsbrædderne
    void testBeregnAntalKorteBeklædningsSkruer() throws IllegalDimensionException{
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(3, calculator.skur.pakkerKorteBeklædningsSkruerAntal);
    }

    @Test //Udregner hvor mange pakker skruer der skal til yderbeklædningsbrædderne
    void testBeregnAntalLangeBeklædningsSkruer() throws IllegalDimensionException{
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(2, calculator.skur.pakkerLangeBeklædningsSkruerAntal);
    }

    @Test
    void testAddAllSkurItemsTilListe() throws IllegalDimensionException{
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(9, calculator.skur.skurList.size());
    }
}