package com.lixiong.straight.login.bean;

/**
 * Created by john on 2017/5/28.
 */

public class CodeBean {
    /**
     * xmzbAccountEntityCustom : {"cellphone":13128706274,"codemsg":629530}
     */

    private XmzbAccountEntityCustomBean xmzbAccountEntityCustom;

    public XmzbAccountEntityCustomBean getXmzbAccountEntityCustom() {
        return xmzbAccountEntityCustom;
    }

    public void setXmzbAccountEntityCustom(XmzbAccountEntityCustomBean xmzbAccountEntityCustom) {
        this.xmzbAccountEntityCustom = xmzbAccountEntityCustom;
    }

    public static class XmzbAccountEntityCustomBean {
        /**
         * cellphone : 13128706274
         * codemsg : 629530
         */

        private long cellphone;
        private int codemsg;

        public long getCellphone() {
            return cellphone;
        }

        public void setCellphone(long cellphone) {
            this.cellphone = cellphone;
        }

        public int getCodemsg() {
            return codemsg;
        }

        public void setCodemsg(int codemsg) {
            this.codemsg = codemsg;
        }
    }
}
