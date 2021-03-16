package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUser extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button btn_update_user;
    private ImageView img_back_update_user;
    private EditText ed_name,ed_birthdate,ed_contact,ed_address,
            ed_eduaction,ed_professional,ed_experience;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private String gender;
    private MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        ed_name= findViewById(R.id.ed_name_user_update);
        ed_birthdate= findViewById(R.id.ed_birthdate_user_update);
        ed_contact= findViewById(R.id.ed_contact_user_update);
        ed_address= findViewById(R.id.ed_address_user_update);
        ed_eduaction= findViewById(R.id.ed_education_user_update);
        ed_professional= findViewById(R.id.ed_professional_user_update);
        ed_experience= findViewById(R.id.ed_experience_user_update);
        btn_update_user= findViewById(R.id.btn_update_user);
        toolbar= findViewById(R.id.toolbar_update_user);
        img_back_update_user= findViewById(R.id.img_back_update_user);
        rgGender= findViewById(R.id.rgGender_update);

        UserAPI userAPI= Url.getInstance().create(UserAPI.class);
        Call<UserModel> userModelCall= userAPI.GetProfile(Url.accessToken,Url.id);
        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(UpdateUser.this, "User Not found", Toast.LENGTH_SHORT).show();
                }else {
                    ed_name.setText(response.body().getName());
                    ed_address.setText(response.body().getAddress());
                    ed_birthdate.setText(response.body().getBirth_date());
                    gender=response.body().getGender();
                    ed_contact.setText(response.body().getContact());
                    ed_eduaction.setText(response.body().getEducation_level());
                    ed_professional.setText(response.body().getProfessional_skill());
                    ed_experience.setText(response.body().getExperience());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(UpdateUser.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

        img_back_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateUser.this, UserDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rgGender.getCheckedRadioButtonId();
                rbGender = findViewById(selectedId);
                gender = rbGender.getText().toString().trim();
                UserModel userModel = new UserModel();
                userModel.setName(ed_name.getText().toString().trim());
                userModel.setBirth_date(ed_birthdate.getText().toString().trim());
                userModel.setContact(ed_contact.getText().toString().trim());
                userModel.setGender(gender);
                userModel.setAddress(ed_address.getText().toString().trim());
                userModel.setEducation_level(ed_eduaction.getText().toString().trim());
                userModel.setProfessional_skill(ed_professional.getText().toString().trim());
                userModel.setExperience(ed_experience.getText().toString().trim());



                final Call<ResponseFromAPI> responseFromAPICall= userAPI.UpdateUser(Url.accessToken,userModel,Url.id);
                responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                    @Override
                    public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UpdateUser.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            if(response.body().getStatus()){
                                Intent intent = new Intent(UpdateUser.this, UserDashboard.class);
                                Toast.makeText(UpdateUser.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                finish();
                            } else{
                                Toast.makeText(UpdateUser.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                        Toast.makeText(UpdateUser.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        ed_birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });
    }


    public void DatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateUser.this, this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        ed_birthdate.setText(year + "-" + (month + 1) + "-" +dayOfMonth );
    }

}