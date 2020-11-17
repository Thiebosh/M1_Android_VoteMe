package com.lesbougs.androidprojectm1.api;



import com.lesbougs.androidprojectm1.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FormApiService {
    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> registration(@Field("username") String username,
                             @Field("password") String password);

}
