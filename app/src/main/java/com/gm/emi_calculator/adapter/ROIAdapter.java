package com.gm.emi_calculator.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gm.emi_calculator.R;
import com.gm.emi_calculator.model.ROI;

import java.util.ArrayList;

public class ROIAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<ROI> rois;

    public ROIAdapter(Activity activity, ArrayList<ROI> rois) {
        this.activity = activity;
        this.rois = rois;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.roi_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final ROI roi = rois.get(position);
        holder.bank.setText(roi.getBank());
        holder.interest.setText(roi.getInterest());

    }


    @Override
    public int getItemCount() {
        return rois.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final TextView bank,interest;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            bank = itemView.findViewById(R.id.bank);
            interest = itemView.findViewById(R.id.interest);



        }
    }
}

