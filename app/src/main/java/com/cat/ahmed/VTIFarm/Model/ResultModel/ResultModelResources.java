package com.cat.ahmed.VTIFarm.Model.ResultModel;

import com.google.gson.annotations.SerializedName;

public class ResultModelResources {

    @SerializedName("data")
    public data data;
    @SerializedName("state")
    public String state;

    public class data{
        @SerializedName("id")
        public  String id;
        @SerializedName("user_id")
        public String user_id;
        @SerializedName("water")
        public String water;
        @SerializedName("electricity")
        public String electricity;
        @SerializedName("workers")
        public String workers;
        @SerializedName("farmers")
        public String farmers;
        @SerializedName("doctors")
        public String doctors;
    }

}
