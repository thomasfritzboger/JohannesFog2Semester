package dat.startcode.model.services;

import dat.startcode.model.exceptions.IllegalDimensionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    static CarportCalculator c;

    @BeforeAll
    static void createTestCarportCalculator() {
        c = new CarportCalculator();
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    public void after() {
        System.out.println("After");
    }

    @Test
    void testSetDimensionCarport() {
        c.setDimensionCarport(780,600,210);
        assertEquals(780,c.carportLængde);
        assertEquals(600,c.carportBredde);
        assertEquals(210,c.carportHøjde);
    }

    @Test
    void testCheckDimensionsCarportForLangUdenSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { c.beregnCarport(780,600,210,"n","",
                        "",0);
                });
    }

    @Test
    void testCheckDimensionsCarportForKortUdenSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { c.beregnCarport(400,600,210,"n","",
                        "",0);
                });
    }

    @Test
    void testCheckDimensionsCarportForLangMedSkur() {

        //Kalder en metode, der kaster den rigtige exception
        assertThrows(IllegalDimensionException.class,
                () ->  { c.beregnCarport(780,600,210,"y","c",
                        "midt",3);
                });
    }

    @Test
    void testCheckDimensionsCarportForSmal() {

        assertThrows(IllegalDimensionException.class,
                () ->  { c.beregnCarport(600,200,210,"y","p",
                        "midt",3);
                });
    }

    @Test
    void testCheckDimensionsCarportForHøj() {

        assertThrows(IllegalDimensionException.class,
                () ->  { c.beregnCarport(600,380,410,"y","p",
                        "midt",3);
                });
    }

    @Test
    void testSetAntalSpær() {
        c.carportLængde = 420;
        assertEquals(9, c.setAntalSpær(c.carportLængde));
        c.carportLængde = 480;
        assertEquals(10,c.setAntalSpær(c.carportLængde));
        c.carportLængde = 600;
        assertEquals(12,c.setAntalSpær(c.carportLængde));
        c.carportLængde = 780;
        assertEquals(15, c.setAntalSpær(c.carportLængde));
        c.carportLængde = 900;
        assertEquals(17,c.setAntalSpær(c.carportLængde));
    }

    @Test
    void testBberegnAfstandMellemSpær() {
        c.carportLængde = 600;
        assertEquals(54,c.beregnAfstandMellemSpær(c.carportLængde,c.setAntalSpær(c.carportLængde)));
        c.carportLængde = 780;
        assertEquals(55,c.beregnAfstandMellemSpær(c.carportLængde,c.setAntalSpær(c.carportLængde)));
    }

    @Test
    void testSetStolpeLængde() throws IllegalDimensionException {
        c.beregnCarport(600,600,210,"y","p",
                "midt",3);

        assertEquals(300,c.setStolpeLængde(c.carportHøjde));
    }

    @Test
    void testBeregnAntalStolperSmalUdenSkur() throws IllegalDimensionException {

        c.beregnCarport(500, 300, 210, "n", "",
                "", 0);
        assertEquals(6, c.beregnAntalStolper(c.carportLængde));
    }

    @Test
    void testBeregnAntalStolperBredUdenSkur() throws IllegalDimensionException {
        c.beregnCarport(500, 400, 210, "n", "",
                "", 0);
        assertEquals(7,c.beregnAntalStolper(c.carportLængde));

    }

    @Test
        //Carport under 3m bred
    void testBeregnAntalStolperKortMedSkurMidtSmal() throws IllegalDimensionException {


        CarportCalculator c = new CarportCalculator();

        c.beregnCarport(600, 300, 210, "y", "p",
                "midt", 3);
        assertEquals(9, c.beregnAntalStolper(c.carportLængde));
    }

    @Test
        //Halvlang (310-(310+dørbredde))
    void testBeregnAntalStolperHalvlangMedSkurMidtSmal() throws IllegalDimensionException {

        CarportCalculator c = new CarportCalculator();

        c.beregnCarport(850,300,210,"y","p",
                "midt",7);
        assertEquals(10,c.beregnAntalStolper(c.carportLængde));
    }

    @Test
        //Halvlang (310-(310+dørbredde))
    void testBeregnAntalStolperLangMedSkurMidtSmal() throws IllegalDimensionException {

        //Lang mere end 310+dørbredde
        c.beregnCarport(950,300,210,"y","p",
                "midt",9);
        assertEquals(11,c.beregnAntalStolper(c.carportLængde));
    }






    @Test
    void testBeregnAntalStolperMedKortSkurMidtBred() throws IllegalDimensionException {

        CarportCalculator c = new CarportCalculator();

        //Bred og kort carport med helt skur
        c.beregnCarport(600, 480, 210, "y", "p",
                "midt", 3);
        assertEquals(11, c.beregnAntalStolper(c.carportLængde));

    }

    @Test
    void testBeregnAntalStolperMedMellemlangSkurMidtBred() throws IllegalDimensionException {
        CarportCalculator c = new CarportCalculator();

        //Bred og mellemlang carport med helt skur
        c.beregnCarport(850,480,210,"y","p",
                "midt",7);
        assertEquals(12,c.beregnAntalStolper(c.carportLængde));

        c = null;
        c = new CarportCalculator();

        //Bred og lang carport med helt skur
        c.beregnCarport(950,480,210,"y","p",
                "midt",9);
        assertEquals(13,c.beregnAntalStolper(c.carportLængde));
    }

    @Test
    void testBeregnAntalStolperMedSkurVenstreHøjre() throws IllegalDimensionException {
        CarportCalculator c = new CarportCalculator();

        //Kort bred carport med skur i venstre side
        c.beregnCarport(600,480,210,"y","p",
                "venstre",3);
        assertEquals(11,c.beregnAntalStolper(c.carportLængde));
        //Bred og mellemlang carport med halvt skur (maks 5 spær langt skur)
        //Der kan laves beregniong på ekstra langt smalt skur, men vores antagelser forbyder dette.


    }

    @Test
    void testSetTagType() {

        assertEquals("Trapezplader i plast",c.setTagType("p"));
        assertEquals("Cembrit tagplader",c.setTagType("c"));
    }

    @Test
    void testSetHasSkur() {
        c.setHasSkur("Y");
        assertTrue(c.hasSkur == true);
        c.setHasSkur("N");
        assertTrue(c.hasSkur == false);
    }


    @Test
    void testSetSkurLængde() {
        c.setSkurLængde(165);
        assertEquals(165,c.skur.skurLængde);
    }

    @Test
    void testSetSkurBredde() {
        c.skur.setPlaceringAfSkur("midt");
        c.setSkurLængde(45);
        c.setDimensionCarport(780,600,210);
        assertEquals(600,c.setSkurBredde(c.carportBredde));
        c.skur.setPlaceringAfSkur("venstre");
        c.setDimensionCarport(780,600,210);
        assertEquals(300,c.setSkurBredde(c.carportBredde));
        c.skur.setPlaceringAfSkur("højre");
        c.setDimensionCarport(780,600,210);
        assertEquals(300,c.setSkurBredde(c.carportBredde));
        c.skur.setPlaceringAfSkur("");
        c.setDimensionCarport(780,600,210);
        assertEquals(300,c.setSkurBredde(c.carportBredde));
    }

    @Test
    void testCheckDimensionsSkur() throws IllegalDimensionException {

        c.setDimensionCarport(780,600,210);
        int afstand = c.beregnAfstandMellemSpær(c.carportLængde,c.setAntalSpær(c.carportLængde));
        c.setHasSkur("Y");
        c.skur.setPlaceringAfSkur("venstre");
        int skurLængde = c.setSkurLængde(2*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsSkur(skurLængde);
                });

        assertTrue(c.hasSkur == false);

        c.skur.setPlaceringAfSkur("højre");
        int skurLængde1 = c.setSkurLængde(6*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsSkur(skurLængde1);
                });

        assertTrue(c.hasSkur == false);

        c.skur.setPlaceringAfSkur("midt");
        int skurLængde2 = c.setSkurLængde(11*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsSkur(skurLængde2);
                });

        assertTrue(c.hasSkur == false);


        c.skur.setPlaceringAfSkur("midt");
        int skurLængde3 = c.setSkurLængde(8*afstand);
        c.checkDimensionsSkur(skurLængde3);
        assertTrue(c.hasSkur == true);
    }


    @Test
    void testCheckDimensionsSkurSmalCarport() throws IllegalDimensionException {

        c.setDimensionCarport(620,300,210);
        int afstand = c.beregnAfstandMellemSpær(c.carportLængde,c.setAntalSpær(c.carportLængde));
        c.setHasSkur("Y");
        c.skur.setPlaceringAfSkur("venstre");
        int skurLængde = c.setSkurLængde(4*afstand);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsSkur(skurLængde);
                });

        assertTrue(c.hasSkur == false);


    }

}