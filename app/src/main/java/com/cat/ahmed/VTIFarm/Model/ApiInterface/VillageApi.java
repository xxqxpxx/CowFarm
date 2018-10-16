package com.cat.ahmed.VTIFarm.Model.ApiInterface;

import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelFiterbyResource;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VillageApi {

    @GET("resources/{resource}/{userid}")
    Call<ResultModelFiterbyResource> getUsersByResource(@Path(value = "resource", encoded = true) String id ,
                                                        @Path(value = "userid", encoded = true) String userid );


    @GET("resources")
    Call<Object> resourceLookup();

}
