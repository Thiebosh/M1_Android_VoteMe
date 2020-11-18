package com.lesbougs.androidprojectm1.api;



import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FormApiService {
    @FormUrlEncoded
    @POST("api/users/login")
    Call<JsonObject> signIn(@Field("username") String username,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("api/forms/getFormBySmallID")//envoie le code du formulaire donc pas get
    Call<JsonObject> getForm(@Field("smallID") String smallID);
}
