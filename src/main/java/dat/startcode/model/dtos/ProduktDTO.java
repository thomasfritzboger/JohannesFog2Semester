package dat.startcode.model.dtos;

public class ProduktDTO {

    private int produktId;
    private String produktDescription;
    private double unitPrice;
    private Integer lenght;
    private Integer width;
    private Integer height;
    private Double diamenter;
    private String unitScale;
    private String usementDescription;

    public ProduktDTO(int produktId, String produktDescription, double unitPrice, int lenght, int width, int height, double diamenter, String usementDescription,String unitScale) {
        this.produktId = produktId;
        this.produktDescription = produktDescription;
        this.unitPrice = unitPrice;
        this.lenght = lenght;
        this.width = width;
        this.height = height;
        this.diamenter = diamenter;
        this.unitScale = unitScale;
        this.usementDescription = usementDescription;
    }

    public int getProduktId() {
        return produktId;
    }

    public String getProduktDescription() {
        return produktDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Integer getLenght() {
        return lenght;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Double getDiamenter() {
        return diamenter;
    }

    public String getUnitScale() {
        return unitScale;
    }

    public String getUsementDescription() {
        return usementDescription;
    }

    @Override
    public String toString() {
        return "ProduktDTO{" +
                "produktId=" + produktId +
                ", produktDescription='" + produktDescription + '\'' +
                ", unitPrice=" + unitPrice +
                ", lenght=" + lenght +
                ", width=" + width +
                ", height=" + height +
                ", diamenter=" + diamenter +
                ", unitScale='" + unitScale + '\'' +
                ", usementDescription='" + usementDescription + '\'' +
                '}';
    }
}
