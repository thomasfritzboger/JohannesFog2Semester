package dat.startcode.model.entities;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Request {
    private int carportId;
    private int coverageId;
    private int userId;
    private int width;
    private int length;
    private int height;
    private int shedId;
    private boolean hasShed;
    private Shed shed;
    private boolean isConfirmed;
    private LocalDate created;
    private double carportPrice;


    public Request(int coverageId, int userId, int width, int length, int height, int shedId, boolean hasShed, boolean isConfirmed,double carportPrice) {
        this.coverageId = coverageId;
        this.userId = userId;
        this.width = width;
        this.length = length;
        this.height = height;
        this.shedId = shedId;
        this.hasShed = hasShed;
        this.isConfirmed = isConfirmed;
        this.carportPrice = carportPrice;
    }

    public Request(int carportId, int width, int length, int height, int shed_id, boolean hasShed, Shed shed, boolean isConfirmed, Timestamp created) {
        this.carportId = carportId;
        this.width = width;
        this.length = length;
        this.height = height;
        this.shedId = shed_id;
        this.hasShed = hasShed;
        this.shed = shed;
        this.isConfirmed = isConfirmed;
        this.created = created.toLocalDateTime().toLocalDate();
    }

    public double getCarportPrice() {
        return carportPrice;
    }

    public void setCarportPrice(double carportPrice) {
        this.carportPrice = carportPrice;
    }

    public int getCarportId() {
        return carportId;
    }

    public int getCoverageId() {
        return coverageId;
    }

    public void setCoverageId(int coverageId) {
        this.coverageId = coverageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
    }

    public boolean getHasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public Shed getShed() {
        return shed;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Request{" +
                "carportId=" + carportId +
                ", coverageId=" + coverageId +
                ", userId=" + userId +
                ", width=" + width +
                ", length=" + length +
                ", height=" + height +
                ", shedId=" + shedId +
                ", hasShed=" + hasShed +
                ", shed=" + shed +
                ", isConfirmed=" + isConfirmed +
                ", created=" + created +
                ", carportPrice=" + carportPrice +
                '}';
    }
}
