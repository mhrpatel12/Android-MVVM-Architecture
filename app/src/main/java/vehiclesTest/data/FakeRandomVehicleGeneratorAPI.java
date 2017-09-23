package vehiclesTest.data;

import com.mihir.vehiclelisting.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class FakeRandomVehicleGeneratorAPI {

    private static final String VEHICLE_ADDRESS = "Lesserstraße 170, 22049 Hamburg";
    private static final String VEHICLE_ENGINE_TYPE = "CE";
    private static final String VEHICLE_EXTERIOR = "UNACCEPTABLE";
    private static final String VEHICLE_INTERIOR = "UNACCEPTABLE";
    private static final String VEHICLE_NAME = "HH-GO8522";
    private static final String VEHICLE_VIN = "WME4513341K565439";
    private static final int VEHICLE_FUEL = 100;
    private static final double[] VEHICLE_COORDINATES = {10.07526, 53.59301, 0};

    public static List<Vehicle> getVehicleList() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            vehicles.add(getVehicle());
        }
        return vehicles;
    }

    public static Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.address = VEHICLE_ADDRESS;
        vehicle.coordinates = VEHICLE_COORDINATES;
        vehicle.engineType = VEHICLE_ENGINE_TYPE;
        vehicle.exterior = VEHICLE_EXTERIOR;
        vehicle.interior = VEHICLE_INTERIOR;
        vehicle.fuel = VEHICLE_FUEL;
        vehicle.name = VEHICLE_NAME;
        vehicle.vin = VEHICLE_VIN;
        return vehicle;
    }
}
