package com.cat.ahmed.VTIFarm.Model.ApiInterface;

import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelInventory;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelLogin;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelResources;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelSignUp;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelTopPlayer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @Headers("Content-Type: application/json")
    @POST("login")
    retrofit2.Call<ResultModelLogin> login(@Body Map<String, String> headers
    );  // Login

    @FormUrlEncoded
    @POST("register")
    retrofit2.Call<ResultModelSignUp> signUp(@Field("name") String name,
                                             @Field("username") String username,
                                             @Field("email") String email,
                                             @Field("password") String password,
                                             @Field("password_confirmation") String password_confirmation
    );  // SignUp


    @GET("user/{id}/inventory")
    Call<ResultModelInventory> getInventory(@Path(value = "id", encoded = true) String id );


    @GET("user/{id}/resources")
    Call<ResultModelResources> getResources(@Path(value = "id", encoded = true) String id );


    @GET("user/{id}")
    Call<ResultModelLogin> getUserInfo(@Path(value = "id", encoded = true) String id );


    @GET("users")
    Call<Object> getAllUsers();

    @GET("top-users")
    Call<ResultModelTopPlayer> getTopPLayers();


} // Interface of LoginAPI

