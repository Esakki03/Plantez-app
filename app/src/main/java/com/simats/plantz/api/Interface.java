package com.simats.plantz.api;

import com.simats.plantz.ArticleList;
import com.simats.plantz.SpeciesList;
import com.simats.plantz.serverresponse.PlantResponse;
import com.simats.plantz.serverresponse.SpeciesResponse;
import com.simats.plantz.serverresponse.commonresponse;
import com.simats.plantz.serverresponse.speciesdes;
import com.simats.plantz.serverresponse.userdetail;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Interface {
    @FormUrlEncoded
    @POST("/apilogin")
    Call<commonresponse> login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("/apisignup")
    Call<commonresponse> signup(@Field("username") String username,@Field("password") String password,@Field("rpassword") String rpassword,@Field("email") String email,@Field("fname") String fname, @Field("lname") String lname );
    @GET("/apilogout")
    Call<commonresponse> logout();

    @GET("/apiprofile")
    Call<userdetail> profile();

    @GET("/apiplant-page")
    Call<PlantResponse> plant();

    @GET("/apigrow-page")
    Call<SpeciesList> species();

    @GET("/apiarticle-page")
    Call<ArticleList> article();

    @Multipart
    @POST("/apiidentify") // Replace with your actual endpoint
    Call<speciesdes> speciesuploadImage(@Part MultipartBody.Part image);
}

