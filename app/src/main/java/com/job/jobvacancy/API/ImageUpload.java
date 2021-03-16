package com.job.jobvacancy.API;

import com.job.jobvacancy.Model.ResponseFromAPI;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ImageUpload {
    @Multipart
    @POST("imageUpload")
    Call<ResponseFromAPI> uploadImage(@Part MultipartBody.Part myImage);

}
