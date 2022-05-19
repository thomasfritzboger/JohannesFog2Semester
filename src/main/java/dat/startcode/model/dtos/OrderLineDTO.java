package dat.startcode.model.dtos;

import java.time.LocalDate;

public class OrderLineDTO {

    public String productDescription;
    private int length;
    private int amount;
    private String unitScale;
    private String usementDescription;

    public double totalPrice;

    public OrderLineDTO(String productDescription, int length, int amount, String unitScale, String usementDescription, double totalPrice) {
        this.productDescription = productDescription;
        this.length = length;
        this.amount = amount;
        this.unitScale = unitScale;
        this.usementDescription = usementDescription;
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "OrderLineDTO{" +
                "productDescription='" + productDescription + '\'' +
                ", length=" + length +
                ", amount=" + amount +
                ", unitScale='" + unitScale + '\'' +
                ", usementDescription='" + usementDescription + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
