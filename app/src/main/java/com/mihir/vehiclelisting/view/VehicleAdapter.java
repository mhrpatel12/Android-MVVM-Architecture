package com.mihir.vehiclelisting.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.mihir.vehiclelisting.R;
import com.mihir.vehiclelisting.databinding.ItemVehicleBinding;
import com.mihir.vehiclelisting.model.Vehicle;
import com.mihir.vehiclelisting.viewmodel.ItemVehiclesViewModel;

import java.util.Collections;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleAdapterViewHolder> {

    private Context mContext;
    private List<Vehicle> vehicleList;

    public VehicleAdapter(Context mContext) {
        this.mContext = mContext;
        this.vehicleList = Collections.emptyList();
    }

    @Override
    public VehicleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVehicleBinding itemVehicleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_vehicle, parent, false);
        return new VehicleAdapterViewHolder(itemVehicleBinding);
    }

    @Override
    public void onBindViewHolder(VehicleAdapterViewHolder holder, int position) {
        final VehicleAdapterViewHolder vehicleAdapterViewHolder = (VehicleAdapterViewHolder) holder;

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_recycler_item_show);
        vehicleAdapterViewHolder.itemView.startAnimation(animation);

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
        ItemVehicleBinding itemVehicleBinding;

        public VehicleAdapterViewHolder(ItemVehicleBinding itemVehicleBinding) {
            super(itemVehicleBinding.itemVehicle);
            this.itemVehicleBinding = itemVehicleBinding;
        }

        void bindVehicle(Vehicle vehicle) {
            if (itemVehicleBinding.getVehicleViewModel() == null) {
                itemVehicleBinding.setVehicleViewModel(
                        new ItemVehiclesViewModel(vehicle, itemView.getContext()));
            } else {
                itemVehicleBinding.getVehicleViewModel().setVehicle(vehicle);
            }
        }
    }
}
