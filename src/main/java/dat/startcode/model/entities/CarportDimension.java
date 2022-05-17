package dat.startcode.model.entities;

public class CarportDimension {
    private int dimensionId;
    private int length;
    private int width;
    private int height;

    public CarportDimension(int dimensionId, int length, int width, int height) {
        this.dimensionId = dimensionId;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public CarportDimension(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "CarportDimension{" +
                "dimensionId=" + dimensionId +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
