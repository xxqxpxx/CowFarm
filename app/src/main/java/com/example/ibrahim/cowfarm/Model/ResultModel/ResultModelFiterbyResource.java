package com.example.ibrahim.cowfarm.Model.ResultModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModelFiterbyResource {

    @SerializedName("data")
    public List<data> data;

    @SerializedName("user_id")
    public String user_id;

    public List<com.example.ibrahim.cowfarm.Model.ResultModel.data> getData() {
        return data;
    }

    public void setData(List<com.example.ibrahim.cowfarm.Model.ResultModel.data> data) {
        this.data = data;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
