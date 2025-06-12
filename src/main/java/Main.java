import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static CarDealerShipUI ui = new CarDealerShipUI();
    private static CRUD db;

    public static void main(String[] args) {
        db = new CRUD("localhost", "3306", "CarDealership", "root", "Shoreline@_4321");
        var data = new CarDealershipData(db);
        data.removeVehicle(13);

        var vehicle = ui.makeCustomVehicle();
        data.saveVehicle(vehicle);


//        Vehicle v = new Vehicle(
//                -1,
//                "ABC123",
//                "Acura",
//                "MDX",
//                "Silver",
//                2005,
//                12345.67
//        );
//        data.saveVehicle(v);
//        var vehicles = data.getVehiclesByMakeModel(
//                ui.getString("MAKE"),
//                ui.getString("MODEL")
//        );
//        var vehicles = data.getVehiclesByPriceRange(
//                ui.getDouble("MINIMUM PRICE"),
//                ui.getDouble("MAXIMUM PRICE")
//        );
//        vehicles.forEach(System.out::println);
    }
}
