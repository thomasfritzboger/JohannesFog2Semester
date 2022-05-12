package dat.startcode.model.services;

import dat.startcode.model.exceptions.IllegalDimensionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void testSetDimensionCarport() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(780,600,210);
        assertEquals(780,c.carportLængde);
        assertEquals(600,c.carportBredde);
        assertEquals(210,c.carportHøjde);
    }

    @Test
    void testCheckDimensionsCarportForLangUdenSkur() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(780,600,210);
        c.hasSkur = false;

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsCarport(c.carportLængde,c.carportBredde,c.carportHøjde);
                });
    }

    @Test
    void testCheckDimensionsCarportForKortUdenSkur() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(400,600,210);
        c.hasSkur = false;

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsCarport(c.carportLængde,c.carportBredde,c.carportHøjde);
                });
    }

    @Test
    void testCheckDimensionsCarportForLangMedSkur() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(780,600,210);
        c.hasSkur = true;
        c.setSkurLængde(165);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsCarport(c.carportLængde,c.carportBredde,c.carportHøjde);
                });
    }

    @Test
    void testCheckDimensionsCarportForSmal() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(600,280,210);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsCarport(c.carportLængde,c.carportBredde,c.carportHøjde);
                });
    }

    @Test
    void testCheckDimensionsCarportForHøj() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(600,380,410);

        assertThrows(IllegalDimensionException.class,
                () ->  { c.checkDimensionsCarport(c.carportLængde,c.carportBredde,c.carportHøjde);
                });
    }

    @Test
    void testSetAntalSpær() {
        CarportCalculator c = new CarportCalculator();
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
        CarportCalculator c = new CarportCalculator();
        c.carportLængde = 600;
        assertEquals(54,c.beregnAfstandMellemSpær(c.carportLængde,c.setAntalSpær(c.carportLængde)));
        c.carportLængde = 780;
        assertEquals(55,c.beregnAfstandMellemSpær(c.carportLængde,c.setAntalSpær(c.carportLængde)));
    }

    @Test
    void testSetStolpeLængde() {
        CarportCalculator c = new CarportCalculator();
        c.setDimensionCarport(780,600,210);
        assertEquals(300,c.setStolpeLængde(c.carportHøjde));
    }

    @Test
    void testBeregnAntalStolperUdenSkur() {
        CarportCalculator c = new CarportCalculator();

        c.hasSkur = false;
        c.setDimensionCarport(500,300,210);
        assertEquals(6,c.beregnAntalStolper(c.carportLængde));
        assertEquals(1,c.mList.size());
        c.setDimensionCarport(500,400,210);
        assertEquals(7,c.beregnAntalStolper(c.carportLængde));
        assertEquals(2,c.mList.size());
    }

    @Test
    //Carport under 3m bred
    void testBeregnAntalStolperMedSkurMidtSmal() throws IllegalDimensionException {
        CarportCalculator c = new CarportCalculator();

        c.beregnCarport(600,300,210,"y","plast","midt",3);
        assertEquals(9,c.beregnAntalStolper(c.carportLængde));

        c = null;
        c = new CarportCalculator();


        //Halvlang (310-(310+dørbredde))
        c.beregnCarport(850,300,210,"y","plast","midt",7);


        c = null;
        c = new CarportCalculator();

        //Lang mere end 310+dørbredde
        c.beregnCarport(950,300,210,"y","plast","midt",9);

        assertEquals(11,c.beregnAntalStolper(c.carportLængde));

    }

    @Test
    void testBeregnAntalStolperMedSkurMidtBred() throws IllegalDimensionException {
        CarportCalculator c = new CarportCalculator();

        c.beregnCarport(600,480,210,"y","plast","midt",3);
        assertEquals(11,c.beregnAntalStolper(c.carportLængde));





    }

    @Test
    void testBeregnAntalStolperMedSkurVenstreHøjre() {
        CarportCalculator c = new CarportCalculator();


        //Smal carport (under 3m bred)
        c.skur.setPlaceringAfSkur("venstre");
        c.setDimensionCarport(0,300,210);
        c.hasSkur = true;
        c.setSkurLængde(165);
        c.setSkurBredde(600);


        assertEquals(2,c.beregnAntalStolper(c.carportLængde));
        //assertEquals(1,c.mList.size());
        //Bred carport (mere end 3m bred)
        c.setDimensionCarport(600,400,210);
        //assertEquals(11,c.beregnAntalStolper(c.carportLængde));
        //assertEquals(2,c.mList.size());


        c.skur.setPlaceringAfSkur("venstre");
        c.setDimensionCarport(600,400,210);
        //assertEquals(11,c.beregnAntalStolper(c.carportLængde));

    }

    @Test
    void testSetTagType() {
        CarportCalculator c = new CarportCalculator();
        //c.setTagType("p");
        assertEquals("Trapezplader i plast",c.setTagType("p"));
        assertEquals("Cembrit tagplader",c.setTagType("c"));
    }

    @Test
    void testSetHasSkur() {
        CarportCalculator c = new CarportCalculator();
        c.setHasSkur("Y");
        assertTrue(c.hasSkur == true);
        c.setHasSkur("N");
        assertTrue(c.hasSkur == false);
    }


    @Test
    void testSetSkurLængde() {
        CarportCalculator c = new CarportCalculator();
        c.setSkurLængde(165);
        assertEquals(165,c.skur.skurLængde);
    }

    @Test
    void testSetSkurBredde() {
        CarportCalculator c = new CarportCalculator();
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

        CarportCalculator c = new CarportCalculator();
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

        CarportCalculator c = new CarportCalculator();
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