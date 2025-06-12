import java.time.LocalDate;
import java.util.Date;

abstract class Contract {
    public Integer id;
    public String customer_name; //`customer_name` varchar(255) DEFAULT NULL,
    public Integer dealership_id; //`dealership_id` int DEFAULT NULL,
    public Integer vehicle_id; //`vehicle_id` int DEFAULT NULL,
    public LocalDate when; //`when` datetime DEFAULT CURRENT_TIMESTAMP,
    public Double total_price;
    public Double monthly_payment;
    Contract(Integer id, String customer_name, Integer dealership_id, Integer vehicle_id, LocalDate when, Double total_price, Double monthly_payment) {
        this.id = id;
        this.customer_name = customer_name;
        this.dealership_id = dealership_id;
        this.vehicle_id = vehicle_id;
        this.when = when;
        this.total_price = total_price;
        this.monthly_payment = monthly_payment;
    }
}