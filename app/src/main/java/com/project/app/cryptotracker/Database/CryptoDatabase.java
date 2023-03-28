package com.project.app.cryptotracker.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CryptoDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cryptowallet";
    public static final int DATABASE_VERSION = 1;

    /*Favourites table*/
    public static final String FAVORITE_TABLE = "favorite";
    public static final String FAVORITE_COLUMN_COIN_ID = "id";
    public static final String FAVORITE_COLUMN_COIN_NAME = "coin_name";
    public static final String FAVORITE_COLUMN_COIN_LOGO = "coin_logo";
    public static final String FAVORITE_COLUMN_COIN_SYMBOL = "coin_symbol";

    public CryptoDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
