package com.mohamedabdallah.covid_19.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.mohamedabdallah.covid_19.models.Country;
import com.mohamedabdallah.covid_19.requests.CountryViewModel;
import com.mohamedabdallah.covid_19.R;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {


    private ImageView flag;
    private TextView cases;
    private TextView deaths;
    private TextView todayCases;
    private TextView todayDeaths;
    private TextView critical;
    private TextView recovered;
    private TextView active;
    private TextView casesPerOneMillion;
    private TextView deathsPerOneMillion;
    private TextView countryName;


    private CountryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.activity_detail, container, false);

        flag=view.findViewById(R.id.flag);
        cases=view.findViewById(R.id.cases);
        deaths=view.findViewById(R.id.deaths);
        todayCases=view.findViewById(R.id.todayCases);
        todayDeaths=view.findViewById(R.id.todayDeaths);
        critical=view.findViewById(R.id.critical);
        recovered=view.findViewById(R.id.recovered);
        active=view.findViewById(R.id.active);
        casesPerOneMillion=view.findViewById(R.id.casesPerOneMillion);
        deathsPerOneMillion=view.findViewById(R.id.deathsPerOneMillion);
        countryName=view.findViewById(R.id.country);


        viewModel= new CountryViewModel(getActivity().getApplication());
        viewModel.gethomeInfo().observe(getActivity(), new Observer<Country>() {
            @Override
            public void onChanged(Country home) {



                countryName.setText(home.getCountry());
                cases.setText(home.getCases());
                deaths.setText(home.getDeaths());
                todayCases.setText(home.getTodayCases());
                todayDeaths.setText(home.getTodayDeaths());
                critical.setText(home.getCritical());
                recovered.setText(home.getRecovered());
                active.setText(home.getActive());
                casesPerOneMillion.setText(home.getCasesPerOneMillion());
                deathsPerOneMillion.setText(home.getDeathsPerOneMillion());
                Picasso.with(getActivity()).load(home.getCountryInfo().getFlag()).fit().centerCrop().into(flag);

            }
        });
        return  view;
    }

}
