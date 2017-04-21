package com.muktolab.mvp_dagger_volleysample.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skarim on 4/22/17.
 */

public class DayWiseDataEntity {
    @SerializedName("code")
    public String code;
    @SerializedName("date")
    public String date;
    @SerializedName("day")
    public String day;
    @SerializedName("high")
    public String high;
    @SerializedName("low")
    public String low;
    @SerializedName("text")
    public String text;

    /*{
        "code":"34",
            "date":"21 Apr 2017",
            "day":"Fri",
            "high":"38",
            "low":"26",
            "text":"Mostly Sunny"
    },*/

}
