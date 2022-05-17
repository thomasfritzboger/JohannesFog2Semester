package dat.startcode.model.entities;

public class Produkt {
    public int productId;
    public int productDescriptionId;
    public int unitscaleId;
    public int usementDescriptionId;

    public Produkt(int productDescriptionId, int unitscaleId, int usementDescriptionId) {
        this.productDescriptionId = productDescriptionId;
        this.unitscaleId = unitscaleId;
        this.usementDescriptionId = usementDescriptionId;
    }

    public Produkt(int productId, int productDescriptionId, int unitscaleId, int usementDescriptionId) {
        this.productId = productId;
        this.productDescriptionId = productDescriptionId;
        this.unitscaleId = unitscaleId;
        this.usementDescriptionId = usementDescriptionId;
    }

}
