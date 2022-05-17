package dat.startcode.model.entities;

public class Produkt {

    public int productId;
    public String productDescription;
    public String unitscale;
    public String usementDescription;
    public int amount;
    double unitPrice;
    double totalPrice;

    public Produkt(int productId, String productDescription, String unitscale, String usementDescription, double unitPrice) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.unitscale = unitscale;
        this.usementDescription = usementDescription;
        this.unitPrice = unitPrice;
    }
}
