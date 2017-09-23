package com.mihir.vehiclelisting.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle implements Serializable {

    @SerializedName("address")
    public String address;

    @SerializedName("coordinates")
    public double[] coordinates;

    @SerializedName("engineType")
    public String engineType;

    @SerializedName("exterior")
    public String exterior;

    @SerializedName("fuel")
    public int fuel;

    @SerializedName("interior")
    public String interior;

    @SerializedName("name")
    public String name;

    @SerializedName("vin")
    public String vin;
}
