package dat.startcode.model.services;

import dat.startcode.model.dtos.OrderLineDTO;

import java.util.ArrayList;
import java.util.List;

public class SkurCalculator {
    //Liste med marerialer der kun angår skuret
    public List<OrderLineDTO> skurList;

    //Klassevariabler der bruges til beregninger
    public int skurBredde;
    public int afstandMellemSpær;
    //Skal være delelig med afstand mellem spær
    public int skurLængde;
    public int skurHøjde;

    public String placeringAfSkur;
    //Sættes når bredden sættes i carport klassen
    public int carportBredde;
    //Skal passe med en stolpe
    public int breddeAfDør = 2 * afstandMellemSpær;
    public int længdeLangsideMinusDør;

    //Klassevariabler der er ekstra og hentes via carport
    public int antalStolperPåRemEkstraNårSkur;
    public int antalStolperPåSpærEkstraNårSkur;
    public int antalEkstraBolteNårSkur;
    public int antalEkstraFirkantSkiverNårSkur;
    public int antalVinkelbeslagEkstraNårSkur;

    //Klassevariabler der kun angår skuret og skal tilføjes på liste.
    public String lægteTilDørProductDescription;
    public int lægtePåDørLængde;
    public int lægteTilDørAntal;
    public String lægtePåDørUnitScale;
    public String lægteTilDørUsementDescription;
    public double lægtePåDørUnitPrice;

    public String stalddørsgrebProductDescription;
    public int stalddørsgrebLængde;
    public int stalddørsgrebAntal;
    public String stalddørsgrebUnitScale;
    public String stalddørsgrebUsementDescription;
    public double stalddørsgrebUnitPrice;

    public String tHængselProductDescription;
    public int tHængselLængde;
    public int tHængselAntal;
    public String tHængselUnitScale;
    public String tHængselUsementDescription;
    public double tHængselUnitPrice;

    // Der beregnes 3 udover spær/rem til hver side. Den side med dør kan måske klares med en, da den skæres til i 3 stykker
    public String løsholterLangsideUdenDørDescription;
    public int løsholterLangsideUdenDørLængde;
    public int løsholterLangsideUdenDørAntal;
    public String løsholterLangsideUdenDørUnitScale;
    public String løsholterLangsideUdenDørUsementDescription;
    public double løsholterLangsideUdenDørUnitPrice;

    public String løsholterLangsideMedDørDescription;
    public int løsholterLangsideMedDørLængde;
    public int løsholterLangsideMedDørAntal;
    public String løsholterLangsideMedDørUnitScale;
    public String løsholterLangsideMedDørUsementDescription;
    public double løsholterLangsideMedDørUnitPrice;

    public String løsholterBredsideDescription;
    public int løsholterBredsideLængde;
    public int løsholterBredsideAntal;
    public String løsholterBredsideUnitScale;
    public String løsholterBredsideUsementDescription;
    public double løsholterBredsideUnitPrice;

    public String beklædningsBrædderDescription;
    public int beklædningsBrædderLængde;
    public int beklædningsBrædderAntal;
    public String beklædningsBrædderUnitScale;
    public String beklædningsBrædderUsementDescription;
    public double beklædningsBrædderUnitPrice;

    public String pakkerKorteBeklædningsSkruerDescription;
    public int pakkerKorteBeklædningsSkruerLængde;
    public int pakkerKorteBeklædningsSkruerAntal;
    public String pakkerKorteBeklædningsSkruerUnitScale;
    public String pakkerKorteBeklædningsSkruerUsementDescription;
    public double pakkerKorteBeklædningsSkruerUnitPrice;

    public String pakkerLangeBeklædningsSkruerDescription;
    public int pakkerLangeBeklædningsSkruerLængde;
    public int pakkerLangeBeklædningsSkruerAntal;
    public String pakkerLangeBeklædningsSkruerUnitScale;
    public String pakkerLangeBeklædningsSkruerUsementDescription;
    public double pakkerLangeBeklædningsSkruerUnitPrice;

    public SkurCalculator() {
        tHængselAntal = 2;
        stalddørsgrebAntal = 1;
        lægteTilDørAntal = 1;
        lægtePåDørLængde = 420;
        skurList = new ArrayList<>();
    }

    public void setPlaceringAfSkur (String placering) {

        placeringAfSkur = placering;
        if (placering.equals("midt")) {
            skurBredde = carportBredde;

        } else if (placering.equals("venstre")) {
            skurBredde = carportBredde/2;

        } else if (placering.equals("højre")) {
            skurBredde = carportBredde/2;

        } else {
            System.out.println("Du har fravalgt skur");
        }

        beregnEkstraStolper(skurLængde);
    }

