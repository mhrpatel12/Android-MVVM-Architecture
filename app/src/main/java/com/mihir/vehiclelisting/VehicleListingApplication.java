package com.mihir.vehiclelisting;

import android.app.Application;
import android.content.Context;

import com.mihir.vehiclelisting.data.VehicleBase;
import com.mihir.vehiclelisting.data.VehicleService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class VehicleListingApplication extends Application {

    private VehicleService vehicleService;
    private Scheduler scheduler;

    private static VehicleListingApplication get(Context context) {
        return (VehicleListingApplication) context.getApplicationContext();
    }

    public static VehicleListingApplication create(Context context) {
        return VehicleListingApplication.get(context);
    }

    public VehicleService getVehicleService() {
        if (vehicleService == null) {
            vehicleService = VehicleBase.create();
        }

        return vehicleService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
