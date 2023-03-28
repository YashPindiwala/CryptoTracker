package com.project.app.cryptotracker.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.project.app.cryptotracker.POJO.CryptoDetail;

public class CryptoDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cryptowallet";
    public static final int DATABASE_VERSION = 1;

    /*Favourites table*/
    public static final String FAVORITE_TABLE = "favorite";
    public static final String FAVORITE_COLUMN_COIN_ID = "id";
    public static final String FAVORITE_COLUMN_COIN_NAME = "coin_name";
    public static final String FAVORITE_COLUMN_COIN_LOGO = "coin_logo";
    public static final String FAVORITE_COLUMN_COIN_SYMBOL = "coin_symbol";

    public static final String CREATE_FAVORITE_TABLE = "CREATE TABLE " +
            FAVORITE_TABLE + "(" + FAVORITE_COLUMN_COIN_ID + " INTEGER PRIMARY KEY,"
            + FAVORITE_COLUMN_COIN_NAME + " TEXT,"
            + FAVORITE_COLUMN_COIN_SYMBOL + " TEXT,"
            + FAVORITE_COLUMN_COIN_LOGO + " TEXT)";

    public CryptoDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addToFavorite(CryptoDetail fav){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+ FAVORITE_COLUMN_COIN_NAME + " FROM " + FAVORITE_TABLE + " WHERE " + FAVORITE_COLUMN_COIN_NAME + " = ?",new String[]{fav.getName()});
        if (cursor.moveToFirst()){
            return false;
        }
        sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAVORITE_COLUMN_COIN_NAME,fav.getName());
        contentValues.put(FAVORITE_COLUMN_COIN_SYMBOL,fav.getSymbol());
        contentValues.put(FAVORITE_COLUMN_COIN_LOGO,fav.getLogo());
        sqLiteDatabase.insert(FAVORITE_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return true;
    }
}
