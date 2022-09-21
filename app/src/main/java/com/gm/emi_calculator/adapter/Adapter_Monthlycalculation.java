package com.gm.emi_calculator.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gm.emi_calculator.R;
import com.gm.emi_calculator.model.Model_Monthwisecalculation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adapter_Monthlycalculation extends BaseAdapter {

    /* renamed from: a */
    Activity f3232a;

    /* renamed from: b */
    LayoutInflater f3233b;

    /* renamed from: c */
    ArrayList<Model_Monthwisecalculation> f3234c;

    private class ViewHolder {

        /* renamed from: a */
        TextView f3238a;

        /* renamed from: b */
        TextView f3239b;

        /* renamed from: c */
        TextView f3240c;

        /* renamed from: d */
        TextView f3241d;

        /* renamed from: e */
        TextView f3242e;

        /* renamed from: f */
        TextView f3243f;

        /* renamed from: g */
        LinearLayout f3244g;

        private ViewHolder(Adapter_Monthlycalculation adapter_Monthlycalculation) {
        }
    }

    public Adapter_Monthlycalculation(Activity activity, ArrayList<Model_Monthwisecalculation> arrayList) {
        this.f3234c = arrayList;
        this.f3232a = activity;
        this.f3233b = activity.getLayoutInflater();
    }

    public int getCount() {
        return this.f3234c.size();
    }

    public Object getItem(int i) {
        return this.f3234c.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }



    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder(this);
            view2 = this.f3233b.inflate(R.layout.list_view_design_yearly, (ViewGroup) null);
            viewHolder.f3238a = (TextView) view2.findViewById(R.id.tv_month);
            viewHolder.f3242e = (TextView) view2.findViewById(R.id.tv_serial_no);
            viewHolder.f3243f = (TextView) view2.findViewById(R.id.tv_emi);
            viewHolder.f3239b = (TextView) view2.findViewById(R.id.tv_principal);
            viewHolder.f3240c = (TextView) view2.findViewById(R.id.tv_interest);
            viewHolder.f3241d = (TextView) view2.findViewById(R.id.tv_balance);
            viewHolder.f3244g = (LinearLayout) view2.findViewById(R.id.shedualadvance_monthly_main);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f3238a.setText(new SimpleDateFormat("MMM-yyyy").format(this.f3234c.get(i).getDate()));
        viewHolder.f3240c.setText(this.f3234c.get(i).getInterest());
        TextView textView = viewHolder.f3242e;
        StringBuilder sb = new StringBuilder();
        int i2 = i + 1;
        sb.append(i2);
        sb.append("");
        textView.setText(sb.toString());
        viewHolder.f3239b.setText(this.f3234c.get(i).getPrincipal());
        viewHolder.f3241d.setText(this.f3234c.get(i).getBalance());
        viewHolder.f3243f.setText(this.f3234c.get(i).getEmi());
        final String str = i2 + "";
        this.f3234c.get(i).getTotalPaid();
        this.f3234c.get(i).getBalance();
        viewHolder.f3244g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Adapter_Monthlycalculation adapter_Monthlycalculation = Adapter_Monthlycalculation.this;
//                new CustomDialogSheduleDetails(adapter_Monthlycalculation.f3232a, str, "EMI No", adapter_Monthlycalculation.f3234c.get(i).getBalance(), Adapter_Monthlycalculation.this.f3234c.get(i).getPrincipalPaid(), Adapter_Monthlycalculation.this.f3234c.get(i).getInterestPaid(), Adapter_Monthlycalculation.this.f3234c.get(i).getTotalPaid()).show();
//
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

