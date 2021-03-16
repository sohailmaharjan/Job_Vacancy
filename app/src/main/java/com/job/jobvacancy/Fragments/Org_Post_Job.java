package com.job.jobvacancy.Fragments;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.job.jobvacancy.API.JobAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.JobPostNotification;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.R;
import com.job.jobvacancy.UserDashboard;


import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.job.jobvacancy.JobPostNotification.CHANNEL_1;

public class Org_Post_Job extends Fragment implements DatePickerDialog.OnDateSetListener {
    private EditText ed_title,ed_location,ed_salary,ed_no_of_vacancy,ed_job_categorty
            ,ed_education_level,ed_pro_skill,ed_experience,ed_job_description;
    private Button btn_post_job;
    private Spinner spinner_job_type;
    private String[] job_type_list = {"Full Time", "Half Time"};
    private String job_type="";
    private TextView tv_dateline;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_org__post__job, container, false);
        ed_title=view.findViewById(R.id.ed_title_job_post);
        ed_job_categorty=view.findViewById(R.id.ed_job_category_job_post);
        spinner_job_type=view.findViewById(R.id.spinner_job_type);
        ed_location=view.findViewById(R.id.ed_job_location_job_post);
        ed_salary=view.findViewById(R.id.ed_salary_job_post);
        tv_dateline=view.findViewById(R.id.tv_deadline_job_post);
        ed_no_of_vacancy=view.findViewById(R.id.ed_no_of_vacancy_job_post);
        ed_education_level=view.findViewById(R.id.ed_education_leveljob_post);
        ed_pro_skill=view.findViewById(R.id.ed_pro_skill_ob_post);
        ed_experience=view.findViewById(R.id.ed_experience_job_post);
        ed_experience=view.findViewById(R.id.ed_experience_job_post);
        ed_job_description=view.findViewById(R.id.ed_job_description_job_post);
        btn_post_job=view.findViewById(R.id.btn_post_job);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, job_type_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner_job_type.setAdapter(adapter);

        btn_post_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JobModel jobModel = new JobModel();
                jobModel.setTitle(ed_title.getText().toString().trim());
                jobModel.setJob_category(ed_title.getText().toString().trim());
                jobModel.setJob_type(job_type);
                jobModel.setLocation(ed_location.getText().toString().trim());
                jobModel.setSalary(ed_salary.getText().toString().trim());
                jobModel.setDeadline(tv_dateline.getText().toString().trim());
                jobModel.setNo_of_vacancy(Integer.parseInt(ed_no_of_vacancy.getText().toString().trim()));
                jobModel.setExperience(ed_experience.getText().toString().trim());
                jobModel.setEducation_level(ed_education_level.getText().toString().trim());
                jobModel.setProfessional_skill(ed_pro_skill.getText().toString().trim());
                jobModel.setJob_description(ed_job_description.getText().toString().trim());
                jobModel.setOrg_id(Url.id);



                JobAPI jobAPI= Url.getInstance().create(JobAPI.class);
                Call<ResponseFromAPI> call= jobAPI.JobPost(Url.accessToken,jobModel);
                call.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {

                            if(response.body().getStatus()){
                                Toast.makeText(getContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), UserDashboard.class);
                                sendOnChannel1(v);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                            }

                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(getContext(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });




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


        return view;
    }

    public void DatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        tv_dateline.setText(year + "-" + (month + 1) + "-" +dayOfMonth );
    }

    public void sendOnChannel1(View v) {
        String title = "New job";
        String message = ed_title.getText().toString().trim();
        Toast.makeText(getContext(), "sss", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getContext(), CHANNEL_1)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(1, notification.build());
    }

}