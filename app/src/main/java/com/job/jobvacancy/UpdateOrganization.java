package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateOrganization extends AppCompatActivity {
    private ImageView back;
    private MaterialToolbar toolbar;
    private EditText ed_name_org_update,ed_email_org_update,ed_address_org_update,ed_contact_org_update,ed_description;
    private Button btn_update_org;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_organization);
        ed_name_org_update=findViewById(R.id.ed_name_org_update);
        ed_email_org_update=findViewById(R.id.ed_email_org_update);
        ed_description=findViewById(R.id.ed_description_org_update);
        ed_address_org_update=findViewById(R.id.ed_address_org_update);
        ed_contact_org_update=findViewById(R.id.ed_contact_org_update);
        back=findViewById(R.id.img_back_update_org);
        btn_update_org=findViewById(R.id.btn_update_org);
        toolbar=findViewById(R.id.toolbar_update_org);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateOrganization.this, OrganizationDashboard.class);
                startActivity(intent);

            }
        });

        OrganizationAPI orgAPI= Url.getInstance().create(OrganizationAPI.class);
        Call<OrganizationModel> userModelCall= orgAPI.GetProfile(Url.accessToken,Url.id);
        userModelCall.enqueue(new Callback<OrganizationModel>() {
            @Override
            public void onResponse(Call<OrganizationModel> call, Response<OrganizationModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UpdateOrganization.this, "Cannot find Organization details", Toast.LENGTH_SHORT).show();
                }else{
                    ed_email_org_update.setText(response.body().getEmail());
                    ed_name_org_update.setText(response.body().getName());
                    ed_address_org_update.setText(response.body().getAddress());
                    ed_contact_org_update.setText(response.body().getContact());
                    ed_description.setText(response.body().getDescription());
                }
            }

            @Override
            public void onFailure(Call<OrganizationModel> call, Throwable t) {
                Toast.makeText(UpdateOrganization.this,t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrganizationModel orgModel= new OrganizationModel();
                orgModel.setName(ed_name_org_update.getText().toString().trim());
                orgModel.setAddress(ed_address_org_update.getText().toString().trim());
                orgModel.setContact(ed_contact_org_update.getText().toString().trim());
                orgModel.setEmail(ed_email_org_update.getText().toString().trim());
                orgModel.setDescription(ed_description.getText().toString().trim());

                OrganizationAPI organizationAPI= Url.getInstance().create(OrganizationAPI.class);
                final  Call<ResponseFromAPI> responseFromAPICall= organizationAPI.UpdateOrg(Url.accessToken,orgModel,Url.id);
                responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UpdateOrganization.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            if(response.body().getStatus()){
                                Intent intent= new Intent(UpdateOrganization.this, OrganizationDashboard.class);
                                Toast.makeText(UpdateOrganization.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            } else{
                                Toast.makeText(UpdateOrganization.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(UpdateOrganization.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}