package com.mohamedabdallah.covid_19.requests;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mohamedabdallah.covid_19.models.Country;
import com.mohamedabdallah.covid_19.requests.ApiClient;
import com.mohamedabdallah.covid_19.requests.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends AndroidViewModel {
    MutableLiveData<List<Country>> countryList;
    MutableLiveData<Country> home;

    public CountryViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Country>> getCountries()
    {
        if (countryList==null)
        {
            countryList=new MutableLiveData<List<Country>>();
            loadCountryDtat();
        }
        return countryList;
    }
    public LiveData<Country> gethomeInfo()
    {
        if (countryList==null)
        {
            home=new MutableLiveData<Country>();
            loadHomeInfo();
        }
        return home;
    }
    public void loadHomeInfo() {
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Country> listCall=apiInterface.getHomeInfo();
        listCall.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                if(response.isSuccessful())
                {
                    home.setValue(response.body());
                }
                else
                    Toast.makeText(getApplication(), "response is not succsesful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });
    }


    public void loadCountryDtat() {
        ApiInterface apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Country>> listCall=apiInterface.getAllCountries();

        listCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                if(response.isSuccessful())
                {
                    countryList.setValue(response.body());
                }
                else
                    Toast.makeText(getApplication(), "response is not succssful", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Toast.makeText(getApplication(), "Failure To load data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
