package com.gm.emi_calculator.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gm.emi_calculator.R;
import com.gm.emi_calculator.model.Model_Yearwisecalculation;

import java.util.ArrayList;

public class Adapter_YearlyCalculation extends BaseAdapter {

    /* renamed from: a */
    Activity f3245a;

    /* renamed from: b */
    LayoutInflater f3246b;

    /* renamed from: c */
    ArrayList<Model_Yearwisecalculation> f3247c;

    private class ViewHolder {

        /* renamed from: a */
        TextView f3251a;

        /* renamed from: b */
        TextView f3252b;

        /* renamed from: c */
        TextView f3253c;

        /* renamed from: d */
        TextView f3254d;

        /* renamed from: e */
        TextView f3255e;

        /* renamed from: f */
        TextView f3256f;

        /* renamed from: g */
        LinearLayout f3257g;

        private ViewHolder(Adapter_YearlyCalculation adapter_YearlyCalculation) {
        }
    }

    public Adapter_YearlyCalculation(Activity activity, ArrayList<Model_Yearwisecalculation> arrayList) {
        this.f3247c = arrayList;
        this.f3245a = activity;
        this.f3246b = activity.getLayoutInflater();
    }

    public int getCount() {
        return this.f3247c.size();
    }

    public Object getItem(int i) {
        return this.f3247c.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder(this);
            view2 = this.f3246b.inflate(R.layout.list_view_design_yearly, (ViewGroup) null);
            viewHolder.f3251a = (TextView) view2.findViewById(R.id.tv_month);
            viewHolder.f3252b = (TextView) view2.findViewById(R.id.tv_serial_no);
            viewHolder.f3253c = (TextView) view2.findViewById(R.id.tv_emi);
            viewHolder.f3254d = (TextView) view2.findViewById(R.id.tv_principal);
            viewHolder.f3255e = (TextView) view2.findViewById(R.id.tv_interest);
            viewHolder.f3256f = (TextView) view2.findViewById(R.id.tv_balance);
            viewHolder.f3257g = (LinearLayout) view2.findViewById(R.id.shedualadvance_monthly_main);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f3251a.setText(this.f3247c.get(i).getMonth());
        viewHolder.f3255e.setText(this.f3247c.get(i).getInterest());
        TextView textView = viewHolder.f3252b;
        StringBuilder sb = new StringBuilder();
        int i2 = i + 1;
        sb.append(i2);
        sb.append("");
        textView.setText(sb.toString());
        viewHolder.f3254d.setText(this.f3247c.get(i).getPrincipal());
        viewHolder.f3256f.setText(this.f3247c.get(i).getBalance());
        viewHolder.f3253c.setText(this.f3247c.get(i).getEmi());
        final String str = i2 + "";
        this.f3247c.get(i).getPrincipal();
        this.f3247c.get(i).getTotalPaid();
        this.f3247c.get(i).getInterest();
        this.f3247c.get(i).getBalance();
        viewHolder.f3257g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Adapter_YearlyCalculation adapter_YearlyCalculation = Adapter_YearlyCalculation.this;
//                new CustomDialogSheduleDetails(adapter_YearlyCalculation.f3245a, str, "Financial year", adapter_YearlyCalculation.f3247c.get(i).getBalance(), Adapter_YearlyCalculation.this.f3247c.get(i).getPrincipalPaid(), Adapter_YearlyCalculation.this.f3247c.get(i).getInterestPaid(), Adapter_YearlyCalculation.this.f3247c.get(i).getTotalPaid()).show();
            }
        });
        if (i % 2 == 0) {
            view2.setBackgroundResource(R.drawable.even_list_click);
        } else {
            view2.setBackgroundResource(R.drawable.odd_list_click);
        }
        return view2;
    }
}

