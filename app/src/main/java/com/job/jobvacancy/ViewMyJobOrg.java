package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.JobAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.ResponseFromAPI;

import java.nio.channels.InterruptedByTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMyJobOrg extends AppCompatActivity {
    private TextView tv_name, tv_description, tv_title, tv_job_category, tv_job_type, tv_location, tv_salary, tv_deadline, tv_postdate, tv_education, tv_professional, tv_experience, tv_job_description;
    private Button btn_update, btn_delete;
    private MaterialToolbar toolbar;
    private ImageView back;
    private int job_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_job_org);
        tv_name = findViewById(R.id.tv_name_view_job_org);
        tv_description = findViewById(R.id.tv_description_view_job_org);
        tv_title = findViewById(R.id.tv_title_view_job_org);
        tv_job_category = findViewById(R.id.tv_job_category_view_job_org);
        tv_job_type = findViewById(R.id.tv_job_type_view_job_org);
        tv_location = findViewById(R.id.tv_location_view_job_org);
        tv_salary = findViewById(R.id.tv_salary_view_job_org);
        tv_deadline = findViewById(R.id.tv_deadline_view_job_org);
        tv_postdate = findViewById(R.id.tv_postdate_view_job_org);
        tv_education = findViewById(R.id.tv_education_view_job_org);
        tv_professional = findViewById(R.id.tv_professional_view_job_org);
        tv_experience = findViewById(R.id.tv_experience_view_job_org);
        tv_job_description = findViewById(R.id.tv_job_description_view_job_org);
        btn_update = findViewById(R.id.btn_update_job);
        btn_delete = findViewById(R.id.btn_delete_job);

        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            job_id = bundle.getInt("job_id_view_job_org");

        }

        JobAPI jobAPI = Url.getInstance().create(JobAPI.class);
        Call<JobModel> jobModelCall = jobAPI.GetJobById(Url.accessToken, job_id);
        jobModelCall.enqueue(new Callback<JobModel>() {
            @Override
            public void onResponse(Call<JobModel> call, Response<JobModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewMyJobOrg.this, "Data not found", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    tv_name.setText(response.body().getName());
                    tv_description.setText(response.body().getDescription());
                    tv_title.setText(response.body().getTitle());
                    tv_job_category.setText("Job Category: " + response.body().getJob_category());
                    tv_job_type.setText("Job Type: " + response.body().getJob_type());
                    tv_location.setText("Job Location : " + response.body().getLocation());
                    tv_deadline.setText("Deadline: " + response.body().getDeadline());
                    tv_salary.setText("Salary: Rs " + response.body().getSalary());
                    tv_postdate.setText("Post Date: " + response.body().getPostdate());
                    tv_education.setText("Education Level: " + response.body().getEducation_level());
                    tv_professional.setText("Professional Skill : " + response.body().getProfessional_skill());
                    tv_experience.setText("Experience: " + response.body().getExperience());
                    tv_job_description.setText(response.body().getJob_description());
                }
            }

            @Override
            public void onFailure(Call<JobModel> call, Throwable t) {
                Toast.makeText(ViewMyJobOrg.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewMyJobOrg.this, UpdateJobPost.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("job_id_update",job_id);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewMyJobOrg.this);
                        // Setting Alert Dialog Title
                        alertDialogBuilder.setTitle("Delete..!!!");
                        // Icon Of Alert Dialog
//                        alertDialogBuilder.setIcon(R.drawable.alertdialog);
                        // Setting Alert Dialog Message
                        alertDialogBuilder.setMessage("Are you sure,you want to delete this job");
                        alertDialogBuilder.setCancelable(false);

                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Call<ResponseFromAPI> fromAPICall=jobAPI.DeleteJob(Url.accessToken,job_id);
                                fromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                                    @Override
                                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                                        if(!response.body().getStatus()){
                                            Toast.makeText(ViewMyJobOrg.this,response.body().getMessage()+ "", Toast.LENGTH_SHORT).show();
                                            return;
                                        }else{
                                            if (response.body().getStatus()){
                                                Toast.makeText(ViewMyJobOrg.this,response.body().getMessage()+ "", Toast.LENGTH_SHORT).show();
                                                Intent intent= new Intent(ViewMyJobOrg.this,OrganizationDashboard.class);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                Toast.makeText(ViewMyJobOrg.this,response.body().getMessage()+ "", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                                        Toast.makeText(ViewMyJobOrg.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });

                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"You clicked on No",Toast.LENGTH_SHORT).show();
                            }
                        });
                        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });



    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_view_job_org);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        back = findViewById(R.id.img_back_view_job_org);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMyJobOrg.this, OrganizationDashboard.class);
                startActivity(intent);
                finish();
            }
        });

    }

}