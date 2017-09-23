package com.mihir.vehiclelisting.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface VehicleService {

  @GET Observable<VehicleResponse> fetchVehicles(@Url String url);

}
