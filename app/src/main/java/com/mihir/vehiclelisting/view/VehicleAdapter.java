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

package com.mihir.vehiclelisting.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mihir.vehiclelisting.R;
import com.mihir.vehiclelisting.databinding.ItemVehicleBinding;
import com.mihir.vehiclelisting.model.Vehicle;
import com.mihir.vehiclelisting.viewmodel.ItemVehiclesViewModel;

import java.util.Collections;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleAdapterViewHolder> {

    private List<Vehicle> vehicleList;

    public VehicleAdapter() {
        this.vehicleList = Collections.emptyList();
    }

    @Override
    public VehicleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVehicleBinding itemVehicleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_vehicle,
                        parent, false);
        return new VehicleAdapterViewHolder(itemVehicleBinding);
    }

    @Override
    public void onBindViewHolder(VehicleAdapterViewHolder holder, int position) {
        holder.bindVehicle(vehicleList.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
        notifyDataSetChanged();
    }

    public static class VehicleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemVehicleBinding mItemPeopleBinding;

        public VehicleAdapterViewHolder(ItemVehicleBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }

        void bindVehicle(Vehicle people) {
            if (mItemPeopleBinding.getVehicleViewModel() == null) {
                mItemPeopleBinding.setVehicleViewModel(
                        new ItemVehiclesViewModel(people, itemView.getContext()));
            } else {
                mItemPeopleBinding.getVehicleViewModel().setVehicle(people);
            }
        }
    }
}
