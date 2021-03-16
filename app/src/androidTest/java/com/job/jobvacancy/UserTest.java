package com.job.jobvacancy;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class UserTest {
//
    @Rule
    public ActivityTestRule<UserSignUp> usertest =

            new ActivityTestRule<>(UserSignUp.class);


    @Test
    public  void enter_name() {
        onView(withId(R.id.ed_name_user))
                .perform(typeText("Abhinav Bhattari"));


        onView(withId(R.id.ed_name_org))
                .check(matches(withText("softwarica")));

    }

        public  void enter_address()
        {

            onView(withId(R.id.ed_address_user))
                    .perform(typeText("Kathmandu"));

            onView(withId(R.id.ed_address_user))
                    .check(matches(withText("Kathmandu")));
        }
            public  void enter_contact()
            {
                onView(withId(R.id.ed_contact_user))
                    .perform(typeText("9813457541"));
        onView(withId(R.id.ed_contact_user))
                .check(matches(withText("9813457541")));

    }

}
