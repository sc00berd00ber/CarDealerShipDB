
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDealershipData {
    CRUD db;

    CarDealershipData(CRUD db) {
        this.db = db;
    }

    void removeVehicle(Integer id){
        db.delete("vehicles", id, "VEHICLE_ID");
    }

    void saveVehicle(Vehicle vehicle){
        db.create("Vehicles",
                new String[]{
                        "VIN",
                        "MAKE",
                        "MODEL",
                        "COLOR",
                        "YEAR",
                        "Price",
                },
                new String[]{
                        vehicle.vin,
                        vehicle.make,
                        vehicle.model,
                        vehicle.color,
                        vehicle.year.toString(),
                        vehicle.price.toString(),
                }

        );
}

    Vehicle makeVehicle(ResultSet recordSet) {
        Vehicle vehicle = null;
        try {
            vehicle = new Vehicle(
                    recordSet.getInt("VEHICLE_ID"),
                    recordSet.getString("VIN"),
                    recordSet.getString("MAKE"),
                    recordSet.getString("MODEL"),
                    recordSet.getString("COLOR"),
                    recordSet.getInt("YEAR"),
                    recordSet.getDouble("PRICE")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicle;
    }

    ArrayList<Vehicle> getVehicles() {
        return getVehicles("1");
    }

    ArrayList<Vehicle> getVehicles(String criteria) {
        ArrayList<Vehicle> list = new ArrayList<>();
        boolean hasRow = false;
        try {
            try (var recordSet = db.read(
                    String.format("SELECT * FROM Vehicles WHERE %s", criteria)
            )) {
                hasRow = recordSet.next();
                while (hasRow) {
                    list.add(makeVehicle(recordSet));
                    hasRow = recordSet.next();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    ArrayList<Vehicle> getVehiclesByPriceRange(Double min, Double max) {
        return getVehicles(String.format("PRICE >= %f AND PRICE <= %f", min, max));
    }
    ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return getVehicles(String.format("MAKE LIKE '%%%s%%' AND MODEL LIKE '%%%s%%'", make, model));
    }
    /*
1. By price range
2. By make/model
3. By year range
4. By color
5. By mileage range
6. By type
     */
}