    private void beregnEkstraStolper(int l){

        switch (placeringAfSkur){

            case "midt":
                if (skurBredde > 310) {
                    antalStolperPåSpærEkstraNårSkur += 1;
                }

                if (l<=310) {

                    antalStolperPåRemEkstraNårSkur += 3;
                    antalEkstraBolteNårSkur += 3;
                    antalEkstraFirkantSkiverNårSkur += 3;

                } else if (l > 310 && l < 310+breddeAfDør ) {



                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;

                } else {


                    antalStolperPåRemEkstraNårSkur += 5;
                    antalEkstraBolteNårSkur += 5;
                    antalEkstraFirkantSkiverNårSkur += 5;

                }
                break;

            case "venstre":



                if (l<=310) {

                    antalStolperPåSpærEkstraNårSkur += 2;
                    antalStolperPåRemEkstraNårSkur += 2;
                    antalEkstraBolteNårSkur += 2;
                    antalEkstraFirkantSkiverNårSkur += 2;




                } else if (l > 310 && l < 310+breddeAfDør ) {

                    antalStolperPåSpærEkstraNårSkur += 2;
                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;

                }
                break;

            case "højre":
                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur += 2;
                    antalStolperPåRemEkstraNårSkur += 2;
                    antalEkstraBolteNårSkur += 2;
                    antalEkstraFirkantSkiverNårSkur += 2;



                } else if (l > 310 && l <  310+breddeAfDør ) {

                    antalStolperPåSpærEkstraNårSkur += 2;
                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;

                }
                break;

            default: {
                antalStolperPåSpærEkstraNårSkur = 0;
                antalStolperPåRemEkstraNårSkur = 0;
                antalEkstraBolteNårSkur =0;
                antalEkstraFirkantSkiverNårSkur = 0;
                tHængselAntal = 0;
                stalddørsgrebAntal = 0;
                antalVinkelbeslagEkstraNårSkur = 0;
                lægteTilDørAntal = 0;
                lægtePåDørLængde = 0;
            }
        }
    }

    public void beregnAntalLøsholter (int l, int b) {

        //Løsholter skurside med dør
        if (3*længdeLangsideMinusDør <=240) {
            løsholterLangsideMedDørLængde = 240;
            løsholterLangsideMedDørAntal += 1;
            antalVinkelbeslagEkstraNårSkur += 6;    //HER VINKELBESLAG//

        } else if (3*længdeLangsideMinusDør <= 480) {
            løsholterLangsideMedDørLængde = (int) Math.ceil((3*længdeLangsideMinusDør)/30.0)*30;
            løsholterLangsideMedDørAntal += 1;
            antalVinkelbeslagEkstraNårSkur += 6;

        } else {
            if (længdeLangsideMinusDør<=240) {
                løsholterLangsideMedDørLængde = 240;
                løsholterLangsideMedDørAntal += 3;
                antalVinkelbeslagEkstraNårSkur += 6;

            } else if (længdeLangsideMinusDør <= 310) {
                løsholterLangsideMedDørLængde = (int) Math.ceil((længdeLangsideMinusDør)/30.0)*30;
                løsholterLangsideMedDørAntal += 3;
                antalVinkelbeslagEkstraNårSkur += 6;

            } else {
                løsholterLangsideMedDørLængde = 240;
                løsholterLangsideMedDørAntal += 6;
                antalVinkelbeslagEkstraNårSkur += 6;
            }
        }
        //Løsholter skurside overfor dør
        if (l <= 240) {
            løsholterLangsideUdenDørLængde = 240;
            løsholterLangsideUdenDørAntal += 3;
            antalVinkelbeslagEkstraNårSkur += 6;
        }
        else if (l > 240 && l <= 310 ) {
            l = (int) Math.ceil((l)/30.0)*30;
            løsholterLangsideUdenDørLængde = l;
            løsholterLangsideUdenDørAntal += 3;
            antalVinkelbeslagEkstraNårSkur += 6;

        } else {
            if (skurLængde/2 <= 240 ) {
                løsholterLangsideUdenDørLængde = 240;
                løsholterLangsideUdenDørAntal += 6;
                antalVinkelbeslagEkstraNårSkur += 12;

            } else {
                løsholterLangsideUdenDørLængde = (int) Math.ceil((l/2)/30.0)*30;
                løsholterLangsideUdenDørAntal += 6;
                antalVinkelbeslagEkstraNårSkur += 12;
            }
        }

        //Løsholter på skurets bredsider
        if (skurBredde <= 240) {
            løsholterBredsideLængde = 240;
            løsholterBredsideAntal += 6;
            antalVinkelbeslagEkstraNårSkur += 12;
        }

        if (skurBredde > 240 && skurBredde <= 310) {
            løsholterBredsideLængde = (int) Math.ceil((b)/30.0)*30;
            løsholterBredsideAntal += 6;
            antalVinkelbeslagEkstraNårSkur += 12;
        }
        if (skurBredde > 310) {
            if (skurBredde/2 <= 240) {
                løsholterBredsideLængde = 240;
                løsholterBredsideAntal += 12;
                antalVinkelbeslagEkstraNårSkur += 24;
            } else {
                løsholterBredsideLængde = (int) Math.ceil((b/2)/30.0)*30;
                løsholterBredsideAntal += 12;
                antalVinkelbeslagEkstraNårSkur += 24;
            }
        }
    }

    //Antagelse, der skal sidde et hvert 16 cm
    public void beregenAntalBeklædningsBrædder () {
        beklædningsBrædderLængde = skurHøjde;
        //Gange 2 fordi der er inde og ude brædder
        //Gange 2 fordi der er 2 længdesider og 2 breddesider
        //Dividere med 16, for så meget dækker et brædt inklusiv "hul" i mellem
        beklædningsBrædderAntal = 2*(2*(skurLængde+skurBredde))/16;
    }

    //Antagelse: der skal bruges 8 skruer pr inderbrædt, og disse er jo halvdelen af alle brædder
    //Kommer i pakker af 300
    public void beregnAntalKorteBeklædningsSkruer () {
        pakkerKorteBeklædningsSkruerAntal = (int) Math.ceil(((beklædningsBrædderAntal /2)*8.0)/300);
    }

    //Antagelse: der skal bruges 8 skruer pr yderbrædt, og disse er jo halvdelen af alle brædder
    //Kommer i pakker af 400
    public void beregnAntalLangeBeklædningsSkruer () {
        pakkerLangeBeklædningsSkruerAntal = (int) Math.ceil(((beklædningsBrædderAntal /2)*8.0)/400);
    }

    public void addAllSkurItemsTilListe() {


        //productdescription, length, amount, unitscale, usementdescription, totalprice
        skurList.add(new OrderLineDTO(lægteTilDørProductDescription,
                lægtePåDørLængde, lægteTilDørAntal, lægtePåDørUnitScale,
                lægteTilDørUsementDescription,lægtePåDørUnitPrice * lægteTilDørAntal));
        skurList.add(new OrderLineDTO(stalddørsgrebProductDescription,
                stalddørsgrebLængde, stalddørsgrebAntal, stalddørsgrebUnitScale,
                stalddørsgrebUsementDescription, stalddørsgrebUnitPrice * stalddørsgrebAntal));
        skurList.add(new OrderLineDTO(tHængselProductDescription,
                tHængselLængde, tHængselAntal, tHængselUnitScale,
                tHængselUsementDescription, tHængselUnitPrice * tHængselAntal));
        skurList.add(new OrderLineDTO(løsholterLangsideUdenDørDescription,
                løsholterLangsideUdenDørLængde, løsholterLangsideUdenDørAntal, løsholterLangsideUdenDørUnitScale,
                løsholterLangsideUdenDørUsementDescription, løsholterLangsideUdenDørUnitPrice * løsholterLangsideUdenDørAntal));
        skurList.add(new OrderLineDTO(løsholterLangsideMedDørDescription,
                løsholterLangsideMedDørLængde, løsholterLangsideMedDørAntal, løsholterLangsideMedDørUnitScale,
                løsholterLangsideMedDørUsementDescription, løsholterLangsideMedDørUnitPrice * løsholterLangsideMedDørAntal));
        skurList.add(new OrderLineDTO(løsholterBredsideDescription,
                løsholterBredsideLængde, løsholterBredsideAntal, løsholterBredsideUnitScale,
                løsholterBredsideUsementDescription,  løsholterBredsideUnitPrice * løsholterBredsideAntal));
        skurList.add(new OrderLineDTO(beklædningsBrædderDescription,
                beklædningsBrædderLængde, beklædningsBrædderAntal, beklædningsBrædderUnitScale,
                beklædningsBrædderUsementDescription, beklædningsBrædderUnitPrice * beklædningsBrædderAntal));
        skurList.add(new OrderLineDTO(pakkerKorteBeklædningsSkruerDescription,
                pakkerKorteBeklædningsSkruerLængde, pakkerKorteBeklædningsSkruerAntal, pakkerKorteBeklædningsSkruerUnitScale,
                pakkerKorteBeklædningsSkruerUsementDescription, pakkerKorteBeklædningsSkruerUnitPrice* pakkerKorteBeklædningsSkruerAntal));
        skurList.add(new OrderLineDTO(pakkerLangeBeklædningsSkruerDescription,
                pakkerLangeBeklædningsSkruerLængde, pakkerLangeBeklædningsSkruerAntal, pakkerLangeBeklædningsSkruerUnitScale,
                pakkerLangeBeklædningsSkruerUsementDescription, pakkerLangeBeklædningsSkruerUnitPrice* pakkerLangeBeklædningsSkruerAntal));
    }
}