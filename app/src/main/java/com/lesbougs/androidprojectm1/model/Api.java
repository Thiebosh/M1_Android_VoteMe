package com.lesbougs.androidprojectm1.model;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://android-m1-back.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        return retrofit;
    }
}
