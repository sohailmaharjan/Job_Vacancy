package com.job.jobvacancy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.AdminDashboard;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.R;
import com.job.jobvacancy.UserDashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminUser extends Fragment {
    private Button btn_admin_login;
    private EditText ed_email_admin_login,ed_password_admin_login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_user, container, false);
        btn_admin_login= view.findViewById(R.id.btn_admin_login);
        ed_email_admin_login= view.findViewById(R.id.ed_email_admin_login);
        ed_password_admin_login= view.findViewById(R.id.ed_password_admin_login);

        ed_email_admin_login.setText("admin@gmail.com");
        ed_password_admin_login.setText("Admin@1234");

        btn_admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserAPI userAPI= Url.getInstance().create(UserAPI.class);
                Call<ResponseFromAPI> userCall = userAPI.Login(ed_email_admin_login.getText().toString().trim(),ed_password_admin_login.getText().toString().trim());
                userCall.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            if(response.body().getStatus() && response.body().getUser_type().equals("Admin")){
                                Url.accessToken = response.body().getAccessToken();
                                Url.id = response.body().getId();
                                Log.d("User Id", response.body().getId()+"");
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), AdminDashboard.class);
                                startActivity(intent);
                            }else if (response.body().getStatus() && response.body().getUser_type().equals("User")) {
                                Toast.makeText(getContext(), "Either email or password do not match", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(getContext(), "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        return view;
    }
}