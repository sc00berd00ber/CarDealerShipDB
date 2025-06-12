import java.time.LocalDate;

public class SalesContract extends Contract{

    SalesContract(int id, String customer_name, int dealership_id, int vehicle_id, LocalDate when, Double total_price, Double monthly_payment) {
        super(id, customer_name, dealership_id, vehicle_id, when, total_price, monthly_payment);
    }
}
