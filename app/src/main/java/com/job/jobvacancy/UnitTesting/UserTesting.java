package com.job.jobvacancy.UnitTesting;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTesting {
    private Boolean isSuccess=true;
    private String accesstoken= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyRW1haWwiOiJzb2Z0d2FyaWNhQGdtYWlsLmNvbSIsImlhdCI6MTYwOTU4NzEzNn0.v2IqrQX5LdJEy04QkgmSzY2vClUzxi79ZvjEhpLCAQE";
    UserAPI userAPI= Url.getInstance().create(UserAPI.class);


    public Boolean userSignup(UserModel userModel){
        Call<ResponseFromAPI> userCall= userAPI.SignUp(userModel);
        userCall.enqueue(new Callback<ResponseFromAPI>() {
            @Override
            public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                if(response.isSuccessful()){
                    isSuccess=true;
                }
            }
            @Override
            public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                isSuccess=false;
            }


        });

        return false;

    }

    public boolean LoginUser(String email,String password){
        Call<ResponseFromAPI> userCall= userAPI.Login(email,password);
        userCall.enqueue(new Callback<ResponseFromAPI>() {
           @Override
           public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
               if(response.body().getStatus()){
                   isSuccess=response.body().getStatus();
               }else {
                   isSuccess=response.body().getStatus();
               }
           }

           @Override
           public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                isSuccess=false;

           }
       });

        return isSuccess;
    }

    public boolean UpdateUser(UserModel userModel,int id){
        Call<ResponseFromAPI> userCall= userAPI.UpdateUser(accesstoken,userModel,id);
        userCall.enqueue(new Callback<ResponseFromAPI>() {
            @Override
            public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                if(response.body().getStatus()){
                    isSuccess=response.body().getStatus();
                }else {
                    isSuccess=response.body().getStatus();
                }
            }

            @Override
            public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                isSuccess=false;

            }
        });

        return isSuccess;
    }

    public boolean GetUserProfile(int id){
        Call<UserModel> userCall= userAPI.GetProfile(accesstoken,id);
        userCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    isSuccess=true;
                }else {
                    isSuccess=false;
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                isSuccess=false;

            }
        });

        return isSuccess;
    }



}
