package com.example.ibrahim.cowfarm.Model.ApiInterface;

import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelBuyItem;
import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelGeneric;
import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelLogin;
import com.example.ibrahim.cowfarm.Model.ResultModel.ResultModelUpgradeRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MarketApi {


    @POST("buildinginfo")
    Call<ResultModelGeneric> get_building_info(@Body Map<String, String> headers);


    @POST("upgrade")
    Call<ResultModelUpgradeRequest> upgrade_item(@Body Map<String, String> headers);


    @POST("item-sell")
    Call<ResultModelBuyItem> sell_item( @Body Map<String, String> headers);

    @POST("item-buy")
    Call<ResultModelBuyItem> buy_item(@Body Map<String, String> headers);



}
