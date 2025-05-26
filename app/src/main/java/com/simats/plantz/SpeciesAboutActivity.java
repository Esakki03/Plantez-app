package com.simats.plantz;

import static com.simats.plantz.api.RetroClient.Base_Url;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.simats.plantz.databinding.ActivityPlantDetailsBinding;
import com.simats.plantz.databinding.ActivitySpeciesAboutBinding;

public class SpeciesAboutActivity extends AppCompatActivity {
    ActivitySpeciesAboutBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_species_about);
        Binding = ActivitySpeciesAboutBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getIntent().hasExtra("title")) {

            Intent item = getIntent();
            Glide.with(SpeciesAboutActivity.this)
                    .load(Base_Url + item.getStringExtra("image"))
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(Binding.img);

            Binding.topic.setText(item.getStringExtra("title"));
            Binding.subject.setText(item.getStringExtra("subject"));
            Binding.content.setText(item.getStringExtra("content"));
        }

    }
}