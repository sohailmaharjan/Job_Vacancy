package com.job.jobvacancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.job.jobvacancy.Fragments.AdminAboutUs;
import com.job.jobvacancy.Fragments.AdminNewRequest;
import com.job.jobvacancy.Fragments.AdminOrg_List;
import com.job.jobvacancy.Fragments.UserAboutUs;
import com.job.jobvacancy.Fragments.UserApplyedJob;
import com.job.jobvacancy.Fragments.UserProfile;
import com.job.jobvacancy.Fragments.UserVacancy;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        BottomNavigationView bottomNav = findViewById(R.id.admin_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,
                    new AdminOrg_List()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_org_list:
                            selectedFragment = new AdminOrg_List();
                            break;
                        case R.id.nav_new_request:
                            selectedFragment = new AdminNewRequest();
                            break;
                        case R.id.nav_about_admin:
                            selectedFragment = new AdminAboutUs();
                            break;


                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
