package com.gm.emi_calculator.Databasehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gm.emi_calculator.Constant.Constant_Variable;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DB_Helper extends SQLiteAssetHelper {
    SQLiteDatabase f3282a;

    public DB_Helper(Context context) {
        super(context, Constant_Variable.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 13);
    }

    public Cursor getLoanDetails(String str) {
        this.f3282a = getReadableDatabase();
        return this.f3282a.rawQuery("Select id as _id,Loanname,Bankname,Loanacno,Loandate,Emidate,Processfee,Emi,Amount,Interest,Tenure,Totalinterest,CurrencyType From Data where id =" + str + "", (String[]) null);
    }
}
