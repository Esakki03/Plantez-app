package com.simats.plantz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.simats.plantz.api.Interface;
import com.simats.plantz.api.RetroClient;
import com.simats.plantz.databinding.ActivityAboutUsBinding;
import com.simats.plantz.databinding.ActivityIdentifyBinding;
import com.simats.plantz.serverresponse.speciesdes;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class IdentifyActivity extends AppCompatActivity {
    ActivityIdentifyBinding Binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private Interface imageUploadService;
    private Button processImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_identify);
        Binding = ActivityIdentifyBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        processImage=Binding.process;
        Binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Binding.process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdentifyActivity.this, IdentifyResultActivity.class));
            }
        });
        imageUploadService = RetroClient.getRetrofit().create(Interface.class);
        Binding.uploadBox.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });
        processImage.setOnClickListener(v -> uploadImage());

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
        }
    }
    private void uploadImage() {
        Binding.loadingAnimation.setVisibility(View.VISIBLE);
        Binding.darkOverlay.setVisibility(View.VISIBLE);
        if (imageUri == null) {
            Binding.loadingAnimation.setVisibility(View.GONE);
            Binding.darkOverlay.setVisibility(View.GONE);
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(FileUtils.getPath(this, imageUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(imageUri)), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Call<speciesdes> call = imageUploadService.speciesuploadImage(body);
        call.enqueue(new Callback<speciesdes>() {
            @Override
            public void onResponse(Call<speciesdes> call, Response<speciesdes> response) {
                if(response.isSuccessful()) {
                    speciesdes speciesdes=response.body();
                    Log.i("responseCheck",speciesdes.getResponse());
/*
                    if(speciesdes.getResponse().equals('1')){
                        Toast.makeText(IdentifyActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(IdentifyActivity.this,IdentifyResultActivity.class);
                        intent.putExtra("species",speciesdes.getSpecies());
                        intent.putExtra("description",""+speciesdes.getDespription0());
                        intent.putExtra("Geo_availablity",""+speciesdes.getGeo_availablity());
                        intent.putExtra("Benefits",""+speciesdes.getBenefits());
                        intent.putExtra("Seasonal",""+speciesdes.getSeasonal());
                        startActivity(intent);
                    }


*/
                    
                    assert response.body() != null;
                    if(speciesdes.getResponse().equals("1")){
                        Toast.makeText(IdentifyActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(IdentifyActivity.this,IdentifyResultActivity.class);
                        intent.putExtra("species",response.body().getSpecies());
                        intent.putExtra("content",response.body().getDespription0());
                        intent.putExtra("benifits",response.body().getBenefits());
                        intent.putExtra("geo",response.body().getGeo_availablity());
                        intent.putExtra("season",response.body().getSeasonal());
                        intent.putExtra("filename",response.body().getFilename2());
                        Binding.loadingAnimation.setVisibility(View.GONE);
                        Binding.darkOverlay.setVisibility(View.GONE);
                       // intent.putExtra("description",""+speciesdes.getDespription0());
//                        intent.putExtra("Geo_availablity",""+speciesdes.getGeo_availablity());
//                        intent.putExtra("Benefits",""+speciesdes.getBenefits());
//                        intent.putExtra("Seasonal",""+speciesdes.getSeasonal());
                        startActivity(intent);
                    }
                    else if (speciesdes.getResponse().equals("2")){
                        Intent intent=new Intent(IdentifyActivity.this, IdentifyProcessActivity.class);
                        intent.putExtra("species1",response.body().getSpecies1());
                        intent.putExtra("species2",response.body().getSpecies2());
                        intent.putExtra("species3",response.body().getSpecies3());
                        intent.putExtra("confidence1",response.body().getConfidence1());
                        intent.putExtra("confidence2",response.body().getConfidence2());
                        intent.putExtra("confidence3",response.body().getConfidence3());
                        startActivity(intent );
                        Toast.makeText(IdentifyActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        Binding.loadingAnimation.setVisibility(View.GONE);
                        Binding.darkOverlay.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(IdentifyActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        Binding.loadingAnimation.setVisibility(View.GONE);
                        Binding.darkOverlay.setVisibility(View.GONE);
                    }
//                    Toast.makeText(IdentifyActivity.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(IdentifyActivity.this,IdentifyResultActivity.class);
//                    intent.putExtra("species",speciesdes.getSpecies());
//                    intent.putExtra("description",""+speciesdes.getDespription0());
//                    intent.putExtra("Geo_availablity",""+speciesdes.getGeo_availablity());
//                    intent.putExtra("Benefits",""+speciesdes.getBenefits());
//                    intent.putExtra("Seasonal",""+speciesdes.getSeasonal());
//                    startActivity(intent);

                }
                else {
                    Binding.loadingAnimation.setVisibility(View.GONE);
                    Binding.darkOverlay.setVisibility(View.GONE);
                    Log.e("ImageUpload", "Upload failed: " + response.errorBody());
                    Toast.makeText(IdentifyActivity.this, "Failed to get response: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<speciesdes> call, Throwable throwable) {
                Binding.loadingAnimation.setVisibility(View.GONE);
                Binding.darkOverlay.setVisibility(View.GONE);
            }
        });

    }
}