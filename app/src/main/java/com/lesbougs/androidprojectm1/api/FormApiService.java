package com.lesbougs.androidprojectm1.api;

import com.lesbougs.androidprojectm1.model.FAPIData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FormApiService {
    @GET("people/")
    Call<FAPIData> getData();
}
