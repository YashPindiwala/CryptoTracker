package com.project.app.cryptotracker.POJO;

import android.content.Context;

public class CoinListing {
    private String coinName;
    private String coinSymbol;
    private int id;

    public CoinListing(int id, String coinName, String coinSymbol, Context context) {
        this.id = id;
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
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
}
