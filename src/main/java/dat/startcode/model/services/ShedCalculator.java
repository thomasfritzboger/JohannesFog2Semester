package dat.startcode.model.services;

import dat.startcode.model.dtos.OrderLineDTO;
import dat.startcode.model.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ShedCalculator {

    protected List<OrderLineDTO> shedMaterialList;
    List<ProductDTO> productDTOList;

    //Klassevariabler der bruges til beregninger
    protected int shedWidth;
    protected int afstandMellemSpær;
    //Skal være delelig med afstand mellem spær
    protected int shedLength;
    protected int shedHeight;

    protected String shedPlacement;
    //Sættes når bredden sættes i carport klassen
    protected int carportWidth;
    //Skal passe med en stolpe
    protected int doorWidth = 2 * afstandMellemSpær;
    protected int længdeLangsideMinusDør;

    //Klassevariabler der er ekstra og hentes via carport
    private int antalStolperPåRemEkstraNårSkur;
    private int antalStolperPåSpærEkstraNårSkur;
    private int antalEkstraBolteNårSkur;
    private int antalEkstraFirkantSkiverNårSkur;
    private int antalVinkelbeslagEkstraNårSkur;

    //Klassevariabler der kun angår skuret og skal tilføjes på liste.
    private int lægtePåDørLængde, lægteTilDørAntal;
    private int stalddørsgrebLængde, stalddørsgrebAntal;
    private int tHængselLængde, tHængselAntal;
    private int løsholterLangsideUdenDørLængde, løsholterLangsideUdenDørAntal;
    private int løsholterLangsideMedDørLængde, løsholterLangsideMedDørAntal;
    private int løsholterBredsideLængde, løsholterBredsideAntal;
    private int beklædningsBrædderLængde, beklædningsBrædderAntal;
    private int pakkerKorteBeklædningsSkruerLængde, pakkerKorteBeklædningsSkruerAntal;
    private int pakkerLangeBeklædningsSkruerLængde, pakkerLangeBeklædningsSkruerAntal;

    public ShedCalculator(List<ProductDTO> liste) {
        this.productDTOList = liste;
        shedMaterialList = new ArrayList<>();
        tHængselAntal = 2;
        addItemToList(25,tHængselLængde,tHængselAntal);
        stalddørsgrebAntal = 1;
        addItemToList(24,stalddørsgrebLængde,stalddørsgrebAntal);
        lægteTilDørAntal = 1;
        lægtePåDørLængde = 420;
        addItemToList(4,lægtePåDørLængde,lægteTilDørAntal);
    }

    public void setShedPlacement(String shedPlacement) {

        this.shedPlacement = shedPlacement;
        if (shedPlacement.equals("center")) {
            shedWidth = carportWidth;
        } else if (shedPlacement.equals("left")) {
            shedWidth = carportWidth/2;
        } else if (shedPlacement.equals("right")) {
            shedWidth = carportWidth/2;
        } else {
            shedWidth = 0;
        }
        beregnEkstraStolper(shedLength);
    }

    private void beregnEkstraStolper(int l){
        switch (shedPlacement){
            case "center":
                if (shedWidth > 310) {
                    antalStolperPåSpærEkstraNårSkur += 2;
                }
                if (l<=310) {

                    antalStolperPåRemEkstraNårSkur += 3;
                    antalEkstraBolteNårSkur += 3;
                    antalEkstraFirkantSkiverNårSkur += 3;
                } else if (l > 310 && l < 310+ doorWidth) {

                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;
                } else {

                    antalStolperPåRemEkstraNårSkur += 5;
                    antalEkstraBolteNårSkur += 5;
                    antalEkstraFirkantSkiverNårSkur += 5;
                }
                break;
            case "left":
                if (l<=310) {

                    antalStolperPåSpærEkstraNårSkur += 3;
                    antalStolperPåRemEkstraNårSkur += 2;
                    antalEkstraBolteNårSkur += 2;
                    antalEkstraFirkantSkiverNårSkur += 2;
                } else if (l > 310 && l < 310+ doorWidth) {

                    antalStolperPåSpærEkstraNårSkur += 3;
                    antalStolperPåRemEkstraNårSkur += 4;
                    antalEkstraBolteNårSkur += 4;
                    antalEkstraFirkantSkiverNårSkur += 4;
                }
                break;
            case "right":
                if (l<=310) {
                    antalStolperPåSpærEkstraNårSkur += 3;
                    antalStolperPåRemEkstraNårSkur += 2;
                    antalEkstraBolteNårSkur += 2;
                    antalEkstraFirkantSkiverNårSkur += 2;

                } else if (l > 310 && l <  310+ doorWidth) {
                    antalStolperPåSpærEkstraNårSkur += 3;
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
        } //end switch
    }

    public void beregnAntalLøsholter (int l, int b) {

        //Løsholter skurside med dør
        if (3*længdeLangsideMinusDør <=240) {
            løsholterLangsideMedDørLængde = 240;
            løsholterLangsideMedDørAntal += 1;
            antalVinkelbeslagEkstraNårSkur += 6;

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
                antalVinkelbeslagEkstraNårSkur += 12;
            }
        } //End calculate skurside med dør
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
            if (shedLength /2 <= 240 ) {
                løsholterLangsideUdenDørLængde = 240;
                løsholterLangsideUdenDørAntal += 6;
                antalVinkelbeslagEkstraNårSkur += 12;
            } else {
                løsholterLangsideUdenDørLængde = (int) Math.ceil((l/2)/30.0)*30;
                løsholterLangsideUdenDørAntal += 6;
                antalVinkelbeslagEkstraNårSkur += 12;
            }
        } //End calculate side overfor dør

        //Løsholter på skurets bredsider
        if (shedWidth <= 240) {
            løsholterBredsideLængde = 240;
            løsholterBredsideAntal += 6;
            antalVinkelbeslagEkstraNårSkur += 12;
        }

        if (shedWidth > 240 && shedWidth <= 310) {
            løsholterBredsideLængde = (int) Math.ceil((b)/30.0)*30;
            løsholterBredsideAntal += 6;
            antalVinkelbeslagEkstraNårSkur += 12;
        }
        if (shedWidth > 310) {
            if (shedWidth /2 <= 240) {
                løsholterBredsideLængde = 240;
                løsholterBredsideAntal += 12;
                antalVinkelbeslagEkstraNårSkur += 24;
            } else {
                løsholterBredsideLængde = (int) Math.ceil((b/2)/30.0)*30;
                løsholterBredsideAntal += 12;
                antalVinkelbeslagEkstraNårSkur += 24;
            } //End calculate sider i carportens bredde
        }
        addItemToList(6,løsholterLangsideUdenDørLængde,løsholterLangsideUdenDørAntal);
        addItemToList(6,løsholterLangsideMedDørLængde,løsholterLangsideMedDørAntal);
        addItemToList(5,løsholterBredsideLængde,løsholterBredsideAntal);
    }

    public void beregenAntalBeklædningsBrædder (int shedHeight) {

        beklædningsBrædderLængde = (int) Math.ceil(shedHeight/30.0)*30;
        //Gange 2 fordi der er inde og ude brædder
        //Gange 2 fordi der er 2 længdesider og 2 breddesider
        //Dividere med 16, for så meget dækker et brædt inklusiv "hul" i mellem
        beklædningsBrædderAntal = 2*(2*(shedLength + shedWidth))/16;
        addItemToList(10,beklædningsBrædderLængde,beklædningsBrædderAntal);
    }

    public void beregnAntalKorteBeklædningsSkruer () {
        pakkerKorteBeklædningsSkruerAntal = (int) Math.ceil(((beklædningsBrædderAntal /2)*8.0)/300);
        addItemToList(23,pakkerKorteBeklædningsSkruerLængde,pakkerKorteBeklædningsSkruerAntal);
    }

    public void beregnAntalLangeBeklædningsSkruer () {
        pakkerLangeBeklædningsSkruerAntal = (int) Math.ceil(((beklædningsBrædderAntal /2)*8.0)/400);
        addItemToList(22,pakkerLangeBeklædningsSkruerLængde,pakkerLangeBeklædningsSkruerAntal);
    }

    private void addItemToList(int index, int length, int amount) {
        if (length == 0) {
            shedMaterialList.add(new OrderLineDTO(productDTOList.get(index).getProductId(), productDTOList.get(index).getProductDescription(),
                    length, amount,
                    productDTOList.get(index).getUnitScale(),
                    productDTOList.get(index).getUsementDescription(),
                    amount* productDTOList.get(index).getUnitPrice()));
        } else {
            shedMaterialList.add(new OrderLineDTO(productDTOList.get(index).getProductId(), productDTOList.get(index).getProductDescription(),
                    length, amount,
                    productDTOList.get(index).getUnitScale(),
                    productDTOList.get(index).getUsementDescription(),
                    amount*length/100.0* productDTOList.get(index).getUnitPrice()));
        }
    }

    public int getShedLength() {
        return shedLength;
    }

    public int getShedHeight() {
        return shedHeight;
    }

    public int getAntalStolperPåRemEkstraNårSkur() {
        return antalStolperPåRemEkstraNårSkur;
    }

    public int getAntalStolperPåSpærEkstraNårSkur() {
        return antalStolperPåSpærEkstraNårSkur;
    }

    public int getLøsholterLangsideUdenDørLængde() {
        return løsholterLangsideUdenDørLængde;
    }

    public int getLøsholterLangsideUdenDørAntal() {
        return løsholterLangsideUdenDørAntal;
    }

    public int getLøsholterLangsideMedDørLængde() {
        return løsholterLangsideMedDørLængde;
    }

    public int getLøsholterLangsideMedDørAntal() {
        return løsholterLangsideMedDørAntal;
    }

    public int getLøsholterBredsideLængde() {
        return løsholterBredsideLængde;
    }

    public int getLøsholterBredsideAntal() {
        return løsholterBredsideAntal;
    }

    public int getBeklædningsBrædderAntal() {
        return beklædningsBrædderAntal;
    }

    public int getPakkerKorteBeklædningsSkruerAntal() {
        return pakkerKorteBeklædningsSkruerAntal;
    }

    public int getPakkerLangeBeklædningsSkruerAntal() {
        return pakkerLangeBeklædningsSkruerAntal;
    }
}