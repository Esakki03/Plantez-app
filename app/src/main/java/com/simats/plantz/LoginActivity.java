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
import com.simats.plantz.databinding.ActivityGetStartedBinding;
import com.simats.plantz.databinding.ActivityLoginBinding;
import com.simats.plantz.serverresponse.commonresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        SharedPreferences share=getSharedPreferences("session",MODE_PRIVATE);
        String username= share.getString("username",null);
        if(username!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        Binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Call<commonresponse> call =RetroClient.getInterface().login(Binding.user.getText().toString(),Binding.pass.getText().toString());

                    call.enqueue(new Callback<commonresponse>() {
                        @Override
                        public void onResponse(Call<commonresponse> call, Response<commonresponse> response) {
                            if (response.isSuccessful()){
                                if (response.body().getMessage()){
                                    SharedPreferences sharedPreferences=getSharedPreferences("session",MODE_PRIVATE);
                                    SharedPreferences.Editor edit=sharedPreferences.edit();
                                    edit.putString("username",response.body().getUsername());
                                    edit.putString("email",response.body().getEmail());
                                    edit.putString("fname",response.body().getFname());
                                    edit.putString("lname",response.body().getLname());

                                    edit.commit();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                                }
                                else {
                                    Toast.makeText(LoginActivity.this,"Check username and password", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<commonresponse> call, Throwable throwable) {
                            Toast.makeText(LoginActivity.this,"Failed", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
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