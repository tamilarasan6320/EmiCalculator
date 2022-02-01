package com.example.emi_calculator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.emi_calculator.fragment.Amount_calFragment;
import com.example.emi_calculator.fragment.Emi_calFragment;
import com.example.emi_calculator.fragment.Roi_calFragment;
import com.example.emi_calculator.fragment.SalariedFragment;
import com.example.emi_calculator.fragment.Self_EmployedFragment;
import com.example.emi_calculator.fragment.Tenure_calFragment;

public class TabAdapter3 extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public TabAdapter3(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SalariedFragment EMI = new SalariedFragment();
                return EMI;
            case 1:
                Self_EmployedFragment Amount = new Self_EmployedFragment();
                return Amount;






            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
