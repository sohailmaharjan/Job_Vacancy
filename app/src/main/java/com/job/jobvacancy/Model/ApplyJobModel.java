package com.job.jobvacancy.Model;

public class ApplyJobModel {
    private int job_id,user_id,job_apply_id;
    private String apply_date;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getJob_apply_id() {
        return job_apply_id;
    }

    public void setJob_apply_id(int job_apply_id) {
        this.job_apply_id = job_apply_id;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }
}
