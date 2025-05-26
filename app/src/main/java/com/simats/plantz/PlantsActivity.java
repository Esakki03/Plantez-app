package com.simats.plantz;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.simats.plantz.api.RetroClient;
import com.simats.plantz.serverresponse.PlantResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlantAdapter plantAdapter;
    private List<PlantList> plantList;
    private ImageView gback;
    private SearchView searchView;
    private LottieAnimationView loadingAnimation; // Declare here
    private View darkOverlay; // Declare here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);  // Layout is set here

        // Initialize the views after setContentView
        loadingAnimation = findViewById(R.id.loadingAnimation);  // Initialize here
        darkOverlay = findViewById(R.id.darkOverlay);  // Initialize here

        // Set visibility to make them visible initially
        loadingAnimation.setVisibility(View.VISIBLE);
        darkOverlay.setVisibility(View.VISIBLE);

        recyclerView = findViewById(R.id.recyclerview);
        gback = findViewById(R.id.banner);
        searchView = findViewById(R.id.search);

        // Set up back button listener
        gback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize the list and set the adapter
        plantList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns for the grid
        plantAdapter = new PlantAdapter(this, plantList);
        recyclerView.setAdapter(plantAdapter);

        // Load plants from the API
        loadPlants();

        // Set up search bar functionality
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
        List<PlantList> filteredList = new ArrayList<>();

        if (query == null || query.isEmpty()) {
            // If query is null or empty, show the full list
            filteredList.addAll(plantList);
        } else {
            // Perform partial match filtering
            if (!plantList.isEmpty()) {
                for (PlantList plant : plantList) {
                    if (plant.getName().toLowerCase().contains(query.toLowerCase())) {
                        filteredList.add(plant);
                    }
                }
            }
        }

        // Update the adapter with the filtered list
        plantAdapter.filterBySearch(filteredList);
    }

    private void loadPlants() {
        // Make the API call to load the plants
        Call<PlantResponse> res = RetroClient.getInterface().plant();

        res.enqueue(new Callback<PlantResponse>() {
            @Override
            public void onResponse(Call<PlantResponse> call, Response<PlantResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<PlantList> list = response.body().getPlantList();
                        plantList.addAll(list);
                        plantAdapter.notifyDataSetChanged();
                        loadingAnimation.setVisibility(View.GONE);  // Hide loading animation
                        darkOverlay.setVisibility(View.GONE);  // Hide dark overlay
                    } else {
                        Toast.makeText(PlantsActivity.this, "No Data to Show", Toast.LENGTH_SHORT).show();
                        loadingAnimation.setVisibility(View.GONE);
                        darkOverlay.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<PlantResponse> call, Throwable throwable) {
                loadingAnimation.setVisibility(View.GONE);
                darkOverlay.setVisibility(View.GONE);
                Toast.makeText(PlantsActivity.this, "Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
