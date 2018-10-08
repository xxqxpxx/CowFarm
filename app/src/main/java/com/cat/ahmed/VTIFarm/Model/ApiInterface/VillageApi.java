package com.cat.ahmed.VTIFarm.Model.ApiInterface;

import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelFiterbyResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VillageApi {

    @GET("resources/{resource}")
    Call<ResultModelFiterbyResource> getUsersByResource(@Path(value = "resource", encoded = true) String id );


    @GET("resources")
    Call<Object> resourceLookup();

}
