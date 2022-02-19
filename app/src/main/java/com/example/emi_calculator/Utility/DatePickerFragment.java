package com.example.emi_calculator.Utility;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    /* renamed from: V */
    boolean f3765V;

    /* renamed from: W */
    Calendar f3766W;
    private TextView dateViewObject;
    public SimpleDateFormat dateDisplayFormat = new SimpleDateFormat("MMM-yyyy");

    @SuppressLint("WrongConstant")
    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, this.f3766W.get(1), this.f3766W.get(2), this.f3766W.get(5));
        try {
            for (Field field : datePickerDialog.getClass().getDeclaredFields()) {
                if (field.getName().equals("mDayPicker")) {
                    field.setAccessible(true);
                    ((View) field.get(datePickerDialog)).setVisibility(8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datePickerDialog;
    }

    public Calendar getCalander() {
        if (this.f3766W == null) {
            this.f3766W = Calendar.getInstance();
        }
        return this.f3766W;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.f3766W = Calendar.getInstance();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (!TextUtils.isEmpty(this.dateViewObject.getText())) {
                this.f3766W.setTime(dateDisplayFormat.parse(this.dateViewObject.getText().toString()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DatePickerDialog createDialogWithoutDateField = createDialogWithoutDateField();
        if (this.f3765V) {
            createDialogWithoutDateField.getDatePicker().setMinDate(currentTimeMillis);
        }
        return createDialogWithoutDateField;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        this.f3766W = instance;
        instance.set(i, i2, i3);
        this.dateViewObject.setText(dateDisplayFormat.format(this.f3766W.getTime()));
    }

    public void setDateViewObject(TextView textView, boolean z) {
        this.dateViewObject = textView;
        this.f3765V = z;
    }
}

