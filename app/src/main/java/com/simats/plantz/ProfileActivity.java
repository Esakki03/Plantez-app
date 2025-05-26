package com.simats.plantz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.simats.plantz.api.RetroClient;
import com.simats.plantz.databinding.ActivityMenuBinding;
import com.simats.plantz.databinding.ActivityProfileBinding;
import com.simats.plantz.serverresponse.commonresponse;
import com.simats.plantz.serverresponse.userdetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Binding.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "gh");
        String email = sharedPreferences.getString("email", "gh");
        String lname = sharedPreferences.getString("lname", "gh");
        String fname = sharedPreferences.getString("fname", "gh");
        Binding.user1.setText(username+" "+lname);
        Binding.email1.setText(email);
        Binding.firstname1.setText(fname);
        Binding.lastname1.setText(lname);
    }
}
//        Call<userdetail> call=RetroClient.getInterface().profile();
//        call.enqueue(new Callback<userdetail>() {
//            @Override
//            public void onResponse(Call<userdetail> call, Response<userdetail> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    userdetail userDetails = response.body();
//
//                    // Display fetched data in UI components
//                    Binding.user1.setText(userDetails.getUsername());
//                    Binding.email1.setText(userDetails.getEmail());
//                    Binding.firstname1.setText(userDetails.getFname());
//                    Binding.lastname1.setText(userDetails.getLname());
//
//                    Toast.makeText(ProfileActivity.this, "Fetched response successfully", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(ProfileActivity.this, "response failed", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<userdetail> call, Throwable throwable) {
//                Toast.makeText(ProfileActivity.this, "failed to fetch response", Toast.LENGTH_SHORT).show();
//
//            }
//        });

