package dat.startcode.model.entities;

import java.time.LocalDate;

public class Carport {

    private int carportId;
    private int userId;
    private int carportWidth;
    private int carportLength;
    private int carportHeight;
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

    public Carport(int carportId, int width, int length, int height, Shed shed, boolean hasShed) {
        this.carportId = carportId;
        this.carportWidth = width;
        this.carportLength = length;
        this.carportHeight = height;
        this.shed = shed;
        this.hasShed = hasShed;
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
}
      