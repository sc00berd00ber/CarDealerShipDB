public class Vehicle { //POJO Plain Ordinary Java Object
    Integer vehicle_id;
    String vin;
    String make;
    String model;
    String color;
    Integer year;
    Double price;

    Vehicle(Integer vehicle_id, String vin, String make, String model, String color, Integer year, Double price) {
        this.vehicle_id = vehicle_id;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Vehicle{vehicle_id=%d, vin='%s', make='%s', model='%s', color='%s', year=%d, price=%.2f}",
                vehicle_id, vin, make, model, color, year, price);
    }
}