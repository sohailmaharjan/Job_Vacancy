package com.job.jobvacancy.Adapter;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginViewPagerAdapter extends FragmentPagerAdapter   {
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> fragmentTitle = new ArrayList<>();

    public LoginViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        return fragmentList.get(i);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentTitle.get(position);
    }
    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

    public void addLoginFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }

}
