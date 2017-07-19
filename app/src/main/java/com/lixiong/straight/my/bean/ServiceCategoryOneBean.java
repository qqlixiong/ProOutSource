package com.lixiong.straight.my.bean;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by john on 2017/6/1.
 */

public class ServiceCategoryOneBean extends SugarRecord<ServiceCategoryOneBean>{
    private List<XmzbDictItemEntityCustomBean> xmzbDictItemEntityCustom;

    public List<XmzbDictItemEntityCustomBean> getXmzbDictItemEntityCustom() {
        return xmzbDictItemEntityCustom;
    }

    public void setXmzbDictItemEntityCustom(List<XmzbDictItemEntityCustomBean> xmzbDictItemEntityCustom) {
        this.xmzbDictItemEntityCustom = xmzbDictItemEntityCustom;
    }

    @Override
    public String toString() {
        return "ServiceCategoryOneBean{" +
                "xmzbDictItemEntityCustom=" + xmzbDictItemEntityCustom +
                '}';
    }

    public static class XmzbDictItemEntityCustomBean extends SugarRecord<XmzbDictItemEntityCustomBean>{
        /**
         * itemCode : 1
         * itemName : 品牌设计
         */

        private String itemCode;
        private String itemName;

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
            return "XmzbDictItemEntityCustomBean{" +
                    "itemCode='" + itemCode + '\'' +
                    ", itemName='" + itemName + '\'' +
                    '}';
        }
    }
}
