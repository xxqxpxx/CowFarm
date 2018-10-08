package com.cat.ahmed.VTIFarm.Model.ResultModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModelUpgradeRequest {

    @SerializedName("data")
    public List<String> data;
    @SerializedName("state")
    public String state;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
