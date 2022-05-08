package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.emi_calculator.fragment.LOBFragment;
import com.example.emi_calculator.fragment.BusinessFragment;
import com.example.emi_calculator.fragment.HomeFragment;
import com.example.emi_calculator.fragment.PersonalFragment;

public class TabsAdapter2 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsAdapter2(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                PersonalFragment Personal = new PersonalFragment();
                return Personal;
            case 1:
                HomeFragment Amount = new HomeFragment();
                return Amount;

            case 2:
                BusinessFragment Business = new BusinessFragment();
                return Business;

            case 3:
                LOBFragment LOB = new LOBFragment();
                return LOB;


            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
