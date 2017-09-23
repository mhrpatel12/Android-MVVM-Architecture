package com.mihir.vehiclelisting.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mihir.vehiclelisting.model.Vehicle;


public class ItemVehiclesViewModel extends BaseObservable {

    private Vehicle vehicle;
    private Context context;

    public ItemVehiclesViewModel(Vehicle vehicle, Context context) {
        this.vehicle = vehicle;
        this.context = context;
    }

    public String getName() {
        return vehicle.name;
    }

    public String getInterior() {
        return vehicle.interior;
    }

    public String getExterior() {
        return vehicle.exterior;
    }

    public String getAddress() {
        return vehicle.address;
    }

    public String getEngineType() {
        return vehicle.engineType;
    }

    public String getFuel() {
        return vehicle.fuel + "";
    }

    public String getPictureLocation() {
        String getMapURL = "http://maps.googleapis.com/maps/api/staticmap?zoom=18&size=450x450&scale=2&markers=size:mid|color:red|"
                + vehicle.coordinates[1]
                + ","
                + vehicle.coordinates[0]
                + "&sensor=false";
        return getMapURL;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        notifyChange();
    }

    public void onItemClick(View view) {
    }
}
