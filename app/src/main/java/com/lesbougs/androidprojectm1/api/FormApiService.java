package com.lesbougs.androidprojectm1.api;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
    @POST("api/forms/deleteForm")
    Call<JsonObject> deleteForm(@Header("Cookie") String headerPayload, @Header("Cookie") String signature,@Field("_id") String ID);

    @FormUrlEncoded
    @POST("api/forms/setResult")
    Call<JsonObject> setFormResult(@Field("_id") String id, @Field("result") String results);

    @Headers({"Content-Type: application/json"})
    @POST("api/forms/createForm")
    Call<JsonObject> createForm(@Header("Cookie") String headerPayload ,  @Header("Cookie") String signature, @Body JsonObject body);
}
