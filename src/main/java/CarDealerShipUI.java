public class CarDealerShipUI extends UI{

    public Vehicle makeCustomVehicle() {
        return new Vehicle(
                -1,
                getString("VIN"),
                getString("MAKE"),
                getString("MODEL"),
                getString("COLOR"),
                getInteger("YEAR"),
                getDouble("PRICE")
        );
    }
}
