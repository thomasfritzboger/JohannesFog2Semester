package dat.startcode.model.services;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    CarportCalculator calculator;
    String user = "root";
    String password = "DiskoT@ngo";
    String url = "jdbc:mysql://localhost:3306/fog";
    ConnectionPool connectionPool = new ConnectionPool(user,password,url);
    List<ProduktDTO> pDTO = ProductFacade.getProduktDTOs(connectionPool);

    CarportCalculatorTest() throws DatabaseException { }

    @BeforeEach
    void setUp() {
         calculator = new CarportCalculator(pDTO);
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

        calculator = new CarportCalculator(pDTO);
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
        assertTrue(calculator.hasSkur == true);
        calculator.setHasSkur("N");
        assertTrue(calculator.hasSkur == false);
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
}