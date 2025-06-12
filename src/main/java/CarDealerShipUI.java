
import java.util.ArrayList;

public class CarDealerShipUI extends UI {
    CarDealershipData data;
    CarDealerShipUI(CarDealershipData data) {
        super();
        this.data = data;
    }

    public void mainMenu() {
        var options = new ArrayList<MenuOption>();
        options.add(new MenuOption("0","Exit App", this::exitMenu));
        options.add(new MenuOption("1","Add Vehicle", this::addVehicle));
        options.add(new MenuOption("2","Show All Vehicles", this::showAllVehicles));
        options.add(new MenuOption("9","Admin Menu", this::adminMenu));
        showMenu(options, "Main Menu");
    }

    public void adminMenu() {
        var options = new ArrayList<MenuOption>();
        options.add(new MenuOption("0","Exit Admin Menu", this::exitMenu));
        options.add(new MenuOption("1","Show Statistics", this::showStats));
        showMenu(options, "Admin Menu");
    }

    public void exitMenu() {
        System.out.println("Exiting Menu");
    }
    public void showStats() {
        System.out.println("STATISTICS");
    }
    public void addVehicle() {
        var v = makeCustomVehicle();
    }
    public void showAllVehicles() {
        System.out.println("ALL VEHICLES");
        data.getVehicles().forEach(System.out::println);
    }
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

    public Contract makeContract() {
        String contractType = getString("Contract Type (Sale/Lease)");
        if(contractType.equals("Sale")) {
            return new SalesContract(
                    -1,
                    getString("Customer Name"),
                    getInteger("Dealership ID"),
                    getInteger("Vehicle ID"),
                    getDate("Date"),
                    getDouble("Total Price"),
                    getDouble("Monthly Payment")
            );
        } else if (contractType.equals("Lease")) {
            return new LeaseContract(
                    -1,
                    getString("Customer Name"),
                    getInteger("Dealership ID"),
                    getInteger("Vehicle ID"),
                    getDate("Date"),
                    getDouble("Total Price"),
                    getDouble("Monthly Payment")
            );
        }else{
            System.out.println("ERROR: Invalid Contract Type");
            return null;
        }
    }

}
