package com.simats.plantz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.simats.plantz.databinding.ActivityAboutUsBinding;
import com.simats.plantz.databinding.ActivityMenuBinding;

public class AboutUsActivity extends AppCompatActivity {
    ActivityAboutUsBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsActivity.this, MenuActivity.class));
            }
        });

    }
}