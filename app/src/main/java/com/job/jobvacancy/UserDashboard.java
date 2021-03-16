package com.job.jobvacancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Fragments.UserAboutUs;
import com.job.jobvacancy.Fragments.UserApplyedJob;
import com.job.jobvacancy.Fragments.UserProfile;
import com.job.jobvacancy.Fragments.UserVacancy;

public class UserDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        BottomNavigationView bottomNav = findViewById(R.id.user_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,
                    new UserVacancy()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_job_vacancy:
                            selectedFragment = new UserVacancy();
                            break;
                        case R.id.nav_job_apply:
                            selectedFragment = new UserApplyedJob();
                            break;
                        case R.id.nav_about:
                            selectedFragment = new UserAboutUs();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new UserProfile();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
