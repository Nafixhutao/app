package com.example.tombolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper SQLite untuk menyimpan jumlah penekanan tombol secara permanen.
 * Data tetap ada meski aplikasi ditutup / HP direstart.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tombol.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE = "counter";
    private static final String COL_ID = "id";
    private static final String COL_COUNT = "total";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Buat tabel dan satu baris awal dengan nilai 0
        db.execSQL("CREATE TABLE " + TABLE + " ("
                + COL_ID + " INTEGER PRIMARY KEY, "
                + COL_COUNT + " INTEGER NOT NULL)");
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, 1);
        cv.put(COL_COUNT, 0);
        db.insert(TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    /** Ambil jumlah penekanan saat ini. */
    public int getCount() {
        SQLiteDatabase db = getReadableDatabase();
        int value = 0;
        Cursor c = db.rawQuery(
                "SELECT " + COL_COUNT + " FROM " + TABLE + " WHERE " + COL_ID + " = 1", null);
        if (c.moveToFirst()) {
            value = c.getInt(0);
        }
        c.close();
        return value;
    }

    /** Tambah 1 penekanan, simpan, lalu kembalikan nilai baru. */
    public int increment() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE + " SET " + COL_COUNT + " = " + COL_COUNT
                + " + 1 WHERE " + COL_ID + " = 1");
        return getCount();
    }

    /** Reset kembali ke 0 (opsional, dipakai tombol tahan-lama). */
    public void reset() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE + " SET " + COL_COUNT + " = 0 WHERE " + COL_ID + " = 1");
    }
}
