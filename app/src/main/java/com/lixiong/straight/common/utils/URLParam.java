package com.lixiong.straight.common.utils;

/**
 * Created by john on 2017/5/22.
 */

public class URLParam {
    public static final String BASE_URL = "http://192.168.1.116:8080";

    public static final String BASE_URL2 = "http://192.168.1.101:8080";

    public static final String LOGIN_BASE_URL = "/XMZB/w/account/account";

    public static final String BASE_PROP = "/XMZB/w/tool/toolInfos";

    public static final String BASE_PROJECT = "/XMZB/w/projects/pro";

    public static final String BASE_REVISE = "/XMZB/w/person/person";

    //上传个人信息的URL
    public static final String UPLOAD_PERSONAL_MESSAGE_URL = "http://192.168.1.124:8080/XMZB/FileUploadServlet";

    //账号密码登录地址
    public static final String USERNAME_PWD_LOGIN = BASE_URL+LOGIN_BASE_URL+"/accountlogin";

    //获取验证码地址
    public static final String GET_CODE = BASE_URL+LOGIN_BASE_URL+"/getSmsCode";

    //注册时根据手机号查询用户是否已存在地址
    public static final String QUERY_PHONE_EXIST = BASE_URL+LOGIN_BASE_URL+"/findByCellphone";

    //用户手机号、验证码登录
    public static final String USERNAME_CODE_URL = BASE_URL+LOGIN_BASE_URL+"/accountQuicklogin";

    //手机号、密码、身份状态码
    public static final String REGISTER_URL = BASE_URL+LOGIN_BASE_URL+"/registerInfo";

    //查询所有道具商城信息
    public static final String SHOPPING_PROP_URL = BASE_URL+BASE_PROP+"/findAllToolsInfo";

    //根据道具编码查询某一条道具信息
    public static final String QUERY_PROP = BASE_URL+BASE_PROP+"/findToolByCode";

    //查询所有已发布项目信息(非置顶项目)
    public static final String QUERY_ALL_PROJECT = BASE_URL2+"/XMZB/w/projects/pro/proAllInfoMsg";

    //根据传入的条件查询项目信息:FindByCondition
    public static final String QUERY_BY_CONDITION = BASE_URL2+BASE_PROJECT+"/proFindByCondition";

    //根据传入的条件查询项目信息
    public static final String QUERY_PROJECT_FIND = BASE_URL2+"/XMZB/w/projects/pro/findByProSysCodeInfo";

    //修改手机号
    public static final String REVISE_PHONE = BASE_URL2+BASE_REVISE+"/upPerCell";

    //修改密码
    public static final String REVISE_PWD = BASE_URL2+BASE_REVISE+"/upAccPwd";
}
