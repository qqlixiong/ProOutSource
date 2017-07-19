package com.lixiong.straight.login.bean;

import com.orm.SugarApp;

import java.util.List;

/**
 * Created by john on 2017/5/24.
 */

public class QueryPhoneBean extends SugarApp{
    private List<XmzbAccountEntityCustomBean> xmzbAccountEntityCustom;

    public List<XmzbAccountEntityCustomBean> getXmzbAccountEntityCustom() {
        return xmzbAccountEntityCustom;
    }

    public void setXmzbAccountEntityCustom(List<XmzbAccountEntityCustomBean> xmzbAccountEntityCustom) {
        this.xmzbAccountEntityCustom = xmzbAccountEntityCustom;
    }

    public static class XmzbAccountEntityCustomBean extends SugarApp{
        /**
         * accSyscode : ec674bad-ce8f-444e-b6a7-450b2a8f9bbd
         * cellphone : 13691715128
         */

        private String accSyscode;
        private long cellphone;

        public String getAccSyscode() {
            return accSyscode;
        }

        public void setAccSyscode(String accSyscode) {
            this.accSyscode = accSyscode;
        }

        public long getCellphone() {
            return cellphone;
        }

        public void setCellphone(long cellphone) {
            this.cellphone = cellphone;
        }

        @Override
        public String toString() {
            return "XmzbAccountEntityCustomBean{" +
                    "accSyscode='" + accSyscode + '\'' +
                    ", cellphone=" + cellphone +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "QueryPhoneBean{" +
                "xmzbAccountEntityCustom=" + xmzbAccountEntityCustom +
                '}';
    }
}
