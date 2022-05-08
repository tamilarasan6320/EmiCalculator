package com.example.emi_calculator;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.emi_calculator.model.ModelNewBTHistory;
import com.example.emi_calculator.model.ROI;

import java.util.ArrayList;

public class ROISqliteManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "jp.db";
    public static final int version = 1;

    public String bank_hfc = "bank_hfc";
    public ROISqliteManager(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbQuery = "CREATE TABLE ROIs (id INTEGER PRIMARY KEY AUTOINCREMENT,bank_hfc TEXT)";
        sqLiteDatabase.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
    public void addROI(ROI modelROI) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(bank_hfc, modelROI.getBank());

        db.insert("ROIs", null, contentValues);//Items is table name
        db.close();
    }
    public void deleteitem() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from Items");
        db.close();
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }
    public ArrayList<ROI> readAllROIs() {
        ArrayList<ROI> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
//see above point 2 function
        Cursor cursor = db.query("ROIs"
                , null// columns - null will give all
                , null// selection
                , null// selection arguments
                , null// groupBy
                , null// having
                , null);// no need or order by for now;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // move the cursor to next row if there is any to read it's data
                ROI item = readItem(cursor);
                items.add(item);
            }
        }
        return items;
    }
    @SuppressLint("Range")
    private ROI readItem(Cursor cursor) {
        ROI item = new ROI();

//        item.setSalary(cursor.getString(cursor.getColumnIndex(Salary)));
//        item.setSanctionedAmount(cursor.getString(cursor.getColumnIndex(SanctionedAmount)));
//        item.setCurrentBalance(cursor.getString(cursor.getColumnIndex(CurrentBalance)));
//        item.setEMIPaid(cursor.getString(cursor.getColumnIndex(EMIPaid)));
//        item.setBTAmount(cursor.getString(cursor.getColumnIndex(BTAmount)));
//        item.setTopupAmount(cursor.getString(cursor.getColumnIndex(TopupAmount)));
//        item.setBTROI(cursor.getString(cursor.getColumnIndex(BTROI)));
//        item.setTopupROI(cursor.getString(cursor.getColumnIndex(TopupROI)));
//        item.setBTTenure(cursor.getString(cursor.getColumnIndex(BTTenure)));
//        item.setTopupTenure(cursor.getString(cursor.getColumnIndex(TopupTenure)));
//        item.setBTEMI(cursor.getString(cursor.getColumnIndex(BTEMI)));
//        item.setTopupEMI(cursor.getString(cursor.getColumnIndex(TopupEMI)));
//        item.setFoir(cursor.getString(cursor.getColumnIndex(Foir)));
        return item;
    }
}
