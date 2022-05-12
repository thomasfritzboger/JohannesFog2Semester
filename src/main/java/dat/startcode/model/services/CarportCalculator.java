package dat.startcode.model.services;

import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.exceptions.IllegalDimensionException;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {

    public int carportLængde;
    public int carportBredde;
    public int carportHøjde;
    public List<OrderLineDTO> mList = new ArrayList<>();

    public boolean hasSkur;
    public SkurCalculator skur = new SkurCalculator();

    public int antalSpær;
    public int afstandMellemSpær;
    public int stolpeLængde;
    public int stolpeIkkeFastgjortRem;

    public String tagtype;

    public CarportCalculator() {


    }

    public List<OrderLineDTO> beregnCarport(int l, int b, int h, String hasSkur, String tagmateriale,
                                            String placeringSkur, int skurSize) throws IllegalDimensionException {


        setDimensionCarport(l,b,h);
        setHasSkur(hasSkur);
        setAntalSpær(l);
        beregnAfstandMellemSpær(l,antalSpær);


        if (this.hasSkur) {
            skur.breddeAfDør = 2*afstandMellemSpær;
            skur.afstandMellemSpær = afstandMellemSpær;
            skur.længdeLangsideMinusDør = skur.skurLængde-skur.breddeAfDør;
            setSkurLængde(skurSize*afstandMellemSpær);



            skur.setPlaceringAfSkur(placeringSkur);
            setSkurBredde(carportBredde);
            checkDimensionsSkur(skur.skurLængde);


            //Er ikke testet og sat i skurCalculator
            //skur.beregnAntalLøsholter(skur.skurLængde,skur.skurBredde);
        }

        checkDimensionsCarport(carportLængde,carportBredde,carportHøjde);
        setStolpeLængde(carportHøjde);
        beregnAntalStolper(carportLængde);
        tagtype = setTagType(tagmateriale);




        return mList;
    }



    public void setDimensionCarport(int l, int b, int h){
        carportLængde = l;
        carportBredde = b;
        carportHøjde = h;

        skur.carportBredde = carportBredde;

    }

    public void checkDimensionsCarport (int l, int b, int h) throws IllegalDimensionException {

        if (b < 300 || b > 600)  { throw new IllegalDimensionException("Din carport er enten for smal eller for bred, bredden skal være mellem 3,0 m til 6 m");}
        if (h < 210 || h > 300)  { throw new IllegalDimensionException("Din carport er enten for lav eller for høj, højden skal være mellem 2,1 m til 3,6 m");}


        if (!hasSkur && (l < 420 || l > 600))
        {throw new IllegalDimensionException("Din carport er enten for kort eller for lang, længden skal være mellem 4,2 m til 6 m");}

        if (hasSkur && (l < skur.skurLængde+420 || l > skur.skurLængde+600))
        { throw new IllegalDimensionException("Din carport er enten for kort eller for lang, når du har skur, den skal være mellem  "+((skur.skurLængde+420))+"cm og "+((skur.skurLængde+600))+"cm lang"); }

    }


    public int setAntalSpær (int l){
        antalSpær =  1+ (int) Math.ceil(carportLængde/59.0);
        return antalSpær;
        //Faktisk ikke helt korrekt, men ellers bliver det ikke 55 cm som på tegning
    }

    public int beregnAfstandMellemSpær(int l, int numOfSpær) {
        afstandMellemSpær = (int)Math.ceil((carportLængde/(antalSpær-1)));
        return afstandMellemSpær;
    }

    public int setStolpeLængde (int l) {
        stolpeLængde = l + 90;
        return stolpeLængde;
    }

    public int beregnAntalStolper(int l) {

        double stolpeUnitPrice = 75.95;
        int res = 0;
        if (carportBredde<=310) {
            stolpeIkkeFastgjortRem = 0;

        } else {
            stolpeIkkeFastgjortRem = 1;

        }
        if (!hasSkur) {
            res = 6+stolpeIkkeFastgjortRem;
            OrderLineDTO orderLineDTO = new OrderLineDTO("97x97 mm. trykimp. Stolpe",
                    stolpeLængde,res,"stk","Stolper nedgraves 90 cm i jord",(stolpeLængde/100)*(res)*stolpeUnitPrice);
            mList.add(orderLineDTO);
            return res;
        } else {


            int ekstra =stolpeIkkeFastgjortRem + skur.antalStolperPåRemEkstraNårSkur + skur.antalStolperPåSpærEkstraNårSkur;


            System.out.println("Stolpe ikke på rem "+stolpeIkkeFastgjortRem);
            System.out.println("Skur ekstra på spær "+skur.antalStolperPåSpærEkstraNårSkur);
            System.out.println("Skur ekstra på rem "+skur.antalStolperPåRemEkstraNårSkur);
            System.out.println("Ekstra i alt "+ekstra);

            res = 6+ekstra;



            OrderLineDTO orderLineDTO = new OrderLineDTO("97x97 mm. trykimp. Stolpe",
                    stolpeLængde,res,"stk","Stolper nedgraves 90 cm i jord",(stolpeLængde/100)*(res)*stolpeUnitPrice);

            mList.add(orderLineDTO);


            return res;

        }

    }

    public String setTagType(String s) {
        if (s.toLowerCase().equals("p")) {
            tagtype = "Trapezplader i plast";
        }
        if (s.toLowerCase().equals("c")) {
            tagtype = "Cembrit tagplader";
        }
        return tagtype;
    }

    public void setHasSkur(String s) {
        if (s.toLowerCase().equals("y")) {
            hasSkur = true;
            System.out.println("Du har valgt at bygge med skur?: " + hasSkur);

        } else {
            hasSkur = false;
            System.out.println("Har carport skur?: "+hasSkur);

        }

    }

    public int setSkurLængde(int i) {
        skur.skurLængde = i;
        return skur.skurLængde;
    }

    public int setSkurBredde (int b) {
        if (skur.placeringAfSkur.equals("midt")) {
            skur.skurBredde = b;


        }   else {
            skur.skurBredde = b/2;
        }
        return skur.skurBredde;
    }

    public void checkDimensionsSkur(int l) throws IllegalDimensionException {

        if (skur.placeringAfSkur.equals("venstre") || skur.placeringAfSkur.equals("højre")) {

            if (carportBredde<310) {
                hasSkur = false;
                throw new IllegalDimensionException("Dit skur kan kun vælges med placering i midten, hvis din carport er smallere end 310");

            }

            if (l < 3*afstandMellemSpær || l > 5*afstandMellemSpær) {
                hasSkur = false;
                throw new IllegalDimensionException("Dit skur er enten for kort eller for lang, længden skal være mellem "+(3*afstandMellemSpær)+" cm til "+(5*afstandMellemSpær)+" cm når du har valgt et halvt skur");

            }  else {
                hasSkur = true;
            }
        }

        if (skur.placeringAfSkur.equals("midt")) {
            if (l < 3*afstandMellemSpær || l > 10*afstandMellemSpær) {
                hasSkur = false;
                throw new IllegalDimensionException("Dit skur er enten for kort eller for lang, længden skal være mellem "+(3*afstandMellemSpær)+" cm til "+(10*afstandMellemSpær)+" cm når du har valgt et halvt skur");

            }     else {
                hasSkur = true;
            }
        }
    }




/*


    public int antalStolper;




    public int tagHældning;
    final public int antalRem = 2;

    final public int antalRullerHulbånd = 2;


    public int antalTagpladerPlastLange;
    public int længdeAfTagpladerPlastLange;
    public int antalTagpladerPlastKorte;
    public int længdeAfTagpladerPlastKorte;
    public int antalTagpladerCembrit;

    static int antalOversternBrædderLangside;
    static int længdeAfOverSternBrædderLangside;
    static int antalUndersternBrædderLangside;
    static int længdeAfUnderSternBrædderLangside;

    static int antalOversternBrædderBredside;
    static int længdeAfOverSternBrædderBredside;
    static int antalUndersternBrædderBredside;
    static int længdeAfUnderSternBrædderBredside;

    static int antalAfVandbrædderLangside;
    static int længdeAfVandbrædderLangside;
    static int antalAfVandbrædderBredside;
    static int længdeAfVandbrædderBredside;

    static int antalReglar;

    static int antalFirkantSkiver;
    static int antalBolte;

    static int antalPakkerBundskruer;
    static int antalPakkerTagskruerCembritPlader;

    static int antalUniversalbeslagVenstre;
    static int antalUniversalbeslagHøjre;
    static int antalVinkelbeslag;

    final static int antalPakkerMedSkruerAf45x60 = 1;
    static int antalPakkerMedBeslagskruer;



        //Lav instans af main.Skur
        dat.startcode.model.services.SkurCalculator skur = new dat.startcode.model.services.SkurCalculator();

        //Set om skur og placering ift carport, sætter taghældning til 0, hvis der ikke er skur, ellers sæt længde af skur



        //Set carportlængde til beregning af afstand mellem spær
        setCarportLængde(760);

        //Beregn antal spær
        setAntalSpær(carportLængde);
        System.out.println("Der skal ialt bruges "+antalSpær+" spær.");
        System.out.println("Afstand mellem spær: "+afstandMellemSpær+" cm." );

        if (hasSkur){

            checkDimensionsSkur(skur.skurLængde);

            skur.længdeLangsideMinusDør = skur.skurLængde-skur.breddeAfDør;

            //skur.beregnAntalLøsholter(skur.skurLængde,skur.skurBredde);
        }

        //Set dimensioner for carport
        void setDimensionCarport(carportLængde,600,210);
        setSkurBredde(carportBredde);

        if (hasSkur) {   skur.beregnAntalLøsholter(skur.skurLængde,skur.skurBredde); }

        //Beregn stolpeLængde
        setStolpeLængde(carportHøjde);
        //System.out.println("Stolpelængden skal være: "+stolpeLængde+" cm, når skurhøjden er: "+ carportHøjde +" cm!");

        //Set type tagplade
        setTagType("p");
        System.out.println("Der er valgt "+tagtype+" til taget.");

        //Beregn antal Stolper
        antalStolper = beregnAntalStolper(carportLængde);
        System.out.println("Der skal ialt bruges "+antalStolper+" stolper.");

        //Beregn antal remme, disse er altid 2, så ingen beregning
        //System.out.println("Der skal ialt bruges "+antalRem+" remme.");


        //Beregn antal ruller hulbånd, disse er altid 2, så ingen beregning
        //System.out.println("Der skal ialt bruges "+antalRullerHulbånd+" ruller hulbånd.");

        //Beregn antal tagplader i plast
        beregnAntalTagpladerPlast(carportLængde,carportBredde);
        //System.out.println("Du skal bruge "+ antalTagpladerPlastLange + " lange tagplader");
        //System.out.println("De lange plader er "+ længdeAfTagpladerPlastLange +" lange.");
        //System.out.println("Du skal bruge "+ antalTagpladerPlastKorte + " korte tagplader");
        //System.out.println("De korte plader er "+ længdeAfTagpladerPlastKorte +" lange.");

        //Antagelse 16 skruer pr kvm
        //Beregn antal pakker med bundskruer til trapez tag;
        beregnAntalPakkerBundskruerPlastTag(carportLængde,carportBredde);
        //System.out.println("Der skal ialt bruges "+antalPakkerBundskruer+" pakker bundskruer");

        //Antagelse 8 skruer pr kvm, der er 100 skruer i en pakke.
        //Beregn antal pakker med tagskruer til cembrit tag;
        beregnAntalPakkerTagskruerCembritTag(carportLængde,carportBredde);
        //System.out.println("Der skal bruges "+antalPakkerTagskruerCembritPlader+" pakker tagskruer til cembrittaget");

        //Beregn antal tagplader i cembrit
        beregnAntalTagpladerCembrit(carportLængde,carportBredde);
        //System.out.println("Du skal ialt bruge "+antalTagpladerCembrit+" cembrit tagplader");

        //Beregen antal sternbrædder
        beregnAntalUnderSternbrædder(carportLængde,carportBredde);
        //System.out.println("\nTil siderne skal der bruges "+antalUndersternBrædderLangside+" understernbrædder");
        //System.out.println("Længden på understernebædderne på siderne skal være "+længdeAfUnderSternBrædderLangside+ "cm.");
        //System.out.println("Til enderne skal der bruges "+antalUndersternBrædderBredside+" understernbrædder");
        //System.out.println("Længden på understernebædderne på enderne skal være "+længdeAfUnderSternBrædderBredside+ "cm.");
        //System.out.println("Til siderne skal der bruges "+antalOversternBrædderLangside+" oversternbrædder");
        //System.out.println("Længden på oversternbrædderne på siderne skal være "+længdeAfOverSternBrædderLangside+" cm.");
        //System.out.println("Til enderne skal der bruges "+antalOversternBrædderBredside+" oversternbrædder");
        //System.out.println("Længden på oversternbrædderne på enderne skal være "+længdeAfOverSternBrædderBredside+" cm.");

        //Beregn antal vandbrædder
        beregnAntalVandbrædder(carportLængde,carportBredde);
        //System.out.println("Til siderne skal der bruges "+antalAfVandbrædderLangside+" vandbrædder");
        //System.out.println("Længden af vandbrædderne på siderne skal være "+længdeAfVandbrædderLangside+" cm.");
        //System.out.println("Til enderne skal der bruges "+antalAfVandbrædderBredside+" vandbrædder");
        //System.out.println("Længden af vandbrædderne på enderne skal være "+længdeAfVandbrædderBredside+" cm.");


        //Antagelse at der skal bruges en skive pr bolt, som fastgøres rem
        //Beregn antal firkantskiver
        antalFirkantSkiver =  2*(antalStolper-stolpeIkkeFastgjortRem);
        //System.out.println("Der skal ialt bruges "+antalFirkantSkiver+" firkantskiver.");

        //Beregn antal bolte
        antalBolte = 2*(antalStolper-stolpeIkkeFastgjortRem);
        //System.out.println("Der skal ialt bruges "+antalBolte+" bolte.");

        //Beregn antal univesal beslag
        antalUniversalbeslagVenstre = 2*antalSpær;
        antalUniversalbeslagHøjre = 2*antalSpær;
        //System.out.println("Der skal bruges "+antalUniversalbeslagVenstre+" venstre universalbeslag.");
        //System.out.println("Der skal bruges "+antalUniversalbeslagHøjre+" højre universalbeslag.");

        //Antagelse: Der skal bruges 2 vinkelbeslag mellem spær og stolper, der ikke er boltet til rem
        //Antagelse: Der skal bruges 2 vinkelbeslag mellem løsholter og stolper
        //Beregn antal vinkelbeslag. Hvis der er skur, kan dette kan først beregnes antal reglar er beregnet.
        beregnAntalVinkelbeslag();
        System.out.println("Der skal ialt bruges "+(antalVinkelbeslag+skur.antalVinkelbeslagEkstraNårSkur)+" vinkeklbeslag");

        //Antagelse: Der skal altid bruges en pakke med skruer i størrelse 4,5x60
        //System.out.println("Der skal bruges "+ antalPakkerMedSkruerAf45x60 +" pakker med skruer i str 4,5x60.");

        //Antagelse: Der bruges 4 skruer hver gang hulbånd krydser et spær og 9 skruer til hvert universalbeslag
        //Beregn antal beslagskruer
        beregnAntalPakkerMedBeslagskruer();
        //System.out.println("Der skal bruges "+antalPakkerMedBeslagskruer+" pakker med beslagskruer");

        skur.beregenAntalBeklædningsBrædder();
        System.out.println("Antal beklædningsbrædder er: " + skur.antalBeklædningsBrædder);
        System.out.println("Skur længde og bredde: "+skur.skurLængde + " "+skur.skurBredde);

        skur.beregnAntalKorteBeklædningsSkruer();

        skur.beregnAntalLangeBeklædningsSkruer();



    static int beregnBrædderLængde(int l, int b, int maxLength) {
        int res = 0;
        if (l<=maxLength) {
            res = (int) Math.ceil(b/60.0)*60;

        }  else if (l<=2*maxLength) {
            res = (int) Math.ceil((l/2)/60.0)*60;
        }   else {
            res = (int) Math.ceil((l/3)/60.0)*60;
        }
        return res;
    }



    static public int beregnAntalStolper(double d) {
        if (!hasSkur) {
            stolpeIkkeFastgjortRem = 1;
            return 7;
        }
        stolpeIkkeFastgjortRem = 1 + skur.antalStolperPåRemEkstraNårSkur;
        int ex = skur.antalStolperPåSpærEkstraNårSkur;
        int res = 7 + stolpeIkkeFastgjortRem;




        return res;
        //return 11;
    }

    static void beregnAntalTagpladerPlast(int l, int b) {
        if (l<=600) {
            antalTagpladerPlastLange = (int)Math.ceil(carportBredde/100.0);
            længdeAfTagpladerPlastLange = (int) Math.ceil(l/60.0)*60;
        } else if (l<=820){
            antalTagpladerPlastLange = (int)Math.ceil(carportBredde/100.0);
            antalTagpladerPlastKorte = (int)Math.ceil(carportBredde/100.0);
            længdeAfTagpladerPlastLange = (int)Math.ceil((l-220)/60.0)*60;
            længdeAfTagpladerPlastKorte = 240;
        } else {
            antalTagpladerPlastLange = (int) Math.ceil(carportBredde/100.0);
            antalTagpladerPlastKorte = (int) Math.ceil(carportBredde/100.0);
            længdeAfTagpladerPlastLange = 600;
            længdeAfTagpladerPlastKorte = (int) Math.ceil((l-580)/60.0)*60;
        }
    }

    static void beregnAntalPakkerBundskruerPlastTag(int l, int b) {
        antalPakkerBundskruer = ((l/100)*(b/100)*16)/200;
    }


    static void beregnAntalTagpladerCembrit(int l, int b){
        int res;
        int langsideRundetOp;
        int bredsideRundetOp;
        langsideRundetOp = (int) Math.ceil(carportLængde/100.0);
        bredsideRundetOp = (int) Math.ceil(carportBredde/100.0);
        res = langsideRundetOp*bredsideRundetOp;
        antalTagpladerCembrit = res;
    }

    static void beregnAntalPakkerTagskruerCembritTag (int l, int b) {
        antalPakkerTagskruerCembritPlader = (int) Math.ceil((((l/100.0)*(b/100.0))*8)/100);
    }

    static void beregnAntalUnderSternbrædder(int l, int b) {
        if (l<=600) {
            længdeAfUnderSternBrædderLangside = (int) Math.ceil(l/60.0)*60;
            længdeAfOverSternBrædderLangside = (int) Math.ceil(l/60.0)*60;
            antalUndersternBrædderLangside = 2;
            antalOversternBrædderLangside = 2;
        } else  {
            længdeAfUnderSternBrædderLangside = (int) Math.ceil((l/2)/60.0)*60;
            længdeAfOverSternBrædderLangside = (int) Math.ceil((l/2)/60.0)*60;
            antalUndersternBrædderLangside = 4;
            antalOversternBrædderLangside = 4;
        }
        if (b<=600) {
            længdeAfUnderSternBrædderBredside = (int) Math.ceil(b/60.0)*60;
            længdeAfOverSternBrædderBredside = (int) Math.ceil(b/60.0)*60;
            antalUndersternBrædderBredside = 2;
            antalOversternBrædderBredside = 1;
        }  else {
            længdeAfUnderSternBrædderBredside = (int) Math.ceil((b/2)/60.0)*60;
            længdeAfOverSternBrædderBredside = (int) Math.ceil((b/2)/60.0)*60;
            antalUndersternBrædderBredside = 4;
            antalOversternBrædderBredside = 2;
        }
    }

    static void beregnAntalVandbrædder(int l, int b){
        if (l<=480){
            længdeAfVandbrædderLangside = (int) Math.ceil(l/60.0)*60;
            antalAfVandbrædderLangside = 2;
        } else if (l<960){
            længdeAfVandbrædderLangside = (int) Math.ceil((l/2)/60.0)*60;
            antalAfVandbrædderLangside = 4;
        }
        else {
            længdeAfVandbrædderLangside = (int) Math.ceil((l/3)/60.0)*60;
            antalAfVandbrædderLangside = 6;
        }
        if (b<=480) {
            længdeAfVandbrædderBredside =  (int) Math.ceil(b/60.0)*60;
            antalAfVandbrædderBredside = 2;
        } else if (b<960){
            længdeAfVandbrædderBredside = (int) Math.ceil((b/2)/60.0)*60;
            antalAfVandbrædderBredside = 4;
        }else {
            længdeAfVandbrædderBredside = (int) Math.ceil((b/3)/60.0)*60;
            antalAfVandbrædderBredside = 6;
        }
    }

    static void beregnAntalVinkelbeslag () {
        if (!hasSkur) {
            antalVinkelbeslag = 2*stolpeIkkeFastgjortRem;
        }  else {
            antalVinkelbeslag = 2*stolpeIkkeFastgjortRem + 2*antalReglar;
        }
    }

    static void beregnAntalPakkerMedBeslagskruer() {
        int tilUniversal = 2*antalSpær*9;
        int tilHulbånd = 4*antalSpær;
        antalPakkerMedBeslagskruer = (int) Math.ceil ((tilHulbånd+tilUniversal+0.0)/250);
    }

    //Skal være deleligt med afstand mellem spær
    static void setDimensionSkur(int l) {
        //checkDimensionsSkur(l);
        skur.skurLængde = l;
        skur.skurHøjde = carportHøjde;

    }

    public void setDimensionCarport(int l, int b, int h){
        carportLængde = l;
        carportBredde = b;
        carportHøjde = h;
        skur.carportBredde = carportBredde;

        checkDimensionsCarport(l,b,h);
    }




    static void setCarportLængde(int l) {
        carportLængde = l;
    }


    static void checkDimensionsCarport (int l, int b, int h) {

        if (!hasSkur) {
            if (l < 420 || l > 600)  { System.out.println("Din carport er enten for kort eller for lang, længden skal være mellem 4,2 m til 6 m"); }
            if (b < 300 || b > 600)  { System.out.println("Din carport er enten for smal eller for bred, bredden skal være mellem 3,0 m til 6 m"); }
            if (h < 210 || h > 300)  { System.out.println("Din carport er enten for lav eller for høj, højden skal være mellem 2,1 m til 3,6 m"); }
        }
        else {
            if (l < skur.skurLængde+420 || l > skur.skurLængde+600)  { System.out.println("Diiiiin carport er enten for kort eller for lang, når du har skur, den skal være mellem  "+((skur.skurLængde+420))+"cm og "+((skur.skurLængde+600))+"cm lang"); }
            if (b < 300 || b > 600)  { System.out.println("Din carport er enten for smal eller for bred, bredden skal være mellem 3,0 m til 6 m"); }
            if (h < 210 || h > 300)  { System.out.println("Din carport er enten for lav eller for høj, højden skal være mellem 2,1 m til 3,6 m"); }
        }
    }


    static void setStolpeLængde (int l) {
        stolpeLængde = l + 90;
    }

    static void setSkurBredde (int b) {
        if (skur.placeringAfSkur.equals("midt")) {
            skur.skurBredde = carportBredde;
        }   else {
            skur.skurBredde = carportBredde/2;
        }

    }

    static void beregnAfstandMellemSpær (int l) {

    }



    static void setTagType(String s) {
        if (s.toLowerCase().equals("p")) {
            tagtype = "Trapezplader i plast";
        }
        if (s.toLowerCase().equals("c")) {
            tagtype = "Cembrit tagplader";
        }
    }
*/

    }
