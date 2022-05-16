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

public class SkurCalculator {
    public int størrelseIForholdTilCarportBredde;
    public String placeringAfSkur;

    //Sættes når bredden sættes i carport klassen
    public int carportBredde;

    //Skal være delelig med afstand mellem spær
    public int skurLængde;
    public int skurBredde;
    public int skurHøjde;

    public int afstandMellemSpær;

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


    public int antalLægteTilDør;
    public int længdeAfLægtePåDør;
    public int antalStalddørsgreb;
    public int antalTHængsel;

    //Ikke beregnet fuldt, skal gøres ift løsholter
    public int antalVinkelbeslagEkstraNårSkur;

    // Der beregnes 3 udover spær/rem til hver side. Den side med dør kan måske klares med en, da den skæres til i 3 stykker
    public int antalEkstraLøsholterNårSkurLangsideUdenDør;
    public int længdeAfLøsholterSkurLangsideUdenDør;
    public int antalEkstraLøsholterNårSkurLangsideMedDør;
    public int længdeAfLøsholterSkurLangsideMedDør;
    public int antalEkstraLøsholterNårSkurBredside;
    public int længdeAfLøsholterSkurBredside;

    public int antalBeklædningsBrædder;
    public int længdeAfBeklædningsBrædder;
    public int antalPakkerKorteBeklædningsSkruer;
    public int antalPakkerLangeBeklædningsSkruer;



    public SkurCalculator() {

        antalTHængsel = 2;
        antalStalddørsgreb = 1;
        antalLægteTilDør = 1;
        længdeAfLægtePåDør = 420;

    }


