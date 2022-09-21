package com.gm.emi_calculator.adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.gm.emi_calculator.R;
import com.gm.emi_calculator.model.ModelBTHistory;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;



public class AdapterBTHistory extends RecyclerView.Adapter<AdapterBTHistory.MyViewHolder> {
    private List<ModelBTHistory> list;
    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cvClick;
        public TextView tvID;
        public TextView tvMain;

        public MyViewHolder(AdapterBTHistory adapterBTHistory, View view) {
            super(view);
            this.tvMain = (TextView) view.findViewById(R.id.view_history_tv_main);
            this.tvID = (TextView) view.findViewById(R.id.view_history_tv_id);
            this.cvClick = (CardView) view.findViewById(R.id.view_history_cv_click);
        }

        public void bind(final ModelBTHistory modelBTHistory, final OnItemClickListener onItemClickListener) {
//            this.itemView.setOnClickListener(new View.OnClickListener(this) {
//                public void onClick(View view) {
//                    onItemClickListener.onItemClick(modelBTHistory);
//                }
//            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ModelBTHistory modelBTHistory);
    }

    public AdapterBTHistory(Activity activity, List<ModelBTHistory> list2, OnItemClickListener onItemClickListener) {
        this.list = list2;
        this.listener = onItemClickListener;
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(this.list.get(i), this.listener);
        ModelBTHistory modelBTHistory = this.list.get(i);
        TextView textView = myViewHolder.tvID;
        textView.setText(modelBTHistory.getBTHistoryID() + "");
        TextView textView2 = myViewHolder.tvMain;
        textView2.setText(Html.fromHtml("Emi <B>" + printCurrency((double) modelBTHistory.getTopupEMIAmountwithInsurance()) + "</B> for <B>" +printCurrency((double) modelBTHistory.getTopupEMIAmountwithInsurance()) + "</B> @ <B>" + modelBTHistory.getTopupLoanROI() + "% </B>for <B>" + (modelBTHistory.getTopupLoanTenure() / 12) + "</B> Years"));
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_bt_history, viewGroup, false));
    }
    public String printCurrency(double d) {
        String str = "₹" + "" + Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.round(Math.abs(d))), "₹");
        if (d >= Utils.DOUBLE_EPSILON) {
            return str;
        }
        return "-" + str;
    }
}
