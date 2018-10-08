package com.cat.ahmed.VTIFarm.Presenter;

import android.support.v7.app.AppCompatActivity;

import com.cat.ahmed.VTIFarm.Model.ResultModel.ResultModelBuyItem;

public interface wrapper  {
     AppCompatActivity getActivity();

     void updateUi(ResultModelBuyItem resultModelBuyItem);
}
