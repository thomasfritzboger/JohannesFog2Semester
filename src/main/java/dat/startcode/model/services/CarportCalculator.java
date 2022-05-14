package dat.startcode.model.services;

import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.exceptions.IllegalDimensionException;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {
    //Materialeliste for carport inklusive skur, hvis de bruger samme type
    public List<OrderLineDTO> mList = new ArrayList<>();

    public int carportLængde;
    public int carportBredde;
    public int carportHøjde;
    public int stolpeIkkeFastgjortRem;
    public int afstandMellemSpær;
    public int tagHældning;
    public String tagtype;
    public boolean hasSkur;
    public int reglarAntal;
    public SkurCalculator skur = new SkurCalculator();

    //Klassevariabler der skal tilføjes materialelisten
    public String spærProductDescription;
    public int spærLængde;
    public int spærAntal;
    public String spærUnitScale;
    public String spærUsementDescription;
    public double spærUnitPrice;

    public String remProductDescription;
    public int remLængde;
    public int remAntal;
    public String remUnitScale;
    public String remUsementDescription;
    public double remUnitPrice;

    public String stolperProductDecsription;
    public int stolperLængde;
    public int stolperAntal;
    public String stolperUnitScale;
    public String stolperUsementDescription;
    public double stolperUnitPrice;

    public String oversternLangsideDecsription;
    public int oversternLangsideLængde;
    public int oversternLangsideAntal;
    public String oversternLangsideUnitScale;
    public String oversternLangsideUsementDescription;
    public double oversternLangsideUnitPrice;

    public String understernLangsideDecsription;
    public int understernLangsideLængde;
    public int understernLangsideAntal;
    public String understernLangsideUnitScale;
    public String understernLangsideUsementDescription;
    public double understernLangsideUnitPrice;

    public String oversternBredsideDecsription;
    public int oversternBredsideLængde;
    public int oversternBredsideAntal;
    public String oversternBredsideUnitScale;
    public String oversternBredsideUsementDescription;
    public double oversternBredsideUnitPrice;

    public String understernBredsideDecsription;
    public int understernBredsideLængde;
    public int understernBredsideAntal;
    public String understernBredsideUnitScale;
    public String understernBredsideUsementDescription;
    public double understernBredsideUnitPrice;

    public String vandbrædderLangsideDecsription;
    public int vandbrædderLangsideLængde;
    public int vandbrædderLangsideAntal;
    public String vandbrædderLangsideUnitScale;
    public String vandbrædderLangsideUsementDescription;
    public double uvandbrædderLangsideUnitPrice;

    public String vandbrædderBredsideDecsription;
    public int vandbrædderBredsideLængde;
    public int vandbrædderBredsideAntal;
    public String vandbrædderBredsideUnitScale;
    public String vandbrædderBredsideUsementDescription;
    public double vandbrædderBredsideUnitPrice;

    public String rullerHulbåndDecsription;
    public int rullerHulbåndLængde;
    public int rullerHulbåndAntal;
    public String rullerHulbåndUnitScale;
    public String rullerHulbåndUsementDescription;
    public double rullerHulbåndUnitPrice;

    public String firkantSkiverDecsription;
    public int firkantSkiverLængde;
    public int firkantSkiverAntal;
    public String firkantSkiverUnitScale;
    public String firkantSkiverUsementDescription;
    public double firkantSkiverlUnitPrice;

    public String bolteDecsription;
    public int bolteLængde;
    public int bolteAntal;
    public String bolteUnitScale;
    public String bolteUsementDescription;
    public double bolteUnitPrice;

    public String pakkerPlastTagskruerDecsription;
    public int pakkerPlastTagskruerLængde;
    public int pakkerPlastTagskruerAntal;
    public String pakkerPlastTagskruerUnitScale;
    public String pakkerPlastTagskruerUsementDescription;
    public double pakkerPlastTagskruerUnitPrice;

    public String pakkerCembritTagskruerDecsription;
    public int pakkerCembritTagskruerLængde;
    public int pakkerCembritTagskruerAntal;
    public String pakkerCembritTagskruerUnitScale;
    public String pakkerCembritTagskruerUsementDescription;
    public double pakkerCembritTagskruerUnitPrice;

    public String universalbeslagVenstreDecsription;
    public int universalbeslagVenstreLængde;
    public int universalbeslagVenstreAntal;
    public String universalbeslagVenstreUnitScale;
    public String universalbeslagVenstreUsementDescription;
    public double universalbeslagVenstreUnitPrice;

    public String universalbeslagHøjreDecsription;
    public int universalbeslagHøjreLængde;
    public int universalbeslagHøjreAntal;
    public String universalbeslagHøjreUnitScale;
    public String universalbeslagHøjreUsementDescription;
    public double universalbeslagHøjreUnitPrice;

    public String vinkelbeslagDecsription;
    public int vinkelbeslagLængde;
    public int vinkelbeslagAntal;
    public String vinkelbeslagUnitScale;
    public String vinkelbeslagUsementDescription;
    public double vinkelbeslagUnitPrice;

    public String plastTagpladerLangeDecsription;
    public int plastTagpladerLangeLængde;
    public int plastTagpladerLangeAntal;
    public String plastTagpladerLangeUnitScale;
    public String plastTagpladerLangeUsementDescription;
    public double plastTagpladerLangeUnitPrice;

    public String plastTagpladerKorteDecsription;
    public int plastTagpladerKorteLængde;
    public int plastTagpladerKorteAntal;
    public String plastTagpladerKorteUnitScale;
    public String plastTagpladerKorteUsementDescription;
    public double plastTagpladerKorteUnitPrice;

    public String cembritpladerDecsription;
    public int cembritpladerLængde;
    public int cembritpladerAntal;
    public String cembritpladerUnitScale;
    public String cembritpladerUsementDescription;
    public double cembritpladerUnitPrice;

    public String pakkerSkruerAf45x60Decsription;
    public int pakkerSkruerAf45x60Længde;
    public int pakkerSkruerAf45x60Antal;
    public String pakkerSkruerAf45x60UnitScale;
    public String pakkerSkruerAf45x60UsementDescription;
    public double pakkerSkruerAf45x60UnitPrice;

    public String pakkerBeslagskruerDecsription;
    public int pakkerBeslagskruerLængde;
    public int pakkerBeslagskruerAntal;
    public String pakkerBeslagskruerUnitScale;
    public String pakkerBeslagskruerUsementDescription;
    public double pakkerBeslagskruerUnitPrice;



    public CarportCalculator() {

    }

    //Metode til beregning med med skur
    public List<OrderLineDTO> beregnCarport(int l, int b, int h, String hasSkur, String tagmateriale,
                                            String placeringSkur, int skurSize) throws IllegalDimensionException {

        setDimensionCarport(l,b,h);
        setHasSkur(hasSkur.toLowerCase());
        setAntalSpær(l);
        beregnAfstandMellemSpær(l, spærAntal);


        if (this.hasSkur) {
            skur.breddeAfDør = 2*afstandMellemSpær;
            skur.afstandMellemSpær = afstandMellemSpær;

            setSkurLængde(skurSize*afstandMellemSpær);

            skur.længdeLangsideMinusDør = skur.skurLængde-skur.breddeAfDør;


            skur.setPlaceringAfSkur(placeringSkur.toLowerCase());
            setSkurBredde(carportBredde);
            checkDimensionsSkur(skur.skurLængde);

            skur.beregnAntalLøsholter(skur.skurLængde,skur.skurBredde);
            skur.skurHøjde = carportHøjde;
            skur.beregenAntalBeklædningsBrædder();
            skur.beregnAntalKorteBeklædningsSkruer();
            skur.beregnAntalLangeBeklædningsSkruer();
            skur.addAllSkurItemsTilListe();


            //Alle beregninger på skur er færdige, hent materialer fra skuret og læg dem i carportlisten
            for (OrderLineDTO orderLineDTO : skur.skurList) {
                mList.add(orderLineDTO);
            }

        }

        setAntalReglar();
        checkDimensionsCarport(carportLængde,carportBredde,carportHøjde);
        setStolpeLængde(carportHøjde);
        beregnAntalStolper(carportLængde);
        tagtype = setTagType(tagmateriale.toLowerCase());
        remAntal = 2;
        rullerHulbåndAntal = 2;
        beregnTagPlader(carportLængde,carportBredde);
        beregnAntalSternbrædder(carportLængde,carportBredde);
        beregnAntalVandbrædder(carportLængde,carportBredde);
        beregnAntalVinkelbeslag();
        beregnSkruerTag(carportLængde,carportBredde);
        //Beregn antal univesal beslag
        universalbeslagVenstreAntal = 2*spærAntal;
        universalbeslagHøjreAntal = 2*spærAntal;
        //Antagelse: Der skal altid bruges en pakke med skruer i størrelse 4,5x60
        pakkerSkruerAf45x60Antal = 1;
        beregnAntalPakkerMedBeslagskruer();

        //Antagelse at der skal bruges en skive pr bolt, som fastgøres rem
        //Beregn antal firkantskiver
        firkantSkiverAntal =  2*(stolperAntal-stolpeIkkeFastgjortRem);

        //Beregn antal bolte
        bolteAntal = 2*(stolperAntal-stolpeIkkeFastgjortRem);
        addAllCarportItemsTilListe();

        for (OrderLineDTO orderLineDTO : mList) {System.out.println(orderLineDTO);}

        return mList;
    }


    public void setDimensionCarport(int l, int b, int h){
        carportLængde = l;
        carportBredde = b;
        carportHøjde = h;
        skur.carportBredde = carportBredde;
    }

    public void checkDimensionsCarport (int l, int b, int h) throws IllegalDimensionException {

        if (b < 300 || b > 600)  { throw new IllegalDimensionException
                ("Din carport er enten for smal eller for bred, bredden skal være mellem 3,0 m til 6 m");}
        if (h < 210 || h > 300)  { throw new IllegalDimensionException
                ("Din carport er enten for lav eller for høj, højden skal være mellem 2,1 m til 3,6 m");}
        if (!hasSkur && (l < 420 || l > 600)) {throw new IllegalDimensionException
                ("Din carport er enten for kort eller for lang, længden skal være mellem 4,2 m til 6 m");}
        if (hasSkur && (l < skur.skurLængde+420 || l > skur.skurLængde+600)) { throw new IllegalDimensionException
                ("Din carport er enten for kort eller for lang, når du har skur, den skal være mellem  "
                        +((skur.skurLængde+420))+"cm og "+((skur.skurLængde+600))+"cm lang"); }
    }


    public int setAntalSpær (int l){
        spærAntal =  1+ (int) Math.ceil(l/59.0);
        return spærAntal;
        //Faktisk ikke helt korrekt, men ellers bliver det ikke 55 cm som på tegning
    }

    public int beregnAfstandMellemSpær(int l, int numOfSpær) {
        afstandMellemSpær = (int)Math.ceil((l/(numOfSpær-1)));
        return afstandMellemSpær;
    }

    public int setStolpeLængde (int l) {
        stolperLængde = l + 90;
        return stolperLængde;
    }

    public int beregnAntalStolper(int l) {

        int res;
        if (carportBredde<=310) {
            stolpeIkkeFastgjortRem = 0;
        } else {
            stolpeIkkeFastgjortRem = 1;

        }
        if (!hasSkur) {
            res = 6+stolpeIkkeFastgjortRem;
            stolperAntal = res;
            return res;
        } else {
            int ekstra =stolpeIkkeFastgjortRem + skur.antalStolperPåRemEkstraNårSkur + skur.antalStolperPåSpærEkstraNårSkur;
            res = 6+ekstra;
            stolperAntal = res;
            return res;
        }
    }

    public String setTagType(String s) {
        if (s.equalsIgnoreCase("p")) {
            tagtype = "Trapezplader i plast";
        }
        if (s.equalsIgnoreCase("c")) {
            tagtype = "Cembrit tagplader";
        }
        return tagtype;
    }

    public void setHasSkur(String s) {
        if (s.equalsIgnoreCase("y")) {
            hasSkur = true;

        } else {
            hasSkur = false;
        }
    }

    public int setSkurLængde(int i) {
        skur.skurLængde = i;
        return skur.skurLængde;
    }

    public int setSkurBredde (int b) {
        if (skur.placeringAfSkur.equals("midt")) {
            skur.skurBredde = b -70;

        }   else {
            skur.skurBredde = (b/2)-35;
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

    public void beregnTagPlader (int l, int b) {

        if (tagtype.equals("c")) {
            int res;
            int langsideRundetOp;
            int bredsideRundetOp;
            langsideRundetOp = (int) Math.ceil(carportLængde/100.0);
            bredsideRundetOp = (int) Math.ceil(carportBredde/100.0);
            res = langsideRundetOp*bredsideRundetOp;
            cembritpladerAntal = res;

        } else if (tagtype.equals("p")) {
            if (l<=600) {
                plastTagpladerLangeAntal = (int)Math.ceil(carportBredde/100.0);
                plastTagpladerLangeLængde = (int) Math.ceil(l/60.0)*60;
            } else if (l<=820){
                plastTagpladerLangeAntal = (int)Math.ceil(carportBredde/100.0);
                plastTagpladerKorteAntal = (int)Math.ceil(carportBredde/100.0);
                plastTagpladerLangeLængde = (int)Math.ceil((l-220)/60.0)*60;
                plastTagpladerKorteLængde = 240;
            } else {
                plastTagpladerLangeAntal = (int) Math.ceil(carportBredde/100.0);
                plastTagpladerKorteAntal = (int) Math.ceil(carportBredde/100.0);
                plastTagpladerLangeLængde = 600;
                plastTagpladerKorteLængde = (int) Math.ceil((l-580)/60.0)*60;
            }

        } else {

        }
    }

    //Bruges fm udregningen af antal vinkelbeslag
    public void setAntalReglar () {
        reglarAntal = (skur.løsholterLangsideMedDørAntal+skur.løsholterLangsideUdenDørAntal+
                skur.løsholterBredsideAntal);
    }

    public void beregnSkruerTag (int l, int b) {

        //Antagelse 16 skruer pr kvm
        //Beregn antal pakker med bundskruer til trapez tag;
        if (tagtype.equals("Trapezplader i plast")) {

            pakkerPlastTagskruerAntal = (int) Math.ceil(((l/100)*(b/100)*16.0)/200);
        }

        //Antagelse 8 skruer pr kvm, der er 100 skruer i en pakke.
        //Beregn antal pakker med bundskruer til cembrit tag;
        else if (tagtype.equals("Cembrit tagplader")) {
            pakkerCembritTagskruerAntal = (int) Math.ceil((((l/100.0)*(b/100.0))*8)/100);

        } else {
            System.out.println("Ingen skruer, da tagtype ikke kan vælges");
        }
    }

    public void beregnAntalSternbrædder(int l, int b) {
        if (l<=600) {
            understernLangsideLængde = (int) Math.ceil(l/60.0)*60;
            oversternLangsideLængde = (int) Math.ceil(l/60.0)*60;
            understernLangsideAntal = 2;
            oversternLangsideAntal = 2;
        } else  {
            understernLangsideLængde = (int) Math.ceil((l/2)/60.0)*60;
            oversternLangsideLængde = (int) Math.ceil((l/2)/60.0)*60;
            understernLangsideAntal = 4;
            oversternLangsideAntal = 4;
        }
        if (b<=600) {
            understernBredsideLængde = (int) Math.ceil(b/60.0)*60;
            oversternBredsideLængde = (int) Math.ceil(b/60.0)*60;
            understernBredsideAntal = 2;
            oversternBredsideAntal = 1;
        }  else {
            understernBredsideLængde = (int) Math.ceil((b/2)/60.0)*60;
            oversternBredsideLængde = (int) Math.ceil((b/2)/60.0)*60;
            understernBredsideAntal = 4;
            oversternBredsideAntal = 2;
        }
    }

    public void beregnAntalVandbrædder(int l, int b){
        if (l<=480){
            vandbrædderLangsideLængde = (int) Math.ceil(l/60.0)*60;
            vandbrædderLangsideAntal = 2;
        } else if (l<960){
            vandbrædderLangsideLængde = (int) Math.ceil((l/2)/60.0)*60;
            vandbrædderLangsideAntal = 4;
        }
        else {
            vandbrædderLangsideLængde = (int) Math.ceil((l/3)/60.0)*60;
            vandbrædderLangsideAntal = 6;
        }
        if (b<=480) {
            vandbrædderBredsideLængde =  (int) Math.ceil(b/60.0)*60;
            vandbrædderBredsideAntal = 2;
        } else  {
            vandbrædderBredsideLængde = (int) Math.ceil((b/2)/60.0)*60;
            vandbrædderBredsideAntal = 4;
        }
    }

    //Antagelse: Der skal bruges 2 vinkelbeslag mellem spær og stolper, der ikke er boltet til rem
    //Antagelse: Der skal bruges 2 vinkelbeslag mellem løsholter og stolper
    public void beregnAntalVinkelbeslag () {
        if (!hasSkur) {
            vinkelbeslagAntal = 2*stolpeIkkeFastgjortRem;
        }  else {
            vinkelbeslagAntal = 2*stolpeIkkeFastgjortRem + 2*reglarAntal;
        }
    }

    //Antagelse: Der bruges 4 skruer hver gang hulbånd krydser et spær og 9 skruer til hvert universalbeslag
    public void beregnAntalPakkerMedBeslagskruer() {
        int tilUniversal = 2*spærAntal*9;
        int tilHulbånd = 4*spærAntal;
        pakkerBeslagskruerAntal = (int) Math.ceil ((tilHulbånd+tilUniversal+0.0)/250);
    }

    public void addAllCarportItemsTilListe () {
        mList.add(new OrderLineDTO( spærProductDescription,
                spærLængde, spærAntal, spærUnitScale,
                spærUsementDescription, spærUnitPrice*spærAntal));
        mList.add(new OrderLineDTO(remProductDescription,
                remLængde,remAntal,remUnitScale,
                remUsementDescription, remUnitPrice*remAntal));
        mList.add(new OrderLineDTO(stolperProductDecsription,
                stolperLængde,stolperAntal,stolperUnitScale,
                stolperUsementDescription, stolperUnitPrice*spærAntal));
        mList.add(new OrderLineDTO(oversternLangsideDecsription,
                oversternLangsideLængde, oversternLangsideAntal, oversternLangsideUnitScale,
                oversternLangsideUsementDescription, oversternLangsideUnitPrice*oversternLangsideAntal));
        mList.add(new OrderLineDTO(understernLangsideDecsription,
                understernLangsideLængde, understernLangsideAntal, understernLangsideUnitScale,
                understernLangsideUsementDescription, understernLangsideUnitPrice*understernLangsideAntal));
        mList.add(new OrderLineDTO(oversternBredsideDecsription,
                oversternBredsideLængde,oversternBredsideAntal,oversternBredsideUnitScale,
                oversternBredsideUsementDescription, oversternBredsideUnitPrice*oversternBredsideAntal));
        mList.add(new OrderLineDTO(understernBredsideDecsription,
                understernBredsideLængde, understernBredsideAntal, understernBredsideUnitScale,
                understernBredsideUsementDescription, understernBredsideUnitPrice*understernBredsideAntal));
        mList.add(new OrderLineDTO(vandbrædderLangsideDecsription,
                vandbrædderLangsideLængde, vandbrædderLangsideAntal, vandbrædderLangsideUnitScale,
                vandbrædderLangsideUsementDescription, uvandbrædderLangsideUnitPrice*vandbrædderLangsideAntal));
        mList.add(new OrderLineDTO(vandbrædderBredsideDecsription,
                vandbrædderBredsideLængde, vandbrædderBredsideAntal,vandbrædderBredsideUnitScale,
                vandbrædderBredsideUsementDescription, vandbrædderBredsideUnitPrice*vandbrædderBredsideAntal));
        mList.add(new OrderLineDTO(rullerHulbåndDecsription,
                rullerHulbåndLængde, rullerHulbåndAntal, rullerHulbåndUnitScale,
                rullerHulbåndUsementDescription, rullerHulbåndUnitPrice*rullerHulbåndAntal));
        mList.add(new OrderLineDTO(firkantSkiverDecsription,
                firkantSkiverLængde, firkantSkiverAntal, firkantSkiverUnitScale,
                firkantSkiverUsementDescription, firkantSkiverlUnitPrice*firkantSkiverAntal));
        mList.add(new OrderLineDTO(bolteDecsription,
                bolteLængde, bolteAntal, bolteUnitScale,
                bolteUsementDescription, bolteUnitPrice*bolteAntal));
        mList.add(new OrderLineDTO(pakkerPlastTagskruerDecsription,
                pakkerPlastTagskruerLængde, pakkerPlastTagskruerAntal, pakkerPlastTagskruerUnitScale,
                pakkerPlastTagskruerUsementDescription, pakkerPlastTagskruerUnitPrice*pakkerPlastTagskruerAntal));
        mList.add(new OrderLineDTO(pakkerCembritTagskruerDecsription,
                pakkerCembritTagskruerLængde, pakkerCembritTagskruerAntal, pakkerCembritTagskruerUnitScale,
                pakkerCembritTagskruerUsementDescription, pakkerCembritTagskruerUnitPrice*pakkerCembritTagskruerAntal));
        mList.add(new OrderLineDTO(universalbeslagVenstreDecsription,
                universalbeslagVenstreLængde, universalbeslagVenstreAntal, universalbeslagVenstreUnitScale,
                universalbeslagVenstreUsementDescription, universalbeslagVenstreUnitPrice*universalbeslagVenstreAntal));
        mList.add(new OrderLineDTO(universalbeslagHøjreDecsription,
                universalbeslagHøjreLængde, universalbeslagHøjreAntal, universalbeslagHøjreUnitScale,
                universalbeslagHøjreUsementDescription, universalbeslagHøjreUnitPrice*universalbeslagHøjreAntal));
        mList.add(new OrderLineDTO(vinkelbeslagDecsription,
                vinkelbeslagLængde, vinkelbeslagAntal, vinkelbeslagUnitScale,
                vinkelbeslagUsementDescription, vinkelbeslagUnitPrice*vinkelbeslagAntal));
        mList.add(new OrderLineDTO(plastTagpladerLangeDecsription,
                plastTagpladerLangeLængde, plastTagpladerLangeAntal, plastTagpladerLangeUnitScale,
                plastTagpladerLangeUsementDescription, plastTagpladerLangeUnitPrice*plastTagpladerLangeAntal));
        mList.add(new OrderLineDTO(plastTagpladerKorteDecsription,
                plastTagpladerKorteLængde, plastTagpladerKorteAntal, plastTagpladerKorteUnitScale,
                plastTagpladerKorteUsementDescription, plastTagpladerKorteUnitPrice*plastTagpladerKorteAntal));
        mList.add(new OrderLineDTO(cembritpladerDecsription,
                cembritpladerLængde, cembritpladerAntal, cembritpladerUnitScale,
                cembritpladerUsementDescription, cembritpladerUnitPrice*cembritpladerAntal));
        mList.add(new OrderLineDTO(pakkerSkruerAf45x60Decsription,
                pakkerSkruerAf45x60Længde, pakkerSkruerAf45x60Antal, pakkerSkruerAf45x60UnitScale,
                pakkerSkruerAf45x60UsementDescription, pakkerSkruerAf45x60UnitPrice*pakkerSkruerAf45x60Antal));
        mList.add(new OrderLineDTO(pakkerBeslagskruerDecsription,
                pakkerBeslagskruerLængde, pakkerBeslagskruerAntal, pakkerBeslagskruerUnitScale,
                pakkerBeslagskruerUsementDescription, pakkerBeslagskruerUnitPrice*pakkerBeslagskruerAntal));

    }
}
