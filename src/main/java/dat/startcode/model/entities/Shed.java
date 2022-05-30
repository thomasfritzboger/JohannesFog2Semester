package dat.startcode.model.entities;

public class Shed {
    private int shedId;
    private int width;
    private int length;
    private String placement;

    public Shed(int shedId, int width, int length, String placement) {
        this.shedId = shedId;
        this.width = width;
        this.length = length;
        this.placement = placement;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
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
        return "Shed{" +
                "shedId=" + shedId +
                ", width=" + width +
                ", length=" + length +
                ", placement='" + placement + '\'' +
                '}';
    }
}
