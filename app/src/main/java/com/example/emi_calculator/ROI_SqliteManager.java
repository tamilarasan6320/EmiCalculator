package com.example.emi_calculator;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emi_calculator.model.ROI;

import java.util.ArrayList;

public class ROI_SqliteManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "emical";
    public static final int version = 6;
    public String bank_hfc = "bank_hfc";
    public String category = "category";
    public String lakhs_30_75 = "lakhs_30_75";
    public String lakhs_75 = "lakhs_75";

    public ROI_SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbQuery = "CREATE TABLE Roi (id INTEGER PRIMARY KEY AUTOINCREMENT,category TEXT,bank_hfc TEXT,lakhs_30_75 TEXT,lakhs_75 TEXT)";
        sqLiteDatabase.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
    public void addItem(ROI modelBTHistory) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(category, modelBTHistory.getCategory());
        contentValues.put(bank_hfc, modelBTHistory.getBank_hfc());
        contentValues.put(lakhs_30_75, modelBTHistory.getLakhs_30_75());
        contentValues.put(lakhs_75, modelBTHistory.getLakhs_75());

        // description is column in Roi table, item.description has value for description
        db.insert("Roi", null, contentValues);//Roi is table name
        db.close();
    }
    public void deleteitem() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from Roi");
        db.close();
    }
    public ArrayList<ROI> readAllRoi() {
        ArrayList<ROI> Roi = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
//see above point 2 function
        Cursor cursor = db.query("Roi"
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
                Roi.add(item);
            }
        }
        return Roi;
    }
    @SuppressLint("Range")
    private ROI readItem(Cursor cursor) {
        ROI item = new ROI();
        item.setBank_hfc(cursor.getString(cursor.getColumnIndex(bank_hfc)));
        return item;
    }
}
