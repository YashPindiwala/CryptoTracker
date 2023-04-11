package com.project.app.cryptotracker.POJO;

import android.content.Context;

public class CoinListing {
    private String coinName;
    private String coinSymbol;
    private int id;
    private double percentChange;

    private double price;



    public CoinListing(int id, String coinName, String coinSymbol, double percentChange,double price, Context context) {
        this.id = id;
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.percentChange = percentChange;
        this.price = price;
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
}
