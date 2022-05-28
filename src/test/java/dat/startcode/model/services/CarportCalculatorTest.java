package dat.startcode.model.services;

import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    CarportCalculator calculator;
    List<ProductDTO> produktDTOList = new ArrayList<>();

    CarportCalculatorTest() throws DatabaseException { }

    @BeforeEach
    void setUp() {



       // produktDTOList = new ArrayList<>();
        produktDTOList.add(new ProductDTO(1,"25x200 mm. trykimp. Brædt",54.96,200,25,0,0,"understernbrædder til for & bag ende","stk"));
        produktDTOList.add(new ProductDTO(2,"25x200 mm. trykimp. Brædt",54.96,200,25,0,0,"understernbrædder til siderne","stk"));
        produktDTOList.add(new ProductDTO(3,"25x125 mm. trykimp. Brædt",35.68,125,25,0,0,"oversternbrædder til forenden","stk"));
        produktDTOList.add(new ProductDTO(4,"25x125 mm. trykimp. Brædt",35.68,125,25,0,0,"oversternbrædder til siderne","stk"));
        produktDTOList.add(new ProductDTO(5,"38x73 mm lægte ubh",35.68,73,38,0,0,"til z på bagside af dør","stk"));
        produktDTOList.add(new ProductDTO(6,"45x95 mm. regulr ubh",59.91,95,45,0,0,"løsholter til skur gavle","stk"));
        produktDTOList.add(new ProductDTO(7,"45x95 mm. regulr ubh",59.91,95,45,0,0,"løsholter til skur sider","stk"));
        produktDTOList.add(new ProductDTO(8,"45x195 mm. spærtræ ubh",67.82,195,45,0,0,"remme i sider, sadles ned i stolper","stk"));
        produktDTOList.add(new ProductDTO(9,"45x195 mm. spærtræ ubh",67.82,195,45,0,0,"spær, monteres på rem","stk"));
        produktDTOList.add(new ProductDTO(10,"97x97 mm. trykimp. brædt",54.25,97,97,0,0,"stolper nedgraves 90 cm. i jord","stk"));
        produktDTOList.add(new ProductDTO(11,"19x100 mm. trykimo brædt",21.39,100,19,0,0,"til beklædning af skur 1 på 2","stk"));
        produktDTOList.add(new ProductDTO(12,"19x100 mm. trykimo brædt",21.39,100,19,0,0,"vandbrædt på stern i sider","stk"));
        produktDTOList.add(new ProductDTO(13,"19x100 mm. trykimo brædt",21.39,100,19,0,0,"vandbrædt på stern i forende","stk"));
        produktDTOList.add(new ProductDTO(14,"Plastmo Ecolite blåtonet",37.2,0,0,0,0,"tagplader monteres på spær","stk"));
        produktDTOList.add(new ProductDTO(15,"Plastmo bundskruer 200stk.",282.11,0,0,0,0,"skruer til tagplader","pakke"));
        produktDTOList.add(new ProductDTO(16,"Hulbånd 1x20mm. 10 mtr.",206.43,1,20,0,0,"til vindkryds på spær","rulle"));
        produktDTOList.add(new ProductDTO(17,"Universal 190 mm højre",39.25,190,0,0,0,"til montering af spær på rem","stk"));
        produktDTOList.add(new ProductDTO(18,"Universal 190 mm venstre",39.25,190,0,0,0,"til montering af spær på rem","stk"));
        produktDTOList.add(new ProductDTO(19,"4,5 x 60 mm. skruer 200 stk.",71.39,60,0,0,4.5,"til montering af stern & vandbrædt","pakke"));
        produktDTOList.add(new ProductDTO(20,"4,0 x 50 mm. beslagskruer 250 stk.",55.61,50,0,0,4,"til montering af universalbeslag + hulbånd","pakke"));
        produktDTOList.add(new ProductDTO(21,"Bræddebolt 10 x 120 mm.",10.26,120,10,0,0,"til montering af rem på stolper","stk"));
        produktDTOList.add(new ProductDTO(22,"Firkantskiver 40x40x11mm",6.41,40,40,11,0,"til montering af rem på stolper","stk"));
        produktDTOList.add(new ProductDTO(23,"4,5 x 70 mm. skruer 400 stk.",142.14,70,0,0,4.5,"til montering af yderste beklædning","rulle"));
        produktDTOList.add(new ProductDTO(24,"4,5 x 50 mm. skruer 300 stk.",77.86,50,0,0,4.5,"til montering af inderste beklædning","rulle"));
        produktDTOList.add(new ProductDTO(25,"Stalddærsgreb 50x75",185,75,50,0,0,"til lås på dør i skur","sæt"));
        produktDTOList.add(new ProductDTO(26,"T hængsel 390 mm",80.83,390,0,0,0,"til skurdør","stk"));
        produktDTOList.add(new ProductDTO(27,"Vinkelbeslag 35",8.63,35,0,0,0,"til montering af løsholter i skur","stk"));
        produktDTOList.add(new ProductDTO(28,"Cembrit tagplade 1090x1180",119.95,0,0,0,0,"tagplader monteres på spær","stk"));
        produktDTOList.add(new ProductDTO(29,"'Eternitskrue 6.0x100'",123.04,0,0,0,0,"'skruer til tagplader'","stk"));

        calculator = new CarportCalculator(produktDTOList);

    }

    @Test
    void testSetDimensionCarport() {
        calculator.setDimensionCarport(780,600,210);
        assertEquals(780, calculator.getCarportLength());
        assertEquals(600, calculator.getCarportWidth());
        assertEquals(210, calculator.getCarportHeight());
    }

    @Test
    void testCheckDimensionsCarportForLangUdenSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.calculateCarport(780,600,210,"n","",
                        "",0);
                });
    }

    @Test
    void testCheckDimensionsCarportForKortUdenSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.calculateCarport(400,600,210,"n","",
                        "",0);
                });
    }

    @Test
    void testCheckDimensionsCarportForLangMedSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.calculateCarport(780,600,210,"y","c",
                        "center",3);
                });
    }

    @Test
    void testCheckDimensionsCarportForSmal() {

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.calculateCarport(600,200,210,"y","p",
                        "center",3);
                });
    }

    @Test
    void testCheckDimensionsCarportForHøj() {

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.calculateCarport(600,380,410,"y","p",
                        "center",3);
                });
    }

    @Test
    void testSetAntalSpær() throws IllegalDimensionException {
        calculator.calculateCarport(600,400,220,"y","p","center",3);
        assertEquals(12, calculator.getSpærAntal());
    }

    @Test
    void testBberegnAfstandMellemSpær() throws IllegalDimensionException {
        calculator.calculateCarport(780,500,230,"y","p","center",5);
        assertEquals(55, calculator.getAfstandMellemSpær());
   }

    @Test
    void testSetStolpeLængde() throws IllegalDimensionException {
        calculator.calculateCarport(600,600,210,"y","p",
                "center",3);

        assertEquals(300, calculator.getStolperLængde());
    }

    @Test
    void testBeregnAntalStolperSmalUdenSkur() throws IllegalDimensionException {

        calculator.calculateCarport(500, 300, 210, "n", "p",
                "", 0);
        assertEquals(6, calculator.getStolperAntal());
    }

    @Test
    void testBeregnAntalStolperBredUdenSkur() throws IllegalDimensionException {
        calculator.calculateCarport(500, 400, 210, "n", "p",
                "", 0);
        assertEquals(6, calculator.getStolperAntal());

    }

    @Test
        //Carport under 3m bred
    void testBeregnAntalStolperKortMedSkurMidtSmal() throws IllegalDimensionException {

        calculator.calculateCarport(600, 300, 210, "y", "p",
                "center", 3);
        assertEquals(9, calculator.getStolperAntal());
    }

    @Test
        //Halvlang (310-(310+dørbredde))
    void testBeregnAntalStolperHalvlangMedSkurMidtSmal() throws IllegalDimensionException {

        calculator.calculateCarport(850,300,210,"y","p",
                "center",7);
        assertEquals(10, calculator.getStolperAntal());
    }

    @Test
        //Halvlang (310-(310+dørbredde))
    void testBeregnAntalStolperLangMedSkurMidtSmal() throws IllegalDimensionException {

        //Lang mere end 310+dørbredde
        calculator.calculateCarport(950,300,210,"y","p",
                "center",9);
        assertEquals(11, calculator.getStolperAntal());
    }

    @Test
    void testBeregnAntalStolperMedKortSkurMidtBred() throws IllegalDimensionException {

        //Bred og kort carport med helt skur
        calculator.calculateCarport(600, 480, 210, "y", "p",
                "center", 3);
        assertEquals(11, calculator.getStolperAntal());
    }

    @Test
    void testBeregnAntalStolperMedMellemlangSkurMidtBred() throws IllegalDimensionException {

        //Bred og mellemlang carport med helt skur
        calculator.calculateCarport(850,480,210,"y","p",
                "center",7);
        assertEquals(12, calculator.getStolperAntal());

        calculator = new CarportCalculator(produktDTOList);
        //Bred og lang carport med helt skur
        calculator.calculateCarport(950,480,210,"y","p",
                "center",9);
        assertEquals(13, calculator.getStolperAntal());
    }

    @Test
    void testBeregnAntalStolperMedSkurVenstreHøjre() throws IllegalDimensionException {

        //Kort bred carport med skur i venstre side
        calculator.calculateCarport(600,480,210,"y","p",
                "left",3);
        assertEquals(11, calculator.getStolperAntal());
        //Bred og mellemlang carport med halvt skur (maks 5 spær langt skur)
        //Der kan laves beregniong på ekstra langt smalt skur, men vores antagelser forbyder dette.
    }

    @Test
    void testSetTagType() {

        assertEquals("Trapezplader i plast", calculator.setRoofMaterial("p"));
        assertEquals("Cembrit tagplader", calculator.setRoofMaterial("c"));
    }

    @Test
    void testSetHasSkur() {
        calculator.setHasShed("Y");
        assertTrue(calculator.isHasShed());
        calculator.setHasShed("N");
        assertFalse(calculator.isHasShed());
    }

    @Test
    void testSetSkurLængde() throws IllegalDimensionException {
        calculator.calculateCarport(600,300,210,"y","p","center",3);
        calculator.setShedLength(165);
        assertEquals(165, calculator.shedCalculator.getShedLength());
    }

    @Test
    void testSetShedWidth() throws IllegalDimensionException {
        calculator.calculateCarport(780,600,210,"y","p","center",5);
        calculator.shedCalculator.setShedPlacement("center");
        assertEquals(530, calculator.setShedWidth(calculator.getCarportWidth()));

        calculator.shedCalculator.setShedPlacement("left");
        assertEquals(265, calculator.setShedWidth(calculator.getCarportWidth()));

        calculator.shedCalculator.setShedPlacement("right");
        assertEquals(265, calculator.setShedWidth(calculator.getCarportWidth()));

        calculator.shedCalculator.setShedPlacement("");
        assertEquals(265, calculator.setShedWidth(calculator.getCarportWidth()));
    }

    @Test
    void testCheckDimensionsSkur() throws IllegalDimensionException {

        calculator.setDimensionCarport(780,600,210);
        int afstand = calculator.beregnAfstandMellemSpær(calculator.getCarportLength(), calculator.getSpærAntal());
        calculator.setHasShed("Y");
        calculator.shedCalculator.setShedPlacement("left");
        int skurLængde = calculator.setShedLength(2*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsShed(skurLængde);
                });

        assertFalse(calculator.isHasShed());

        calculator.shedCalculator.setShedPlacement("right");
        int skurLængde1 = calculator.setShedLength(6*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsShed(skurLængde1);
                });
        assertFalse(calculator.isHasShed());

        calculator.shedCalculator.setShedPlacement("center");
        int skurLængde2 = calculator.setShedLength(11*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsShed(skurLængde2);
                });
        assertFalse(calculator.isHasShed());
    }

    @Test
    void testCheckDimensionsSkurSmalCarport() throws IllegalDimensionException {

        calculator.setDimensionCarport(620,300,210);
        int afstand = calculator.beregnAfstandMellemSpær(calculator.getCarportLength(), calculator.getSpærAntal());
        calculator.setHasShed("Y");
        calculator.shedCalculator.setShedPlacement("left");
        int skurLængde = calculator.setShedLength(4*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { calculator.checkDimensionsShed(skurLængde);
                });
        assertFalse(calculator.isHasShed());
    }

    @Test
    void testBeregnSkruerTag() throws IllegalDimensionException {
        calculator.calculateCarport(600,500,210,"y","p",
                "center",3);
        assertEquals(3, calculator.getPakkerPlastTagskruerAntal());
        calculator.calculateCarport(600,500,210,"y","c",
                "center",3);
        assertEquals(3, calculator.getPakkerCembritTagskruerAntal());
    }

    @Test
    void testBeregn() throws IllegalDimensionException, DatabaseException {
        calculator.calculateCarport(600,300,210,"n","p","center",4);
    }

    @Test
    void beregnCarportPris() throws IllegalDimensionException {
        calculator.calculateCarport(780,600,220,"y","p","center",5);
        assertEquals(31561, calculator.getCarportPrice(),1);
    }
}