package com.job.jobvacancy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.job.jobvacancy.Adapter.LoginViewPagerAdapter;
import com.job.jobvacancy.Fragments.AdminUser;
import com.job.jobvacancy.Fragments.LoginUser;
import com.job.jobvacancy.Fragments.OrganizationUser;

public class Login extends AppCompatActivity {
    private ViewPager loginViewPager;
    private TabLayout loginTablayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginTablayout = findViewById(R.id.loginTablayout);
        loginViewPager=findViewById(R.id.loginViewPager);

        LoginViewPagerAdapter adapter= new LoginViewPagerAdapter(getSupportFragmentManager());
        adapter.addLoginFragment(new LoginUser(),"User");
        adapter.addLoginFragment(new OrganizationUser(),"Organization");
        adapter.addLoginFragment(new AdminUser(),"Admin");

        loginViewPager.setAdapter(adapter);
        loginTablayout.setupWithViewPager(loginViewPager);
    }
}