package com.simats.plantz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.simats.plantz.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });

        Binding.mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ProfileActivity.class));
            }
        });
        Binding.mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, AboutUsActivity.class));
            }
        });
        Binding.mbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ContactUsActivity.class));
            }
        });
        Binding.b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences share=getSharedPreferences("session",MODE_PRIVATE);
                share.edit().putString("username",null).apply();
                String username= share.getString("username",null);
                Log.d("Logout", "onClick: "+username);
                Toast.makeText(MenuActivity.this, ""+username, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            }
        });
        Binding.identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, IdentifyActivity.class));
            }
        });
        Binding.species.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, PlantsActivity.class));
            }
        });
        Binding.growplant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, SpeciesGrowActivity.class));
            }
        });
        Binding.article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ArticleActivity.class));
            }
        });



    }
}