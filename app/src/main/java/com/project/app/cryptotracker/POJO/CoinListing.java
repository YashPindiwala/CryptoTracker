package com.project.app.cryptotracker.POJO;

import android.content.Context;

public class CoinListing {
    private String coinName;
    private String coinSymbol;
    private int id;
    private int coinId;
    private double percentChange;
    private double price;
    private String lastUpdate;




    public CoinListing(){

    }

    public CoinListing(int id, String coinName, String coinSymbol, double percentChange,double price, Context context) {
        this.id = id;
    }

    public CoinListing(int coinId, String coinName, String coinSymbol, double percentChange, double price) {
        this.coinId = coinId;
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.percentChange = percentChange;
        this.price = price;
//        this.lastUpdate = lastUpdate;
    }

    public CoinListing(int coinId, String coinName, String coinSymbol, double percentChange, double price, String lastUpdate) {
        this.coinId = coinId;
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.percentChange = percentChange;
        this.price = price;
        this.lastUpdate = lastUpdate;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.coinName;
    }
    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
