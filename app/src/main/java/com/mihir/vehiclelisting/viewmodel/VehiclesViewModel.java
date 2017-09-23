/**
 * Copyright 2016 Erik Jhordan Rey. <p/> Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at <p/> http://www.apache.org/licenses/LICENSE-2.0 <p/> Unless required by
 * applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

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

    public ObservableInt peopleProgress;
    public ObservableInt peopleRecycler;
    public ObservableInt peopleLabel;
    public ObservableField<String> messageLabel;

    private List<Vehicle> vehicleList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    Timer timer = new Timer();

    public VehiclesViewModel(@NonNull Context context) {

        this.context = context;
        this.vehicleList = new ArrayList<>();
        peopleProgress = new ObservableInt(View.GONE);
        peopleRecycler = new ObservableInt(View.GONE);
        peopleLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));

        initializeViews();
    }

    public void initializeViews() {
        peopleLabel.set(View.GONE);
        peopleRecycler.set(View.GONE);
        peopleProgress.set(View.VISIBLE);
        fetchVehiclesList();
        timer.schedule(new RefreshVehicleList(), 0, 30000);
    }

    class RefreshVehicleList extends TimerTask {
        public void run() {
            peopleLabel.set(View.GONE);
            peopleRecycler.set(View.GONE);
            peopleProgress.set(View.VISIBLE);
            fetchVehiclesList();
        }
    }

    private void fetchVehiclesList() {

        VehicleListingApplication vehicleListingApplication = VehicleListingApplication.create(context);
        VehicleService peopleService = vehicleListingApplication.getVehicleService();

        Disposable disposable = peopleService.fetchVehicles("vehicles.json")
                .subscribeOn(vehicleListingApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VehicleResponse>() {
                    @Override
                    public void accept(VehicleResponse peopleResponse) throws Exception {
                        changeVehicleDataSet(peopleResponse.getVehicleList());
                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.GONE);
                        peopleRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_people));
                        peopleProgress.set(View.GONE);
                        peopleLabel.set(View.VISIBLE);
                        peopleRecycler.set(View.GONE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void changeVehicleDataSet(List<Vehicle> peoples) {
        vehicleList.clear();
        vehicleList.addAll(peoples);
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
