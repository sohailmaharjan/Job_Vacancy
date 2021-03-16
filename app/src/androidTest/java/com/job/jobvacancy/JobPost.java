package com.job.jobvacancy;

import androidx.test.rule.ActivityTestRule;

import com.job.jobvacancy.Fragments.Org_Post_Job;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class JobPost {
//
    @Rule
    public ActivityTestRule<OrganizationDashboard> jobTest =

            new ActivityTestRule<>(OrganizationDashboard.class);

    @Before
    public void init(){
        jobTest.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }


    @Test
    public  void enter_title() {
        onView(withId(R.id.ed_title_job_post))
                .perform(typeText("Android Developer"));
        onView(withId(R.id.ed_title_job_post))
                .check(matches(withText("Android Developer")));

    }

        public  void enterJobCatogory()
        {
            onView(withId(R.id.ed_job_category_job_post))
                    .perform(typeText("IT and Computer"));

            onView(withId(R.id.ed_job_category_job_post))
                    .check(matches(withText("IT and Computer")));
        }
        public  void enter_location()
            {
                onView(withId(R.id.ed_job_location_job_post))
                    .perform(typeText("Kathmandu"));
                onView(withId(R.id.ed_job_location_job_post))
                .check(matches(withText("Kathmandu")));
    }

    public  void enter_educationLevel()
    {
        onView(withId(R.id.ed_education_leveljob_post))
                .perform(typeText("Bachelor Clear"));
        onView(withId(R.id.ed_education_leveljob_post))
                .check(matches(withText("Kathmandu")));

    }

}
