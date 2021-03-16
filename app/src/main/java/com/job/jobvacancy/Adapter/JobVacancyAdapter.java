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
import com.job.jobvacancy.R;
import com.job.jobvacancy.ViewJobVacancy;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobVacancyAdapter extends RecyclerView.Adapter<JobVacancyAdapter.JobVancayHolder>implements Filterable {

    Context context;
    List<JobModel> jobDetailsModels;
    List<JobModel> jobDetailsModelsfilter;

    public JobVacancyAdapter(Context context, List<JobModel> jobDetailsModels) {
        this.context = context;
        this.jobDetailsModels = jobDetailsModels;
        this.jobDetailsModelsfilter = jobDetailsModels;
    }

    @NonNull
    @Override
    public JobVancayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_job_description, parent, false );
        return new JobVacancyAdapter.JobVancayHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull JobVancayHolder holder, final int position) {
        holder.layout_job_description.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.tv_date.setText(jobDetailsModelsfilter.get(position).getDeadline());
        holder.tv_title.setText(jobDetailsModelsfilter.get(position).getTitle());
        holder.tv_organization_name.setText(jobDetailsModelsfilter.get(position).getName());
        holder.tv_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewJobVacancy.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("job_id_view_job",jobDetailsModelsfilter.get(position).getJob_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobDetailsModelsfilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Key= charSequence.toString();
                if(Key.isEmpty()){
                    jobDetailsModelsfilter=jobDetailsModels;
                }else{
                    List<JobModel> listFiltered = new ArrayList<>();
                    for(JobModel row :jobDetailsModels){
                        if(row.getTitle().toLowerCase().contains(Key.toLowerCase())){
                            listFiltered.add(row);
                        }
                    } jobDetailsModelsfilter=listFiltered;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=jobDetailsModelsfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                jobDetailsModelsfilter=(List<JobModel>) filterResults.values;
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
