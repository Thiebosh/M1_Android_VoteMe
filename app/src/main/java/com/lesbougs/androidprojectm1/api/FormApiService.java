package com.lesbougs.androidprojectm1.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FormApiService {
    @FormUrlEncoded
    @POST("api/users/login")
    Call<JsonObject> signIn(@Field("username") String username,
                            @Field("password") String password);

    @GET("api/users/getFormByUserID")
    Call<JsonObject> getFormByUserID(@Header("Cookie") String headerPayload ,
                                     @Header("Cookie") String signature);

    @FormUrlEncoded
    @POST("api/forms/changeStateForm")
    Call<JsonObject> closeForm(@Header("Cookie") String headerPayload ,
                                     @Header("Cookie") String signature , @Field("_id") String id , @Field("value") Boolean value);

    @FormUrlEncoded
    @POST("api/forms/getFormBySmallID")
    Call<JsonObject> getForm(@Field("smallID") String smallID);

    @FormUrlEncoded
    @POST("api/forms/setResult")
    Call<JsonObject> setFormResult(@Field("_id") String id, @Field("result") JsonObject results);
}
