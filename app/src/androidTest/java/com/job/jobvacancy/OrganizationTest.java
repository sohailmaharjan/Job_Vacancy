package com.job.jobvacancy;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class OrganizationTest {
    @Rule
    public ActivityTestRule<OrganizationSignUp> signupTestrule =

            new ActivityTestRule<>(OrganizationSignUp.class);

    @Test
    public  void entername() {
        onView(withId(R.id.ed_name_org))
                .perform(typeText("softwarica"));
        onView(withId(R.id.ed_name_org))
                .check(matches(withText("softwarica")));
    }

    @Test
    public  void enterContact() {
        onView(withId(R.id.ed_contact_org))
                .perform(typeText("9876543211"));
        onView(withId(R.id.ed_contact_org))
                .check(matches(withText("9876543211")));
    }

    @Test
    public  void Enteraddress() {
        onView(withId(R.id.ed_address_org))
                .perform(typeText("Kathmandu"));
        onView(withId(R.id.ed_address_org))
                .check(matches(withText("Kathmandu")));
    }

}
