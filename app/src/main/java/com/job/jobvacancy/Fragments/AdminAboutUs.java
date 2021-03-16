package com.job.jobvacancy.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Login;
import com.job.jobvacancy.R;

public class AdminAboutUs extends Fragment {
    private MaterialToolbar toolbar;
    private ImageView img_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_about_us, container, false);
        toolbar = view.findViewById(R.id.toolbar_admin_about_us);
        img_logout = view.findViewById(R.id.img_admin_logout);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url.accessToken="";
                Url.id=0;
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);

            }
        });
        return view;
    }
}