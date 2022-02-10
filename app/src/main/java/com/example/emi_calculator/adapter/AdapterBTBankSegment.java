package com.example.emi_calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emi_calculator.R;
import com.example.emi_calculator.model.ModelBTBankSegment;

import java.util.ArrayList;

public class AdapterBTBankSegment extends BaseAdapter {

    /* renamed from: a */
    Context f3221a;

    /* renamed from: b */
    ArrayList<ModelBTBankSegment> f3222b;

    public AdapterBTBankSegment(Context context, ArrayList<ModelBTBankSegment> arrayList) {
        this.f3221a = context;
        this.f3222b = arrayList;
    }

    public int getCount() {
        return this.f3222b.size();
    }

    public Object getItem(int i) {
        return this.f3222b.get(i);
    }

    public long getItemId(int i) {
        return (long) this.f3222b.get(i).getBTBankSegmentID();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            view = LayoutInflater.from(this.f3221a).inflate(R.layout.view_row_spinner, (ViewGroup) null);
            textView = (TextView) view.findViewById(R.id.tvCompanyName);
            view.setTag(textView);
        } else {
            textView = (TextView) view.getTag();
        }
        textView.setText(this.f3222b.get(i).getBankSegmentName());
        return view;
    }
}

