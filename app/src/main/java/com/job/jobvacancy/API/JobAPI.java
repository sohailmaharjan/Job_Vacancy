package com.job.jobvacancy.API;

import com.job.jobvacancy.Model.ApplyJobModel;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.ResponseFromAPI;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JobAPI {

    @POST("job/job_post")
    Call<ResponseFromAPI> JobPost(@Header("Authorization") String accessToken,@Body JobModel jobModel);

    @POST("job/job_apply")
    Call<ResponseFromAPI> ApplyJob(@Header("Authorization") String accessToken,@Body ApplyJobModel applyJobModel);


    @GET("job/get_by_id/{id}")
    Call<JobModel> GetJobById(@Header("Authorization") String accessToken, @Path("id") int id );


    @GET("job/get_all")
    Call<List<JobModel>> GetAll(@Header("Authorization") String accessToken );

    @GET("job/get_by_user/{id}")
    Call<List<JobModel>> GetByUser(@Header("Authorization") String accessToken,@Path("id") int id);

    @GET("job/get_by_org_id/{id}")
    Call<List<JobModel>> GetByOrg(@Header("Authorization") String accessToken,@Path("id") int id);

    @DELETE("job/delete_job/{id}")
    Call<ResponseFromAPI> DeleteJob(@Header("Authorization") String accessToken,@Path("id") int id);

    @PUT("job/update_job/{id}")
    Call<ResponseFromAPI> UpdateJob(@Header("Authorization") String accessToken,@Body JobModel jobModel,@Path("id") int id);


}
