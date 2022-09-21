package com.gm.emi_calculator.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gm.emi_calculator.R;
import com.gm.emi_calculator.model.ModelBTHistory;
import com.gm.emi_calculator.model.ModelNewBTHistory;

import java.util.List;


public class AdapterBTTopupHistory extends RecyclerView.Adapter<AdapterBTTopupHistory.MyViewHolder> {
    private List<ModelNewBTHistory> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView etSalary,etSanctionedAmount,etCurrentBalance,etEMIPaid,etBTAmount,etTopupAmount,etBTROI,etTopupROI,etBTEMI,etTopupEMI,etBTTenure,etTopupTenure,etFoir;


        public MyViewHolder(AdapterBTTopupHistory adapterBTHistory, View view) {
            super(view);
            etSalary = view.findViewById(R.id.etSalary);
            etSanctionedAmount = view.findViewById(R.id.etSanctionedAmount);
            etCurrentBalance = view.findViewById(R.id.etCurrentBalance);
            etEMIPaid = view.findViewById(R.id.etEMIPaid);
            etBTAmount = view.findViewById(R.id.etBTAmount);
            etTopupAmount = view.findViewById(R.id.etTopupAmount);
            etBTROI = view.findViewById(R.id.etBTROI);
            etTopupROI = view.findViewById(R.id.etTopupROI);
            etBTTenure = view.findViewById(R.id.etBTTenure);
            etTopupTenure = view.findViewById(R.id.etTopupTenure);
            etBTEMI = view.findViewById(R.id.etBTEMI);
            etTopupEMI = view.findViewById(R.id.etTopupEMI);
            etFoir = view.findViewById(R.id.etFoir);

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

    public AdapterBTTopupHistory(Activity activity, List<ModelNewBTHistory> list2) {
        this.list = list2;
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void onBindViewHolder(MyViewHolder holder, int i) {
        ModelNewBTHistory model = this.list.get(i);
        holder.etSalary.setText("₹ "+model.getSalary());
        holder.etSanctionedAmount.setText("₹ "+model.getSanctionedAmount());
        holder.etCurrentBalance.setText("₹ "+model.getCurrentBalance());
        holder.etEMIPaid.setText("₹ "+model.getEMIPaid());
        holder.etBTAmount.setText("₹ "+model.getBTAmount());
        holder.etTopupAmount.setText("₹ "+model.getTopupAmount());
        holder.etBTROI.setText(model.getBTROI()+" %");
        holder.etTopupROI.setText(model.getTopupROI()+" %");
        holder.etTopupTenure.setText(model.getTopupTenure() +" Months");
        holder.etBTTenure.setText(model.getBTTenure()+" Months");
        holder.etBTEMI.setText("₹ "+model.getBTEMI());
        holder.etTopupEMI.setText("₹ "+model.getTopupEMI());
        holder.etFoir.setText(model.getFoir()+" %");
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_bttopup_history, viewGroup, false));
    }

}
