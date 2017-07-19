package com.lixiong.straight.my.prop.bean;

/**
 * Created by john on 2017/6/9.
 */

public class CardPrice {
    private String price;
    private String priceType;

    public CardPrice(String price, String priceType) {
        this.price = price;
        this.priceType = priceType;
    }

    @Override
    public String toString() {
        return "CardPrice{" +
                "price='" + price + '\'' +
                ", priceType='" + priceType + '\'' +
                '}';
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
}
