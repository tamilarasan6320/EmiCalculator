package com.gm.emi_calculator;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gm.emi_calculator.model.ModelNewBTHistory;

import java.util.ArrayList;

public class SqliteManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "jp.db";
    public static final int version = 1;

    public String Salary = "Salary";
    public String SanctionedAmount = "SanctionedAmount";
    public String CurrentBalance = "CurrentBalance";
    public String EMIPaid = "EMIPaid";
    public String BTAmount = "BTAmount";
    public String TopupAmount = "TopupAmount";
    public String BTROI = "BTROI";
    public String TopupROI = "TopupROI";
    public String BTTenure = "BTTenure";
    public String TopupTenure = "TopupTenure";
    public String BTEMI = "BTEMI";
    public String TopupEMI = "TopupEMI";
    public String Foir = "Foir";

    public SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbQuery = "CREATE TABLE Items (id INTEGER PRIMARY KEY AUTOINCREMENT,Salary TEXT,SanctionedAmount TEXT,CurrentBalance TEXT,EMIPaid TEXT,BTAmount TEXT,TopupAmount TEXT,BTROI TEXT,TopupROI TEXT,BTTenure TEXT,TopupTenure TEXT,BTEMI TEXT,TopupEMI TEXT,Foir TEXT)";
        sqLiteDatabase.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
    public void addItem(ModelNewBTHistory modelBTHistory) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Salary, modelBTHistory.getSalary());
        contentValues.put(SanctionedAmount, modelBTHistory.getSanctionedAmount());
        contentValues.put(CurrentBalance, modelBTHistory.getCurrentBalance());
        contentValues.put(EMIPaid, modelBTHistory.getEMIPaid());
        contentValues.put(BTAmount, modelBTHistory.getBTAmount());
        contentValues.put(TopupAmount, modelBTHistory.getTopupAmount());
        contentValues.put(BTROI, modelBTHistory.getBTROI());
        contentValues.put(TopupROI, modelBTHistory.getTopupROI());
        contentValues.put(BTTenure, modelBTHistory.getBTTenure());
        contentValues.put(TopupTenure, modelBTHistory.getTopupTenure());
        contentValues.put(BTEMI, modelBTHistory.getBTEMI());
        contentValues.put(TopupEMI, modelBTHistory.getTopupEMI());
        contentValues.put(Foir, modelBTHistory.getFoir());
        // description is column in items table, item.description has value for description
        db.insert("Items", null, contentValues);//Items is table name
        db.close();
    }
    public void deleteitem() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from Items");
        db.close();
    }
    public ArrayList<ModelNewBTHistory> readAllItems() {
        ArrayList<ModelNewBTHistory> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
//see above point 2 function
        Cursor cursor = db.query("Items"
                , null// columns - null will give all
                , null// selection
                , null// selection arguments
                , null// groupBy
                , null// having
                , null);// no need or order by for now;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // move the cursor to next row if there is any to read it's data
                ModelNewBTHistory item = readItem(cursor);
                items.add(item);
            }
        }
        return items;
    }
    @SuppressLint("Range")
    private ModelNewBTHistory readItem(Cursor cursor) {
        ModelNewBTHistory item = new ModelNewBTHistory();

        item.setSalary(cursor.getString(cursor.getColumnIndex(Salary)));
        item.setSanctionedAmount(cursor.getString(cursor.getColumnIndex(SanctionedAmount)));
        item.setCurrentBalance(cursor.getString(cursor.getColumnIndex(CurrentBalance)));
        item.setEMIPaid(cursor.getString(cursor.getColumnIndex(EMIPaid)));
        item.setBTAmount(cursor.getString(cursor.getColumnIndex(BTAmount)));
        item.setTopupAmount(cursor.getString(cursor.getColumnIndex(TopupAmount)));
        item.setBTROI(cursor.getString(cursor.getColumnIndex(BTROI)));
        item.setTopupROI(cursor.getString(cursor.getColumnIndex(TopupROI)));
        item.setBTTenure(cursor.getString(cursor.getColumnIndex(BTTenure)));
        item.setTopupTenure(cursor.getString(cursor.getColumnIndex(TopupTenure)));
        item.setBTEMI(cursor.getString(cursor.getColumnIndex(BTEMI)));
        item.setTopupEMI(cursor.getString(cursor.getColumnIndex(TopupEMI)));
        item.setFoir(cursor.getString(cursor.getColumnIndex(Foir)));
        return item;
    }
}
