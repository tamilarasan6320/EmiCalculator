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
    public static final int version = 10;
    public String bank = "bank";
    public String category = "category";
    public String interest = "interest";

    public ROI_SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String dbQuery = "CREATE TABLE Roi (id INTEGER PRIMARY KEY AUTOINCREMENT,category TEXT,bank TEXT,interest TEXT)";
        sqLiteDatabase.execSQL(dbQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }
    public void addItem(ROI modelBTHistory) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(category, modelBTHistory.getCategory());
        contentValues.put(bank, modelBTHistory.getBank());
        contentValues.put(interest, modelBTHistory.getInterest());


        // description is column in Roi table, item.description has value for description
        db.insert("Roi", null, contentValues);//Roi is table name
        db.close();
    }
    public void deleteitem() {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from Roi");
        db.close();
    }
    public ArrayList<ROI> readAllRoi(String cat) {
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
                if (item.getCategory().equals(cat)){
                    Roi.add(item);

                }

            }
        }
        return Roi;
    }
    @SuppressLint("Range")
    private ROI readItem(Cursor cursor) {
        ROI item = new ROI();
        item.setCategory(cursor.getString(cursor.getColumnIndex(category)));
        item.setBank(cursor.getString(cursor.getColumnIndex(bank)));
        item.setInterest(cursor.getString(cursor.getColumnIndex(interest)));

        return item;
    }
}
