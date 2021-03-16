package com.job.jobvacancy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.OrganizationDashboard;
import com.job.jobvacancy.OrganizationSignUp;
import com.job.jobvacancy.R;
import com.job.jobvacancy.UserDashboard;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganizationUser extends Fragment {
    private TextView tv_organization_signup;
    private Button btn_organization_login;
    private EditText ed_email_org_login,ed_password_org_login;
    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{8,}"+"$");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_organization_user, container, false);
        tv_organization_signup=view.findViewById(R.id.tv_organization_signup);
        btn_organization_login=view.findViewById(R.id.btn_organization_login);
        ed_password_org_login=view.findViewById(R.id.ed_password_org_login);
        ed_email_org_login=view.findViewById(R.id.ed_email_org_login);



        tv_organization_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), OrganizationSignUp.class);
                startActivity(intent);
            }
        });

        btn_organization_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    OrganizationAPI orgAPI= Url.getInstance().create(OrganizationAPI.class);
                    Call<ResponseFromAPI> responseFromAPICall= orgAPI.Login(ed_email_org_login.getText().toString().trim(),ed_password_org_login.getText().toString().trim());
                    responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                        @Override
                        public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(getContext(), response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                if (response.body().getStatus() &&  response.body().getVarified().equals("y")) {
                                    Url.accessToken = response.body().getAccessToken();
                                    Url.id = response.body().getId();
                                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), OrganizationDashboard.class);
                                    startActivity(intent);

                          }else {
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