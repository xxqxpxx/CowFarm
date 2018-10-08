package com.cat.ahmed.cowfarm.Model.ResultModel;

import com.google.gson.annotations.SerializedName;

public class ResultModelInventory {

    @SerializedName("data")
    public data data;
    @SerializedName("state")
    public String state;

    public class data{
        @SerializedName("id")
        public  String id;
        @SerializedName("user_id")
        public String user_id;
        @SerializedName("drug")
        public String drug;
        @SerializedName("food")
        public String food;
        @SerializedName("animals")
        public String animals;
        @SerializedName("gold")
        public String gold;
    }
}
