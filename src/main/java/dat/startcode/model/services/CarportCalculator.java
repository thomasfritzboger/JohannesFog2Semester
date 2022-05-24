package dat.startcode.model.services;

import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.IllegalDimensionException;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator{

    List<ProduktDTO> pDTO;

    public CarportCalculator(List<ProduktDTO> liste) {
        this.pDTO = liste;
    }

    public CarportCalculator() {} //Test-constructor needed for test to not error.

    //Materialeliste for carport inklusive skur, hvis de bruger samme type
    public List<OrderLineDTO> mList = new ArrayList<>();

    public double carportPris;

    public int carportLængde;
    public int carportBredde;
    public int carportHøjde;
    public int stolpeIkkeFastgjortRem;
    public int afstandMellemSpær;
    public int tagHældning;
    public String tagtype;
    public boolean hasSkur;
    public int reglarAntal;
    public SkurCalculator skur;

    //Klassevariabler der skal tilføjes materialelisten
    public int spærLængde;
    public int spærAntal;

    public int remLængde;
    public int remAntal;

    public int stolperLængde;
    public int stolperAntal;

    public int oversternLangsideLængde;
    public int oversternLangsideAntal;

    public int understernLangsideLængde;
    public int understernLangsideAntal;

    public int oversternBredsideLængde;
    public int oversternBredsideAntal;

    public int understernBredsideLængde;
    public int understernBredsideAntal;

    public int vandbrædderLangsideLængde;
    public int vandbrædderLangsideAntal;

    public int vandbrædderBredsideLængde;
    public int vandbrædderBredsideAntal;

    public int rullerHulbåndLængde;
    public int rullerHulbåndAntal;

    public int firkantSkiverLængde;
    public int firkantSkiverAntal;

    public int bolteLængde;
    public int bolteAntal;

    public int pakkerPlastTagskruerLængde;
    public int pakkerPlastTagskruerAntal;

    public int pakkerCembritTagskruerLængde;
    public int pakkerCembritTagskruerAntal;

    public int universalbeslagVenstreLængde;
    public int universalbeslagVenstreAntal;

    public int universalbeslagHøjreLængde;
    public int universalbeslagHøjreAntal;

    public int vinkelbeslagLængde;
    public int vinkelbeslagAntal;

    public int plastTagpladerLangeLængde;
    public int plastTagpladerLangeAntal;

    public int plastTagpladerKorteLængde;
    public int plastTagpladerKorteAntal;

    public int cembritpladerLængde;
    public int cembritpladerAntal;

    public int pakkerSkruerAf45x60Længde;
    public int pakkerSkruerAf45x60Antal;

    public int pakkerBeslagskruerLængde;
    public int pakkerBeslagskruerAntal;

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
            skur.beregenAntalBeklædningsBrædder(skur.skurHøjde);
            skur.beregnAntalKorteBeklædningsSkruer();
            skur.beregnAntalLangeBeklædningsSkruer();

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
        remLængde = carportLængde;

        addItemToList(7,remLængde,remAntal);

        rullerHulbåndAntal = 2;

        addItemToList(15,rullerHulbåndLængde,rullerHulbåndAntal);

        beregnTagPlader(carportLængde,carportBredde);
        beregnAntalSternbrædder(carportLængde,carportBredde);
        beregnAntalVandbrædder(carportLængde,carportBredde);
        beregnAntalVinkelbeslag();
        beregnSkruerTag(carportLængde,carportBredde);

        //Beregn antal univesal beslag
        universalbeslagHøjreAntal = spærAntal;

        addItemToList(16,universalbeslagHøjreLængde,universalbeslagHøjreAntal);

        universalbeslagVenstreAntal = spærAntal;

        addItemToList(17,universalbeslagVenstreLængde,universalbeslagVenstreAntal);

        //Antagelse: Der skal altid bruges en pakke med skruer i størrelse 4,5x60
        pakkerSkruerAf45x60Antal = 1;

        addItemToList(18,pakkerSkruerAf45x60Længde,pakkerSkruerAf45x60Antal);
        beregnAntalPakkerMedBeslagskruer();

        //Antagelse at der skal bruges en skive pr bolt, som fastgøres rem
        //Beregn antal firkantskiver
        firkantSkiverAntal =  2*(stolperAntal-stolpeIkkeFastgjortRem);

        addItemToList(21,firkantSkiverLængde,firkantSkiverAntal);

        //Beregn antal bolte
        bolteAntal = 2*(stolperAntal-stolpeIkkeFastgjortRem);


        addItemToList(20,bolteLængde,bolteAntal);

        for (OrderLineDTO orderLineDTO : mList) {
            if (orderLineDTO.productDescription != null)  System.out.println(orderLineDTO);
        }

        beregnCarportPris();
        return mList;
    }

    public void setDimensionCarport(int l, int b, int h){
        carportLængde = l;
        carportBredde = b;
        carportHøjde = h;
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


    public void setAntalSpær (int l){
        spærAntal =  1+ (int) Math.ceil(l/59.0);
        spærLængde = carportBredde;

        addItemToList(8,spærLængde,spærAntal);

    }

    public int beregnAfstandMellemSpær(int l, int numOfSpær) {
        afstandMellemSpær = (int)Math.ceil((l/(numOfSpær-1)));
        return afstandMellemSpær;
    }

    public void setStolpeLængde (int l) {

        stolperLængde = l + 90;
    }

    public void beregnAntalStolper(int l) {

        int res;

        if (!hasSkur) {
            //res = 6+stolpeIkkeFastgjortRem;
            stolperAntal = 6;
        } else {
            int ekstra =stolpeIkkeFastgjortRem + skur.antalStolperPåRemEkstraNårSkur + skur.antalStolperPåSpærEkstraNårSkur;
            res = 6+ekstra;
            stolperAntal = res;
        }

        addItemToList(9,stolperLængde,stolperAntal);

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
            this.skur = new SkurCalculator(pDTO);
            skur.carportBredde = carportBredde;

        } else {
            hasSkur = false;
        }
    }

    public int setSkurLængde(int i) {
        if (skur != null) {
            skur.skurLængde = i;
            return skur.skurLængde;
        }

        return 0;
    }

    //Minus 70 hvis udhæng begge sider, ellers 35cm til halvt skur, som har udhæng i en side
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

        if (tagtype.equals("Cembrit tagplader")) {

            int langsideRundetOp;
            int bredsideRundetOp;
            langsideRundetOp = (int) Math.ceil(carportLængde/100.0);
            bredsideRundetOp = (int) Math.ceil(carportBredde/100.0);
            cembritpladerAntal = langsideRundetOp*bredsideRundetOp;

            addItemToList(27,cembritpladerLængde,cembritpladerAntal);

        }
        if (tagtype.equals("Trapezplader i plast")) {
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

            addItemToList(13,plastTagpladerLangeLængde,plastTagpladerLangeAntal);
            addItemToList(13,plastTagpladerKorteLængde,plastTagpladerKorteAntal);
        }
    }

    //Bruges ifm udregningen af antal vinkelbeslag
    public void setAntalReglar () {
        if (skur != null) {
            reglarAntal = (skur.løsholterLangsideMedDørAntal+skur.løsholterLangsideUdenDørAntal+
                    skur.løsholterBredsideAntal);
        }
        // reglarAntal = (skur.løsholterLangsideMedDørAntal+skur.løsholterLangsideUdenDørAntal+
        //         skur.løsholterBredsideAntal);
    }

    public void beregnSkruerTag (int l, int b) {

        //Antagelse 16 skruer pr kvm
        //Beregn antal pakker med bundskruer til trapez tag;
        if (tagtype.equals("Trapezplader i plast")) {

            pakkerPlastTagskruerAntal = (int) Math.ceil(((l/100)*(b/100)*16.0)/200);

            addItemToList(14,pakkerPlastTagskruerLængde,pakkerPlastTagskruerAntal);

        } else if (tagtype.equals("Cembrit tagplader")) {
            pakkerCembritTagskruerAntal = (int) Math.ceil((((l/100.0)*(b/100.0))*8)/100);

        addItemToList(28,pakkerCembritTagskruerLængde,pakkerCembritTagskruerAntal);

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

        addItemToList(0,understernBredsideLængde,understernBredsideAntal);
        addItemToList(1,understernLangsideLængde,understernLangsideAntal);
        addItemToList(2,oversternBredsideLængde,oversternBredsideAntal);
        addItemToList(3,oversternLangsideLængde, oversternLangsideAntal);
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
            vandbrædderBredsideAntal = 1;
        } else  {
            vandbrædderBredsideLængde = (int) Math.ceil((b/2)/60.0)*60;
            vandbrædderBredsideAntal = 2;
        }

        addItemToList(11,vandbrædderLangsideLængde, vandbrædderLangsideAntal);
        addItemToList(12,vandbrædderBredsideLængde, vandbrædderBredsideAntal);
    }

    //Antagelse: Der skal bruges 2 vinkelbeslag mellem spær og stolper, der ikke er boltet til rem
    //Antagelse: Der skal bruges 2 vinkelbeslag mellem løsholter og stolper
    public void beregnAntalVinkelbeslag () {
        if (!hasSkur) {
            vinkelbeslagAntal = 2*stolpeIkkeFastgjortRem;
        }  else {
            if (skur.længdeLangsideMinusDør*3 < 480) {

            vinkelbeslagAntal = 4+ 2*skur.antalStolperPåSpærEkstraNårSkur+ 2*reglarAntal;
            } else {

            vinkelbeslagAntal = 2*skur.antalStolperPåSpærEkstraNårSkur+ 2*reglarAntal;
            }

        }

        addItemToList(26,vinkelbeslagLængde, vinkelbeslagAntal);

    }

    //Antagelse: Der bruges 4 skruer hver gang hulbånd krydser et spær og 9 skruer til hvert universalbeslag
    public void beregnAntalPakkerMedBeslagskruer() {
        int tilUniversal = 2*spærAntal*9;
        int tilHulbånd = 4*spærAntal;
        pakkerBeslagskruerAntal = (int) Math.ceil ((tilHulbånd+tilUniversal+0.0)/250);

        addItemToList(19,pakkerBeslagskruerLængde,pakkerBeslagskruerAntal);

    }

    private void addItemToList(int index, int length, int amount) {
        if (length == 0) {
            mList.add(new OrderLineDTO(pDTO.get(index).getProduktId(),pDTO.get(index).getProduktDescription(),
                    length, amount,
                    pDTO.get(index).getUnitScale(),
                    pDTO.get(index).getUsementDescription(),
                    amount*pDTO.get(index).getUnitPrice()));
        } else {
            mList.add(new OrderLineDTO(pDTO.get(index).getProduktId(),pDTO.get(index).getProduktDescription(),
                    length, amount,
                    pDTO.get(index).getUnitScale(),
                    pDTO.get(index).getUsementDescription(),
                    amount*length/100.0*pDTO.get(index).getUnitPrice()));
        }
    }

    public void beregnCarportPris() {
        for (OrderLineDTO orderLineDTODTO : mList) {
            carportPris += orderLineDTODTO.totalLinePrice;
        }
    }
}