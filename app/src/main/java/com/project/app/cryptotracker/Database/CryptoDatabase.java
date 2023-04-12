package com.project.app.cryptotracker.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.project.app.cryptotracker.POJO.CoinInvestment;
import com.project.app.cryptotracker.POJO.CoinListing;
import com.project.app.cryptotracker.POJO.CryptoDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CryptoDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cryptowallet";
    public static final int DATABASE_VERSION = 1;

    /*Favourites table*/
    public static final String FAVORITE_TABLE = "favorite";
    public static final String FAVORITE_COLUMN_COIN_ID = "id";
    public static final String FAVORITE_COLUMN_COIN_NAME = "coin_name";
    public static final String FAVORITE_COLUMN_COIN_LOGO = "coin_logo";
    public static final String FAVORITE_COLUMN_COIN_SYMBOL = "coin_symbol";
    public static final String FAVORITE_COLUMN_FAV_COIN_ID = "coin_id";

    /*Coins Table*/
    public static final String COIN_TABLE = "coin";
    public static final String COIN_COLUMN_COIN_ID = "id";
    public static final String COIN_COLUMN_COINID = "coin_id";
    public static final String COIN_COLUMN_COIN_NAME = "coin_name";
    public static final String COIN_COLUMN_COIN_SYMBOL = "coin_symbol";
    public static final String COIN_COLUMN_COIN_PRICE = "coin_price";
    public static final String COIN_COLUMN_COIN_CHANGE = "coin_change";
    public static final String COIN_COLUMN_COIN_LAST_UPDATE = "last_update";

    /*Investment Table*/
    public static final String INVESTMENT_TABLE = "investment";
    public static final String INVESTMENT_COLUMN_ID = "id";
    public static final String INVESTMENT_COLUMN_COIN_ID = "coin_id"; // will reference to the coins table -> id
    public static final String INVESTMENT_COLUMN_SYMBOL = "investment_coin_symbol";
    public static final String INVESTMENT_COLUMN_PRICE = "investment_coin_price";
    public static final String INVESTMENT_COLUMN_QNTY = "investment_coin_qnty";


    /*CREATE tables*/
    public static final String CREATE_COIN_TABLE = "CREATE TABLE " +
            COIN_TABLE + "(" + COIN_COLUMN_COIN_ID + " INTEGER PRIMARY KEY,"
            + COIN_COLUMN_COINID + " INTEGER,"
            + COIN_COLUMN_COIN_NAME + " TEXT,"
            + COIN_COLUMN_COIN_SYMBOL + " TEXT,"
            + COIN_COLUMN_COIN_PRICE + " INTEGER,"
            + COIN_COLUMN_COIN_CHANGE +" INTEGER,"
            + COIN_COLUMN_COIN_LAST_UPDATE + " TEXT)";

    public static final String CREATE_FAVORITE_TABLE = "CREATE TABLE " +
            FAVORITE_TABLE + "(" + FAVORITE_COLUMN_COIN_ID + " INTEGER PRIMARY KEY,"
            + FAVORITE_COLUMN_COIN_NAME + " TEXT,"
            + FAVORITE_COLUMN_COIN_SYMBOL + " TEXT,"
            + FAVORITE_COLUMN_COIN_LOGO + " TEXT,"
            + FAVORITE_COLUMN_FAV_COIN_ID + " INTEGER)";

    public static final String CREATE_INVESTMENT_TABLE = "CREATE TABLE " +
            INVESTMENT_TABLE + "("
            + INVESTMENT_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + INVESTMENT_COLUMN_COIN_ID + " INTEGER,"
            + INVESTMENT_COLUMN_SYMBOL + " TEXT,"
            + INVESTMENT_COLUMN_PRICE + " INTEGER,"
            + INVESTMENT_COLUMN_QNTY + " INTEGER,"
            + "FOREIGN KEY (" + INVESTMENT_COLUMN_COIN_ID + ") REFERENCES "+ COIN_TABLE + "("+ COIN_COLUMN_COINID +"))";

    public CryptoDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COIN_TABLE);
        db.execSQL(CREATE_FAVORITE_TABLE);
        db.execSQL(CREATE_INVESTMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean truncateCoinTable(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase.delete(COIN_TABLE,null,null) > 0){
            return true;
        }
        sqLiteDatabase.close();
        return false;

    }

    public CoinListing getFirstCoin(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(COIN_TABLE,new String[]{"*"},null,null,null,null,null);
        if (cursor != null && cursor.moveToFirst()){
            return new CoinListing(cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getDouble(5),cursor.getInt(4),cursor.getString(6));
        }
        return null;
    }

    public boolean addToCoin(CoinListing coin){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COIN_COLUMN_COINID, coin.getCoinId());
        contentValues.put(COIN_COLUMN_COIN_NAME, coin.getCoinName());
        contentValues.put(COIN_COLUMN_COIN_SYMBOL, coin.getCoinSymbol());
        contentValues.put(COIN_COLUMN_COIN_PRICE, coin.getPrice());
        contentValues.put(COIN_COLUMN_COIN_CHANGE, coin.getPercentChange());
        contentValues.put(COIN_COLUMN_COIN_LAST_UPDATE, dateFormat.format(System.currentTimeMillis()));
        sqLiteDatabase.insert(COIN_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return true;
    }

    public boolean addAllCoin(ArrayList<CoinListing> coins){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        for (CoinListing coin:
             coins) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COIN_COLUMN_COINID, coin.getCoinId());
            contentValues.put(COIN_COLUMN_COIN_NAME, coin.getCoinName());
            contentValues.put(COIN_COLUMN_COIN_SYMBOL, coin.getCoinSymbol());
            contentValues.put(COIN_COLUMN_COIN_PRICE, coin.getPrice());
            contentValues.put(COIN_COLUMN_COIN_CHANGE, coin.getPercentChange());
            contentValues.put(COIN_COLUMN_COIN_LAST_UPDATE, dateFormat.format(System.currentTimeMillis()));
            sqLiteDatabase.insert(COIN_TABLE,null,contentValues);
        }
        sqLiteDatabase.close();
        return true;
    }

    public ArrayList<CoinListing> getAlCoin(){
        ArrayList<CoinListing> coinListings = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + COIN_TABLE,null);
        while (cursor.moveToNext()){
            CoinListing coin = new CoinListing();
            coin.setId(cursor.getInt(0));
            coin.setCoinId(cursor.getInt(1));
            coin.setCoinName(cursor.getString(2));
            coin.setCoinSymbol(cursor.getString(3));
            coin.setPrice(cursor.getDouble(4));
            coin.setPercentChange(cursor.getDouble(5));
            coin.setLastUpdate(cursor.getString(6));
            coinListings.add(coin);
        }
        sqLiteDatabase.close();
        return coinListings;
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
    public ArrayList<CryptoDetail> getAllFavCoin(){
        ArrayList<CryptoDetail> cryptoDetails = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + FAVORITE_TABLE,null);
        while (cursor.moveToNext()){
            CryptoDetail cryptoDetail = new CryptoDetail();
            cryptoDetail.setId(cursor.getInt(0));
            cryptoDetail.setName(cursor.getString(1));
            cryptoDetail.setSymbol(cursor.getString(2));
            cryptoDetail.setLogo(cursor.getString(3));
            cryptoDetails.add(
                    cryptoDetail
            );
        }
        sqLiteDatabase.close();
        return cryptoDetails;
    }

    public boolean removeFavCoin(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        int status = database.delete(FAVORITE_TABLE,FAVORITE_COLUMN_COIN_ID + "= ?",new String[]{String.valueOf(id)});
        database.close();
        if (status == 1)
            return true;
        else
            return false;
    }

    public boolean addToInvestment(CoinInvestment coinInvestment){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INVESTMENT_COLUMN_COIN_ID, coinInvestment.getCoinId());
        contentValues.put(INVESTMENT_COLUMN_SYMBOL, coinInvestment.getCoinSymbol());
        contentValues.put(INVESTMENT_COLUMN_PRICE, coinInvestment.getPrice());
        contentValues.put(INVESTMENT_COLUMN_QNTY, coinInvestment.getQnty());
        sqLiteDatabase.insert(INVESTMENT_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return true;
    }

    public ArrayList<CoinInvestment> getAllInvestment(){
        ArrayList<CoinInvestment> coinInvestments = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT i."+INVESTMENT_COLUMN_COIN_ID+",i."+INVESTMENT_COLUMN_SYMBOL+",i."+INVESTMENT_COLUMN_PRICE+",i."+INVESTMENT_COLUMN_QNTY+",c."+COIN_COLUMN_COIN_PRICE+" FROM " + INVESTMENT_TABLE + " AS i," + COIN_TABLE + " AS c WHERE i." + INVESTMENT_COLUMN_COIN_ID + "= c." +COIN_COLUMN_COIN_ID,null);
        while (cursor.moveToNext()){
            CoinInvestment coinInvestment = new CoinInvestment(
                    cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getDouble(3)
            );
            coinInvestment.setMarket(cursor.getDouble(4));
            coinInvestments.add(coinInvestment);
        }
        sqLiteDatabase.close();
        return coinInvestments;
    }
}
