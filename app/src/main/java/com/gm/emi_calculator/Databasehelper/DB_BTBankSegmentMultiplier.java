package com.gm.emi_calculator.Databasehelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gm.emi_calculator.Constant.Constant_Variable;
import com.gm.emi_calculator.model.ModelBTBankSegmentMultiplier;
import com.github.mikephil.charting.utils.Utils;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DB_BTBankSegmentMultiplier extends SQLiteAssetHelper {
    private static DB_BTBankSegmentMultiplier instance;

    /* renamed from: a */
    SQLiteDatabase f3279a;
    public String colBTBankSegmentID = "BTBankSegmentID";
    public String colEMIMax = "EMIMax";
    public String colEMIMin = "EMIMin";
    public String colID = "BTBankSegmentMobMultiplierID";
    public String colMultiplier = "Multiplier";
    public String tblName = "BTBankSegmentMobMultiplier";

    public DB_BTBankSegmentMultiplier(Context context) {
        super(context, Constant_Variable.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 13);
    }

    public static DB_BTBankSegmentMultiplier getInstance(Context context) {
        if (instance == null) {
            instance = new DB_BTBankSegmentMultiplier(context);
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo11437a() {
        SQLiteDatabase sQLiteDatabase = this.f3279a;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo11438b() {
        this.f3279a = getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<ModelBTBankSegmentMultiplier> getAllBankSegmentMultiplier() {
        ArrayList<ModelBTBankSegmentMultiplier> arrayList = new ArrayList<>();
        mo11438b();
        Cursor rawQuery = this.f3279a.rawQuery("SELECT " + this.colID + ", CASE WHEN " + this.colBTBankSegmentID + " IS NULL THEN 0 ELSE " + this.colBTBankSegmentID + " END AS " + this.colBTBankSegmentID + ", CASE WHEN " + this.colEMIMin + " IS NULL THEN 0 ELSE " + this.colEMIMin + " END AS " + this.colEMIMin + ", CASE WHEN " + this.colEMIMax + " IS NULL THEN 0 ELSE " + this.colEMIMax + " END AS " + this.colEMIMax + ", CASE WHEN " + this.colMultiplier + " IS NULL THEN 0 ELSE " + this.colMultiplier + " END AS " + this.colMultiplier + " FROM  " + this.tblName, (String[]) null);
        if (rawQuery.moveToLast()) {
            do {
                ModelBTBankSegmentMultiplier modelBTBankSegmentMultiplier = new ModelBTBankSegmentMultiplier();
                modelBTBankSegmentMultiplier.setBTBankSegmentMobMultiplierID(rawQuery.getInt(rawQuery.getColumnIndex(this.colID)));
                modelBTBankSegmentMultiplier.setBTBankSegmentID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTBankSegmentID)));
                modelBTBankSegmentMultiplier.setEMIMax(rawQuery.getInt(rawQuery.getColumnIndex(this.colEMIMax)));
                modelBTBankSegmentMultiplier.setEMIMin(rawQuery.getInt(rawQuery.getColumnIndex(this.colEMIMin)));
                modelBTBankSegmentMultiplier.setMultiplier((double) rawQuery.getInt(rawQuery.getColumnIndex(this.colMultiplier)));
                arrayList.add(modelBTBankSegmentMultiplier);
            } while (rawQuery.moveToPrevious());
        }
        mo11437a();
        return arrayList;
    }

    public double getBankSegmentMultiplier(int i, int i2) {
        mo11438b();
        String str = "SELECT Multiplier FROM BTBankSegmentMobMultiplier WHERE BTBankSegmentID=" + i + " AND EMIMin <= " + i2 + " AND EMIMax >= " + i2;
        Log.d("Multiplear", str);
        Cursor rawQuery = this.f3279a.rawQuery(str, (String[]) null);
        @SuppressLint("Range") double d = rawQuery.moveToLast() ? rawQuery.getDouble(rawQuery.getColumnIndex(this.colMultiplier)) : Utils.DOUBLE_EPSILON;
        mo11437a();
        return d;
    }
}

