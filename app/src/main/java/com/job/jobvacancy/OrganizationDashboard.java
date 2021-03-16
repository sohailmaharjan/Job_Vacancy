package com.job.jobvacancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.job.jobvacancy.Fragments.Org_About_Us;
import com.job.jobvacancy.Fragments.Org_Job_List;
import com.job.jobvacancy.Fragments.Org_Post_Job;
import com.job.jobvacancy.Fragments.Org_Profile;
import com.job.jobvacancy.Fragments.UserAboutUs;
import com.job.jobvacancy.Fragments.UserApplyedJob;
import com.job.jobvacancy.Fragments.UserProfile;
import com.job.jobvacancy.Fragments.UserVacancy;

public class OrganizationDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_dashboard);
        BottomNavigationView bottomNav = findViewById(R.id.org_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.org_fragment_container,
                    new Org_Job_List()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_post_job_list:
                            selectedFragment = new Org_Job_List();
                            break;
                        case R.id.nav_post_job:
                            selectedFragment = new Org_Post_Job();
                            break;
                        case R.id.nav_about_org:
                            selectedFragment = new Org_About_Us();
                            break;
                        case R.id.nav_profile_org:
                            selectedFragment = new Org_Profile();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.org_fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };


}