package vehiclesTest;

import android.databinding.Observable;

import com.mihir.vehiclelisting.BuildConfig;
import com.mihir.vehiclelisting.VehicleListingApplication;
import com.mihir.vehiclelisting.model.Vehicle;
import com.mihir.vehiclelisting.viewmodel.ItemVehiclesViewModel;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ItemVehicleViewModelTest {

    private static final String VEHICLE_ADDRESS = "Lesserstraße 170, 22049 Hamburg";
    private static final String VEHICLE_ENGINE_TYPE = "CE";
    private static final String VEHICLE_EXTERIOR = "UNACCEPTABLE";
    private static final int VEHICLE_FUEL = 100;

    private VehicleListingApplication vehicleListingApplication;

    @Before
    public void setUpItemVehicleModelTest() {
        vehicleListingApplication = (VehicleListingApplication) RuntimeEnvironment.application;
    }

    @Test
    public void shouldGetVehicleFuel() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.fuel = VEHICLE_FUEL;
        ItemVehiclesViewModel itemVehicleViewModel = new ItemVehiclesViewModel(vehicle, vehicleListingApplication);
        assertEquals(vehicle.fuel + "", itemVehicleViewModel.getFuel());
    }

    @Test
    public void shouldGetVehicleAddress() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.address = VEHICLE_ADDRESS;
        ItemVehiclesViewModel itemVehicleViewModel = new ItemVehiclesViewModel(vehicle, vehicleListingApplication);
        assertEquals(vehicle.address, itemVehicleViewModel.getAddress());
    }

    @Ignore
    public void shouldGetVehicleEngineType() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.engineType = VEHICLE_ENGINE_TYPE;
        ItemVehiclesViewModel itemVehicleViewModel = new ItemVehiclesViewModel(vehicle, vehicleListingApplication);
        assertEquals(vehicle.engineType, itemVehicleViewModel.getEngineType());
    }

    @Test
    public void shouldGetVehicleExterior() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.exterior = VEHICLE_EXTERIOR;
        ItemVehiclesViewModel itemVehicleViewModel = new ItemVehiclesViewModel(vehicle, vehicleListingApplication);
        assertEquals(vehicle.exterior, itemVehicleViewModel.getExterior());
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        ItemVehiclesViewModel itemVehicleViewModel = new ItemVehiclesViewModel(vehicle, vehicleListingApplication);
        Observable.OnPropertyChangedCallback mockCallback =
                mock(Observable.OnPropertyChangedCallback.class);
        itemVehicleViewModel.addOnPropertyChangedCallback(mockCallback);
        itemVehicleViewModel.setVehicle(vehicle);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
