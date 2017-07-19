package com.lixiong.straight.project.bean;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

/**
 * 项目信息
 * Created by john on 2017/6/10.
 */

public class ProMsg extends SugarRecord<ProMsg>{
    private List<ProjectsEntity> xmzbProjectsEntityCustom;

    public List<ProjectsEntity> getXmzbProjectsEntityCustom() {
        return xmzbProjectsEntityCustom;
    }

    public void setXmzbProjectsEntityCustom(List<ProjectsEntity> xmzbProjectsEntityCustom) {
        this.xmzbProjectsEntityCustom = xmzbProjectsEntityCustom;
    }

    public static class ProjectsEntity extends SugarRecord<ProjectsEntity> implements Serializable{
        /**
         * accSyscode : 0b03e755-00b2-4107-b220-75f8f2e1041f
         * city : 郑州市
         * identityTypeid : 1002
         * itemCode : 19.07,19.06,19.08
         * itemCodefirst : 7
         * itemFirstNames : 销售行业
         * itemName : 手游,3D游戏,网页游戏
         * personCode : 5aceb37b-1faf-4998-abf3-a5cd1df85b29
         * personName : 汪海鹏
         * priceBegin : 100
         * priceEnd : 600
         * proname : 游戏广告
         * protime : 2周
         * province : 河南省
         */

        private String accSyscode;
        private String city;
        private String identityTypeid;
        private String itemCode;
        private String itemCodefirst;
        private String personLogo;
        private String proItemFirstNames;
        private String itemName;
        private String personCode;
        private String personName;
        private String priceBegin;
        private String priceEnd;
        private String proname;
        private String protime;
        private String province;

        public ProjectsEntity() {
        }

        public ProjectsEntity(String accSyscode, String city, String identityTypeid, String itemCode, String itemCodefirst, String personLogo, String proItemFirstNames, String itemName, String personCode, String personName, String priceBegin, String priceEnd, String proname, String protime, String province) {
            this.accSyscode = accSyscode;
            this.city = city;
            this.identityTypeid = identityTypeid;
            this.itemCode = itemCode;
            this.itemCodefirst = itemCodefirst;
            this.personLogo = personLogo;
            this.proItemFirstNames = proItemFirstNames;
            this.itemName = itemName;
            this.personCode = personCode;
            this.personName = personName;
            this.priceBegin = priceBegin;
            this.priceEnd = priceEnd;
            this.proname = proname;
            this.protime = protime;
            this.province = province;
        }

        public String getAccSyscode() {
            return accSyscode;
        }

        public String getProItemFirstNames() {
            return proItemFirstNames;
        }

        public void setProItemFirstNames(String proItemFirstNames) {
            this.proItemFirstNames = proItemFirstNames;
        }

        public String getPersonLogo() {
            return personLogo;
        }

        public void setPersonLogo(String personLogo) {
            this.personLogo = personLogo;
        }

        public void setAccSyscode(String accSyscode) {
            this.accSyscode = accSyscode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIdentityTypeid() {
            return identityTypeid;
        }

        public void setIdentityTypeid(String identityTypeid) {
            this.identityTypeid = identityTypeid;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getItemCodefirst() {
            return itemCodefirst;
        }

        public void setItemCodefirst(String itemCodefirst) {
            this.itemCodefirst = itemCodefirst;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getPersonCode() {
            return personCode;
        }

        public void setPersonCode(String personCode) {
            this.personCode = personCode;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getPriceBegin() {
            return priceBegin;
        }

        public void setPriceBegin(String priceBegin) {
            this.priceBegin = priceBegin;
        }

        public String getPriceEnd() {
            return priceEnd;
        }

        public void setPriceEnd(String priceEnd) {
            this.priceEnd = priceEnd;
        }

        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }

        public String getProtime() {
            return protime;
        }

        public void setProtime(String protime) {
            this.protime = protime;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
