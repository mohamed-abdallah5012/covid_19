package com.mohamedabdallah.covid_19.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mohamedabdallah.covid_19.models.Country;
import com.mohamedabdallah.covid_19.requests.CountryViewModel;
import com.mohamedabdallah.covid_19.ui.activities.DetailActivity;
import com.mohamedabdallah.covid_19.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class WorldFragment extends Fragment {

    private CartAdapter adapter;
    private RecyclerView recyclerView;
    private List<Country> countryArrayList =new ArrayList<>();
    private List<Country> countryArrayList_search =new ArrayList<>();
    private ProgressBar progressBar;
    private SearchView searchView;

    private CountryViewModel viewModel;

    Button sort_recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_world, container, false);

        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        searchView=view.findViewById(R.id.search_view);
        search(searchView);

        sort_recycler=view.findViewById(R.id.sort_recycler);
        sort_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemViewCacheSize(100000000);

        adapter=new CartAdapter(getActivity(),countryArrayList);
        recyclerView.setAdapter(adapter);

        ConnectivityManager manager = (ConnectivityManager) Objects.requireNonNull(getActivity()).
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert  manager!=null;
        NetworkInfo network=manager.getActiveNetworkInfo();
        if(network!=null&&network.isConnected())
        {
            viewModel= new CountryViewModel(getActivity().getApplication());
            viewModel.getCountries().observe(this, new Observer<List<Country>>() {
                @Override
                public void onChanged(List<Country> countries) {
                    countryArrayList=countries;
                    continue_sort("Cases : High to Low");
                    adapter=new CartAdapter(getActivity(),countries);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        else
        {
            final Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Objects.requireNonNull(getActivity()).finish();
                    Toast.makeText(getActivity(), "Check your connection and try again later", Toast.LENGTH_SHORT).show();
                }
            },1000);
        }

        return  view;
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                co_search(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                co_search(s);
                return false;
            }
        });

    }
    private void co_search(String word)
    {
        countryArrayList_search.clear();
        for (int i = 0; i < countryArrayList.size(); i++) {
            String name = countryArrayList.get(i).getCountry();
            if (name.toLowerCase().contains(word.toLowerCase())) {
                countryArrayList_search.add(countryArrayList.get(i));
            }
        }
        adapter = new CartAdapter(getActivity(), countryArrayList_search);
        recyclerView.setAdapter(adapter);

    }

    private void sort() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] selectedItem = new String[1];

        // Set the alert dialog title
        builder.setTitle("Sort.");


        // Initializing an array of language
        final String[] languages = new String[]{
                "Cases : Low to High",
                "Cases : High to Low",
                "Deaths : Low to High",
                "Deaths  : High to Low",
        };

        // Set a single choice items list for alert dialog
        builder.setSingleChoiceItems(
                languages, // Items list
                -1, // Index of checked item (-1 = no selection)
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Get the alert dialog selected item's text
                        selectedItem[0] = Arrays.asList(languages).get(i);

                        // Display the selected item's text on snack bar
                        //Toast.makeText(getApplicationContext(), "Checked : " + selectedItem[0], Toast.LENGTH_SHORT).show();
                    }
                });

        // Set the a;ert dialog positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Just dismiss the alert dialog after selection
                // Or do something now
                continue_sort(selectedItem[0]);


            }
        });

        // Create the alert dialog
        AlertDialog dialog = builder.create();

        // Finally, display the alert dialog
        dialog.show();
    }

    private void continue_sort(String key) {

         if (key == "Cases : High to Low") {
            Collections.sort(countryArrayList, new Comparator<Country>() {
                @Override
                public int compare(Country p1, Country p2) {

                    return Integer.valueOf(p2.getCases()).compareTo(Integer.parseInt(p1.getCases()));
                }
            });
        }
        else if (key == "Cases : Low to High") {
            Collections.sort(countryArrayList, new Comparator<Country>() {
                @Override
                public int compare(Country p1, Country p2) {

                    return Integer.valueOf(p1.getCases()).compareTo(Integer.parseInt(p2.getCases()));
                }
            });
        }
        else if (key == "Deaths  : High to Low") {
            Collections.sort(countryArrayList, new Comparator<Country>() {
                @Override
                public int compare(Country p1, Country p2) {

                    return Integer.valueOf(p2.getDeaths()).compareTo(Integer.parseInt(p1.getDeaths()));
                }
            });

        }
        else if (key == "Deaths : Low to High") {
            Collections.sort(countryArrayList, new Comparator<Country>() {
                @Override
                public int compare(Country p1, Country p2) {

                    return Integer.valueOf(p1.getDeaths()).compareTo(Integer.parseInt(p2.getDeaths()));
                }
            });
        }

        else {
            //do nothing
        }
        adapter=new CartAdapter(getActivity(),countryArrayList);
        recyclerView.setAdapter(adapter);

    }

    class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

        private Context mContext;
        private List<Country> countryList = new ArrayList<>();

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private ImageView flagUrl;
            private TextView countryName;
            private TextView countryCases;
            private TextView countryDeath;
            private TextView countryRecoverd;


            public MyViewHolder(View view) {
                super(view);
                countryName = (TextView) view.findViewById(R.id.country_name);
                flagUrl = (ImageView) view.findViewById(R.id.flag_url);
                countryCases = (TextView) view.findViewById(R.id.country_cases);
                countryDeath = (TextView) view.findViewById(R.id.country_death);
                countryRecoverd = (TextView) view.findViewById(R.id.country_recoverd);

            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public CartAdapter(Context mContext, List<Country> countryList1) {
            this.mContext = mContext;
            this.countryList = countryList1;
        }
        // Create new views (invoked by the layout manager)
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.country_item, parent, false);

            return new MyViewHolder(itemView);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final Country album = countryList.get(position);
            holder.countryName.setText(album.getCountry());
            holder.countryCases.setText(holder.countryCases.getText()+" "+album.getCases());
            holder.countryDeath.setText(holder.countryDeath.getText()+" "+album.getDeaths());
            holder.countryRecoverd.setText(holder.countryRecoverd.getText()+" "+album.getRecovered());

            Picasso.with(mContext).load(album.getCountryInfo().getFlag()).into(holder.flagUrl);
            holder.flagUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, DetailActivity.class);

                    intent.putExtra("ID", countryList.get(position).getCountryInfo().get_id());
                    intent.putExtra("COUNTRY", countryList.get(position).getCountry());
                    intent.putExtra("CASES", countryList.get(position).getCases());
                    intent.putExtra("DEATHS", countryList.get(position).getDeaths());
                    intent.putExtra("TODAY_CASES", countryList.get(position).getTodayCases());
                    intent.putExtra("TODAY_DEATHS", countryList.get(position).getTodayDeaths());
                    intent.putExtra("CRITICAL", countryList.get(position).getCritical());
                    intent.putExtra("RECOVERED", countryList.get(position).getRecovered());
                    intent.putExtra("ACTIVE", countryList.get(position).getActive());
                    intent.putExtra("CASES_PER_ONE_MILLION", countryList.get(position).getCasesPerOneMillion());
                    intent.putExtra("DEATHS_PER_ONE_MILLION", countryList.get(position).getDeathsPerOneMillion());
                    intent.putExtra("FLAG", countryList.get(position).getCountryInfo().getFlag());
                    intent.putExtra("LAT", countryList.get(position).getCountryInfo().getLat());
                    intent.putExtra("LONG", countryList.get(position).getCountryInfo().getLong());
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return countryList.size();
        }
    }

}
