package dat.startcode.model.entities;

import java.time.LocalDate;

public class Carport {

    private int carportId;
    private int userId;
    private int carportWidth;
    private int carportLength;
    private boolean hasShed;
    private int shedWidth;
    private int shedLength;

//    Calculator variables



    public Carport (int carportId, int userId, int carportWidth, int carportLength){
        this.carportId = carportId;
        this.userId = userId;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
    }
    public Carport(int carportId, int userId, int carportWidth, int carportLength, boolean hasShed, int shedWidth, int shedLength) {
        this.carportId = carportId;
        this.userId = userId;
        this.carportWidth = carportWidth;
        this.carportLength = carportLength;
        this.hasShed = hasShed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
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

    public boolean isHasShed() {
        return hasShed;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }
}
      