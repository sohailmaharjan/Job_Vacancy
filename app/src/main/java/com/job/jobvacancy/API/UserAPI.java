package com.job.jobvacancy.API;

import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseFromAPI> Login(@Field("email")String email, @Field("password")String password);

    @POST("user/signup")
    Call<ResponseFromAPI> SignUp(@Body UserModel userModel);

    @GET("user/get_profile/{id}")
    Call<UserModel> GetProfile(@Header("Authorization") String accessToken, @Path("id") int id );

    @PUT("user/update_user/{id}")
    Call<ResponseFromAPI> UpdateUser(@Header("Authorization") String accessToken, @Body UserModel userModel, @Path("id") int id );



}
