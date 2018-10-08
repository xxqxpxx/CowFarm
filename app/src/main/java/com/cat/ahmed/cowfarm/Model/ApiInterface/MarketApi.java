package com.cat.ahmed.cowfarm.Model.ApiInterface;

import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelBuyItem;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelGeneric;
import com.cat.ahmed.cowfarm.Model.ResultModel.ResultModelUpgradeRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MarketApi {


    @POST("building-info")
    Call<ResultModelGeneric> get_building_info(@Body Map<String, String> headers);


    @POST("upgrade")
    Call<ResultModelUpgradeRequest> upgrade_item(@Body Map<String, String> headers);


    @POST("item-sell")
    Call<ResultModelBuyItem> sell_item( @Body Map<String, String> headers);

    @POST("item-buy")
    Call<ResultModelBuyItem> buy_item(@Body Map<String, String> headers);



}
