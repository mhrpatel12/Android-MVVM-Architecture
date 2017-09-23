
package com.mihir.vehiclelisting.data;

import com.google.gson.annotations.SerializedName;
import com.mihir.vehiclelisting.model.Vehicle;

import java.util.List;

public class VehicleResponse {

    @SerializedName("placemarks")
    private List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
}
