package com.mohamedabdallah.covid_19.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mohamedabdallah.covid_19.R;
import com.mohamedabdallah.covid_19.ui.fragment.AboutFragment;
import com.mohamedabdallah.covid_19.ui.fragment.HomeFragment;
import com.mohamedabdallah.covid_19.ui.fragment.WorldFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        if(savedInstanceState==null)
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_cointainer,new WorldFragment()).commit();

    }
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Click Again To Exit", Toast.LENGTH_SHORT).show();

        }
        back_pressed = System.currentTimeMillis();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener listener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment fragment=null;
                    switch (menuItem.getItemId())
                    {
                        case R.id.bottom_world:
                            fragment=new WorldFragment();
                            break;

                        case R.id.bottom_home:
                            fragment=new HomeFragment();
                            break;

                        case R.id.bottom_about:
                            fragment=new AboutFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_cointainer,fragment).commit();
                    return true;
                }
            };
}
