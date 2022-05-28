package dat.startcode.model.dtos;

public class ProductDTO {

    private int productId;
    private String productDescription;
    private double unitPrice;
    private Integer length;
    private Integer width;
    private Integer height;
    private Double diameter;
    private String unitScale;
    private String usementDescription;

    public ProductDTO(int productId, String productDescription, double unitPrice, int length, int width, int height, double diameter, String usementDescription, String unitScale) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.length = length;
        this.width = width;
        this.height = height;
        this.diameter = diameter;
        this.unitScale = unitScale;
        this.usementDescription = usementDescription;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Double getDiameter() {
        return diameter;
    }

    public String getUnitScale() {
        return unitScale;
    }

    public String getUsementDescription() {
        return usementDescription;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productDescription='" + productDescription + '\'' +
                ", unitPrice=" + unitPrice +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", diameter=" + diameter +
                ", unitScale='" + unitScale + '\'' +
                ", usementDescription='" + usementDescription + '\'' +
                '}';
    }
}
