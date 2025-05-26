package com.simats.plantz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.simats.plantz.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding Binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        Binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.b2);
        arrayList.add(R.drawable.b3);
        arrayList.add(R.drawable.b4);
        SliderAdapter adapter = new SliderAdapter(MainActivity.this, arrayList);
        Binding.recycler.setAdapter(adapter);

        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
        Binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdentifyActivity.class));
            }
        });
        Binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlantsActivity.class));
            }
        });
        Binding.p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlantsActivity.class));
            }
        });
        Binding.p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlantsActivity.class));
            }
        });
        Binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ArticleActivity.class));
            }
        });
        Binding.a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ArticleActivity.class));
            }
        });
        Binding.a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ArticleActivity.class));
            }
        });
        Binding.species.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlantsActivity.class));
            }
        });
  
        Binding.grow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeciesGrowActivity.class));
            }
        });
        Binding.g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeciesGrowActivity.class));
            }
        });
        Binding.g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeciesGrowActivity.class));
            }
        });
        Binding.articlick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ArticleActivity.class));
            }
        });

    }
}