package com.example.emi_calculator.Databasehelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.emi_calculator.Constant.Constant_Variable;
import com.example.emi_calculator.model.ModelBTHistory;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DB_BTHistory extends SQLiteAssetHelper {
    private static DB_BTHistory instance;

    /* renamed from: a */
    SQLiteDatabase f3281a;
    public String colBTBankSegmentID = "BTBankSegmentID";
    public String colBTCompanyID = "BTCompanyID";
    public String colBTEMIAmount = "BTEMIAmount";
    public String colBTLoanAmount = "BTLoanAmount";
    public String colBTLoanROI = "BTLoanROI";
    public String colBTLoanTenure = "BTLoanTenure";
    public String colCustomerMobile = "CustomerMobile";
    public String colCustomerName = "CustomerName";
    public String colCustomerReference = "CustomerReference";
    public String colEMIPaid = "EMIPaid";
    public String colID = "BTHistoryID";
    public String colInsurance = "Insurance";
    public String colInsuranceAmount = "InsuranceAmount";
    public String colInsuranceAmountwithGST = "InsuranceAmountwithGST";
    public String colInsuranceType = "InsuranceType";
    public String colMultiplier = "Multiplier";
    public String colOutstandingAmount = "OutstandingAmount";
    public String colProcessingFee = "ProcessingFee";
    public String colProcessingFeeAmount = "ProcessingFeeAmount";
    public String colProcessingFeeAmountwithGST = "ProcessingFeeAmountwithGST";
    public String colProcessingFeeType = "ProcessingFeeType";
    public String colSanctionedAmount = "SanctionedAmount";
    public String colTopupEMIAmount = "TopupEMIAmount";
    public String colTopupEMIAmountwithInsurance = "TopupEMIAmountwithInsurance";
    public String colTopupLoanAmount = "TopupLoanAmount";
    public String colTopupLoanAmountwithInsurance = "TopupLoanAmountwithInsurance";
    public String colTopupLoanROI = "TopupLoanROI";
    public String colTopupLoanTenure = "TopupLoanTenure";
    public String tblName = "BTHistory";

    public DB_BTHistory(Context context) {
        super(context, Constant_Variable.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 13);
    }

    public static DB_BTHistory getInstance(Context context) {
        if (instance == null) {
            instance = new DB_BTHistory(context);
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo11444a() {
        SQLiteDatabase sQLiteDatabase = this.f3281a;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo11445b() {
        this.f3281a = getWritableDatabase();
    }

    public void clearAllLog() {
        mo11445b();
        this.f3281a.execSQL("Delete FROM " + this.tblName);
        this.f3281a.close();
    }

    @SuppressLint("Range")
    public ArrayList<ModelBTHistory> getAllHistory() {
        ArrayList<ModelBTHistory> arrayList = new ArrayList<>();
        mo11445b();
        Cursor rawQuery = this.f3281a.rawQuery("SELECT *  FROM  " + this.tblName, (String[]) null);
        if (rawQuery.moveToLast()) {
            do {
                ModelBTHistory modelBTHistory = new ModelBTHistory();
                modelBTHistory.setBTHistoryID(rawQuery.getInt(rawQuery.getColumnIndex(this.colID)));
                modelBTHistory.setCustomerName(rawQuery.getString(rawQuery.getColumnIndex(this.colCustomerName)));
                modelBTHistory.setCustomerMobile(rawQuery.getString(rawQuery.getColumnIndex(this.colCustomerMobile)));
                modelBTHistory.setCustomerReference(rawQuery.getString(rawQuery.getColumnIndex(this.colCustomerReference)));
                modelBTHistory.setBTCompanyID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTCompanyID)));
                modelBTHistory.setBTBankSegmentID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTBankSegmentID)));
                modelBTHistory.setSanctionedAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colSanctionedAmount)));
                modelBTHistory.setOutstandingAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colOutstandingAmount)));
                modelBTHistory.setEMIPaid(rawQuery.getLong(rawQuery.getColumnIndex(this.colEMIPaid)));
                modelBTHistory.setMultiplier(rawQuery.getDouble(rawQuery.getColumnIndex(this.colMultiplier)));
                modelBTHistory.setBTLoanAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colBTLoanAmount)));
                modelBTHistory.setBTLoanROI(rawQuery.getDouble(rawQuery.getColumnIndex(this.colBTLoanROI)));
                modelBTHistory.setBTLoanTenure(rawQuery.getLong(rawQuery.getColumnIndex(this.colBTLoanTenure)));
                modelBTHistory.setTopupLoanAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupLoanAmount)));
                modelBTHistory.setTopupLoanROI(rawQuery.getDouble(rawQuery.getColumnIndex(this.colTopupLoanROI)));
                modelBTHistory.setTopupLoanTenure(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupLoanTenure)));
                modelBTHistory.setProcessingFee(rawQuery.getDouble(rawQuery.getColumnIndex(this.colProcessingFee)));
                modelBTHistory.setProcessingFeeType(rawQuery.getString(rawQuery.getColumnIndex(this.colProcessingFeeType)));
                modelBTHistory.setProcessingFeeAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colProcessingFeeAmount)));
                modelBTHistory.setProcessingFeeAmountwithGST(rawQuery.getLong(rawQuery.getColumnIndex(this.colProcessingFeeAmountwithGST)));
                modelBTHistory.setInsurance(rawQuery.getDouble(rawQuery.getColumnIndex(this.colInsurance)));
                modelBTHistory.setInsuranceType(rawQuery.getString(rawQuery.getColumnIndex(this.colInsuranceType)));
                modelBTHistory.setInsuranceAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colInsuranceAmount)));
                modelBTHistory.setInsuranceAmountwithGST(rawQuery.getLong(rawQuery.getColumnIndex(this.colInsuranceAmountwithGST)));
                modelBTHistory.setTopupLoanAmountwithInsurance(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupLoanAmountwithInsurance)));
                modelBTHistory.setBTEMIAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colBTEMIAmount)));
                modelBTHistory.setTopupEMIAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupEMIAmount)));
                modelBTHistory.setTopupEMIAmountwithInsurance(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupEMIAmountwithInsurance)));
                arrayList.add(modelBTHistory);
            } while (rawQuery.moveToPrevious());
        }
        mo11444a();
        return arrayList;
    }

    public int getCountOfRecord() {
        mo11445b();
        Cursor rawQuery = this.f3281a.rawQuery("Select COUNT() as maximum from " + this.tblName, (String[]) null);
        @SuppressLint("Range") int i = rawQuery.moveToFirst() ? rawQuery.getInt(rawQuery.getColumnIndex("maximum")) : 0;
        this.f3281a.close();
        return i;
    }

    @SuppressLint("Range")
    public ModelBTHistory getHistoryByID(int i) {
        ModelBTHistory modelBTHistory = new ModelBTHistory();
        mo11445b();
        Cursor rawQuery = this.f3281a.rawQuery("SELECT *  FROM  " + this.tblName + " WHERE BTHistoryID=" + i, (String[]) null);
        if (rawQuery.moveToLast()) {
            modelBTHistory.setCustomerName(rawQuery.getString(rawQuery.getColumnIndex(this.colCustomerName)));
            modelBTHistory.setCustomerMobile(rawQuery.getString(rawQuery.getColumnIndex(this.colCustomerMobile)));
            modelBTHistory.setCustomerReference(rawQuery.getString(rawQuery.getColumnIndex(this.colCustomerReference)));
            modelBTHistory.setBTCompanyID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTCompanyID)));
            modelBTHistory.setBTBankSegmentID(rawQuery.getInt(rawQuery.getColumnIndex(this.colBTBankSegmentID)));
            modelBTHistory.setSanctionedAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colSanctionedAmount)));
            modelBTHistory.setOutstandingAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colOutstandingAmount)));
            modelBTHistory.setEMIPaid(rawQuery.getLong(rawQuery.getColumnIndex(this.colEMIPaid)));
            modelBTHistory.setMultiplier(rawQuery.getDouble(rawQuery.getColumnIndex(this.colMultiplier)));
            modelBTHistory.setBTLoanAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colBTLoanAmount)));
            modelBTHistory.setBTLoanROI(rawQuery.getDouble(rawQuery.getColumnIndex(this.colBTLoanROI)));
            modelBTHistory.setBTLoanTenure(rawQuery.getLong(rawQuery.getColumnIndex(this.colBTLoanTenure)));
            modelBTHistory.setTopupLoanAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupLoanAmount)));
            modelBTHistory.setTopupLoanROI(rawQuery.getDouble(rawQuery.getColumnIndex(this.colTopupLoanROI)));
            modelBTHistory.setTopupLoanTenure(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupLoanTenure)));
            modelBTHistory.setProcessingFee(rawQuery.getDouble(rawQuery.getColumnIndex(this.colProcessingFee)));
            modelBTHistory.setProcessingFeeType(rawQuery.getString(rawQuery.getColumnIndex(this.colProcessingFeeType)));
            modelBTHistory.setProcessingFeeAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colProcessingFeeAmount)));
            modelBTHistory.setProcessingFeeAmountwithGST(rawQuery.getLong(rawQuery.getColumnIndex(this.colProcessingFeeAmountwithGST)));
            modelBTHistory.setInsurance(rawQuery.getDouble(rawQuery.getColumnIndex(this.colInsurance)));
            modelBTHistory.setInsuranceType(rawQuery.getString(rawQuery.getColumnIndex(this.colInsuranceType)));
            modelBTHistory.setInsuranceAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colInsuranceAmount)));
            modelBTHistory.setInsuranceAmountwithGST(rawQuery.getLong(rawQuery.getColumnIndex(this.colInsuranceAmountwithGST)));
            modelBTHistory.setTopupLoanAmountwithInsurance(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupLoanAmountwithInsurance)));
            modelBTHistory.setBTEMIAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colBTEMIAmount)));
            modelBTHistory.setTopupEMIAmount(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupEMIAmount)));
            modelBTHistory.setTopupEMIAmountwithInsurance(rawQuery.getLong(rawQuery.getColumnIndex(this.colTopupEMIAmountwithInsurance)));
        }
        mo11444a();
        return modelBTHistory;
    }

    public long insertHistory(ModelBTHistory modelBTHistory) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.colCustomerName, modelBTHistory.getCustomerName());
        contentValues.put(this.colCustomerMobile, modelBTHistory.getCustomerMobile());
        contentValues.put(this.colCustomerReference, modelBTHistory.getCustomerReference());
        contentValues.put(this.colBTCompanyID, Integer.valueOf(modelBTHistory.getBTCompanyID()));
        contentValues.put(this.colBTBankSegmentID, Integer.valueOf(modelBTHistory.getBTBankSegmentID()));
        contentValues.put(this.colSanctionedAmount, Long.valueOf(modelBTHistory.getSanctionedAmount()));
        contentValues.put(this.colOutstandingAmount, Long.valueOf(modelBTHistory.getOutstandingAmount()));
        contentValues.put(this.colEMIPaid, Long.valueOf(modelBTHistory.getEMIPaid()));
        contentValues.put(this.colMultiplier, Double.valueOf(modelBTHistory.getMultiplier()));
        contentValues.put(this.colBTLoanAmount, Long.valueOf(modelBTHistory.getBTLoanAmount()));
        contentValues.put(this.colBTLoanROI, Double.valueOf(modelBTHistory.getBTLoanROI()));
        contentValues.put(this.colBTLoanTenure, Long.valueOf(modelBTHistory.getBTLoanTenure()));
        contentValues.put(this.colTopupLoanAmount, Long.valueOf(modelBTHistory.getTopupLoanAmount()));
        contentValues.put(this.colTopupLoanROI, Double.valueOf(modelBTHistory.getTopupLoanROI()));
        contentValues.put(this.colTopupLoanTenure, Long.valueOf(modelBTHistory.getTopupLoanTenure()));
        contentValues.put(this.colProcessingFee, Double.valueOf(modelBTHistory.getProcessingFee()));
        contentValues.put(this.colProcessingFeeType, modelBTHistory.getProcessingFeeType());
        contentValues.put(this.colProcessingFeeAmount, Long.valueOf(modelBTHistory.getProcessingFeeAmount()));
        contentValues.put(this.colProcessingFeeAmountwithGST, Long.valueOf(modelBTHistory.getProcessingFeeAmountwithGST()));
        contentValues.put(this.colInsurance, Double.valueOf(modelBTHistory.getInsurance()));
        contentValues.put(this.colInsuranceType, modelBTHistory.getInsuranceType());
        contentValues.put(this.colInsuranceAmount, Long.valueOf(modelBTHistory.getInsuranceAmount()));
        contentValues.put(this.colInsuranceAmountwithGST, Long.valueOf(modelBTHistory.getInsuranceAmountwithGST()));
        contentValues.put(this.colTopupLoanAmountwithInsurance, Long.valueOf(modelBTHistory.getTopupLoanAmountwithInsurance()));
        contentValues.put(this.colBTEMIAmount, Long.valueOf(modelBTHistory.getBTEMIAmount()));
        contentValues.put(this.colTopupEMIAmount, Long.valueOf(modelBTHistory.getTopupEMIAmount()));
        contentValues.put(this.colTopupEMIAmountwithInsurance, Long.valueOf(modelBTHistory.getTopupEMIAmountwithInsurance()));
        mo11445b();
        long insert = writableDatabase.insert(this.tblName, (String) null, contentValues);
        writableDatabase.close();
        return insert;
    }

    public long updateHistory(ModelBTHistory modelBTHistory) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.colCustomerName, modelBTHistory.getCustomerName());
        contentValues.put(this.colCustomerMobile, modelBTHistory.getCustomerMobile());
        contentValues.put(this.colCustomerReference, modelBTHistory.getCustomerReference());
        contentValues.put(this.colBTCompanyID, Integer.valueOf(modelBTHistory.getBTCompanyID()));
        contentValues.put(this.colBTBankSegmentID, Integer.valueOf(modelBTHistory.getBTBankSegmentID()));
        contentValues.put(this.colSanctionedAmount, Long.valueOf(modelBTHistory.getSanctionedAmount()));
        contentValues.put(this.colOutstandingAmount, Long.valueOf(modelBTHistory.getOutstandingAmount()));
        contentValues.put(this.colEMIPaid, Long.valueOf(modelBTHistory.getEMIPaid()));
        contentValues.put(this.colMultiplier, Double.valueOf(modelBTHistory.getMultiplier()));
        contentValues.put(this.colBTLoanAmount, Long.valueOf(modelBTHistory.getBTLoanAmount()));
        contentValues.put(this.colBTLoanROI, Double.valueOf(modelBTHistory.getBTLoanROI()));
        contentValues.put(this.colBTLoanTenure, Long.valueOf(modelBTHistory.getBTLoanTenure()));
        contentValues.put(this.colTopupLoanAmount, Long.valueOf(modelBTHistory.getTopupLoanAmount()));
        contentValues.put(this.colTopupLoanROI, Double.valueOf(modelBTHistory.getTopupLoanROI()));
        contentValues.put(this.colTopupLoanTenure, Long.valueOf(modelBTHistory.getTopupLoanTenure()));
        contentValues.put(this.colProcessingFee, Double.valueOf(modelBTHistory.getProcessingFee()));
        contentValues.put(this.colProcessingFeeType, modelBTHistory.getProcessingFeeType());
        contentValues.put(this.colProcessingFeeAmount, Long.valueOf(modelBTHistory.getProcessingFeeAmount()));
        contentValues.put(this.colProcessingFeeAmountwithGST, Long.valueOf(modelBTHistory.getProcessingFeeAmountwithGST()));
        contentValues.put(this.colInsurance, Double.valueOf(modelBTHistory.getInsurance()));
        contentValues.put(this.colInsuranceType, modelBTHistory.getInsuranceType());
        contentValues.put(this.colInsuranceAmount, Long.valueOf(modelBTHistory.getInsuranceAmount()));
        contentValues.put(this.colInsuranceAmountwithGST, Long.valueOf(modelBTHistory.getInsuranceAmountwithGST()));
        contentValues.put(this.colTopupLoanAmountwithInsurance, Long.valueOf(modelBTHistory.getTopupLoanAmountwithInsurance()));
        contentValues.put(this.colBTEMIAmount, Long.valueOf(modelBTHistory.getBTEMIAmount()));
        contentValues.put(this.colTopupEMIAmount, Long.valueOf(modelBTHistory.getTopupEMIAmount()));
        contentValues.put(this.colTopupEMIAmountwithInsurance, Long.valueOf(modelBTHistory.getTopupEMIAmountwithInsurance()));
        mo11445b();
        String[] strArr = {String.valueOf(modelBTHistory.getBTHistoryID())};
        String str = this.tblName;
        long update = (long) writableDatabase.update(str, contentValues, this.colID + "=?", strArr);
        writableDatabase.close();
        return update;
    }
}
