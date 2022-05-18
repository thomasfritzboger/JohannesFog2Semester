package dat.startcode.model.dtos;

public class RequestDTO {

    private int carportId;
    private int userId;
    private String created;
    private int coverage;
    private double price;

    public RequestDTO(int carportId, int userId, String created, int coverage, double price) {
        this.carportId = carportId;
        this.userId = userId;
        this.created = created;
        this.coverage = coverage;
        this.price = price;
    }

    public int getCarportId() {
        return carportId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCreated() {
        return created;
    }

    public int getCoverage() {
        return coverage;
    }

    public double getPrice() {
        return price;
    }
}
