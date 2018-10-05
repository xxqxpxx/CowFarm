package com.example.ibrahim.cowfarm.Model.ApiInterface;

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
    Call<Object> getInventory(@Path(value = "id", encoded = true) String id );


    @GET("user/{id}/resources")
    Call<Object> getResources(@Path(value = "id", encoded = true) String id );


    @GET("user/{id}")
    Call<Object> getUserInfo(@Path(value = "id", encoded = true) String id );


    @GET("users")
    Call<Object> getAllUsers();



} // Interface of LoginAPI

