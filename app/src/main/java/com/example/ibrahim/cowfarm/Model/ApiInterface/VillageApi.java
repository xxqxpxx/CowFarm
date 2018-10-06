package com.example.ibrahim.cowfarm.Model.ApiInterface;

import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelFiterbyResource;
import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelLogin;
import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelSignUp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VillageApi {

    @GET("resources/{resource}")
    Call<ResultModelFiterbyResource> getUsersByResource(@Path(value = "resource", encoded = true) String id );


    @GET("resources")
    Call<Object> resourceLookup();

}
