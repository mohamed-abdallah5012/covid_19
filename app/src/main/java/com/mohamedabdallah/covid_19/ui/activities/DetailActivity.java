package com.mohamedabdallah.covid_19.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamedabdallah.covid_19.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    private ImageView flag;
    private TextView country;
    private TextView cases;
    private TextView deaths;
    private TextView todayCases;
    private TextView todayDeaths;
    private TextView critical;
    private TextView recovered;
    private TextView active;
    private TextView casesPerOneMillion;
    private TextView deathsPerOneMillion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        country=findViewById(R.id.country);
        cases=findViewById(R.id.cases);
        deaths=findViewById(R.id.deaths);
        todayCases=findViewById(R.id.todayCases);
        todayDeaths=findViewById(R.id.todayDeaths);
        critical=findViewById(R.id.critical);
        recovered=findViewById(R.id.recovered);
        active=findViewById(R.id.active);
        casesPerOneMillion=findViewById(R.id.casesPerOneMillion);
        deathsPerOneMillion=findViewById(R.id.deathsPerOneMillion);
        flag=findViewById(R.id.flag);


        // define intent instance to get the data from the previous activity
        Intent intent = getIntent();
        if(intent !=null)

        {
            // store the data first then show it as you need
            String id1 = intent.getStringExtra("ID");
            String country1 = intent.getStringExtra("COUNTRY");
            String cases1 = intent.getStringExtra("CASES");
            String deaths1 = intent.getStringExtra("DEATHS");
            String today_cases1 = intent.getStringExtra("TODAY_CASES");
            String today_deaths1 = intent.getStringExtra("TODAY_DEATHS");
            String critical1 = intent.getStringExtra("CRITICAL");
            String recovered1 = intent.getStringExtra("RECOVERED");
            String active1 = intent.getStringExtra("ACTIVE");
            String cases_per_one_million1 = intent.getStringExtra("CASES_PER_ONE_MILLION");
            String deaths_per_one_million1 = intent.getStringExtra("DEATHS_PER_ONE_MILLION");
            String flag1 = intent.getStringExtra("FLAG");
            String lat1 = intent.getStringExtra("LAT");
            String Longt1 = intent.getStringExtra("LONG");

            country.setText(country1);
            cases.setText(cases1);
            deaths.setText(deaths1);
            todayCases.setText(today_cases1);
            todayDeaths.setText(today_deaths1);
            critical.setText(critical1);
            recovered.setText(recovered1);
            active.setText(active1);
            casesPerOneMillion.setText(cases_per_one_million1);
            deathsPerOneMillion.setText(deaths_per_one_million1);

            Picasso.with(DetailActivity.this).load(flag1).fit().centerCrop().into(flag);
        }
    }
}
