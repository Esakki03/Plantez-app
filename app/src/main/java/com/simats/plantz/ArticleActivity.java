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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    private ArticleAdapter adapter;

    private RecyclerView recyclerView;
    private com.simats.plantz.ArticleAdapter ArticleAdapter;
    private List<Article> ArticleList;
    private ImageView gback;
    private SearchView searchView;
    private LottieAnimationView loadingAnimation; // Declare as member variable
    private View darkOverlay; // Declare as member variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article);

        // Initialize views after setContentView
        recyclerView = findViewById(R.id.recyclerview);
        gback = findViewById(R.id.banner);
        searchView = findViewById(R.id.search);
        loadingAnimation = findViewById(R.id.loadingAnimation); // Initialize here
        darkOverlay = findViewById(R.id.darkOverlay); // Initialize here

        gback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Set visibility to show loading animation and dark overlay
        loadingAnimation.setVisibility(View.VISIBLE);
        darkOverlay.setVisibility(View.VISIBLE);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ArticleList = new ArrayList<>();
        ArticleAdapter = new ArticleAdapter(this, ArticleList);
        recyclerView.setAdapter(ArticleAdapter);

        // Load data
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
        List<Article> filteredList = new ArrayList<>();

        if (query == null || query.isEmpty()) {
            // If query is null or empty, show the full list
            filteredList.addAll(ArticleList);
        } else {
            // Perform partial match filtering
            for (Article article : ArticleList) {
                if (article.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(article);
                }
            }
        }

        // Update the adapter with the filtered list
        ArticleAdapter.filterBySearch(filteredList);
    }

    private void loadPlants() {
        Call<ArticleList> res = RetroClient.getInterface().article();

        res.enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Article> list = response.body().getArticleList();
                        ArticleList.addAll(list);
                        ArticleAdapter.notifyDataSetChanged();
                        loadingAnimation.setVisibility(View.GONE);
                        darkOverlay.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(ArticleActivity.this, "No Data to Show", Toast.LENGTH_SHORT).show();
                        loadingAnimation.setVisibility(View.GONE);
                        darkOverlay.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable throwable) {
                loadingAnimation.setVisibility(View.GONE);
                darkOverlay.setVisibility(View.GONE);
                Toast.makeText(ArticleActivity.this, "Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
