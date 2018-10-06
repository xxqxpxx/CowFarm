package com.example.ibrahim.cowfarm.Model.ResultModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModelUpgradeRequest {

    @SerializedName("data")
    public List<String> data;
    @SerializedName("state")
    public String state;

}
