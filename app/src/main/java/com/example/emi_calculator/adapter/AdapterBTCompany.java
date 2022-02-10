package com.example.emi_calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emi_calculator.R;
import com.example.emi_calculator.model.ModelBTCompany;

import java.util.ArrayList;

public class AdapterBTCompany extends BaseAdapter {

    /* renamed from: a */
    Context f3223a;

    /* renamed from: b */
    ArrayList<ModelBTCompany> f3224b;

    public AdapterBTCompany(Context context, ArrayList<ModelBTCompany> arrayList) {
        this.f3223a = context;
        this.f3224b = arrayList;
    }

    public int getCount() {
        return this.f3224b.size();
    }

    public Object getItem(int i) {
        return this.f3224b.get(i);
    }

    public long getItemId(int i) {
        return (long) this.f3224b.get(i).getBTCompanyID();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            view = LayoutInflater.from(this.f3223a).inflate(R.layout.view_row_spinner, (ViewGroup) null);
            textView = (TextView) view.findViewById(R.id.tvCompanyName);
            view.setTag(textView);
        } else {
            textView = (TextView) view.getTag();
        }
        textView.setText(this.f3224b.get(i).getBTCompanyName());
        return view;
    }
}

