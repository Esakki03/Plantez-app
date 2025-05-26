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
import com.simats.plantz.databinding.ActivityArticleAboutBinding;
import com.simats.plantz.databinding.ActivitySpeciesAboutBinding;

public class ArticleAboutActivity extends AppCompatActivity {
    ActivityArticleAboutBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article_about);
        Binding = ActivityArticleAboutBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getIntent().hasExtra("title")) {

            Intent item = getIntent();
            Glide.with(ArticleAboutActivity.this)
                    .load(Base_Url+item.getStringExtra("image"))
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(Binding.img);

            Binding.topic.setText(item.getStringExtra("title"));
            Binding.subject.setText(item.getStringExtra("subject"));
            Binding.content.setText(item.getStringExtra("content"));

        }

    }
}