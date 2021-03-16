package com.job.jobvacancy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.R;
import com.job.jobvacancy.ViewJobVacancy;
import com.job.jobvacancy.ViewOrg;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrgListAdapter extends RecyclerView.Adapter<OrgListAdapter.JobVancayHolder>implements Filterable {

    Context context;
    List<OrganizationModel> organizationModels;
    List<OrganizationModel> organizationModelsFilters;

    public OrgListAdapter(Context context, List<OrganizationModel> organizationModels) {
        this.context = context;
        this.organizationModels = organizationModels;
        this.organizationModelsFilters = organizationModels;
    }

    @NonNull
    @Override
    public JobVancayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_job_description, parent, false );
        return new OrgListAdapter.JobVancayHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull JobVancayHolder holder, final int position) {
        holder.layout_job_description.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tv_date.setText(organizationModelsFilters.get(position).getContact());
        holder.tv_title.setText(organizationModelsFilters.get(position).getAddress());
        holder.tv_organization_name.setText(organizationModelsFilters.get(position).getName());
        holder.tv_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewOrg.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("org_id_view_org",organizationModelsFilters.get(position).getOrg_id());
                intent.putExtra("verify",organizationModelsFilters.get(position).getVarified());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return organizationModelsFilters.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    organizationModelsFilters=organizationModels;
                }else{
                    List<OrganizationModel> listFiltered = new ArrayList<>();
                    for(OrganizationModel row :organizationModelsFilters){
                        if(row.getName().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } organizationModelsFilters=listFiltered;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=organizationModelsFilters;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                organizationModelsFilters=(List<OrganizationModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class JobVancayHolder extends RecyclerView.ViewHolder{
        private TextView tv_date,tv_title,tv_organization_name,tv_view_more;
        private LinearLayout layout_job_description;
        private CircleImageView img_organization_job_description;
        public JobVancayHolder(@NonNull View itemView) {
            super(itemView);
            layout_job_description= itemView.findViewById(R.id.layout_job_description);
            tv_date= itemView.findViewById(R.id.tv_date_job_description_layout);
            tv_title= itemView.findViewById(R.id.tv_title_job_description_layout);
            img_organization_job_description= itemView.findViewById(R.id.img_organization_job_description);
            tv_organization_name= itemView.findViewById(R.id.tv_organization_name_job_description_layout);
            tv_view_more= itemView.findViewById(R.id.tv_view_more_job_desctiption_layout);

        }
    }
}
