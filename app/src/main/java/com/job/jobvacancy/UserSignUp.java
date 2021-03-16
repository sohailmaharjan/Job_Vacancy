package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.API.UserAPI;
import com.job.jobvacancy.Model.ResponseFromAPI;
import com.job.jobvacancy.Model.UserModel;

import java.util.Calendar;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button btn_signUp,btn_back;
    private EditText ed_name_user,ed_birthdate_user,ed_contact_user,ed_address_user,ed_email_user,ed_password_user;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        btn_back= findViewById(R.id.btn_usersignup_back);
        btn_signUp= findViewById(R.id.btn_user_signUp);
        ed_name_user= findViewById(R.id.ed_name_user);
        ed_birthdate_user= findViewById(R.id.ed_birthdate_user);
        ed_contact_user= findViewById(R.id.ed_contact_user);
        ed_address_user= findViewById(R.id.ed_address_user);
        ed_email_user= findViewById(R.id.ed_email_user);
        ed_password_user= findViewById(R.id.ed_password_user);

        rgGender= findViewById(R.id.rgGender);

        ed_birthdate_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker();
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserSignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   int selectedId = rgGender.getCheckedRadioButtonId();
                   rbGender = findViewById(selectedId);
                   String gender = rbGender.getText().toString().trim();
                   UserModel userModel = new UserModel();
                   userModel.setName(ed_name_user.getText().toString().trim());
                   userModel.setBirth_date(ed_birthdate_user.getText().toString().trim());
                   userModel.setContact(ed_contact_user.getText().toString().trim());
                   userModel.setGender(gender);
                   userModel.setAddress(ed_address_user.getText().toString().trim());
                   userModel.setEmail(ed_email_user.getText().toString().trim());
                   userModel.setPassword(ed_password_user.getText().toString().trim());
                   userModel.setImage_name(null);
                   userModel.setUser_type("User");
                   userModel.setEducation_level(null);
                   userModel.setProfessional_skill(null);
                   userModel.setExperience(null);

                   UserAPI userAPI= Url.getInstance().create(UserAPI.class);

                   final Call<ResponseFromAPI> responseFromAPICall= userAPI.SignUp(userModel);
                   responseFromAPICall.enqueue(new Callback<ResponseFromAPI>() {
                       @Override
                       public void onResponse(Call<ResponseFromAPI> call, Response<ResponseFromAPI> response) {
                           if(!response.isSuccessful()){
                               Toast.makeText(UserSignUp.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                               return;
                           }else {
                               if(response.body().getStatus()){
                                   Intent intent = new Intent(UserSignUp.this, Login.class);
                                   Toast.makeText(UserSignUp.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                   startActivity(intent);
                                   finish();
                               } else{
                                   Toast.makeText(UserSignUp.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                                   return;
                               }
                           }
                       }

                       @Override
                       public void onFailure(Call<ResponseFromAPI> call, Throwable t) {
                           Toast.makeText(UserSignUp.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(UserSignUp.this, this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        ed_birthdate_user.setText(year + "-" + (month + 1) + "-" +dayOfMonth );
    }

}
