package com.lixiong.straight.my.bean;

import java.io.Serializable;

/**
 * Created by john on 2017/6/3.
 */

public class ServiceCategory implements Serializable{

    private String itemCode;
    private String itemName;

    public ServiceCategory() {
    }

    public ServiceCategory(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "ServiceCategory{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
