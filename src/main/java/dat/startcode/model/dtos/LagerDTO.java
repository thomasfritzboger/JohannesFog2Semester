package dat.startcode.model.dtos;

public class LagerDTO {

    private int lagerId;
    private String lagerDescription;
    private double lagerPrice;

    public LagerDTO(int lagerId, String lagerDescription, double lagerPrice) {
        this.lagerId = lagerId;
        this.lagerDescription = lagerDescription;
        this.lagerPrice = lagerPrice;
    }

    public int getLagerId() {
        return lagerId;
    }

    public String getLagerDescription() {
        return lagerDescription;
    }

    public double getLagerPrice() {
        return lagerPrice;
    }
}
