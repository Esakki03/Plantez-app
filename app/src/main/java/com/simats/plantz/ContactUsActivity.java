package com.simats.plantz;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.simats.plantz.databinding.ActivityContactUsBinding;
import com.simats.plantz.databinding.ActivityIdentifyBinding;

public class ContactUsActivity extends AppCompatActivity {
    ActivityContactUsBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_us);
        Binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}