package com.mihir.vehiclelisting.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.mihir.vehiclelisting.R;
import com.mihir.vehiclelisting.VehicleListingApplication;
import com.mihir.vehiclelisting.data.VehicleResponse;
import com.mihir.vehiclelisting.data.VehicleService;
import com.mihir.vehiclelisting.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class VehiclesViewModel extends Observable {

    public ObservableInt vehicleProgress;
    public ObservableInt vehicleRecycler;
    public ObservableInt vehicleLabel;
    public ObservableField<String> messageLabel;

    private List<Vehicle> vehicleList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    Timer timer = new Timer();

    public VehiclesViewModel(@NonNull Context context) {

        this.context = context;
        this.vehicleList = new ArrayList<>();
        vehicleProgress = new ObservableInt(View.GONE);
        vehicleRecycler = new ObservableInt(View.GONE);
        vehicleLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_vehicle));

        initializeViews();
    }

    public void initializeViews() {
        vehicleLabel.set(View.GONE);
        vehicleRecycler.set(View.GONE);
        vehicleProgress.set(View.VISIBLE);
        fetchVehiclesList();
        timer.schedule(new RefreshVehicleList(), 0, 30000);
    }

    class RefreshVehicleList extends TimerTask {
        public void run() {
            vehicleLabel.set(View.GONE);
            vehicleRecycler.set(View.GONE);
            vehicleProgress.set(View.VISIBLE);
            fetchVehiclesList();
        }
    }

    private void fetchVehiclesList() {

        VehicleListingApplication vehicleListingApplication = VehicleListingApplication.create(context);
        VehicleService vehicleService = vehicleListingApplication.getVehicleService();

        Disposable disposable = vehicleService.fetchVehicles("vehicles.json")
                .subscribeOn(vehicleListingApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VehicleResponse>() {
                    @Override
                    public void accept(VehicleResponse vehicleResponse) throws Exception {
                        changeVehicleDataSet(vehicleResponse.getVehicleList());
                        vehicleProgress.set(View.GONE);
                        vehicleLabel.set(View.GONE);
                        vehicleRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_vehicle));
                        vehicleProgress.set(View.GONE);
                        vehicleLabel.set(View.VISIBLE);
                        vehicleRecycler.set(View.GONE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void changeVehicleDataSet(List<Vehicle> vehicles) {
        vehicleList.clear();
        vehicleList.addAll(vehicles);
        setChanged();
        notifyObservers();
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

/*    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }*/
}
