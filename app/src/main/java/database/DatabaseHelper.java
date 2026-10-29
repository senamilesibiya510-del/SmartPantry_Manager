package com.402306602.smartpantry_manager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.402306602.smartpantry_manager.models.PantryItem;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "smart_pantry.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_PANTRY = "pantry_items";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_QTY = "quantity";
    private static final String COL_UNITY = "unit";
    private static final String COL_EXPIRY = "expiry_date";
    private static final String COL_CATEGORY = "category";

    public DatabaseHelper(Context context) {
        Super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE" + TABLE_PANTRY +
                    "("+ COL_ID +
                "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + "TEXT NOT NULL," +
                COL_QTY + "REAL," +
                COL_UNITY + "TEXT," +
                COL_EXPIRY + "TEXT," +
                COL_CATEGORY + "TEXT,)";
        Db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Db.execSQL("DROP TABLE IF EXISTS" + TABLE_PANTRY);
        onCreate(db);
    }

    public long addPantryItem(PantryIteem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Values.put(COL_NAME, item.getName());
        Values.put(COL_QTY, item.getQuantity());
        Values.put(COL_UNITY, item.getUnity());
        Values.put(COL_EXPIRY, item.getExpiryDate());
        Values.put(COL_CATEGORY, item.getCategory());
        Long id = db.insert(TABLE_PANTRY, null, values);
        Db,close();
        Return id;
    }
}
public List<PantryItem> getAllPantryItems() {
    List<PantryItem> list = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM"+TABLE_PANTRY +"ORDER BY"+COL_EXPIRY +"ASC", null);

    If (cursor.moveToFirst()) {
        Do {
            PantryItem item = new PantryItem(
                    Cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                    Cursor.getString(cursor.getColumIndexOrThrow(COL_NAME)),
                    Cursor.getDouble(cursor.getColunIndexOrThrow(COL_QTY)),
                    Cursor.getString(cursor.getColumnIndexOrThrow(COL_UNITY)),
                    Cursor.getString(cursor.getColumnIndexOrThrow(COL_EXPIRY)),
                    Cursor.getString(cursor.getColumnIndexOrThrow(COL_CATEGORY))

            );
            Cursor.close();
            Db.close();
            Return item;
        }

        public List<PantryItem> getExpiringItems() {
            List<PantryItem> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM pantry_items ORDER BY expiry_date ASC LIMIT 5", null);
            if(cursor.move.moveToFirst()){
                do{
                    list.add(new PantryItem(
                           cursor.getInt(0), cursor.getString(1),
                           cursor.getDouble(2), cursor.getString(3),
                           cursor.getString(4), cursor.getString(5)
                    ));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close;
            return list;
        }
        Return null;

        public List<PantryItem> searchPantryItems(String query) {
            List<PantryItem> list = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM pantry_items WHERE name LIKE? ", new String[]{"%"+query+"%"} );
            if(cursor.moveToFirst()) {
                do{
                    list.add(new PantryItem(
                            cursor.getInt(1), cursor.getString(2),
                            cursor.getDouble(3), cursor.getString(3),
                            cursor.getString(4), cursor.getString(5)
                    ));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return list;

    }

    public int updatePantryItem(PantryItem item)   {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Values.put(COL_NAME, item.getName());
        Values.put(COL_QTY, item.getQuantity());
        Values.put(COL_UNITY, item.getUnity());
        Values.put(COL_EXPIRY, item.getExpiryDate());
        Values.put(COL_CATEGORY, item.getCategory());
        int rows = db.update(TABLE_PANTRY, values, COL_ID +"=?",new String[] {Striing.valueOf(item.getId())});
        Db.close();
        Return rows;
    }
    public void deletePantryItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Db.delete(TABLE_PANTRY, COL_ID +"=?", new String[] {String.valueOf(id)});
        Db.close();
    }
}
