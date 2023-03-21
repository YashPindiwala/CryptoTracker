package com.project.app.cryptotracker.POJO;

import android.content.Context;

import com.project.app.cryptotracker.API.APIRequestQueue;

public class CoinListing {
    private String coinName;
    private String coinSymbol;

    public CoinListing(String coinName, String coinSymbol, Context context) {
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

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }
}
