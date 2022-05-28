package dat.startcode.model.dtos;

public class StockDTO {

    private int stockId;
    private String stockDescription;
    private double unitPrice;

    public StockDTO(int stockId, String stockDescription, double unitPrice) {
        this.stockId = stockId;
        this.stockDescription = stockDescription;
        this.unitPrice = unitPrice;
    }

    public int getStockId() {
        return stockId;
    }

    public String getStockDescription() {
        return stockDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
