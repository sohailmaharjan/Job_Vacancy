package com.job.jobvacancy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Login;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.R;
import com.job.jobvacancy.UpdateOrganization;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Org_Profile extends Fragment {
    private ImageView img_logout;
    private MaterialToolbar toolbar;
    private TextView tv_name_org_profile,tv_email_org_profile,tv_address_org_profile,tv_contact_org_profile;
    private Button btn_edid_org;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_org__profile, container, false);
        tv_email_org_profile=view.findViewById(R.id.tv_email_org_profile);
        tv_name_org_profile=view.findViewById(R.id.tv_name_org_profile);
        tv_address_org_profile=view.findViewById(R.id.tv_address_org_profile);
        tv_contact_org_profile=view.findViewById(R.id.tv_contact_org_profile);
        toolbar = view.findViewById(R.id.toolbar_org_profile);
        img_logout = view.findViewById(R.id.img_org_logout);
        btn_edid_org = view.findViewById(R.id.btn_edit_org_profile);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url.accessToken="";
                Url.id=0;
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);

            }
        });

        btn_edid_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateOrganization.class);
                startActivity(intent);

            }
        });




        OrganizationAPI orgAPI=Url.getInstance().create(OrganizationAPI.class);
        Call<OrganizationModel> orgModelCall= orgAPI.GetProfile(Url.accessToken,Url.id);
        orgModelCall.enqueue(new Callback<OrganizationModel>() {
            @Override
            public void onResponse(Call<OrganizationModel> call, Response<OrganizationModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Cannot find org details", Toast.LENGTH_SHORT).show();
                } else {
                    tv_email_org_profile.setText(response.body().getEmail());
                    tv_name_org_profile.setText(response.body().getName());
                    tv_address_org_profile.setText(response.body().getAddress());
                    tv_contact_org_profile.setText(response.body().getContact());

                }
            }

            @Override
            public void onFailure(Call<OrganizationModel> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

        return view;


    }
}