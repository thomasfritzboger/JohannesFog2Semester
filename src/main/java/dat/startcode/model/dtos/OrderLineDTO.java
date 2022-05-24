package dat.startcode.model.dtos;

public class OrderLineDTO {

    public int productId;
    public String productDescription;
    private int length;
    private int amount;
    private String unitScale;
    private String usementDescription;

    public double totalLinePrice;

    public OrderLineDTO(int productId, String productDescription, int length, int amount, String unitScale, String usementDescription, double totalLinePrice) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.length = length;
        this.amount = amount;
        this.unitScale = unitScale;
        this.usementDescription = usementDescription;
        this.totalLinePrice = totalLinePrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getLength() {
        return length;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnitScale() {
        return unitScale;
    }

    public String getUsementDescription() {
        return usementDescription;
    }

    public double getTotalLinePrice() {
        return totalLinePrice;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "OrderLineDTO{" +
                "p='" + productDescription + '\'' +
                ", l=" + length +
                ", amount=" + amount +
                ", unitScale='" + unitScale + '\'' +
                ", use='" + usementDescription + '\'' +
                ", total=" + totalLinePrice +
                '}';
    }
}
