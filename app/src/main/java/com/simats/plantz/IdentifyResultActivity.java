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
import com.simats.plantz.databinding.ActivityIdentifyBinding;
import com.simats.plantz.databinding.ActivityIdentifyResultBinding;

public class IdentifyResultActivity extends AppCompatActivity {
    ActivityIdentifyResultBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_identify_result);
        Binding = ActivityIdentifyResultBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent item = getIntent();

        Binding.topic.setText(item.getStringExtra("species"));
        Binding.content.setText(item.getStringExtra("content"));
        Binding.geo.setText(item.getStringExtra("geo"));
        Binding.benifits.setText(item.getStringExtra("benifits"));
        Binding.season.setText(item.getStringExtra("season"));

        Glide.with(IdentifyResultActivity.this)
                .load(Base_Url+"/species/"+item.getStringExtra("filename"))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(Binding.img);


//        if (getIntent().hasExtra("title")) {
//
//            Intent item = getIntent();
//            Glide.with(IdentifyResultActivity.this)
//                    .load(Base_Url + item.getStringExtra("image"))
//                    .placeholder(R.drawable.tulsi)
//                    .error(R.drawable.tulsi)
//                    .into(Binding.img);
//
//            Binding.topic.setText(item.getStringExtra("species"));
//            Binding.content.setText(item.getStringExtra("description"));
//            Binding.benifits.setText(item.getStringExtra("benifits"));
//            Binding.geo.setText(item.getStringExtra("geography"));
//            Binding.season.setText(item.getStringExtra("seasonal"));
//
//        }
    }
}