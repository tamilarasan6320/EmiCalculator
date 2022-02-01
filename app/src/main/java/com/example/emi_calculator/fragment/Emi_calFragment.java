package com.example.emi_calculator.fragment;

import android.icu.number.IntegerWidth;
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
import android.widget.Toast;

import com.example.emi_calculator.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.tabs.TabLayout;


public class Emi_calFragment extends Fragment {
    private BottomSheetBehavior bottomSheetBehavior;
    RangeSlider loanamtSlider, interestrateSlider, loantenureSlider;
    EditText LoanamtEt, InterestrateEt, LoantenureEt;
    int loanamtValue,interestrateValue;
    float loanTenureValue;

    TabLayout tabLayout;
    TextView y_m;

    TextView TI, result, principleamt_tv, interestpay_tv, totalpayment_tv;
    Button calculate;
    LinearLayout linearLayout;
    boolean loantenuremonth = false,loantenureyear = false;



    public Emi_calFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_emi_cal, container, false);

        loanamtSlider = rootview.findViewById(R.id.range_slider0);
        interestrateSlider = rootview.findViewById(R.id.range_slider1);
        loantenureSlider = rootview.findViewById(R.id.range_slider2);

        LoanamtEt = rootview.findViewById(R.id.slider0_et);
        InterestrateEt = rootview.findViewById(R.id.slider1_et);
        LoantenureEt = rootview.findViewById(R.id.slider2_et);
        TI = rootview.findViewById(R.id.TI);
        result = rootview.findViewById(R.id.result);
        principleamt_tv = rootview.findViewById(R.id.p_tv);
        interestpay_tv = rootview.findViewById(R.id.i_tv);
        totalpayment_tv = rootview.findViewById(R.id.ta_tv);

        linearLayout = rootview.findViewById(R.id.design_bottom_sheet);
        y_m = rootview.findViewById(R.id.ym_et);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        calculate = rootview.findViewById(R.id.calculate_btn);
        loantenuremonth = true;
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


                if (loantenuremonth){
                    loanTenureValue = Float.parseFloat(LoantenureEt.getText().toString()) / 12;
                }
                else {
                    loanTenureValue = Float.parseFloat(LoantenureEt.getText().toString());
                }
                Toast.makeText(getActivity(), ""+loanTenureValue, Toast.LENGTH_SHORT).show();




                float p = Float.parseFloat(String.valueOf(LoanamtEt.getText().toString()));
                float i = Float.parseFloat(String.valueOf(InterestrateEt.getText().toString()));
                float t = Float.parseFloat(String.valueOf(loanTenureValue));


                float emi = emi_calculator(p, i, t);
                principleamt_tv.setText("Rs. "+String.valueOf(loanamtValue));
                interestpay_tv.setText(String.valueOf(interestrateValue)+" %");
                int emivalue = Math.round(emi);
                totalpayment_tv.setText("Rs. "+ emivalue);



            }
        });



        loanamtSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                loanamtValue = Math.round(value);
                LoanamtEt.setText(loanamtValue+"");
            }
        });
        interestrateSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                interestrateValue = Math.round(value);
                InterestrateEt.setText(interestrateValue + "");
            }
        });
        loantenureSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                loanTenureValue = Math.round(value);
                LoantenureEt.setText("" + loanTenureValue);
            }
        });


        tabLayout = (TabLayout) rootview.findViewById(R.id.tab_layoutym);
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("year"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_START);
        y_m.setText("( in Month )");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    y_m.setText("( in Month )");
                    loantenuremonth = true;
                    loantenureyear = false;
                } else {
                    y_m.setText("( in Year)");
                    loantenuremonth = false;
                    loantenureyear = true;


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

    public float calPric(float p) {
        return (float) (p);
    }

    public float calInt(float i) {
        return (float) (i / 12 / 100);
    }

    public float calMonth(float y) {
        return (float) (y * 12);
    }

    public float calDvdnt(float Rate, float Months) {
        return (float) (Math.pow(1 + Rate, Months));
    }

    public float calFinalDvdnt(float Principal, float Rate, float Dvdnt) {
        return (float) (Principal * Rate * Dvdnt);
    }

    public float calDivider(float Dvdnt) {
        return (float) (Dvdnt - 1);
    }

    public float calEmi(float FD, Float D) {
        return (float) (FD / D);
    }

    public float calTa(float emi, Float Months) {
        return (float) (emi * Months);
    }

    public float calTotalInt(float TA, float Principal) {
        return (float) (TA - Principal);

    }
    public float emi_calculator(float p, float r, float t) {
        float emi;

        r = r / (12 * 100); // one month interest
        t = t * 12; // one month period
        emi = (p * r * (float)Math.pow(1 + r, t))
                / (float)(Math.pow(1 + r, t) - 1);

        return (emi);

    }
}