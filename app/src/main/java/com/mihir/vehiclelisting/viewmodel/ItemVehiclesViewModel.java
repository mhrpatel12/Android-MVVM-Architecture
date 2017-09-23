/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    public String getFullName() {
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

    public String getPictureProfile() {
        String getMapURL = "http://maps.googleapis.com/maps/api/staticmap?zoom=18&size=450x900&scale=2&markers=size:mid|color:red|"
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

    public void setVehicle(Vehicle people) {
        this.vehicle = people;
        notifyChange();
    }

    public void onItemClick(View view) {
    }
}
