package dat.startcode.model.services;

import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.exceptions.IllegalDimensionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkurCalculatorTest {

    CarportCalculator calculator;

    List<ProductDTO> produktDTOList = new ArrayList<>();



    @BeforeEach
    void setUp() {

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

    @Test //Udregner antal og længde på løsholter på dørside i kort skur
    void testBeregnAntalLøsholterDørSideKort() throws IllegalDimensionException {
        calculator.calculateCarport(700,600,210,"y","p",
                "center",3);
        assertEquals(1, calculator.shedCalculator.getLøsholterLangsideMedDørAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterLangsideMedDørLængde());
    }

    @Test //Udregner antal og længde på løsholter på dørside i mellemlangt skur
    void testBeregnLængdeLøsholterDørSideMellemLang() throws IllegalDimensionException {
        calculator.calculateCarport(700,600,210,"y","p",
                "center",4);
        assertEquals(1, calculator.shedCalculator.getLøsholterLangsideMedDørAntal());
        assertEquals(360, calculator.shedCalculator.getLøsholterLangsideMedDørLængde());

        calculator = new CarportCalculator(produktDTOList);
        calculator.calculateCarport(840,600,210,"y","p",
                "center",6);
        assertEquals(3, calculator.shedCalculator.getLøsholterLangsideMedDørAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterLangsideMedDørLængde());

        calculator = new CarportCalculator(produktDTOList);
        calculator.calculateCarport(840,600,210,"y","p",
                "center",7);
        assertEquals(3, calculator.shedCalculator.getLøsholterLangsideMedDørAntal());
        assertEquals(300, calculator.shedCalculator.getLøsholterLangsideMedDørLængde());
    }

    @Test //Udregner antal og længde på løsholter på dørside i langt skur
    void testBeregnAntalLøsholterDørSideLang() throws IllegalDimensionException {
        calculator.calculateCarport(1000,600,210,"y","p",
                "center",10);
        assertEquals(6, calculator.shedCalculator.getLøsholterLangsideMedDørAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterLangsideMedDørLængde());
    }

    @Test //Udregner antal og længde på løsholter modsat dørside i kort skur
    void testBeregnAntalLøsholterModsatDørSideKort() throws IllegalDimensionException {
        calculator.calculateCarport(700,600,210,"y","p",
                "center",3);
        assertEquals(3, calculator.shedCalculator.getLøsholterLangsideUdenDørAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterLangsideUdenDørLængde());
    }

    @Test //Udregner antal og længde på løsholter modsat dørside i mellemlangt skur
    void testBeregnLængdeLøsholterModsatDørSideMellemLang() throws IllegalDimensionException {
        calculator.calculateCarport(700,600,210,"y","p",
                "center",4);
        assertEquals(3, calculator.shedCalculator.getLøsholterLangsideUdenDørAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterLangsideUdenDørLængde());


        calculator = new CarportCalculator(produktDTOList);
        calculator.calculateCarport(820,600,210,"y","p",
                "center",5);
        assertEquals(3, calculator.shedCalculator.getLøsholterLangsideUdenDørAntal());
        assertEquals(300, calculator.shedCalculator.getLøsholterLangsideUdenDørLængde());
    }

    @Test //Udregner antal og længde på løsholter på dørside i langt skur
    void testBeregnLængdeLøsholterModsatDørSideLang() throws IllegalDimensionException {
        calculator.calculateCarport(790,600,210,"y","p",
                "center",6);
        assertEquals(6, calculator.shedCalculator.getLøsholterLangsideUdenDørAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterLangsideUdenDørLængde());

        calculator = new CarportCalculator(produktDTOList);
        calculator.calculateCarport(960,600,210,"y","p",
                "center",9);
        assertEquals(6, calculator.shedCalculator.getLøsholterLangsideUdenDørAntal());
        assertEquals(270, calculator.shedCalculator.getLøsholterLangsideUdenDørLængde());
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde < 240 og bredde op til 310)
    void testBeregnAntalLøsholterBredsideSmalt() throws IllegalDimensionException {
        calculator.calculateCarport(700,450,210,"y","p",
                "left",3);
        assertEquals(6, calculator.shedCalculator.getLøsholterBredsideAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterBredsideLængde());

        calculator = new CarportCalculator(produktDTOList);
        calculator.calculateCarport(700,540,210,"y","p",
                "left",3);
        assertEquals(6, calculator.shedCalculator.getLøsholterBredsideAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterBredsideLængde());
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde mere end 310 og mindre end 480)
    void testBeregnAntalLøsholterBredsideMellemBred() throws IllegalDimensionException {
        calculator.calculateCarport(700,450,210,"y","p",
                "center",3);
        assertEquals(12, calculator.shedCalculator.getLøsholterBredsideAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterBredsideLængde());
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde mere end 480)
    void testBeregnAntalLøsholterBredsideBred() throws IllegalDimensionException {
        calculator.calculateCarport(700,500,210,"y","p",
                "center",4);
        assertEquals(12, calculator.shedCalculator.getLøsholterBredsideAntal());
        assertEquals(240, calculator.shedCalculator.getLøsholterBredsideLængde());
    }

    @Test //Udregner længde og antal beklædningsbrædder
    void testBeregenAntalBeklædningsBrædder() throws IllegalDimensionException {
        //Da inder og yderbrædder er ens, regnes det samlede antal ud.
        calculator.calculateCarport(780,600,210,"y","p",
                "center",4);
        assertEquals(187, calculator.shedCalculator.getBeklædningsBrædderAntal());
        assertEquals(210, calculator.shedCalculator.getShedHeight());
    }

    @Test //Udregner hvor mange pakker skruer der skal til inderbeklædningsbrædderne
    void testBeregnAntalKorteBeklædningsSkruer() throws IllegalDimensionException{
        calculator.calculateCarport(780,600,210,"y","p",
                "center",4);
        assertEquals(3, calculator.shedCalculator.getPakkerKorteBeklædningsSkruerAntal());
    }

    @Test //Udregner hvor mange pakker skruer der skal til yderbeklædningsbrædderne
    void testBeregnAntalLangeBeklædningsSkruer() throws IllegalDimensionException{
        calculator.calculateCarport(780,600,210,"y","p",
                "center",4);
        assertEquals(2, calculator.shedCalculator.getPakkerLangeBeklædningsSkruerAntal());
    }

    @Test
    void testAddAllSkurItemsTilListe() throws IllegalDimensionException{
        calculator.calculateCarport(780,600,210,"y","p",
                "center",4);
        assertEquals(9, calculator.shedCalculator.shedMaterialList.size());
    }
}