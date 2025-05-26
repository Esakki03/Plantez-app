package com.simats.plantz;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.simats.plantz.api.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeciesGrowActivity extends AppCompatActivity {


    private SpeciesAdapter adapter;

    private RecyclerView recyclerView;
    private com.simats.plantz.SpeciesAdapter SpeciesAdapter;
    private List<GrowItem> SpeciesList;
    private ImageView gback;
    private SearchView searchView;
    private LottieAnimationView loadingAnimation;
    private View darkOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_species_grow);



        recyclerView = findViewById(R.id.recyclerview);
        gback= findViewById(R.id.banner);
        searchView = findViewById(R.id.search);
        gback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadingAnimation = findViewById(R.id.loadingAnimation);
        darkOverlay = findViewById(R.id.darkOverlay);
        loadingAnimation.setVisibility(View.VISIBLE);
        darkOverlay.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SpeciesList = new ArrayList<>();
        SpeciesAdapter = new SpeciesAdapter(this,SpeciesList);
        recyclerView.setAdapter(SpeciesAdapter);

        loadPlants();
        setupSearchBar();

    }
    private void setupSearchBar() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterListByText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListByText(newText);
                return true;
            }
        });

    }

    private void filterListByText(String query) {
        List<GrowItem> filteredList = new ArrayList<>();

        if (query == null || query.isEmpty()) {
            // If query is null or empty, show the full list
            filteredList.addAll(SpeciesList);
        } else {
            // Perform partial match filtering
            for (GrowItem item : SpeciesList) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        // Update the adapter with the filtered list
        SpeciesAdapter.filterBySearch(filteredList);
    }

    private void loadPlants() {


        Call<SpeciesList> res = RetroClient.getInterface().species();

        res.enqueue(new Callback<SpeciesList>() {
            @Override
            public void onResponse(Call<SpeciesList> call, Response<SpeciesList> response) {
                if(response.isSuccessful()){
                    if (response.body() != null){
                        List<GrowItem> list = response.body().getGrowList();
                        SpeciesList.addAll(list);
                        SpeciesAdapter.notifyDataSetChanged();
                        loadingAnimation.setVisibility(View.GONE);
                        darkOverlay.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(SpeciesGrowActivity.this,"No Data to Show",Toast.LENGTH_SHORT).show();
                        loadingAnimation.setVisibility(View.GONE);
                        darkOverlay.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<SpeciesList> call, Throwable throwable) {

                Toast.makeText(SpeciesGrowActivity.this,"Server error",Toast.LENGTH_SHORT).show();
                loadingAnimation.setVisibility(View.GONE);
                darkOverlay.setVisibility(View.GONE);
            }
        });
    }
}