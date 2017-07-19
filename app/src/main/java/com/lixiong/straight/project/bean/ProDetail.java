package com.lixiong.straight.project.bean;

import java.util.List;

/**
 * 项目详情
 * Created by john on 2017/6/11.
 */

public class ProDetail {
    private List<ProjectsDetailEntity> xmzbProjectsEntityCustom;

    public List<ProjectsDetailEntity> getXmzbProjectsEntityCustom() {
        return xmzbProjectsEntityCustom;
    }

    public void setXmzbProjectsEntityCustom(List<ProjectsDetailEntity> xmzbProjectsEntityCustom) {
        this.xmzbProjectsEntityCustom = xmzbProjectsEntityCustom;
    }

    public static class ProjectsDetailEntity {
        /**
         * accSyscode : b40c3ced-a3a1-46fd-bcf7-7722f8f9c660
         * attendStatus : 已关注
         * city : 九江市
         * description : 开了个便利店找合适的团队定做一个收银后台发PM应该多了解背景和筛选最优方案这两个过程，比如我们应该了解反馈的用户是集中在手机端还是web端？是不是我们的支付路径太长？是否可优化？是否有其他购买方式？反馈的用户有些什么特征呢？等等，即便最后也是通过一键购买解决，但值得肯定的是我们选择的是当前的最优解。
         为什么要这么做的原因就是两点：
         1、用户不一定能准确描述自己的需求，或者是用户需求不等于用户的问题。
         2、用户的解决方案不一定是最优的。PM就是发现背后的用户需求然后很优雅的解决它。
         这就是经常听到PM要去了解用户需求和场景重要性的原因，另外需要注意的是向团队描述需求时，一定要先描述用户的需求和场景，然后在描述解决方案，避免跳过这步直接描述功能需求。否则结果大家都懂得。顺丰PM应该多了解背景和筛选最优方案这两个过程，比如我们应该了解反馈的用户是集中在手机端还是web端？是不是我们的支付路径太长？是否可优化？是否有其他购买方式？反馈的用户有些什么特征呢？等等，即便最后也是通过一键购买解决，但值得肯定的是我们选择的是当前的最优解。
         为什么要这么做的原因就是两点：
         1、用户不一定能准确描述自己的需求，或者是用户需求不等于用户的问题。
         2、用户的解决方案不一定是最优的。PM就是发现背后的用户需求然后很优雅的解决它。
         这就是经常听到PM要去了解用户需求和场景重要性的原因，另外需要注意的是向团队描述需求时，一定要先描述用户的需求和场景，然后在描述解决方案，避免跳过这步直接描述功能需求。否则结果大家都懂得。PM应该多了解背景和筛选最优方案这两个过程，比如我们应该了解反馈的用户是集中在手机端还是web端？是不是我们的支付路径太长？是否可优化？是否有其他购买方式？反馈的用户有些什么特征呢？等等，即便最后也是通过一键购买解决，但值得肯定的是我们选择的是当前的最优解。
         为什么要这么做的原因就是两点：
         1、用户不一定能准确描述自己的需求，或者是用户需求不等于用户的问题。
         2、用户的解决方案不一定是最优的。PM就是发现背后的用户需求然后很优雅的解决它。
         这就是经常听到PM要去了解用户需求和场景重要性的原因，另外需要注意的是向团队描述需求时，一定要先描述用户的需求和场景，然后在描述解决方案，避免跳过这步直接描述功能需求。否则结果大家都懂得。
         * identityTypeid : 002
         * itemCode : 8.01,8.03,8.05
         * itemCodefirst : 8
         * itemName : 网站开发,工具软件,行业解决方案
         * number : 8人以上
         * perItemFirstNames : 食品销售
         * personCode : 40bedc32-0156-42bb-a638-f0d4badbc963
         * personIntroduct : 公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对
         * personLogo : http://192.168.1.124:8080/XMZB/upload/logos/1497152010829.png
         * personName : 汪雪梅
         * priceBegin : 4600
         * priceEnd : 8500
         * proItemFirstNames : 软件开发
         * proname : 收银后台系统
         * protime : 30天
         * province : 江西省
         */

        private String accSyscode;
        private String attendStatus;     //0为未关注   1为已关注
        private String city;
        private String description;
        private String identityTypeid;
        private String itemCode;
        private String itemCodefirst;
        private String itemName;
        private String number;
        private String perItemFirstNames;
        private String personCode;
        private String personIntroduct;
        private String personLogo;
        private String personName;
        private String priceBegin;
        private String priceEnd;
        private String proItemFirstNames;
        private String proname;
        private String protime;
        private String province;

        public ProjectsDetailEntity(String accSyscode, String attendStatus, String city, String description, String identityTypeid, String itemCode, String itemCodefirst, String itemName, String number, String perItemFirstNames, String personCode, String personIntroduct, String personLogo, String personName, String priceBegin, String priceEnd, String proItemFirstNames, String proname, String protime, String province) {
            this.accSyscode = accSyscode;
            this.attendStatus = attendStatus;
            this.city = city;
            this.description = description;
            this.identityTypeid = identityTypeid;
            this.itemCode = itemCode;
            this.itemCodefirst = itemCodefirst;
            this.itemName = itemName;
            this.number = number;
            this.perItemFirstNames = perItemFirstNames;
            this.personCode = personCode;
            this.personIntroduct = personIntroduct;
            this.personLogo = personLogo;
            this.personName = personName;
            this.priceBegin = priceBegin;
            this.priceEnd = priceEnd;
            this.proItemFirstNames = proItemFirstNames;
            this.proname = proname;
            this.protime = protime;
            this.province = province;
        }

        public String getAccSyscode() {
            return accSyscode;
        }

        public void setAccSyscode(String accSyscode) {
            this.accSyscode = accSyscode;
        }

        public String getAttendStatus() {
            return attendStatus;
        }

        public void setAttendStatus(String attendStatus) {
            this.attendStatus = attendStatus;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPerItemFirstNames() {
            return perItemFirstNames;
        }

        public void setPerItemFirstNames(String perItemFirstNames) {
            this.perItemFirstNames = perItemFirstNames;
        }

        public String getPersonCode() {
            return personCode;
        }

        public void setPersonCode(String personCode) {
            this.personCode = personCode;
        }

        public String getPersonIntroduct() {
            return personIntroduct;
        }

        public void setPersonIntroduct(String personIntroduct) {
            this.personIntroduct = personIntroduct;
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

        public String getProItemFirstNames() {
            return proItemFirstNames;
        }

        public void setProItemFirstNames(String proItemFirstNames) {
            this.proItemFirstNames = proItemFirstNames;
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
