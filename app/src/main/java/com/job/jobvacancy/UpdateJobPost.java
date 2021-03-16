package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.JobAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.ResponseFromAPI;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateJobPost extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText ed_title,ed_location,ed_salary,ed_no_of_vacancy,ed_job_categorty
            ,ed_education_level,ed_pro_skill,ed_experience,ed_job_description;
    private Button btn_update;
    private Spinner spinner_job_type;
    private String[] job_type_list = {"Full Time", "Half Time"};
    private String job_type="";
    private TextView tv_dateline;
    private ImageView back;
    private MaterialToolbar toolbar;
    private int job_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job_post);
        ed_title=findViewById(R.id.ed_title_update);
        ed_job_categorty=findViewById(R.id.ed_job_category_update);
        spinner_job_type=findViewById(R.id.spinner_job_type_update);
        ed_location=findViewById(R.id.ed_job_location_update);
        ed_salary=findViewById(R.id.ed_salary_update);
        tv_dateline=findViewById(R.id.tv_deadline_update);
        ed_no_of_vacancy=findViewById(R.id.ed_no_of_vacancy_updatte);
        ed_education_level=findViewById(R.id.ed_education_level_update);
        ed_pro_skill=findViewById(R.id.ed_pro_skill_ob_post);
        ed_experience=findViewById(R.id.ed_experience_update);
        ed_job_description=findViewById(R.id.ed_job_description_update);
        btn_update=findViewById(R.id.btn_update_job);
        back=findViewById(R.id.img_back_update_job);

        setToolbar();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            job_id = bundle.getInt("job_id_update");
        }

        JobAPI jobAPI= Url.getInstance().create(JobAPI.class);
        Call<JobModel> jobModelCall= jobAPI.GetJobById(Url.accessToken,job_id);
        jobModelCall.enqueue(new Callback<JobModel>() {
            @Override
            public void onResponse(Call<JobModel> call, Response<JobModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UpdateJobPost.this, "Data not found", Toast.LENGTH_SHORT).show();
                }else {
                    ed_title.setText(response.body().getTitle());
                    ed_job_categorty.setText(response.body().getJob_category());
                    job_type=response.body().getJob_type();
                    ed_location.setText(response.body().getLocation());
                    ed_salary.setText(response.body().getSalary());
                    tv_dateline.setText(response.body().getDeadline());
                    ed_no_of_vacancy.setText(String.valueOf(response.body().getNo_of_vacancy()));
                    ed_education_level.setText(response.body().getEducation_level());
                    ed_pro_skill.setText(response.body().getProfessional_skill());
                    ed_experience.setText(response.body().getExperience());
                    ed_job_description.setText(response.body().getJob_description());
                    job_type=response.body().getJob_type();
                }
            }

            @Override
            public void onFailure(Call<JobModel> call, Throwable t) {
                Toast.makeText(UpdateJobPost.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

            }
        });


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter adapter = new ArrayAdapter(UpdateJobPost.this, android.R.layout.simple_spinner_item, job_type_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_job_type.setAdapter(adapter);

        spinner_job_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View views, int i, long l) {
                job_type = job_type_list[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        tv_dateline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JobModel jobModel= new JobModel();
                jobModel.setTitle(ed_title.getText().toString().trim());
                jobModel.setJob_category(ed_job_categorty.getText().toString().trim());
                jobModel.setJob_type(job_type);
                jobModel.setLocation(ed_location.getText().toString().trim());
                jobModel.setSalary(ed_salary.getText().toString().trim());
                jobModel.setDeadline(tv_dateline.getText().toString().trim());
                jobModel.setNo_of_vacancy(Integer.parseInt(ed_no_of_vacancy.getText().toString().trim()));
                jobModel.setEducation_level(ed_education_level.getText().toString().trim());
                jobModel.setProfessional_skill(ed_pro_skill.getText().toString().trim());
                jobModel.setExperience(ed_experience.getText().toString().trim());
                jobModel.setJob_description(ed_job_description.getText().toString().trim());
                jobModel.setOrg_id(Url.id);

                Call<ResponseFromAPI> responseFromAPICall= jobAPI.UpdateJob(Url.accessToken,jobModel,job_id);
                responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(UpdateJobPost.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            if(response.body().getStatus()){
                                Toast.makeText(UpdateJobPost.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UpdateJobPost.this, ViewMyJobOrg.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("job_id_view_job_org",job_id);
                                startActivity(intent);

                            }else{
                                Toast.makeText(UpdateJobPost.this, response.body().getMessage()+"ss", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(UpdateJobPost.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });





    }

    public void DatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateJobPost.this, this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        tv_dateline.setText(year + "-" + (month + 1) + "-" +dayOfMonth );
    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_update_job);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        back = findViewById(R.id.img_back_update_job);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateJobPost.this, ViewMyJobOrg.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("job_id_view_job_org",job_id);
                startActivity(intent);
            }
        });

    }
}



