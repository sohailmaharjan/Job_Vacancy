package com.job.jobvacancy;

import android.os.UserManager;

import com.job.jobvacancy.API.Url;
import com.job.jobvacancy.Model.JobModel;
import com.job.jobvacancy.Model.OrganizationModel;
import com.job.jobvacancy.Model.UserModel;
import com.job.jobvacancy.UnitTesting.OrganizationTesting;
import com.job.jobvacancy.UnitTesting.UserTesting;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    @Test
    public void UserLoginTest(){
        UserTesting userTesting = new UserTesting();
        Boolean result= userTesting.LoginUser("abbinav@gmail.com","Abhinav@gmail.com");
        assertEquals(true,result);
    }

    @Test
    public void GetUserProfile(){
        UserTesting userTesting = new UserTesting();
        Boolean result =userTesting.GetUserProfile(2);
        assertEquals(true,result);
    }

    @Test
    public void UserSignupTest(){
        UserModel userModel= new UserModel();
        userModel.setName("Abhinav Bhattari");
        userModel.setBirth_date("2002/12/2");
        userModel.setGender("Male");
        userModel.setAddress("Kathmandu,Nepal");
        userModel.setContact("9876543212");
        userModel.setUser_type("User");
        userModel.setEmail("abhinav@gmail.com");
        userModel.setPassword("Abhinav@123");
        userModel.setEducation_level("Completed Bachelor ");
        userModel.setProfessional_skill("Java,oracle ,php, wordpress");
        userModel.setExperience("2 years of experience ");

        UserTesting userTesting = new UserTesting();
        Boolean result= userTesting.userSignup(userModel);
        assertEquals(true,result);
    }

    @Test
    public void UserUpdateTest(){
        UserModel userModel= new UserModel();
        userModel.setName("Sohil Lama");
        userModel.setBirth_date("2002/12/2");
        userModel.setGender("Female");
        userModel.setAddress("Lalitour,Nepal");
        userModel.setContact("9876543212");
        userModel.setEducation_level("Completed Bachelor ");
        userModel.setProfessional_skill("Java,oracle ,php, wordpress");
        userModel.setExperience("2 years of experience ");
        UserTesting userTesting = new UserTesting();
        Boolean result =userTesting.UpdateUser(userModel,4);
        assertEquals(true,result);
    }


    @Test
    public void OrgSignupTest(){
        OrganizationModel orgModel= new OrganizationModel();
        orgModel.setName("Softwarica college of IT and E-commerce ");
        orgModel.setAddress("Kathmandu,Nepal");
        orgModel.setContact("9876543212");
        orgModel.setEmail("softwarica@gmail.com");
        orgModel.setPassword("Softwarica@123");
        orgModel.setDescription("It is a good company");

        OrganizationTesting organizationTesting = new OrganizationTesting();
        Boolean result= organizationTesting.orgSignup(orgModel);
        assertEquals(true,result);
    }

    @Test
    public void OrgLoginTest(){
        OrganizationTesting orgTest = new OrganizationTesting();
        Boolean result= orgTest.OrgLogin("softwarica@gmail.com","Softearica@gmail.com");
        assertEquals(true,result);
    }

    @Test
    public void GetOrgProfile(){
        OrganizationTesting orgTest = new OrganizationTesting();
        Boolean result =orgTest.GetAllOrganization();
        assertEquals(true,result);
    }

    @Test
    public void OrgUdateTest(){
        OrganizationModel orgModel= new OrganizationModel();
        orgModel.setName("Softwarica college of IT and E-commerce ");
        orgModel.setAddress("Kathmandu,Nepal");
        orgModel.setContact("9876543212");
        orgModel.setDescription("It is a good company");

        OrganizationTesting organizationTesting = new OrganizationTesting();
        Boolean result= organizationTesting.orgSignup(orgModel);
        assertEquals(true,result);
    }


    @Test
    public void PostJobTest(){
        JobModel jobModel= new JobModel();
        jobModel.setTitle("Android Developer");
        jobModel.setJob_category("IT");
        jobModel.setJob_type("Full Time");
        jobModel.setLocation("Laltipur");
        jobModel.setSalary("10000");
        jobModel.setDeadline("2021-2-3");
        jobModel.setNo_of_vacancy(12);
        jobModel.setExperience("2 Years");
        jobModel.setEducation_level("Bachelor Degree");
        jobModel.setProfessional_skill("Java mysql oracle");
        jobModel.setJob_description("It is senior  level developer");
        jobModel.setOrg_id(Url.id);

        OrganizationTesting organizationTesting = new OrganizationTesting();
        Boolean result= organizationTesting.JobPost(jobModel);
        assertEquals(true,result);
    }

    @Test
    public void GetAllJobPostTest(){
        OrganizationTesting orgTest = new OrganizationTesting();
        Boolean result =orgTest.GetAllJob();
        assertEquals(true,result);
    }











}