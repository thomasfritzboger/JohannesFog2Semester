package dat.startcode.model.services;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    CarportCalculator calculator;
    //String user = "root";
    //String password = "DiskoT@ngo";
    //String url = "jdbc:mysql://localhost:3306/fog";
    //ConnectionPool connectionPool = new ConnectionPool(user,password,url);
    //List<ProduktDTO> pDTO = ProductFacade.getProduktDTOs(connectionPool);

    //List<ProduktDTO> nyListe = new ArrayList<>();
    List<ProduktDTO> produktDTOList = new ArrayList<>();

    CarportCalculatorTest() throws DatabaseException { }

    @BeforeEach
    void setUp() {



       // produktDTOList = new ArrayList<>();
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
        produktDTOList.add(new ProduktDTO(28,"Cembrit tagplade 1090x1180",119.95,0,0,0,0,"tagplader monteres på spær","stk"));
        produktDTOList.add(new ProduktDTO(29,"'Eternitskrue 6.0x100'",123.04,0,0,0,0,"'skruer til tagplader'","stk"));

        calculator = new CarportCalculator(produktDTOList);

    }

    @Test
    void testSetDimensionCarport() {
        calculator.setDimensionCarport(780,600,210);
        assertEquals(780, calculator.carportLængde);
        assertEquals(600, calculator.carportBredde);
        assertEquals(210, calculator.carportHøjde);
    }

    @Test
    void testCheckDimensionsCarportForLangUdenSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.beregnCarport(780,600,210,"n","",
                        "",0);
                });
    }

    @Test
    void testCheckDimensionsCarportForKortUdenSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.beregnCarport(400,600,210,"n","",
                        "",0);
                });
    }

    @Test
    void testCheckDimensionsCarportForLangMedSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.beregnCarport(780,600,210,"y","c",
                        "midt",3);
                });
    }

    @Test
    void testCheckDimensionsCarportForSmal() {

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.beregnCarport(600,200,210,"y","p",
                        "midt",3);
                });
    }

    @Test
    void testCheckDimensionsCarportForHøj() {

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.beregnCarport(600,380,410,"y","p",
                        "midt",3);
                });
    }

    @Test
    void testSetAntalSpær() throws IllegalDimensionException {
        calculator.beregnCarport(600,400,220,"y","p","midt",3);
        assertEquals(12, calculator.spærAntal);
    }

    @Test
    void testBberegnAfstandMellemSpær() throws IllegalDimensionException {
        calculator.beregnCarport(780,500,230,"y","p","midt",5);
        assertEquals(55, calculator.afstandMellemSpær);
   }

    @Test
    void testSetStolpeLængde() throws IllegalDimensionException {
        calculator.beregnCarport(600,600,210,"y","p",
                "midt",3);

        assertEquals(300, calculator.stolperLængde);
    }

    @Test
    void testBeregnAntalStolperSmalUdenSkur() throws IllegalDimensionException {

        calculator.beregnCarport(500, 300, 210, "n", "p",
                "", 0);
        assertEquals(6, calculator.stolperAntal);
    }

    @Test
    void testBeregnAntalStolperBredUdenSkur() throws IllegalDimensionException {
        calculator.beregnCarport(500, 400, 210, "n", "p",
                "", 0);
        assertEquals(6, calculator.stolperAntal);

    }

    @Test
        //Carport under 3m bred
    void testBeregnAntalStolperKortMedSkurMidtSmal() throws IllegalDimensionException {

        calculator.beregnCarport(600, 300, 210, "y", "p",
                "midt", 3);
        assertEquals(9, calculator.stolperAntal);
    }

    @Test
        //Halvlang (310-(310+dørbredde))
    void testBeregnAntalStolperHalvlangMedSkurMidtSmal() throws IllegalDimensionException {

        calculator.beregnCarport(850,300,210,"y","p",
                "midt",7);
        assertEquals(10, calculator.stolperAntal);
    }

    @Test
        //Halvlang (310-(310+dørbredde))
    void testBeregnAntalStolperLangMedSkurMidtSmal() throws IllegalDimensionException {

        //Lang mere end 310+dørbredde
        calculator.beregnCarport(950,300,210,"y","p",
                "midt",9);
        assertEquals(11, calculator.stolperAntal);
    }

    @Test
    void testBeregnAntalStolperMedKortSkurMidtBred() throws IllegalDimensionException {

        //Bred og kort carport med helt skur
        calculator.beregnCarport(600, 480, 210, "y", "p",
                "midt", 3);
        assertEquals(11, calculator.stolperAntal);
    }

    @Test
    void testBeregnAntalStolperMedMellemlangSkurMidtBred() throws IllegalDimensionException {

        //Bred og mellemlang carport med helt skur
        calculator.beregnCarport(850,480,210,"y","p",
                "midt",7);
        assertEquals(12, calculator.stolperAntal);

        calculator = new CarportCalculator(produktDTOList);
        //Bred og lang carport med helt skur
        calculator.beregnCarport(950,480,210,"y","p",
                "midt",9);
        assertEquals(13, calculator.stolperAntal);
    }

    @Test
    void testBeregnAntalStolperMedSkurVenstreHøjre() throws IllegalDimensionException {

        //Kort bred carport med skur i venstre side
        calculator.beregnCarport(600,480,210,"y","p",
                "venstre",3);
        assertEquals(11, calculator.stolperAntal);
        //Bred og mellemlang carport med halvt skur (maks 5 spær langt skur)
        //Der kan laves beregniong på ekstra langt smalt skur, men vores antagelser forbyder dette.
    }

    @Test
    void testSetTagType() {

        assertEquals("Trapezplader i plast", calculator.setTagType("p"));
        assertEquals("Cembrit tagplader", calculator.setTagType("c"));
    }

    @Test
    void testSetHasSkur() {
        calculator.setHasSkur("Y");
        assertTrue(calculator.hasSkur);
        calculator.setHasSkur("N");
        assertFalse(calculator.hasSkur);
    }

    @Test
    void testSetSkurLængde() throws IllegalDimensionException {
        calculator.beregnCarport(600,300,210,"y","p","midt",3);
        calculator.setSkurLængde(165);
        assertEquals(165, calculator.skur.skurLængde);
    }

    @Test
    void testSetSkurBredde() throws IllegalDimensionException {
        calculator.beregnCarport(780,600,210,"y","p","midt",5);
        calculator.skur.setPlaceringAfSkur("midt");
        assertEquals(530, calculator.setSkurBredde(calculator.carportBredde));

        calculator.skur.setPlaceringAfSkur("venstre");
        assertEquals(265, calculator.setSkurBredde(calculator.carportBredde));

        calculator.skur.setPlaceringAfSkur("højre");
        assertEquals(265, calculator.setSkurBredde(calculator.carportBredde));

        calculator.skur.setPlaceringAfSkur("");
        assertEquals(265, calculator.setSkurBredde(calculator.carportBredde));
    }

    @Test
    void testCheckDimensionsSkur() throws IllegalDimensionException {

        calculator.setDimensionCarport(780,600,210);
        int afstand = calculator.beregnAfstandMellemSpær(calculator.carportLængde, calculator.spærAntal);
        calculator.setHasSkur("Y");
        calculator.skur.setPlaceringAfSkur("venstre");
        int skurLængde = calculator.setSkurLængde(2*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsSkur(skurLængde);
                });

        assertFalse(calculator.hasSkur);

        calculator.skur.setPlaceringAfSkur("højre");
        int skurLængde1 = calculator.setSkurLængde(6*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsSkur(skurLængde1);
                });
        assertFalse(calculator.hasSkur);

        calculator.skur.setPlaceringAfSkur("midt");
        int skurLængde2 = calculator.setSkurLængde(11*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsSkur(skurLængde2);
                });
        assertFalse(calculator.hasSkur);
    }

    @Test
    void testCheckDimensionsSkurSmalCarport() throws IllegalDimensionException {

        calculator.setDimensionCarport(620,300,210);
        int afstand = calculator.beregnAfstandMellemSpær(calculator.carportLængde, calculator.spærAntal);
        calculator.setHasSkur("Y");
        calculator.skur.setPlaceringAfSkur("venstre");
        int skurLængde = calculator.setSkurLængde(4*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsSkur(skurLængde);
                });
        assertFalse(calculator.hasSkur);
    }

    @Test
    void testBeregnSkruerTag() throws IllegalDimensionException {
        calculator.beregnCarport(600,500,210,"y","p",
                "midt",3);
        assertEquals(3, calculator.pakkerPlastTagskruerAntal);
        calculator.beregnCarport(600,500,210,"y","c",
                "midt",3);
        assertEquals(3, calculator.pakkerCembritTagskruerAntal);
    }

    @Test
    void testBeregn() throws IllegalDimensionException, DatabaseException {
        calculator.beregnCarport(600,300,210,"n","p","midt",4);
    }

    @Test
    void beregnCarportPris() throws IllegalDimensionException {
        calculator.beregnCarport(780,600,220,"y","p","midt",5);
        assertEquals(31628,calculator.carportPris,1);
    }
}