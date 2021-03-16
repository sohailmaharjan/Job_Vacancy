package com.job.jobvacancy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.Login;
import com.job.jobvacancy.Model.UserModel;
import com.job.jobvacancy.R;
import com.job.jobvacancy.UpdateUser;
import com.job.jobvacancy.UserDashboard;
import com.job.jobvacancy.ViewJobVacancy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfile extends Fragment {
    private ImageView img_user_profile,img_logout;
    private MaterialToolbar toolbar;
    private TextView tv_name_user_profile,tv_gender_user_profile,tv_email_user_profile,tv_birth_user_profile,tv_address_user_profile,
            tv_contact_user_profile, tv_education_user_profile,tv_professional_user_profile,tv_experience_user_profile;
    private Button btn_edit_profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_profile, container, false);
        tv_email_user_profile=view.findViewById(R.id.tv_email_user_profile);
        tv_name_user_profile=view.findViewById(R.id.tv_name_user_profile);
        tv_gender_user_profile=view.findViewById(R.id.tv_gender_user_profile);
        tv_birth_user_profile=view.findViewById(R.id.tv_birth_user_profile);
        tv_address_user_profile=view.findViewById(R.id.tv_address_user_profile);
        tv_contact_user_profile=view.findViewById(R.id.tv_contact_user_profile);
        tv_education_user_profile=view.findViewById(R.id.tv_education_user_profile);
        tv_professional_user_profile=view.findViewById(R.id.tv_professional_user_profile);
        tv_experience_user_profile=view.findViewById(R.id.tv_experience_user_profile);
        tv_address_user_profile=view.findViewById(R.id.tv_address_user_profile);
        btn_edit_profile=view.findViewById(R.id.btn_edit_profile);
        toolbar = view.findViewById(R.id.toolbar_user_profile);
        img_logout = view.findViewById(R.id.img_logout);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url.accessToken="";
                Url.id=0;
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);

            }
        });

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateUser.class);
                startActivity(intent);
            }
        });

        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<UserModel> userModelCall= userAPI.GetProfile(Url.accessToken,Url.id);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Cannot find user details", Toast.LENGTH_SHORT).show();
                }else{
                    tv_email_user_profile.setText(response.body().getEmail());
                    tv_name_user_profile.setText(response.body().getName());
                    tv_birth_user_profile.setText(response.body().getBirth_date());
                    tv_gender_user_profile.setText("Gender: "+response.body().getGender());
                    tv_address_user_profile.setText("Address: "+response.body().getAddress());
                    tv_contact_user_profile.setText("Contact: "+response.body().getContact());
                    tv_education_user_profile.setText("Education Level: "+response.body().getEducation_level());
                    tv_professional_user_profile.setText("Professional Skill: "+response.body().getProfessional_skill());
                    tv_experience_user_profile.setText("Experience: "+response.body().getExperience());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }



}
