package com.mohamedabdallah.covid_19.requests;

import com.mohamedabdallah.covid_19.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("countries/")
    Call<List<Country>> getAllCountries();


    @GET("countries/egypt/")
    Call<Country> getHomeInfo();

}
