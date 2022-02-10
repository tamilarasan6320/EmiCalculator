package com.example.emi_calculator.Databasehelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.model.ModelBTBankSegment;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DB_BTBankSegment extends SQLiteAssetHelper {
    private static DB_BTBankSegment instance;

    /* renamed from: a */
    SQLiteDatabase f3278a;
    public String colBTCompanyID = "BTCompanyID";
    public String colBankSegmentName = "BankSegmentName";
    public String colID = "BTBankSegmentID";
    public String colSequence = "Sequence";
    public String tblName = "BTBankSegment";

    public DB_BTBankSegment(Context context) {
        super(context, Constant_Variable.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 13);
    }

    public static DB_BTBankSegment getInstance(Context context) {
        if (instance == null) {
            instance = new DB_BTBankSegment(context);
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo11433a() {
        SQLiteDatabase sQLiteDatabase = this.f3278a;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo11434b() {
        this.f3278a = getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<ModelBTBankSegment> getAllBankSegment() {
        ArrayList<ModelBTBankSegment> arrayList = new ArrayList<>();
        mo11434b();
        Cursor rawQuery = this.f3278a.rawQuery("SELECT " + this.colID + ", CASE WHEN " + this.colBankSegmentName + " IS NULL THEN '-' ELSE " + this.colBankSegmentName + " END AS " + this.colBankSegmentName + ", CASE WHEN " + this.colBTCompanyID + " IS NULL THEN 0 ELSE " + this.colBTCompanyID + " END AS " + this.colBTCompanyID + ", CASE WHEN " + this.colSequence + " IS NULL THEN 0 ELSE " + this.colSequence + " END AS " + this.colSequence + " FROM  " + this.tblName + " ORDER BY " + this.colSequence + " DESC", (String[]) null);
        if (rawQuery.moveToFirst()) {
            do {
                ModelBTBankSegment modelBTBankSegment = new ModelBTBankSegment();
                modelBTBankSegment.setBTBankSegmentID(rawQuery.getInt(rawQuery.getColumnIndex(this.colID)));
                modelBTBankSegment.setBankSegmentName(rawQuery.getString(rawQuery.getColumnIndex(this.colBankSegmentName)));
                modelBTBankSegment.setBTCompanyID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTCompanyID)));
                modelBTBankSegment.setSequence(rawQuery.getInt(rawQuery.getColumnIndex(this.colSequence)));
                arrayList.add(modelBTBankSegment);
            } while (rawQuery.moveToNext());
        }
        mo11433a();
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<ModelBTBankSegment> getAllBankSegmentByCompanyID(int i) {
        ArrayList<ModelBTBankSegment> arrayList = new ArrayList<>();
        mo11434b();
        Cursor rawQuery = this.f3278a.rawQuery("SELECT " + this.colID + ", CASE WHEN " + this.colBankSegmentName + " IS NULL THEN '-' ELSE " + this.colBankSegmentName + " END AS " + this.colBankSegmentName + ", CASE WHEN " + this.colBTCompanyID + " IS NULL THEN 0 ELSE " + this.colBTCompanyID + " END AS " + this.colBTCompanyID + ", CASE WHEN " + this.colSequence + " IS NULL THEN 0 ELSE " + this.colSequence + " END AS " + this.colSequence + " FROM  " + this.tblName + " WHERE  " + this.colBTCompanyID + "=" + i, (String[]) null);
        if (rawQuery.moveToFirst()) {
            do {
                ModelBTBankSegment modelBTBankSegment = new ModelBTBankSegment();
                modelBTBankSegment.setBTBankSegmentID(rawQuery.getInt(rawQuery.getColumnIndex(this.colID)));
                modelBTBankSegment.setBankSegmentName(rawQuery.getString(rawQuery.getColumnIndex(this.colBankSegmentName)));
                modelBTBankSegment.setBTCompanyID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTCompanyID)));
                modelBTBankSegment.setSequence(rawQuery.getInt(rawQuery.getColumnIndex(this.colSequence)));
                arrayList.add(modelBTBankSegment);
            } while (rawQuery.moveToNext());
        }
        mo11433a();
        return arrayList;
    }
}

