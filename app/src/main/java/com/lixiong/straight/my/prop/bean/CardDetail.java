package com.lixiong.straight.my.prop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by john on 2017/6/9.
 */

public class CardDetail {
    private List<XmzbToolsEntityCustomBean> xmzbToolsEntityCustom;

    public List<XmzbToolsEntityCustomBean> getXmzbToolsEntityCustom() {
        return xmzbToolsEntityCustom;
    }

    public void setXmzbToolsEntityCustom(List<XmzbToolsEntityCustomBean> xmzbToolsEntityCustom) {
        this.xmzbToolsEntityCustom = xmzbToolsEntityCustom;
    }

    public static class XmzbToolsEntityCustomBean implements Serializable{
        /**
         * description : 开通关注特权,即可享受最高30个关注联系人上限的特权.多人聊天中帮您快速定位重要联系人.关注联系人会在您的消息列表中置顶展示.可进行统一管理.方便快捷.
         * price : 15牛币,25牛币,35牛币
         * priceType : 5人/30天,15人/60天,30人/90天
         * toolCode : 8384161d-18dc-4a6f-bfa9-328e155fbb95
         * toolImg : 无图2
         * toolName : 关注卡
         */

        private String description;
        private String price;
        private String priceType;
        private String toolCode;
        private String toolImg;
        private String toolName;

        public XmzbToolsEntityCustomBean(String description, String price, String priceType, String toolCode, String toolImg, String toolName) {
            this.description = description;
            this.price = price;
            this.priceType = priceType;
            this.toolCode = toolCode;
            this.toolImg = toolImg;
            this.toolName = toolName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getToolCode() {
            return toolCode;
        }

        public void setToolCode(String toolCode) {
            this.toolCode = toolCode;
        }

        public String getToolImg() {
            return toolImg;
        }

        public void setToolImg(String toolImg) {
            this.toolImg = toolImg;
        }

        public String getToolName() {
            return toolName;
        }

        public void setToolName(String toolName) {
            this.toolName = toolName;
        }
    }
}
