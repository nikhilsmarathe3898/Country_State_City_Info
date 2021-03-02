package com.example.country_state_city_info;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "CountryDatabase";
    public static final String TAG = "DatabaseHelper";

    public static final String ID = "_id";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String CITY = "city";

    static final String DB_NAME = "LocationDetail";
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creating The Table ..
        String sql = "CREATE TABLE " + DB_NAME + "(_id INTEGER PRIMARY KEY  AUTOINCREMENT, " + COUNTRY + " TEXT, " + STATE + " TEXT," + CITY + " TEXT)";
        db.execSQL(sql);
        Log.d(TAG, "onCreate: " + sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Delete the Table if Already Exists at Once ..
        db.execSQL("DROP IF TABLE EXISTS " + DB_NAME);
    }

    public boolean insertData(String country, String state, String city) {

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Country", country);
        values.put("State", state);
        values.put("City", city);

        long result = db.insert(DB_NAME, null, values);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){

        SQLiteDatabase db = this.getWritableDatabase();
        String select = "SELECT * FROM " + DB_NAME;

        Cursor data =db.rawQuery(select,null);
        return data;
    }
}
