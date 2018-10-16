package com.cat.ahmed.VTIFarm.Model.ApiInterface;

import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelBuyItem;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelGeneric;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUpgradeInfo;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUpgradeRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MarketApi {

    @Headers("Content-Type: application/json")

    @POST("upgrade-info")
    Call<ResultModelUpgradeInfo> get_building_info(@Body Map<String, String> headers);

    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("upgrade")
    Call<ResultModelUpgradeRequest>upgrade_item(@Body Map<String, String> headers);


    @POST("item-sell")
    Call<ResultModelBuyItem> sell_item( @Body Map<String, String> headers);

    @POST("item-buy")
    Call<ResultModelBuyItem> buy_item(@Body Map<String, String> headers);



}
