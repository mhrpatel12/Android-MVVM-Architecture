package com.mihir.vehiclelisting.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mihir.vehiclelisting.R;
import com.mihir.vehiclelisting.databinding.ActivityHomeBinding;
import com.mihir.vehiclelisting.viewmodel.VehiclesViewModel;

import java.util.Observable;
import java.util.Observer;

public class HomeActivity extends AppCompatActivity implements Observer {

    private ActivityHomeBinding activityHomeBinding;
    private VehiclesViewModel vehiclesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(activityHomeBinding.toolbar);
        setupListPeopleView(activityHomeBinding.listVehicles);
        setupObserver(vehiclesViewModel);
    }

    private void initDataBinding() {
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        vehiclesViewModel = new VehiclesViewModel(this);
        activityHomeBinding.setMainViewModel(vehiclesViewModel);
    }

    private void setupListPeopleView(RecyclerView listVehicles) {
        VehicleAdapter adapter = new VehicleAdapter();
        listVehicles.setAdapter(adapter);
        listVehicles.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //vehiclesViewModel.reset();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof VehiclesViewModel) {
            VehicleAdapter vehicleAdapter = (VehicleAdapter) activityHomeBinding.listVehicles.getAdapter();
            VehiclesViewModel vehiclesViewModel = (VehiclesViewModel) observable;
            vehicleAdapter.setVehicleList(vehiclesViewModel.getVehicleList());
        }
    }
}
