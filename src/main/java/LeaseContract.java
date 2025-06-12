import java.time.LocalDate;

public class LeaseContract extends Contract{

    LeaseContract(int id, String customer_name, int dealership_id, int vehicle_id, LocalDate when, Double total_price, Double monthly_payment) {
        super(id, customer_name, dealership_id, vehicle_id, when, total_price, monthly_payment);
    }
}
