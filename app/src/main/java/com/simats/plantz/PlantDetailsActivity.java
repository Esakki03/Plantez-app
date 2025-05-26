package com.simats.plantz;

import static com.simats.plantz.api.RetroClient.Base_Url;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.simats.plantz.databinding.ActivityPlantDetailsBinding;
import com.simats.plantz.databinding.ActivityPlantDetailsBinding;

public class PlantDetailsActivity extends AppCompatActivity {
    ActivityPlantDetailsBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);
        Binding = ActivityPlantDetailsBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().hasExtra("title")) {

            Intent item = getIntent();
            Glide.with(PlantDetailsActivity.this)
                    .load(Base_Url+item.getStringExtra("image"))
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(Binding.img);

            Binding.topic.setText(item.getStringExtra("title"));
            Binding.content.setText(item.getStringExtra("content"));
            Binding.benifits.setText(item.getStringExtra("benifits"));
            Binding.geo.setText(item.getStringExtra("geography"));
            Binding.season.setText(item.getStringExtra("seasonal"));

        }

    }
}