package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.ResponseFromAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrg extends AppCompatActivity {
    private TextView tv_name,tv_description,tv_contact,tv_email,tv_address;
    private Button btn_verify,btn_cancle;
    private MaterialToolbar toolbar;
    private ImageView back;
    private int org_id;
    private String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_org);
        tv_name= findViewById(R.id.tv_name_view_org);
        tv_description= findViewById(R.id.tv_description_view_org);
        tv_contact= findViewById(R.id.tv_contact_view_org);
        tv_email= findViewById(R.id.tv_email_view_org);
        tv_address= findViewById(R.id.tv_address_view_org);
        btn_verify= findViewById(R.id.btn_verified_org);
        btn_cancle= findViewById(R.id.btn_cancle_org);
        back= findViewById(R.id.img_back_view_org);

        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            org_id = bundle.getInt("org_id_view_org");
            verify = bundle.getString("verify");

        }
        if (verify.equals("n")){
            btn_verify.setVisibility(View.VISIBLE);

        }else{
            btn_verify.setVisibility(View.GONE);

        }


        OrganizationAPI organizationAPI= Url.getInstance().create(OrganizationAPI.class);
        Call<OrganizationModel> organizationModelCall=organizationAPI.GetProfile(Url.accessToken,org_id);
        organizationModelCall.enqueue(new Callback<OrganizationModel>() {
            @Override
            public void onResponse(Call<OrganizationModel> call, Response<OrganizationModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ViewOrg.this, "Data not found", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    tv_name.setText(response.body().getName());
                    tv_description.setText(response.body().getDescription());
                    tv_address.setText("Address: " + response.body().getAddress());
                    tv_contact.setText("Contact: " + response.body().getContact());
                    tv_email.setText("Email: " + response.body().getVarified());


                }
                }

            @Override
            public void onFailure(Call<OrganizationModel> call, Throwable t) {
                Toast.makeText(ViewOrg.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });



        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Call<ResponseFromAPI> responseBodyCall=organizationAPI.Verify(Url.accessToken,org_id);
                 responseBodyCall.enqueue(new Callback<ResponseFromAPI>() {
                     @Override
                     public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                         if(!response.isSuccessful()){
                             Toast.makeText(ViewOrg.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                             return;
                         }else{
                             if (response.body().getStatus()){
                                 Toast.makeText(ViewOrg.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                 Intent intent=new Intent(ViewOrg.this, AdminDashboard.class);
                                 startActivity(intent);
                                 finish();
                             }else{
                                 Toast.makeText(ViewOrg.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();

                             }
                         }
                     }

                     @Override
                     public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                         Toast.makeText(ViewOrg.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                     }
                 });



            }

        });


     btn_cancle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent intent= new Intent(ViewOrg.this,AdminDashboard.class);
        startActivity(intent);
        finish();

        }

    });

}


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_view_org);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        back = findViewById(R.id.img_back_view_org);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewOrg.this, AdminDashboard.class);
                startActivity(intent);
                finish();
            }
        });

    }
}