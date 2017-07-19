package com.lixiong.straight.my.prop.bean;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by john on 2017/6/9.
 */

public class ShoppingProp extends SugarRecord<ShoppingProp>{

    private List<XmzbToolsEntityCustomBean> xmzbToolsEntityCustom;

    public List<XmzbToolsEntityCustomBean> getXmzbToolsEntityCustom() {
        return xmzbToolsEntityCustom;
    }

    public void setXmzbToolsEntityCustom(List<XmzbToolsEntityCustomBean> xmzbToolsEntityCustom) {
        this.xmzbToolsEntityCustom = xmzbToolsEntityCustom;
    }

    public static class XmzbToolsEntityCustomBean extends SugarRecord<XmzbToolsEntityCustomBean>{
        /**
         * description : 开通关注特权,即可享受最高30个关注联系人上限的特权.多人聊天中帮您快速定位重要联系人.关注联系人会在您的消息列表中置顶展示.可进行统一管理.方便快捷.
         * price : 19牛币
         * toolCode : 8384161d-18dc-4a6f-bfa9-328e155fbb95
         * toolImg : 无图2
         * toolName : 关注卡
         * unitInfo : 天
         */

        private String description;
        private String price;
        private String toolCode;
        private String toolImg;
        private String toolName;
        private String unitInfo;

        public XmzbToolsEntityCustomBean(String description, String price, String toolCode, String toolImg, String toolName, String unitInfo) {
            this.description = description;
            this.price = price;
            this.toolCode = toolCode;
            this.toolImg = toolImg;
            this.toolName = toolName;
            this.unitInfo = unitInfo;
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

        public String getUnitInfo() {
            return unitInfo;
        }

        public void setUnitInfo(String unitInfo) {
            this.unitInfo = unitInfo;
        }
    }
}
