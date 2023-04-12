package com.project.app.cryptotracker.POJO;

public class CoinInvestment {
    private int id;
    private int coinId;
    private String coinSymbol;
    private double price;
    private double qnty;
    private double market;

    public CoinInvestment(int coinId, String coinSymbol, double price, double qnty) {
        this.coinId = coinId;
        this.coinSymbol = coinSymbol;
        this.price = price;
        this.qnty = qnty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public double getMarket() {
        return market;
    }

    public void setMarket(double market) {
        this.market = market;
    }
}
