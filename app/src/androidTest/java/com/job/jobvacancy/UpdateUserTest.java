package com.job.jobvacancy;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class UpdateUserTest {
//
    @Rule
    public ActivityTestRule<UpdateUser> updateUser =
            new ActivityTestRule<>(UpdateUser.class);

    @Test
    public  void EnterUserName() {
        onView(withId(R.id.ed_name_user_update))
                .perform(typeText("Abhinav Bhattari"));
        onView(withId(R.id.ed_name_user_update))
                .check(matches(withText("Abhinav Bhattari")));
    }

    @Test
    public  void Entercontact() {
        onView(withId(R.id.ed_contact_user_update))
                .perform(typeText("9813457541"));

        onView(withId(R.id.ed_contact_user_update))
                .perform(typeText("9813457541"));
    }

    @Test
    public  void Enteraddress() {
        onView(withId(R.id.ed_address_user_update))
                .perform(typeText("Kathmandu"));
        onView(withId(R.id.ed_address_user_update))
                .check(matches(withText("Kathmandu")));
    }





}
