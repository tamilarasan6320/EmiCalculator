package com.example.emi_calculator;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.emi_calculator.fragment.Amount_calFragment;
import com.example.emi_calculator.fragment.Emi_calFragment;
import com.example.emi_calculator.fragment.Roi_calFragment;
import com.example.emi_calculator.fragment.Tenure_calFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Emi_calFragment EMI = new Emi_calFragment();
                return EMI;
            case 1:
                Amount_calFragment Amount = new Amount_calFragment();
                return Amount;

            case 2:
                Tenure_calFragment TENURE = new Tenure_calFragment();
                return TENURE;

            case 3:
                Roi_calFragment ROI = new Roi_calFragment();
                return ROI;


            default:
                return null;
        }
    }

}
