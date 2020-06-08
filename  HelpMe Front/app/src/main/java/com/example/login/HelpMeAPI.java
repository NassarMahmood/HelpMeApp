package com.example.login;

import com.example.login.Boundaris.UserBoundary;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HelpMeAPI {
    @GET("user/login/{userEmail}/{password}")
    Call<Object> getLogin(
            @Path("userEmail") String userEmail,
            @Path("password") String password);


    @POST("user/registration/{userId}")
    Call<Object> userRegistration(
            @Path("userId") String userId,
            @Body JsonElement person);



}
