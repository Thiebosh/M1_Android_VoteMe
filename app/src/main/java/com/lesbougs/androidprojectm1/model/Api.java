package com.lesbougs.androidprojectm1.model;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://android-m1-back.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getClient() { return retrofit; }
}
