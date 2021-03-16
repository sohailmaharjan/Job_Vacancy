package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.ResponseFromAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganizationSignUp extends AppCompatActivity {
    private Button btn_organization_signup,btn_organization_back;
    private EditText ed_name_org,ed_contact_org,ed_address_org,ed_email_org,ed_password_org,ed_description_org;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_sign_up);
        btn_organization_back=findViewById(R.id.btn_organization_back);
        btn_organization_signup=findViewById(R.id.btn_organization_signup);
        ed_name_org= findViewById(R.id.ed_name_org);
        ed_contact_org= findViewById(R.id.ed_contact_org);
        ed_address_org= findViewById(R.id.ed_address_org);
        ed_email_org= findViewById(R.id.ed_email_orgq);
        ed_password_org= findViewById(R.id.ed_password_orgq);
        ed_description_org= findViewById(R.id.ed_description_orgq);



        btn_organization_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrganizationModel orgModel= new OrganizationModel();
                orgModel.setName(ed_name_org.getText().toString().trim());
                orgModel.setAddress(ed_address_org.getText().toString().trim());
                orgModel.setContact(ed_contact_org.getText().toString().trim());
                orgModel.setImage_name(null);
                orgModel.setVarified("n");;
                orgModel.setEmail(ed_email_org.getText().toString().trim());
                orgModel.setPassword(ed_password_org.getText().toString().trim());
                orgModel.setDescription(ed_description_org.getText().toString().trim());

                OrganizationAPI organizationAPI= Url.getInstance().create(OrganizationAPI.class);
                final  Call<ResponseFromAPI> responseFromAPICall= organizationAPI.SignUp(orgModel);
                responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(OrganizationSignUp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            if(response.body().getStatus()){
                                Intent intent= new Intent(OrganizationSignUp.this, Login.class);
                                Toast.makeText(OrganizationSignUp.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                            } else{
                                Toast.makeText(OrganizationSignUp.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(OrganizationSignUp.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




        btn_organization_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrganizationSignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}