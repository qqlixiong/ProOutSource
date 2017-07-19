package com.lixiong.straight.my.bean;

import com.orm.SugarRecord;

import java.util.List;

/**
 * 人员信息
 * Created by john on 2017/5/23.
 */

public class PersonBean extends SugarRecord<PersonBean> {

    private List<XmzbAccountEntityCustomBean> xmzbAccountEntityCustom;

    public List<XmzbAccountEntityCustomBean> getXmzbAccountEntityCustom() {
        return xmzbAccountEntityCustom;
    }

    public void setXmzbAccountEntityCustom(List<XmzbAccountEntityCustomBean> xmzbAccountEntityCustom) {
        this.xmzbAccountEntityCustom = xmzbAccountEntityCustom;
    }

    public static class XmzbAccountEntityCustomBean extends SugarRecord<PersonBean> {
        /**
         * accSyscodes : d12035b6-9591-40f8-a433-a1bc659c636f
         * approveStatus : 0
         * cellphone : 待开发
         * codemsg : 0
         * companyContent : 作为国内领先的网络营销软件提供商，不断研发领先的网络营销软件，打造一流的推广产品；更致力于在互联网领域构建全新的OEM合作模式，建立合作更牢固、分工更科学、竞争更有优势的全新合作模式，从而改变现有网络行业效率低下、问题成堆及对渠道商严重不公的渠道体系，与合作伙伴共建网络行业合作新体系。
         * contentDesc : 运营总监
         * email : 2933929509@qq.com
         * identityTypeid : 1001
         * personCode : ec674bad-ce8f-444e-b6a7-450b2a8f9bbd
         * personLogo : img/dsdsadsd.jsg
         * personName : 阿娇
         * personSyscode : c60e4224-319c-40cb-8770-20a9750c14f8
         * weixinnum : 待开发
         */

        private String accSyscodes;
        private String approveStatus;
        private String cellphone;
        private String codemsg;
        private String companyContent;
        private String contentDesc;
        private String email;
        private String identityTypeid;
        private String personCode;
        private String personLogo;
        private String personName;
        private String personSyscode;
        private String weixinnum;

        public XmzbAccountEntityCustomBean(String accSyscodes, String approveStatus, String cellphone, String codemsg, String companyContent, String contentDesc, String email, String identityTypeid, String personCode, String personLogo, String personName, String personSyscode, String weixinnum) {
            this.accSyscodes = accSyscodes;
            this.approveStatus = approveStatus;
            this.cellphone = cellphone;
            this.codemsg = codemsg;
            this.companyContent = companyContent;
            this.contentDesc = contentDesc;
            this.email = email;
            this.identityTypeid = identityTypeid;
            this.personCode = personCode;
            this.personLogo = personLogo;
            this.personName = personName;
            this.personSyscode = personSyscode;
            this.weixinnum = weixinnum;
        }

        public String getAccSyscodes() {
            return accSyscodes;
        }

        public void setAccSyscodes(String accSyscodes) {
            this.accSyscodes = accSyscodes;
        }

        public String getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(String approveStatus) {
            this.approveStatus = approveStatus;
        }

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        public String getCodemsg() {
            return codemsg;
        }

        public void setCodemsg(String codemsg) {
            this.codemsg = codemsg;
        }

        public String getCompanyContent() {
            return companyContent;
        }

        public void setCompanyContent(String companyContent) {
            this.companyContent = companyContent;
        }

        public String getContentDesc() {
            return contentDesc;
        }

        public void setContentDesc(String contentDesc) {
            this.contentDesc = contentDesc;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdentityTypeid() {
            return identityTypeid;
        }

        public void setIdentityTypeid(String identityTypeid) {
            this.identityTypeid = identityTypeid;
        }

        public String getPersonCode() {
            return personCode;
        }

        public void setPersonCode(String personCode) {
            this.personCode = personCode;
        }

        public String getPersonLogo() {
            return personLogo;
        }

        public void setPersonLogo(String personLogo) {
            this.personLogo = personLogo;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getPersonSyscode() {
            return personSyscode;
        }

        public void setPersonSyscode(String personSyscode) {
            this.personSyscode = personSyscode;
        }

        public String getWeixinnum() {
            return weixinnum;
        }

        public void setWeixinnum(String weixinnum) {
            this.weixinnum = weixinnum;
        }
    }
}
