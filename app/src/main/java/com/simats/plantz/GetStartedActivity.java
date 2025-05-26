package com.simats.plantz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.simats.plantz.databinding.ActivityGetStartedBinding;

public class GetStartedActivity extends AppCompatActivity {

    ActivityGetStartedBinding Binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityGetStartedBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartedActivity.this,LoginActivity.class));
            }
        });
    }
}