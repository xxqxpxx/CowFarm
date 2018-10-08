package com.cat.ahmed.VTIFarm.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmed on 9/30/2018.
 */

public class ApiConnection
{
    private Retrofit retrofit;
    Gson gson;

    public  ApiConnection () {} // defaul constructor

    public   Retrofit connectWith()
    {
        gson = new GsonBuilder().serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://farmgame.digitalcatsite.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    } // function of connectWith
} // class of ApiConnection
