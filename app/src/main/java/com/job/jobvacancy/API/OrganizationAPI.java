package com.job.jobvacancy.API;

import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;
import com.job.jobvacancy.OrganizationSignUp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrganizationAPI {
    @FormUrlEncoded
    @POST("org/login")
    Call<ResponseFromAPI> Login(@Field("email")String email, @Field("password")String password);

    @POST("org/signup")
    Call<ResponseFromAPI> SignUp(@Body OrganizationModel organizationModel);

    @GET("org/get_profile/{id}")
    Call<OrganizationModel> GetProfile(@Header("Authorization") String accessToken, @Path("id") int id );


    @GET("org/get_all")
    Call<List<OrganizationModel>> GetAll(@Header("Authorization") String accessToken );

    @GET("org/get_new")
    Call<List<OrganizationModel>> GetNew(@Header("Authorization") String accessToken );

    @PUT("org/verify/{id}")
    Call<ResponseFromAPI> Verify(@Header("Authorization") String accessToken ,@Path("id") int id );

    @PUT("org/update_org/{id}")
    Call<ResponseFromAPI> UpdateOrg(@Header("Authorization") String accessToken ,@Body OrganizationModel organizationModel,@Path("id") int id );

}
