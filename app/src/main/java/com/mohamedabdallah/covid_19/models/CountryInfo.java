package com.mohamedabdallah.covid_19.models;

import com.google.gson.annotations.SerializedName;

public class CountryInfo {
    private String flag;
    private String _id;
    private String lat;
    @SerializedName("long")
    private String longt;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return longt ;
    }

    public void setLong(String longt) {
        this.longt = longt;
    }
}