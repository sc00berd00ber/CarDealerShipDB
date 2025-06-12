
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static CarDealerShipUI ui;
    private static CRUD db;
    public static void main(String[] args) {

        db = new CRUD("localhost", "3306", "CarDealership", "root", "Shoreline@_4321");
        var data = new CarDealershipData(db);

        var ui = new CarDealerShipUI(data);
        ui.mainMenu();

//        data.removeVehicle(15);

//        var vehicle = ui.makeCustomVehicle();
//        data.saveVehicle(vehicle);

//        Contract c = ui.makeContract();
//        data.saveContract(c);

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
