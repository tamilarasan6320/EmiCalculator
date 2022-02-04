package com.example.emi_calculator.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emi_calculator.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.tabs.TabLayout;


public class Roi_calFragment extends Fragment {
    private BottomSheetBehavior bottomSheetBehavior;
    RangeSlider rangeSlider0,rangeSlider1,rangeSlider2;
    TabLayout tabLayout;
    TextView y_m;



    public Roi_calFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_roi_cal, container, false);


        LinearLayout linearLayout = rootview.findViewById(R.id.bottom_sheet_linear);
        y_m = rootview.findViewById(R.id.ym_et);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        Button button = rootview.findViewById(R.id.calculate_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        rangeSlider0 = rootview.findViewById(R.id.range_slider0);
        rangeSlider1 = rootview.findViewById(R.id.range_slider1);
        rangeSlider2 = rootview.findViewById(R.id.range_slider2);

        EditText loan_et = rootview.findViewById(R.id.slider0_et);
        EditText  rate_et = rootview.findViewById(R.id.slider1_et);
        EditText  loatenure_et = rootview.findViewById(R.id.slider2_et);


        rangeSlider0.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                int rangevalue = Math.round(value);
                loan_et.setText("₹"+rangevalue);
            }
        });
        rangeSlider1.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                int rangevalue = Math.round(value);
                rate_et.setText("₹"+rangevalue);            }
        });
        rangeSlider2.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                int rangevalue = Math.round(value);
                loatenure_et.setText(""+rangevalue);
            }
        });

        tabLayout = (TabLayout)rootview.findViewById(R.id.tab_layoutym);
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("year"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_START);
        y_m.setText("( in Month )");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    y_m.setText("( in Month )");
                }
                else{
                    y_m.setText("( in Year)");

                }
                //Toast.makeText(getActivity(),""+ tab.getPosition(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return rootview;
    }
}