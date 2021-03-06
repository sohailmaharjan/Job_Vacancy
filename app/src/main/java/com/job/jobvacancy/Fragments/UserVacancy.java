package com.job.jobvacancy.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.job.jobvacancy.API.JobAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Adapter.JobVacancyAdapter;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserVacancy extends Fragment {
    private RecyclerView recyclerView;
    private EditText searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_vancacy, container, false);
        recyclerView= view.findViewById(R.id.recyclear_view_job_vacancy);
        searchButton= view.findViewById(R.id.ed_search_job_vacancy);

        JobAPI jobAPI = Url.getInstance().create(JobAPI.class);
        Call<List<JobModel>> listCall= jobAPI.GetAll(Url.accessToken);
        listCall.enqueue(new Callback<List<JobModel>>() {
            @Override
            public void onResponse(Call<List<JobModel>> call, Response<List<JobModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Job had not post yet", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (response.body().isEmpty()){
                        Toast.makeText(getContext(), "Job had not posted yet", Toast.LENGTH_SHORT).show();
                    }else{
                        List<JobModel> jobModels= response.body();
                        final JobVacancyAdapter jobVacancyAdapter= new JobVacancyAdapter(getContext(),jobModels );
                        recyclerView.setAdapter(jobVacancyAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        searchButton.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                jobVacancyAdapter.getFilter().filter(charSequence);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });

                    }

                }
            }

            @Override
            public void onFailure(Call<List<JobModel>> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}
