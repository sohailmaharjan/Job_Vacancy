package com.job.jobvacancy.UnitTesting;

import com.job.jobvacancy.API.JobAPI;
import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganizationTesting {

    private Boolean isSuccess=true;
    private String accesstoken= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyRW1haWwiOiJzb2Z0d2FyaWNhQGdtYWlsLmNvbSIsImlhdCI6MTYwOTU4NzEzNn0.v2IqrQX5LdJEy04QkgmSzY2vClUzxi79ZvjEhpLCAQE";
    OrganizationAPI orgAPI= Url.getInstance().create(OrganizationAPI.class);
    JobAPI jobAPI= Url.getInstance().create(JobAPI.class);


    public Boolean orgSignup(OrganizationModel orgModel){
        Call<ResponseFromAPI> response=orgAPI.SignUp(orgModel);
        response.enqueue(new Callback<ResponseFromAPI>() {
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

    public boolean OrgLogin(String email,String password){
        Call<ResponseFromAPI> response= orgAPI.Login(email,password);
        response.enqueue(new Callback<ResponseFromAPI>() {
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

    public boolean OrgUpdate(OrganizationModel organizationModel,int id){
        Call<ResponseFromAPI> orgCall= orgAPI.UpdateOrg(accesstoken,organizationModel,id);
        orgCall.enqueue(new Callback<ResponseFromAPI>() {
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

    public boolean GetAllOrganization(){
        Call<List<OrganizationModel>> orgCall= orgAPI.GetAll(accesstoken);
        orgCall.enqueue(new Callback<List<OrganizationModel>> () {
            @Override
            public void onResponse(Call<List<OrganizationModel>> call, Response<List<OrganizationModel>>  response) {
                if(response.isSuccessful()){
                    isSuccess=true;
                }else {
                    isSuccess=false;
                }
            }

            @Override
            public void onFailure(Call<List<OrganizationModel>> call, Throwable t) {
                isSuccess=false;

            }
        });

        return isSuccess;
    }

    public boolean JobPost(JobModel jobModel){
        Call<ResponseFromAPI> jobCall= jobAPI.JobPost(accesstoken,jobModel );
        jobCall.enqueue(new Callback<ResponseFromAPI>() {
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

    public boolean GetAllJob(){
        Call<List<JobModel>> orgCall= jobAPI.GetAll(accesstoken);
        orgCall.enqueue(new Callback<List<JobModel>> () {
            @Override
            public void onResponse(Call<List<JobModel>>  call, Response<List<JobModel>>  response) {
                if(response.isSuccessful()){
                    isSuccess=true;
                }else {
                    isSuccess=false;
                }
            }

            @Override
            public void onFailure(Call<List<JobModel>>  call, Throwable t) {
                isSuccess=false;

            }
        });

        return isSuccess;
    }

}
