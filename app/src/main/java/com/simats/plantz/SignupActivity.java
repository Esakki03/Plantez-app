package com.simats.plantz;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

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
import com.simats.plantz.databinding.ActivityPlantsBinding;
import com.simats.plantz.databinding.ActivitySignupBinding;
import com.simats.plantz.serverresponse.commonresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Call<commonresponse> call = RetroClient.getInterface().signup(Binding.user.getText().toString(),Binding.pass.getText().toString(),Binding.rpass.getText().toString(),Binding.email.getText().toString(),Binding.firstname.getText().toString(),Binding.lastname.getText().toString());

                    call.enqueue(new Callback<commonresponse>() {
                        @Override
                        public void onResponse(Call<commonresponse> call, Response<commonresponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().getMessage()){
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));

                                }
                                else {
                                    Toast.makeText(SignupActivity.this,"Check username and password", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<commonresponse> call, Throwable throwable) {
                            Toast.makeText(SignupActivity.this,"Failed", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });
        Binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }
    public boolean validate(){
        String username, password;
        username=Binding.user.getText().toString();
        password=Binding.pass.getText().toString();
        if (username.isEmpty() || password.isEmpty()){
            Binding.user.setError("Please enter username and password correctly");
            return false;
        }
        return true;

    }
}