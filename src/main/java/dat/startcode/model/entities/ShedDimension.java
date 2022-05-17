package dat.startcode.model.entities;

public class ShedDimension {
    private int shedId;
    private int width;
    private int length;
    private String placement;

    public ShedDimension(int shedId, int width, int length, String placement) {
        this.shedId = shedId;
        this.width = width;
        this.length = length;
        this.placement = placement;
    }

    public ShedDimension(int width, int length, String placement) {
        this.width = width;
        this.length = length;
        this.placement = placement;
    }

    public int getShedId() {
        return shedId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    @Override
    public String toString() {
        return "ShedDimension{" +
                "shedId=" + shedId +
                ", width=" + width +
                ", length=" + length +
                ", placement='" + placement + '\'' +
                '}';
    }
}
