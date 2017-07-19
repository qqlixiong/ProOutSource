package com.lixiong.straight.my.settings;

/**
 * 修改成功
 * Created by john on 2017/6/13.
 */

public class ModifySuccess {
    /**
     * xmzbBackCodes : {"successCode":201}   201表示成功   202表示失败
     */

    private XmzbBackCodesBean xmzbBackCodes;

    public XmzbBackCodesBean getXmzbBackCodes() {
        return xmzbBackCodes;
    }

    public void setXmzbBackCodes(XmzbBackCodesBean xmzbBackCodes) {
        this.xmzbBackCodes = xmzbBackCodes;
    }

    public static class XmzbBackCodesBean {
        /**
         * successCode : 201
         */

        private int successCode;

        public int getSuccessCode() {
            return successCode;
        }

        public void setSuccessCode(int successCode) {
            this.successCode = successCode;
        }
    }
}
