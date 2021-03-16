package com.job.jobvacancy.API;

import android.os.StrictMode;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String BASE_URL="http://172.20.10.6:8001/job_vacancy/v1/"; //Mobile device home
//    public static final String BASE_URL="http://10.0.2.2:8001/job_vacancy/v1/"; // emulator connection

    public static String accessToken="";
    public static int id;
    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
}
