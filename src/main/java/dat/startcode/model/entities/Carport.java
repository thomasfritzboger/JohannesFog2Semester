package dat.startcode.model.entities;

import java.time.LocalDate;

public class Carport {

    private int carportId;
    private int coverage;
    private int userId;
    private int carportWidth;
    private int carportLength;
    private int carportHeight;
    private String roofType;
    private Double carportPrice;
    private boolean hasShed;
    private Shed shed;
    private LocalDate created;

    public Carport (int carportId, int userId, int carportWidth, int carportLength){
        this.carportId = carportId;
        this.userId = userId;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.created = LocalDate.now();
    }

    public Carport(int carportId, int userId, int carportWidth, int carportLength, int carportHeight, boolean hasShed, Shed shed) {
        this.carportId = carportId;
        this.userId = userId;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.carportHeight = carportHeight;
        this.hasShed = hasShed;
        this.shed = shed;
    }

    public Carport(int carportId, int width, int length, int height, String roofType, Shed shed, boolean hasShed) {
        this.carportId = carportId;
        this.carportWidth = width;
        this.carportLength = length;
        this.carportHeight = height;
        this.roofType = roofType;
        this.shed = shed;
        this.hasShed = hasShed;
    }

    public Carport(int carportId, int newCoverage, double newPrice) {
        this.carportId = carportId;
        this.coverage = newCoverage;
        this.carportPrice = newPrice;
    }

    public Carport(int coverage, Double carportPrice) {
        this.coverage = coverage;
        this.carportPrice = carportPrice;
    }

    public Carport(int carportId) {
        this.carportId = carportId;
    }

    public int getCarportId() {
        return carportId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarportWidth() {
        return carportWidth;
    }

    public int getCarportLength() {
        return carportLength;
    }

    public int getCarportHeight() {
        return carportHeight;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public LocalDate getCreated() { return created; }

    public boolean isHasShed() {
        return hasShed;
    }

    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }

    public Double getCarportPrice() {
        return carportPrice;
    }

    public void setCarportPrice(Double carportPrice) {
        this.carportPrice = carportPrice;
    }

    @Override
    public String toString() {
        return "Carport{" +
                "carportId=" + carportId +
                ", coverage=" + coverage +
                ", userId=" + userId +
                ", carportWidth=" + carportWidth +
                ", carportLength=" + carportLength +
                ", carportHeight=" + carportHeight +
                ", roofType='" + roofType + '\'' +
                ", carportPrice=" + carportPrice +
                ", hasShed=" + hasShed +
                ", shed=" + shed +
                ", created=" + created +
                '}';
    }
}