    public void setPlaceringAfSkur (String placering) {



        placeringAfSkur = placering;
        System.out.println("Skurlængde inden bregning: "+skurLængde);
        System.out.println("Skurlængde inden bregning: "+skurBredde);





        if (placering.equals("midt")) {
            System.out.println("Du har valgt skur i carportbredde");
            skurBredde = carportBredde;


        } else if (placering.equals("venstre")) {
            System.out.println("Du har placeret skuret i venstre side");
            skurBredde = carportBredde/2;

        } else if (placering.equals("højre")) {
            System.out.println("Du har placeret skuret i højre side");

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

        System.out.println("SKUURBREEDDDDE "+skurBredde);
        switch (placeringAfSkur){



            case "midt":
                if (skurBredde > 310) {
                    antalStolperPåSpærEkstraNårSkur += 1;

                    System.out.println("Antal stolper ekstra på spær"+ antalStolperPåSpærEkstraNårSkur);

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

                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur += 2;
                    antalStolperPåRemEkstraNårSkur += 1;
                    antalEkstraBolteNårSkur += 1;
                    antalEkstraFirkantSkiverNårSkur += 1;





                } else if (l > 310 && l < 310+breddeAfDør ) {

                    antalStolperPåSpærEkstraNårSkur += 2;

                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;

                    antalStolperPåRemEkstraNårSkur += 1;
                    antalEkstraBolteNårSkur += 1;
                    antalEkstraFirkantSkiverNårSkur += 1;


                }
                break;

            case "højre":
                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur += 2;

                    antalStolperPåRemEkstraNårSkur += 2;
                    antalEkstraBolteNårSkur += 2;
                    antalEkstraFirkantSkiverNårSkur += 2;

                    antalStolperPåRemEkstraNårSkur += 1;
                    antalEkstraBolteNårSkur += 1;
                    antalEkstraFirkantSkiverNårSkur += 1;





                } else if (l > 310 && l <  310+breddeAfDør ) {

                    antalStolperPåSpærEkstraNårSkur += 2;

                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;

                    antalStolperPåRemEkstraNårSkur += 1;
                    antalEkstraBolteNårSkur += 1;
                    antalEkstraFirkantSkiverNårSkur += 1;


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

                antalTHængsel = 0;
                antalStalddørsgreb = 0;
                antalVinkelbeslagEkstraNårSkur = 0;
                antalLægteTilDør = 0;
                længdeAfLægtePåDør = 0;

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


        //Løsholter skurside med dør
        if (3*længdeLangsideMinusDør <=240) {
            længdeAfLøsholterSkurLangsideMedDør = 240;
            antalEkstraLøsholterNårSkurLangsideMedDør += 1;
            antalVinkelbeslagEkstraNårSkur += 6;    //HER VINKELBESLAG//

            //System.out.println("Længde løsholt til skurside med dør er: "+ længdeLangsideMinusDør + "  " + længdeAfLøsholterSkurLangsideMedDør);

        } else if (3*længdeLangsideMinusDør <= 480) {
            længdeAfLøsholterSkurLangsideMedDør = (int) Math.ceil((3*længdeLangsideMinusDør)/30.0)*30;
            //System.out.println("Længde løsholt til skurside med dør er: "+ længdeLangsideMinusDør + "  " + længdeAfLøsholterSkurLangsideMedDør);
            antalEkstraLøsholterNårSkurLangsideMedDør += 1;

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

                længdeAfLøsholterSkurLangsideMedDør = 240;
                antalEkstraLøsholterNårSkurLangsideMedDør += 3;
                antalVinkelbeslagEkstraNårSkur += 6;

            } else if (længdeLangsideMinusDør <= 310) {
                længdeAfLøsholterSkurLangsideMedDør = (int) Math.ceil((længdeLangsideMinusDør)/30.0)*30;
                antalEkstraLøsholterNårSkurLangsideMedDør += 3;
                antalVinkelbeslagEkstraNårSkur += 6;
            } else  {
                //Vil altid skulle være
                længdeAfLøsholterSkurLangsideMedDør = 240;
                antalEkstraLøsholterNårSkurLangsideMedDør += 6;
                antalVinkelbeslagEkstraNårSkur += 6;
            }

            //System.out.println("Længde løsholt til skurside med dør er: "+ længdeLangsideMinusDør + "  " + længdeAfLøsholterSkurLangsideMedDør);

        }

        //Løsholter skurside overfor dør
        if (l <= 240) {
            længdeAfLøsholterSkurLangsideUdenDør = 240;
            antalEkstraLøsholterNårSkurLangsideUdenDør += 3;
            antalVinkelbeslagEkstraNårSkur += 6;



        }
        else if (l > 240 && l <= 310 ) {
            længdeAfLøsholterSkurLangsideUdenDør = l;
            antalEkstraLøsholterNårSkurLangsideUdenDør += 3;
            antalVinkelbeslagEkstraNårSkur += 6;
        } else {
            if (skurLængde/2 <= 240 ) {
                længdeAfLøsholterSkurLangsideUdenDør = 240;
                antalEkstraLøsholterNårSkurLangsideUdenDør += 6;
                antalVinkelbeslagEkstraNårSkur += 12;

            } else {
                længdeAfLøsholterSkurLangsideUdenDør = (int) Math.ceil((l/2)/30.0)*30;
                antalEkstraLøsholterNårSkurLangsideUdenDør += 6;

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

        System.out.println();
        //System.out.println("Længde løsholt til skurside overfor dør er: "+ skurLængde + "  " + længdeAfLøsholterSkurLangsideUdenDør);

        //Løsholter på skurets bredsider
        if (skurBredde <= 240) {
            længdeAfLøsholterSkurBredside = 240;
            antalEkstraLøsholterNårSkurBredside += 6;
            antalVinkelbeslagEkstraNårSkur += 12;

        }
        if (skurBredde > 240 && skurBredde <= 310) {
            længdeAfLøsholterSkurBredside = (int) Math.ceil((b)/30.0)*30;
            antalEkstraLøsholterNårSkurBredside += 6;

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
          
        længdeAfLøsholterSkurBredside = 240;
                antalEkstraLøsholterNårSkurBredside += 12;
                antalVinkelbeslagEkstraNårSkur += 24;
            } else {
                længdeAfLøsholterSkurBredside = (int) Math.ceil((b/2)/30.0)*30;
                antalEkstraLøsholterNårSkurBredside += 12;
                antalVinkelbeslagEkstraNårSkur += 24;
            }
        }


        // System.out.println("\n\nAntalr efterr beregning vinkel: "+antalVinkelbeslagEkstraNårSkur);
        //System.out.println();
        //System.out.println("Lægnde løsholter på skurets bredside er: " +længdeAfLøsholterSkurBredside + " skurbredde "+skurBredde);

        //System.out.println(antalVinkelbeslagEkstraNårSkur);

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

        længdeAfBeklædningsBrædder = skurHøjde;
        //Gange 2 fordi der er inde og ude brædder
        //Gange 2 fordi der er 2 længdesider og 2 breddesider
        //Dividere med 16, for så meget dækker et brædt inklusiv "hul" i mellem
        antalBeklædningsBrædder= 2*(2*(skurLængde+skurBredde))/16;


    }

    //Antagelse: der skal bruges 8 skruer pr inderbrædt, og disse er jo halvelen af alle brædder
    //Kommef i pakker af 300
    public void beregnAntalKorteBeklædningsSkruer () {

        System.out.println("Antal korte skruer: "+(antalBeklædningsBrædder/2)*8.0);
        antalPakkerKorteBeklædningsSkruer = (int) Math.ceil(((antalBeklædningsBrædder/2)*8.0)/300);
        System.out.println("Antal pakker med korte beklædningsskruer: "+antalPakkerKorteBeklædningsSkruer);

    }

    //Antagelse: der skal bruges 8 skruer pr yderbrædt, og disse er jo halvelen af alle brædder
    //Kommer i pakker af 400
    public void beregnAntalLangeBeklædningsSkruer () {

        System.out.println("Antal lange skruer: "+(antalBeklædningsBrædder/2)*8.0);
        antalPakkerLangeBeklædningsSkruer = (int) Math.ceil(((antalBeklædningsBrædder/2)*8.0)/400);
        System.out.println("Antal pakker med lange beklædningsskruer: "+antalPakkerLangeBeklædningsSkruer);


    }


}
