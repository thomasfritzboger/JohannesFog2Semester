package dat.startcode.calculator;

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

    public int antalStolperPåRemEkstraNårSkur;
    public int antalStolperPåSpærEkstraNårSkur;
    public int antalEkstraBolteNårSkur;
    public int antalEkstraFirkantSkiverNårSkur;

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
        beregnEkstraStolper(skurLængde, placering);



        if (placering.equals("midt")) {
            System.out.println("Du har valgt skur i carportbredde");
            skurBredde = carportBredde;

            System.out.println("SkurBredde  :       " + skurBredde + " Skurlængde     " + skurLængde);

        } else if (placering.equals("venstre")) {
            System.out.println("Du har placeret skuret i venstre side");
            skurBredde = carportBredde/2;

        } else if (placering.equals("højre")) {
            System.out.println("Du har placeret skuret i højre side");
            skurBredde = carportBredde/2;

        } else {
            System.out.println("Du har fravalgt skur");

        }
    }

    private void beregnEkstraStolper(int l, String placering){



        switch (placeringAfSkur){

            case "midt":

                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur = 1;
                    antalStolperPåRemEkstraNårSkur = 3;
                    antalEkstraBolteNårSkur = 3;
                    antalEkstraFirkantSkiverNårSkur = 3;

                } else if (l > 310 && l < 410 ) {
                    antalStolperPåSpærEkstraNårSkur = 1;
                    antalStolperPåRemEkstraNårSkur = 4;
                    antalEkstraBolteNårSkur = 4;
                    antalEkstraFirkantSkiverNårSkur = 4;

                } else {
                    antalStolperPåSpærEkstraNårSkur = 1;
                    antalStolperPåRemEkstraNårSkur = 5;
                    antalEkstraBolteNårSkur = 5;
                    antalEkstraFirkantSkiverNårSkur = 5;

                }
                break;

            case "venstre":

                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur = 2;
                    antalStolperPåRemEkstraNårSkur = 1;
                    antalEkstraBolteNårSkur = 1;
                    antalEkstraFirkantSkiverNårSkur = 1;




                } else if (l > 310 && l < 310+breddeAfDør ) {

                    antalStolperPåSpærEkstraNårSkur = 2;
                    antalStolperPåRemEkstraNårSkur = 1;
                    antalEkstraBolteNårSkur = 1;
                    antalEkstraFirkantSkiverNårSkur = 1;

                }
                break;

            case "højre":
                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur = 2;
                    antalStolperPåRemEkstraNårSkur = 1;
                    antalEkstraBolteNårSkur = 1;
                    antalEkstraFirkantSkiverNårSkur = 1;




                } else if (l > 310 && l <  310+breddeAfDør ) {

                    antalStolperPåSpærEkstraNårSkur = 2;
                    antalStolperPåRemEkstraNårSkur = 1;
                    antalEkstraBolteNårSkur = 1;
                    antalEkstraFirkantSkiverNårSkur = 1;

                }
                break;

            default: {
                antalStolperPåSpærEkstraNårSkur = 0;
                antalStolperPåRemEkstraNårSkur = 0;
                antalEkstraBolteNårSkur =0;
                antalEkstraFirkantSkiverNårSkur = 0;
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