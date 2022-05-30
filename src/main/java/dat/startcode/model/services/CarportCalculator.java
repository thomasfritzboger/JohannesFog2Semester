package dat.startcode.model.services;

import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProductDTO;
import dat.startcode.model.exceptions.IllegalDimensionException;
import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {

    List<ProductDTO> productDTOList;

    public CarportCalculator(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    //Materialeliste for carport inklusive skur, hvis de bruger samme type
    private List<OrderLineDTO> orderLineDTOList = new ArrayList<>();

    private double carportPrice;
    private int carportLength;
    private int carportWidth;
    private int carportHeight;
    private int stolpeIkkeFastgjortRem;
    private int afstandMellemSpær;
    private String roofMaterial;
    private boolean hasShed;
    private int reglarAntal;
    protected ShedCalculator shedCalculator;

    //Klassevariabler der skal tilføjes materialelisten
    private int spærLængde, spærAntal;
    private int remLængde, remAntal;
    private int stolperLængde, stolperAntal;
    private int oversternLangsideLængde, oversternLangsideAntal;
    private int understernLangsideLængde, understernLangsideAntal;
    private int oversternBredsideLængde, oversternBredsideAntal;
    private int understernBredsideLængde, understernBredsideAntal;
    private int vandbrædderLangsideLængde, vandbrædderLangsideAntal;
    private int vandbrædderBredsideLængde, vandbrædderBredsideAntal;
    private int rullerHulbåndLængde, rullerHulbåndAntal;
    private int firkantSkiverLængde, firkantSkiverAntal;
    private int bolteLængde, bolteAntal;
    private int pakkerPlastTagskruerLængde, pakkerPlastTagskruerAntal;
    private int pakkerCembritTagskruerLængde, pakkerCembritTagskruerAntal;
    private int universalbeslagVenstreLængde, universalbeslagVenstreAntal;
    private int universalbeslagHøjreLængde, universalbeslagHøjreAntal;
    private int vinkelbeslagLængde, vinkelbeslagAntal;
    private int plastTagpladerLangeLængde, plastTagpladerLangeAntal;
    private int plastTagpladerKorteLængde, plastTagpladerKorteAntal;
    private int cembritpladerLængde, cembritpladerAntal;
    private int pakkerSkruerAf45x60Længde, pakkerSkruerAf45x60Antal;
    private int pakkerBeslagskruerLængde, pakkerBeslagskruerAntal;

    //Metode til beregning med med skur
    public List<OrderLineDTO> calculateCarport(int carportLength, int carportWidth, int carportHeight, String hasShed, String roofMaterial,
                                               String shedPlacement, int shedSize) throws IllegalDimensionException {

        setDimensionCarport(carportLength,carportWidth,carportHeight);
        setHasShed(hasShed.toLowerCase());
        setAntalSpær(carportLength);

        beregnAfstandMellemSpær(carportLength, spærAntal);

        if (this.hasShed) {
            shedCalculator.doorWidth = 2*afstandMellemSpær;
            shedCalculator.afstandMellemSpær = afstandMellemSpær;

            setShedLength(shedSize*afstandMellemSpær);

            shedCalculator.længdeLangsideMinusDør = shedCalculator.getShedLength() - shedCalculator.doorWidth;

            shedCalculator.setShedPlacement(shedPlacement.toLowerCase());
            setShedWidth(this.carportWidth);
            checkDimensionsShed(shedCalculator.getShedLength());

            shedCalculator.beregnAntalLøsholter(shedCalculator.shedLength, shedCalculator.shedWidth);
            shedCalculator.shedHeight = this.carportHeight;
            shedCalculator.beregenAntalBeklædningsBrædder(shedCalculator.shedHeight);
            shedCalculator.beregnAntalKorteBeklædningsSkruer();
            shedCalculator.beregnAntalLangeBeklædningsSkruer();

            //Alle beregninger på skur er færdige, hent materialer fra skuret og læg dem i carportlisten
            for (OrderLineDTO orderLineDTO : shedCalculator.shedMaterialList) {
                orderLineDTOList.add(orderLineDTO);
            }
        }

        setAntalReglar();
        checkDimensionsCarport(this.carportLength, this.carportWidth, this.carportHeight);
        setStolpeLængde(this.carportHeight);
        beregnAntalStolper();
        this.roofMaterial = setRoofMaterial(roofMaterial.toLowerCase());
        remAntal = 2;
        remLængde = this.carportLength;

        addItemToList(7,remLængde,remAntal);

        rullerHulbåndAntal = 2;

        addItemToList(15,rullerHulbåndLængde,rullerHulbåndAntal);

        beregnTagPlader(this.carportLength, this.carportWidth);
        beregnAntalSternbrædder(this.carportLength, this.carportWidth);
        beregnAntalVandbrædder(this.carportLength, this.carportWidth);
        beregnAntalVinkelbeslag();
        beregnSkruerTag(this.carportLength, this.carportWidth);

        universalbeslagHøjreAntal = spærAntal;

        addItemToList(16,universalbeslagHøjreLængde,universalbeslagHøjreAntal);

        universalbeslagVenstreAntal = spærAntal;

        addItemToList(17,universalbeslagVenstreLængde,universalbeslagVenstreAntal);

        pakkerSkruerAf45x60Antal = 1;

        addItemToList(18,pakkerSkruerAf45x60Længde,pakkerSkruerAf45x60Antal);
        beregnAntalPakkerMedBeslagskruer();

        if (shedCalculator != null) {
            firkantSkiverAntal = 2 * (stolperAntal - stolpeIkkeFastgjortRem - shedCalculator.getAntalStolperPåSpærEkstraNårSkur());
            bolteAntal = 2*(stolperAntal-stolpeIkkeFastgjortRem-shedCalculator.getAntalStolperPåSpærEkstraNårSkur());
        } else {
            firkantSkiverAntal = 2 * (stolperAntal - stolpeIkkeFastgjortRem);
            bolteAntal = 2*(stolperAntal-stolpeIkkeFastgjortRem);
        }

        addItemToList(21,firkantSkiverLængde,firkantSkiverAntal);

        addItemToList(20,bolteLængde,bolteAntal);

        calculateCarportPrice();
        return orderLineDTOList;
    }

    public void setDimensionCarport(int carportLength, int carportWidth, int carportHeight){
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.carportHeight = carportHeight;
    }

    public void checkDimensionsCarport (int carportLength, int carportWidth, int carportHeight) throws IllegalDimensionException {

        if (carportWidth < 300 || carportWidth > 600)  { throw new IllegalDimensionException
                ("Din carport er enten for smal eller for bred, bredden skal være mellem 3,0 m til 6 m");}
        if (carportHeight < 210 || carportHeight > 300)  { throw new IllegalDimensionException
                ("Din carport er enten for lav eller for høj, højden skal være mellem 2,1 m til 3,6 m");}
        if (!hasShed && (carportLength < 420 || carportLength > 600)) {throw new IllegalDimensionException
                ("Din carport er enten for kort eller for lang, længden skal være mellem 4,2 m til 6 m");}
        if (hasShed && (carportLength < shedCalculator.shedLength +420 || carportLength > shedCalculator.shedLength +600)) { throw new IllegalDimensionException
                ("Din carport er enten for kort eller for lang, når du har skur, den skal være mellem  "
                        +((shedCalculator.shedLength +420))+"cm og "+((shedCalculator.shedLength +600))+"cm lang"); }
    }

    public void setAntalSpær (int carportLength){
        spærAntal =  1+ (int) Math.ceil(carportLength/59.0);
        spærLængde = carportWidth;

        addItemToList(8,spærLængde,spærAntal);
    }

    public int beregnAfstandMellemSpær(int carportLength, int totalAntalSpær) {
        afstandMellemSpær = (int)Math.ceil((carportLength/(totalAntalSpær-1)));
        return afstandMellemSpær;
    }

    public void setStolpeLængde (int carportLength) {
        stolperLængde = carportLength + 90;
    }

    public void beregnAntalStolper() {
        int res;

        if (!hasShed) {
            stolperAntal = 6;
        } else {
            int extra =stolpeIkkeFastgjortRem + shedCalculator.getAntalStolperPåRemEkstraNårSkur() + shedCalculator.getAntalStolperPåSpærEkstraNårSkur();
            res = 6+extra;
            stolperAntal = res;
        }
        addItemToList(9,stolperLængde,stolperAntal);
    }

    public String setRoofMaterial(String roofMaterial) {
        if (roofMaterial.equalsIgnoreCase("p")) {
            this.roofMaterial = "Trapezplader i plast";
        }
        if (roofMaterial.equalsIgnoreCase("c")) {
            this.roofMaterial = "Cembrit tagplader";
        }
        return this.roofMaterial;
    }

    public void setHasShed(String hasShed) {
        if (hasShed.equalsIgnoreCase("y")) {
            this.hasShed = true;
            this.shedCalculator = new ShedCalculator(productDTOList);
            shedCalculator.carportWidth = carportWidth;

        } else {
            this.hasShed = false;
        }
    }

    public int setShedLength(int shedLength) {
        if (shedCalculator != null) {
            shedCalculator.shedLength = shedLength;
            return shedCalculator.shedLength;
        }
        return 0;
    }

    //Minus 70 hvis udhæng begge sider, ellers 35cm til halvt skur, som har udhæng i en side
    public int setShedWidth(int shedWidth) {
        if (shedCalculator.shedPlacement.equals("center")) {
            shedCalculator.shedWidth = shedWidth -70;

        }   else {
            shedCalculator.shedWidth = (shedWidth/2)-35;
        }
        return shedCalculator.shedWidth;
    }

    public void checkDimensionsShed(int shedLength) throws IllegalDimensionException {

        if (shedCalculator.shedPlacement.equals("left") || shedCalculator.shedPlacement.equals("right")) {

            if (carportWidth <310) {
                hasShed = false;
                throw new IllegalDimensionException("Dit skur kan kun vælges med placering i midten, hvis din carport er smallere end 310");
            }

            if (shedLength < 3*afstandMellemSpær || shedLength > 5*afstandMellemSpær) {
                hasShed = false;
                throw new IllegalDimensionException("Dit skur er enten for kort eller for lang, længden skal være mellem "+(3*afstandMellemSpær)+" cm til "
                        +(5*afstandMellemSpær)+" cm når du har valgt et halvt skur");
            }  else {
                hasShed = true;
            }
        }

        if (shedCalculator.shedPlacement.equals("center")) {
            if (shedLength < 3*afstandMellemSpær || shedLength > 10*afstandMellemSpær) {
                hasShed = false;
                throw new IllegalDimensionException("Dit skur er enten for kort eller for lang, længden skal være mellem "+(3*afstandMellemSpær)+" cm til "
                        +(10*afstandMellemSpær)+" cm når du har valgt et halvt skur");
            }   else {
                hasShed = true;
            }
        }
    }

    public void beregnTagPlader (int l, int b) {

        if (roofMaterial.equals("Cembrit tagplader")) {

            int langsideRundetOp;
            int bredsideRundetOp;
            langsideRundetOp = (int) Math.ceil(carportLength /100.0);
            bredsideRundetOp = (int) Math.ceil(carportWidth /100.0);
            cembritpladerAntal = langsideRundetOp*bredsideRundetOp;

            addItemToList(27,cembritpladerLængde,cembritpladerAntal);

        }
        if (roofMaterial.equals("Trapezplader i plast")) {
            if (l<=600) {
                plastTagpladerLangeAntal = (int) Math.ceil(carportWidth /100.0);
                plastTagpladerLangeLængde = (int) Math.ceil(l/60.0)*60;
            } else if (l<=820){
                plastTagpladerLangeAntal = (int) Math.ceil(carportWidth /100.0);
                plastTagpladerKorteAntal = (int) Math.ceil(carportWidth /100.0);
                plastTagpladerLangeLængde = (int) Math.ceil((l-220)/60.0)*60;
                plastTagpladerKorteLængde = 240;
            } else {
                plastTagpladerLangeAntal = (int) Math.ceil(carportWidth /100.0);
                plastTagpladerKorteAntal = (int) Math.ceil(carportWidth /100.0);
                plastTagpladerLangeLængde = 600;
                plastTagpladerKorteLængde = (int) Math.ceil((l-580)/60.0)*60;
            }

            addItemToList(13,plastTagpladerLangeLængde,plastTagpladerLangeAntal);
            addItemToList(13,plastTagpladerKorteLængde,plastTagpladerKorteAntal);
        }
    }

    //Bruges ifm udregningen af antal vinkelbeslag
    public void setAntalReglar () {
        if (shedCalculator != null) {
            reglarAntal = (shedCalculator.getLøsholterLangsideMedDørAntal() + shedCalculator.getLøsholterLangsideUdenDørAntal() +
                    shedCalculator.getLøsholterBredsideAntal());
        }
    }

    public void beregnSkruerTag (int l, int b) {
        //Antagelse 16 skruer pr kvm
        //Beregn antal pakker med bundskruer til trapez tag;
        if (roofMaterial.equals("Trapezplader i plast")) {
            pakkerPlastTagskruerAntal = (int) Math.ceil(((l/100)*(b/100)*16.0)/200);
            addItemToList(14,pakkerPlastTagskruerLængde,pakkerPlastTagskruerAntal);
        } else if (roofMaterial.equals("Cembrit tagplader")) {
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

    public void beregnAntalVinkelbeslag () {
        if (!hasShed) {
            vinkelbeslagAntal = 2*stolpeIkkeFastgjortRem;
        }  else {
            if (shedCalculator.længdeLangsideMinusDør*3 < 480) {

            vinkelbeslagAntal = 4+ 2* shedCalculator.getAntalStolperPåSpærEkstraNårSkur() + 2*reglarAntal;
            } else {

            vinkelbeslagAntal = 2* shedCalculator.getAntalStolperPåSpærEkstraNårSkur() + 2*reglarAntal;
            }
        }
        addItemToList(26,vinkelbeslagLængde, vinkelbeslagAntal);
    }

    public void beregnAntalPakkerMedBeslagskruer() {
        int tilUniversalBeslag = 2*spærAntal*9;
        int tilHulbånd = 4*spærAntal;
        pakkerBeslagskruerAntal = (int) Math.ceil ((tilHulbånd+tilUniversalBeslag+0.0)/250);
        addItemToList(19,pakkerBeslagskruerLængde,pakkerBeslagskruerAntal);
    }

    private void addItemToList(int index, int length, int amount) {
        if (length == 0) {
            orderLineDTOList.add(new OrderLineDTO(productDTOList.get(index).getProductId(), productDTOList.get(index).getProductDescription(),
                    length, amount,
                    productDTOList.get(index).getUnitScale(),
                    productDTOList.get(index).getUsementDescription(),
                    amount* productDTOList.get(index).getUnitPrice()));
        } else {
            orderLineDTOList.add(new OrderLineDTO(productDTOList.get(index).getProductId(), productDTOList.get(index).getProductDescription(),
                    length, amount,
                    productDTOList.get(index).getUnitScale(),
                    productDTOList.get(index).getUsementDescription(),
                    amount*length/100.0* productDTOList.get(index).getUnitPrice()));
        }
    }

    public void calculateCarportPrice() {
        for (OrderLineDTO orderLineDTODTO : orderLineDTOList) {
            carportPrice += orderLineDTODTO.getTotalLinePrice();
        }
    }

    public double getCarportPrice() {
        return carportPrice;
    }
    public int getCarportLength() {
        return carportLength;
    }
    public int getCarportWidth() {
        return carportWidth;
    }
    public int getCarportHeight() {
        return carportHeight;
    }
    public int getAfstandMellemSpær() {
        return afstandMellemSpær;
    }
    public boolean isHasShed() {
        return hasShed;
    }
    public int getSpærLængde() {
        return spærLængde;
    }
    public int getSpærAntal() {
        return spærAntal;
    }
    public int getStolperLængde() {
        return stolperLængde;
    }
    public int getStolperAntal() {
        return stolperAntal;
    }
    public int getPakkerPlastTagskruerAntal() {
        return pakkerPlastTagskruerAntal;
    }
    public int getPakkerCembritTagskruerAntal() {
        return pakkerCembritTagskruerAntal;
    }
}