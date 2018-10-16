package com.cat.ahmed.VTIFarm.Presenter;

import android.support.v7.app.AppCompatActivity;

import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelBuyItem;
import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelUpgradeRequest;

public interface wrapper  {
     AppCompatActivity getActivity();

     void updateUiCounter(ResultModelBuyItem resultModelBuyItem);

    void updateUiBuilding(ResultModelUpgradeRequest body);
}
