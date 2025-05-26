package com.simats.plantz;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.simats.plantz.databinding.ActivityIdentifyProcessBinding;

public class IdentifyProcessActivity extends AppCompatActivity {
    ActivityIdentifyProcessBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityIdentifyProcessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        binding.txt01.setText(intent.getStringExtra("species1"));
        binding.species2.setText(intent.getStringExtra("species2"));
        binding.species3.setText(intent.getStringExtra("species3"));
        binding.txt012.setText(intent.getStringExtra("confidence1"));
        binding.accuracy2.setText(intent.getStringExtra("confidence2"));
        binding.accuracy3.setText(intent.getStringExtra("confidence3"));



    }
}