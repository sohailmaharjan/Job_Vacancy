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

import com.job.jobvacancy.API.OrganizationAPI;
import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Adapter.OrgListAdapter;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminNewRequest extends Fragment {
    private RecyclerView recyclerView;
    private EditText searchButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_new_request, container, false);
        recyclerView= view.findViewById(R.id.recyclear_view_new_request);
        searchButton= view.findViewById(R.id.ed_search_new_request);

        OrganizationAPI organizationAPI = Url.getInstance().create(OrganizationAPI.class);
        Call<List<OrganizationModel>> listCall= organizationAPI.GetNew(Url.accessToken);
        listCall.enqueue(new Callback<List<OrganizationModel>>() {
            @Override
            public void onResponse(Call<List<OrganizationModel>> call, Response<List<OrganizationModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "No New Organization Request ", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (response.body().isEmpty()){
                        Toast.makeText(getContext(), "No New Organization Request ", Toast.LENGTH_SHORT).show();
                    }else{
                        List<OrganizationModel> organizationModels= response.body();
                        final OrgListAdapter orgListAdapter= new OrgListAdapter(getContext(),organizationModels );
                        recyclerView.setAdapter(orgListAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        searchButton.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                orgListAdapter.getFilter().filter(charSequence);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });

                    }

                }
            }

            @Override
            public void onFailure(Call<List<OrganizationModel>> call, Throwable t) {
                Toast.makeText(getContext(),t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}