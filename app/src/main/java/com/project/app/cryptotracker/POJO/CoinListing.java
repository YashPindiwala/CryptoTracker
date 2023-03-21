package com.project.app.cryptotracker.POJO;

public class CoinListing {
    private String coinName;
    private String coinSymbol;

    public CoinListing(String coinName, String coinSymbol) {
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
