package com.example.ibrahim.cowfarm.Model.ApiInterface;

import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelSignUp;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ibrahim on 9/30/2018.
 */

public interface SignUpApi
{
    @FormUrlEncoded
    @POST("register")
    retrofit2.Call<ResultModelSignUp> signUp(@Field("name") String name,
                                             @Field("username") String username,
                                             @Field("email") String email,
                                             @Field("password") String password,
                                             @Field("password_confirmation") String password_confirmation
    );  // SignUp

} // Interface of SignUpApi
