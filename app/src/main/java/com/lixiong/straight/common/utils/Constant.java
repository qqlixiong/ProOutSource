package com.lixiong.straight.common.utils;

import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.bean.ServiceCategory;
import com.lixiong.straight.my.prop.bean.CardDetail;
import com.lixiong.straight.my.prop.bean.ShoppingProp;
import com.lixiong.straight.project.bean.ProDetail;
import com.lixiong.straight.project.bean.ProMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by john on 2017/6/3.
 */

public class Constant {
    //一级服务类别
    public static String SERVICE_CATEGORY_ONE = "[{\"itemCode\":1,\"itemName\":\"品牌设计\"},\n" +
            "{\"itemCode\":2,\"itemName\":\"广告制作\"},\n" +
            "{\"itemCode\":3,\"itemName\":\"营销推广\"},\n" +
            "{\"itemCode\":4,\"itemName\":\"策划文案\"},\n" +
            "{\"itemCode\":5,\"itemName\":\"娱乐艺术\"},\n" +
            "{\"itemCode\":6,\"itemName\":\"电商服务\"},\n" +
            "{\"itemCode\":7,\"itemName\":\"销售行业\"},\n" +
            "{\"itemCode\":8,\"itemName\":\"软件开发\"},\n" +
            "{\"itemCode\":9,\"itemName\":\"工商注册\"},\n" +
            "{\"itemCode\":10,\"itemName\":\"法律服务\"},\n" +
            "{\"itemCode\":11,\"itemName\":\"人力资源\"},\n" +
            "{\"itemCode\":12,\"itemName\":\"硬件开发\"},\n" +
            "{\"itemCode\":13,\"itemName\":\"影视摄影\"},\n" +
            "{\"itemCode\":14,\"itemName\":\"动画动漫\"},\n" +
            "{\"itemCode\":15,\"itemName\":\"装修服务\"},\n" +
            "{\"itemCode\":16,\"itemName\":\"工程设计\"},\n" +
            "{\"itemCode\":17,\"itemName\":\"工业制造\"},\n" +
            "{\"itemCode\":18,\"itemName\":\"生活服务\"},\n" +
            "{\"itemCode\":19,\"itemName\":\"游戏开发\"}]";

    //二级服务类别
    public static final String SERVICE_CATEGORY_TOW = "[{\"itemCode\":1.0,\"itemName\":\"logo设计\"},\n" +
            "{\"itemCode\":1.01,\"itemName\":\"vi系统设计\"},\n" +
            "{\"itemCode\":1.02,\"itemName\":\"包装设计\"},\n" +
            "{\"itemCode\":1.03,\"itemName\":\"品牌起名\"},\n" +
            "{\"itemCode\":1.04,\"itemName\":\"PPT制作\"},\n" +
            "{\"itemCode\":1.05,\"itemName\":\"品牌策划\"},\n" +
            "{\"itemCode\":1.06,\"itemName\":\"名片设计\"},\n" +
            "{\"itemCode\":1.07,\"itemName\":\"品牌认证\"},\n" +
            "{\"itemCode\":1.08,\"itemName\":\"取名起名\"},\n" +
            "{\"itemCode\":1.09,\"itemName\":\"易拉宝设计\"},\n" +
            "{\"itemCode\":1.1,\"itemName\":\"卡片设计\"},\n" +
            "{\"itemCode\":1.11,\"itemName\":\"书籍设计\"},\n" +
            "{\"itemCode\":1.12,\"itemName\":\"包装盒设计\"},\n" +
            "{\"itemCode\":1.13,\"itemName\":\"礼品定制\"},\n" +
            "{\"itemCode\":1.14,\"itemName\":\"标签设计\"},\n" +
            "{\"itemCode\":1.15,\"itemName\":\"手提袋设计\"},\n" +
            "{\"itemCode\":2.0,\"itemName\":\"广告设计\"},\n" +
            "{\"itemCode\":2.01,\"itemName\":\"宣传册设计\"},\n" +
            "{\"itemCode\":2.02,\"itemName\":\"海报设计\"},\n" +
            "{\"itemCode\":2.03,\"itemName\":\"喷绘写真\"},\n" +
            "{\"itemCode\":2.04,\"itemName\":\"广告牌\"},\n" +
            "{\"itemCode\":2.05,\"itemName\":\"户外广告\"},\n" +
            "{\"itemCode\":2.06,\"itemName\":\"横幅设计\"},\n" +
            "{\"itemCode\":2.07,\"itemName\":\"展台设计\"},\n" +
            "{\"itemCode\":2.08,\"itemName\":\"灯箱广告\"},\n" +
            "{\"itemCode\":2.09,\"itemName\":\"展架设计\"},\n" +
            "{\"itemCode\":2.1,\"itemName\":\"菜谱设计\"},\n" +
            "{\"itemCode\":2.11,\"itemName\":\"台历设计\"},\n" +
            "{\"itemCode\":2.12,\"itemName\":\"标识标牌\"},\n" +
            "{\"itemCode\":2.13,\"itemName\":\"灯箱\"},\n" +
            "{\"itemCode\":2.14,\"itemName\":\"形象\\/背景墙\"},\n" +
            "{\"itemCode\":2.15,\"itemName\":\"提示牌\"},\n" +
            "{\"itemCode\":2.16,\"itemName\":\"资料架\"},\n" +
            "{\"itemCode\":2.17,\"itemName\":\"促销台\"},\n" +
            "{\"itemCode\":2.18,\"itemName\":\"展示柜\"},\n" +
            "{\"itemCode\":2.19,\"itemName\":\"立体字\"},\n" +
            "{\"itemCode\":3.0,\"itemName\":\"整合营销\"},\n" +
            "{\"itemCode\":3.01,\"itemName\":\"口碑营销\"},\n" +
            "{\"itemCode\":3.02,\"itemName\":\"网络广告\"},\n" +
            "{\"itemCode\":3.03,\"itemName\":\"媒体营销\"},\n" +
            "{\"itemCode\":3.04,\"itemName\":\"事件营销\"},\n" +
            "{\"itemCode\":3.05,\"itemName\":\"网红直播\"},\n" +
            "{\"itemCode\":3.06,\"itemName\":\"品牌公关\"},\n" +
            "{\"itemCode\":3.07,\"itemName\":\"企业通讯\"},\n" +
            "{\"itemCode\":4.0,\"itemName\":\"商业策划\"},\n" +
            "{\"itemCode\":4.01,\"itemName\":\"活动策划\"},\n" +
            "{\"itemCode\":4.02,\"itemName\":\"营销策划\"},\n" +
            "{\"itemCode\":4.03,\"itemName\":\"文案撰写\"},\n" +
            "{\"itemCode\":4.04,\"itemName\":\"PPT策划\"},\n" +
            "{\"itemCode\":4.05,\"itemName\":\"H5策划\"},\n" +
            "{\"itemCode\":4.06,\"itemName\":\"一站式服务\"},\n" +
            "{\"itemCode\":4.07,\"itemName\":\"互联网+\"},\n" +
            "{\"itemCode\":4.08,\"itemName\":\"新媒体策划\"},\n" +
            "{\"itemCode\":4.09,\"itemName\":\"招商策划\"},\n" +
            "{\"itemCode\":4.1,\"itemName\":\"重筹策划\"},\n" +
            "{\"itemCode\":4.11,\"itemName\":\"品牌文案\"},\n" +
            "{\"itemCode\":4.12,\"itemName\":\"文学创作\"},\n" +
            "{\"itemCode\":4.13,\"itemName\":\"营销软文\"},\n" +
            "{\"itemCode\":4.14,\"itemName\":\"线下活动\"},\n" +
            "{\"itemCode\":4.15,\"itemName\":\"线上活动\"},\n" +
            "{\"itemCode\":5.0,\"itemName\":\"音乐\"},\n" +
            "{\"itemCode\":5.01,\"itemName\":\"电影\"},\n" +
            "{\"itemCode\":5.02,\"itemName\":\"电视\"},\n" +
            "{\"itemCode\":5.03,\"itemName\":\"动漫\"},\n" +
            "{\"itemCode\":5.04,\"itemName\":\"星座\"},\n" +
            "{\"itemCode\":5.05,\"itemName\":\"旅游\"},\n" +
            "{\"itemCode\":5.06,\"itemName\":\"摄影\"},\n" +
            "{\"itemCode\":5.07,\"itemName\":\"宠物\"},\n" +
            "{\"itemCode\":5.08,\"itemName\":\"花鸟鱼虫\"},\n" +
            "{\"itemCode\":5.09,\"itemName\":\"绘画\"},\n" +
            "{\"itemCode\":5.1,\"itemName\":\"雕塑\"},\n" +
            "{\"itemCode\":5.11,\"itemName\":\"书法\"},\n" +
            "{\"itemCode\":5.12,\"itemName\":\"舞蹈\"},\n" +
            "{\"itemCode\":5.13,\"itemName\":\"播音与主持\"},\n" +
            "{\"itemCode\":5.14,\"itemName\":\"表演\"},\n" +
            "{\"itemCode\":6.06,\"itemName\":\"详情页设计\"},\n" +
            "{\"itemCode\":6.07,\"itemName\":\"活动页设计\"},\n" +
            "{\"itemCode\":6.08,\"itemName\":\"整店装修\"},\n" +
            "{\"itemCode\":6.09,\"itemName\":\"H5设计\"},\n" +
            "{\"itemCode\":6.1,\"itemName\":\"店铺优化\"},\n" +
            "{\"itemCode\":6.11,\"itemName\":\"店铺模版\"},\n" +
            "{\"itemCode\":6.12,\"itemName\":\"电商解决方案\"},\n" +
            "{\"itemCode\":6.0,\"itemName\":\"店铺设计\"},\n" +
            "{\"itemCode\":6.01,\"itemName\":\"店铺运营\"},\n" +
            "{\"itemCode\":6.02,\"itemName\":\"人力外包\"},\n" +
            "{\"itemCode\":6.03,\"itemName\":\"店铺代运营\"},\n" +
            "{\"itemCode\":6.04,\"itemName\":\"跨境电商\"},\n" +
            "{\"itemCode\":6.05,\"itemName\":\"首页设计\"},{\"itemCode\":7.0,\"itemName\":\"食品销售\"},{\"itemCode\":7.01,\"itemName\":\"日用品销售\"},{\"itemCode\":7.02,\"itemName\":\"汽车销售\"},{\"itemCode\":7.03,\"itemName\":\"家电销售\"},{\"itemCode\":7.04,\"itemName\":\"数码电子产品销售\"},{\"itemCode\":7.05,\"itemName\":\"房地产销售\"},{\"itemCode\":7.06,\"itemName\":\"服装销售\"},{\"itemCode\":7.07,\"itemName\":\"银行\\/保险\\/投资\\/证券\\/基金销售\"},{\"itemCode\":7.08,\"itemName\":\"传媒\"},{\"itemCode\":7.09,\"itemName\":\"能源与原材料\"},{\"itemCode\":7.1,\"itemName\":\"培训服务\"},{\"itemCode\":7.11,\"itemName\":\"医疗制药\"},{\"itemCode\":8.0,\"itemName\":\"APP开发\"},{\"itemCode\":8.01,\"itemName\":\"网站开发\"},{\"itemCode\":8.02,\"itemName\":\"微信开发\"},{\"itemCode\":8.03,\"itemName\":\"工具软件\"},{\"itemCode\":8.04,\"itemName\":\"游戏开发\"},{\"itemCode\":8.05,\"itemName\":\"行业解决方案\"},{\"itemCode\":8.06,\"itemName\":\"手机网站\"},{\"itemCode\":8.07,\"itemName\":\"模版建站\"},{\"itemCode\":8.08,\"itemName\":\"H5网站\"},{\"itemCode\":8.09,\"itemName\":\"网站二次开发\"},{\"itemCode\":8.1,\"itemName\":\"前端开发\"},{\"itemCode\":8.11,\"itemName\":\"后端开发\"},{\"itemCode\":8.12,\"itemName\":\"企业管理软件\"},{\"itemCode\":8.13,\"itemName\":\"行业应用软件\"},{\"itemCode\":8.14,\"itemName\":\"嵌入式软件\"},{\"itemCode\":8.15,\"itemName\":\"测试维护\"},{\"itemCode\":8.16,\"itemName\":\"UI设计\"},{\"itemCode\":9.0,\"itemName\":\"公司注册\"},{\"itemCode\":9.01,\"itemName\":\"法人变更\"},{\"itemCode\":9.02,\"itemName\":\"名称变更\"},{\"itemCode\":9.03,\"itemName\":\"地址变更\"},{\"itemCode\":9.04,\"itemName\":\"经营范围变更\"},{\"itemCode\":9.05,\"itemName\":\"注册资本变更\"},{\"itemCode\":9.06,\"itemName\":\"股权转让\"},{\"itemCode\":9.07,\"itemName\":\"公司注销\"},{\"itemCode\":9.08,\"itemName\":\"资质认定\"},{\"itemCode\":9.09,\"itemName\":\"新企业认定\"},{\"itemCode\":9.1,\"itemName\":\"食物流通许可证\"},{\"itemCode\":9.11,\"itemName\":\"进出口经营权\"},{\"itemCode\":9.12,\"itemName\":\"ICP经营许可证\"},{\"itemCode\":9.13,\"itemName\":\"税控申请\"},{\"itemCode\":9.14,\"itemName\":\"企业年报\"},{\"itemCode\":9.15,\"itemName\":\"出纳外包\"},{\"itemCode\":9.16,\"itemName\":\"财务审计\"},{\"itemCode\":9.17,\"itemName\":\"财税调研\"},{\"itemCode\":9.18,\"itemName\":\"财务咨询\"},{\"itemCode\":9.19,\"itemName\":\"工商年报\"},{\"itemCode\":10.0,\"itemName\":\"法律咨询\"},{\"itemCode\":10.01,\"itemName\":\"合同代写\"},{\"itemCode\":10.02,\"itemName\":\"合同代审\"},{\"itemCode\":10.03,\"itemName\":\"律师聘请服务\"},{\"itemCode\":10.04,\"itemName\":\"新三板上市\"},{\"itemCode\":10.05,\"itemName\":\"股权协议\"},{\"itemCode\":10.06,\"itemName\":\"仲裁服务\"},{\"itemCode\":10.07,\"itemName\":\"合同纠纷\"},{\"itemCode\":10.08,\"itemName\":\"工伤事故\"},{\"itemCode\":10.09,\"itemName\":\"诉讼费用计算\"},{\"itemCode\":10.1,\"itemName\":\"在线审阅合同\"},{\"itemCode\":10.11,\"itemName\":\"委托\"},{\"itemCode\":10.12,\"itemName\":\"法律法规\"},{\"itemCode\":10.13,\"itemName\":\"知识产权\"},{\"itemCode\":10.14,\"itemName\":\"商标业务\"},{\"itemCode\":10.15,\"itemName\":\"商标代理\"},{\"itemCode\":10.16,\"itemName\":\"版权登记\"},{\"itemCode\":10.17,\"itemName\":\"著作权变更\"},{\"itemCode\":10.18,\"itemName\":\"法律顾问\"},{\"itemCode\":10.19,\"itemName\":\"代写律师函\"},{\"itemCode\":10.2,\"itemName\":\"律师见证\"},{\"itemCode\":10.21,\"itemName\":\"代写诉讼状\"},{\"itemCode\":11.0,\"itemName\":\"员工福利\"},{\"itemCode\":11.01,\"itemName\":\"员工保险\"},{\"itemCode\":11.02,\"itemName\":\"员工体验\"},{\"itemCode\":11.03,\"itemName\":\"节日福利\"},{\"itemCode\":11.04,\"itemName\":\"团建福利\"},{\"itemCode\":11.05,\"itemName\":\"招聘服务\"},{\"itemCode\":11.06,\"itemName\":\"筛选简历\"},{\"itemCode\":11.07,\"itemName\":\"组建团队\"},{\"itemCode\":11.08,\"itemName\":\"人才派遣\"},{\"itemCode\":11.09,\"itemName\":\"社保代办\"},{\"itemCode\":11.1,\"itemName\":\"社保公积金\"},{\"itemCode\":11.11,\"itemName\":\"商业保险\"},{\"itemCode\":11.12,\"itemName\":\"档案工资\"},{\"itemCode\":11.13,\"itemName\":\"员工培训\"},{\"itemCode\":11.14,\"itemName\":\"企业咨询\"},{\"itemCode\":11.15,\"itemName\":\"在线测评\"},{\"itemCode\":11.16,\"itemName\":\"高端人才\"},{\"itemCode\":12.0,\"itemName\":\"健身机\"},{\"itemCode\":12.01,\"itemName\":\"心率仪\"},{\"itemCode\":12.02,\"itemName\":\"血压计\"},{\"itemCode\":12.03,\"itemName\":\"按摩器\"},{\"itemCode\":12.04,\"itemName\":\"网络分析仪\"},{\"itemCode\":12.05,\"itemName\":\"示波器\"},{\"itemCode\":12.06,\"itemName\":\"开关电源\"},{\"itemCode\":12.07,\"itemName\":\"无线充电\"},{\"itemCode\":12.08,\"itemName\":\"交直流转换器\"},{\"itemCode\":12.09,\"itemName\":\"锂电池\"},{\"itemCode\":12.1,\"itemName\":\"太阳能电池\"},{\"itemCode\":12.11,\"itemName\":\"LED\"},{\"itemCode\":12.12,\"itemName\":\"PDP\"},{\"itemCode\":12.13,\"itemName\":\"LCD\"},{\"itemCode\":12.14,\"itemName\":\"电子纸\"},{\"itemCode\":12.15,\"itemName\":\"光器件\"},{\"itemCode\":12.16,\"itemName\":\"嵌入式电路\"},{\"itemCode\":12.17,\"itemName\":\"单片机\"},{\"itemCode\":12.18,\"itemName\":\"传感器\"},{\"itemCode\":12.19,\"itemName\":\"摄像头\"},{\"itemCode\":12.2,\"itemName\":\"报警器\"},{\"itemCode\":12.21,\"itemName\":\"门禁识别\"},{\"itemCode\":12.22,\"itemName\":\"图像识别\"},{\"itemCode\":12.23,\"itemName\":\"指纹识别\"},{\"itemCode\":12.24,\"itemName\":\"智能手环\\/手表\"},{\"itemCode\":12.25,\"itemName\":\"VR\\/AR\"},{\"itemCode\":12.26,\"itemName\":\"wifi\\/蓝牙\"},{\"itemCode\":12.27,\"itemName\":\"信号接收机\"},{\"itemCode\":12.28,\"itemName\":\"有线\\/无线\"},{\"itemCode\":12.29,\"itemName\":\"RF\\/射频调试\"},{\"itemCode\":12.3,\"itemName\":\"智能家具\\/家电\"},{\"itemCode\":12.31,\"itemName\":\"机器人\\/无人机\"},{\"itemCode\":12.32,\"itemName\":\"汽车零件\"},{\"itemCode\":13.0,\"itemName\":\"宣传片\"},{\"itemCode\":13.01,\"itemName\":\"微电影\"},{\"itemCode\":13.02,\"itemName\":\"营销视频\"},{\"itemCode\":13.03,\"itemName\":\"创意视频\"},{\"itemCode\":13.04,\"itemName\":\"MV拍摄\"},{\"itemCode\":13.05,\"itemName\":\"摄像服务\"},{\"itemCode\":13.06,\"itemName\":\"影视文案\"},{\"itemCode\":13.07,\"itemName\":\"摄影服务\"},{\"itemCode\":13.08,\"itemName\":\"图片后期\"},{\"itemCode\":13.09,\"itemName\":\"广告摄影\"},{\"itemCode\":13.1,\"itemName\":\"婚庆摄影\"},{\"itemCode\":13.11,\"itemName\":\"电商摄影\"},{\"itemCode\":13.12,\"itemName\":\"活动跟拍\"},{\"itemCode\":13.13,\"itemName\":\"影视后期\"},{\"itemCode\":13.14,\"itemName\":\"配音\"},{\"itemCode\":13.15,\"itemName\":\"字幕\"},{\"itemCode\":13.16,\"itemName\":\"剪辑\"},{\"itemCode\":14.0,\"itemName\":\"二维动画\"},{\"itemCode\":14.01,\"itemName\":\"三维动画\"},{\"itemCode\":14.02,\"itemName\":\"原画\"},{\"itemCode\":14.03,\"itemName\":\"画像\"},{\"itemCode\":14.04,\"itemName\":\"卡通形象\"},{\"itemCode\":14.05,\"itemName\":\"动画配音\"},{\"itemCode\":14.06,\"itemName\":\"漫画设计\"},{\"itemCode\":14.07,\"itemName\":\"动画设计\"},{\"itemCode\":14.08,\"itemName\":\"动画外包\"},{\"itemCode\":14.09,\"itemName\":\"四格漫画\"},{\"itemCode\":14.1,\"itemName\":\"插画\"},{\"itemCode\":14.11,\"itemName\":\"动态表情\"},{\"itemCode\":14.12,\"itemName\":\"静态表情\"},{\"itemCode\":14.13,\"itemName\":\"漫画衍生品\"},{\"itemCode\":14.14,\"itemName\":\"动画衍生品\"},{\"itemCode\":14.15,\"itemName\":\"动画项目整包\"},{\"itemCode\":14.16,\"itemName\":\"动画前期外包\"},{\"itemCode\":14.17,\"itemName\":\"动画后期外包\"},{\"itemCode\":14.18,\"itemName\":\"动漫文案策划\"},{\"itemCode\":15.0,\"itemName\":\"公装服务\"},{\"itemCode\":15.01,\"itemName\":\"家装服务\"},{\"itemCode\":15.02,\"itemName\":\"商业装修\"},{\"itemCode\":15.03,\"itemName\":\"展会展厅\"},{\"itemCode\":15.04,\"itemName\":\"装修风水\"},{\"itemCode\":15.05,\"itemName\":\"监理服务\"},{\"itemCode\":15.06,\"itemName\":\"实体店铺设计\"},{\"itemCode\":15.07,\"itemName\":\"餐饮娱乐设计\"},{\"itemCode\":15.08,\"itemName\":\"行政楼设计\"},{\"itemCode\":15.09,\"itemName\":\"服务设施设计\"},{\"itemCode\":15.1,\"itemName\":\"展厅设计\"},{\"itemCode\":15.11,\"itemName\":\"展台搭建\"},{\"itemCode\":15.12,\"itemName\":\"施工造价\"},{\"itemCode\":15.13,\"itemName\":\"家装设计\"},{\"itemCode\":15.14,\"itemName\":\"家装施工\"},{\"itemCode\":16.0,\"itemName\":\"建筑设计\"},{\"itemCode\":16.01,\"itemName\":\"自建房别墅\"},{\"itemCode\":16.02,\"itemName\":\"效果图\"},{\"itemCode\":16.03,\"itemName\":\"BIM专区\"},{\"itemCode\":16.04,\"itemName\":\"3D建模\"},{\"itemCode\":16.05,\"itemName\":\"VR场景制作\"},{\"itemCode\":16.06,\"itemName\":\"自建房\"},{\"itemCode\":16.07,\"itemName\":\"景观设计\"},{\"itemCode\":16.08,\"itemName\":\"园林景观\"},{\"itemCode\":16.09,\"itemName\":\"规划设计\"},{\"itemCode\":16.1,\"itemName\":\"工程造价\"},{\"itemCode\":16.11,\"itemName\":\"工程咨询\"},{\"itemCode\":16.12,\"itemName\":\"建筑施工\"},{\"itemCode\":16.13,\"itemName\":\"VR房产\"},{\"itemCode\":16.14,\"itemName\":\"VR拍摄\"},{\"itemCode\":16.15,\"itemName\":\"VR宣传品\"},{\"itemCode\":16.16,\"itemName\":\"更多工程设计\"},{\"itemCode\":17.0,\"itemName\":\"机械设计\"},{\"itemCode\":17.01,\"itemName\":\"硬件产品设计\"},{\"itemCode\":17.02,\"itemName\":\"模具设计\"},{\"itemCode\":17.03,\"itemName\":\"模具制造\"},{\"itemCode\":17.04,\"itemName\":\"批量生产\"},{\"itemCode\":17.05,\"itemName\":\"元器件采购\"},{\"itemCode\":17.06,\"itemName\":\"3D打印服务\"},{\"itemCode\":17.07,\"itemName\":\"3D打印机\"},{\"itemCode\":17.08,\"itemName\":\"3D打印耗材\"},{\"itemCode\":17.09,\"itemName\":\"3D打印软件\"},{\"itemCode\":18.0,\"itemName\":\"创意绘画\"},{\"itemCode\":18.01,\"itemName\":\"运势测算\"},{\"itemCode\":18.02,\"itemName\":\"签名设计\"},{\"itemCode\":18.03,\"itemName\":\"趣味生活\"},{\"itemCode\":18.04,\"itemName\":\"手机充值\"},{\"itemCode\":18.05,\"itemName\":\"技能培训\"},{\"itemCode\":18.06,\"itemName\":\"情感咨询\"},{\"itemCode\":18.07,\"itemName\":\"文档翻译\"},{\"itemCode\":18.08,\"itemName\":\"摄像\"},{\"itemCode\":18.09,\"itemName\":\"周易占卜\"},{\"itemCode\":18.1,\"itemName\":\"手工制作\"},{\"itemCode\":19.1,\"itemName\":\"棋牌游戏\"},{\"itemCode\":19.02,\"itemName\":\"游戏策划\"},{\"itemCode\":19.03,\"itemName\":\"游戏\"},{\"itemCode\":19.04,\"itemName\":\"试玩\"},{\"itemCode\":19.05,\"itemName\":\"微信H5游戏\"},{\"itemCode\":19.06,\"itemName\":\"3D游戏\"},{\"itemCode\":19.07,\"itemName\":\"手游\"},{\"itemCode\":19.08,\"itemName\":\"网页游戏\"},{\"itemCode\":19.09,\"itemName\":\"AR游戏\"},{\"itemCode\":\"19.1\",\"itemName\":\"单机游戏\"},{\"itemCode\":19.11,\"itemName\":\"游戏UI\"},{\"itemCode\":19.12,\"itemName\":\"游戏音乐\"},{\"itemCode\":19.13,\"itemName\":\"游戏视频\"}]";

    //发单者打招呼语
    public static String[] PERSON_CALL_LANGUAGE = new String[]{"您好，有兴趣聊聊吗？",
            "看了您的服务信息介绍，希望能在沟通一下。","请问，您目前还提供（服务类目）的服务吗？",
            "Hi，我正在寻找（服务类型）的服务商，您考虑吗？","方便相互了解一下吗？很看好您的能力。",
            "对您提供的（服务类目）服务很感兴趣，期待深入交流。","诚邀您了解下我们发布的项目信息，期待您的回复。",
            "请问您目前还考虑接新的项目吗？"};

    //接单者打招呼语
    public static String[] ORDERS_CALL_LANGUAGE = new String[]{"您好，有兴趣聊聊吗？",
            "看了您的项目需求介绍，希望能在沟通一下。","请问，您发布的（需求类目）项目还在寻找服务商吗？",
            "Hi，我正在寻找（需求类目）的项目，能聊聊吗？","方便相互了解一下吗？很看好您的项目。",
            "对您发布的（需求类目）项目很感兴趣，期待深入交流。","诚邀您了解下我们提供的服务信息，期待您的回复。",
            "想与您详细沟通下（需求类型）的需求内容，现在方便吗？"};

    /**
     * 获取热门搜索的类目
     * @return
     */
    public static List<ServiceCategory> searchCategory(){
        List<ServiceCategory> searchCategoryList = new ArrayList<>();
        String[] searchName = new String[]{"logo设计","品牌策划","广告设计","商业策划","营销策划",
                "店铺运营","人力外包","APP开发","网站开发","UI设计","手游","整合营销"};
        String[] searchCode = new String[]{"1.0","1.05","2.0","4.0","4.02",
                "6.01","6.02","8.0","8.01","8.16","19.07","3.0"};
        for(int i=0;i<searchName.length;i++){
            ServiceCategory searchCategory = new ServiceCategory(searchCode[i],searchName[i]);
            searchCategoryList.add(searchCategory);
        }
        return searchCategoryList;
    }

    public static List<String> getMessagePerName(){
        List<String> list = new ArrayList<>();
        list.add("小张");
        list.add("小李");
        list.add("阿凤");
        list.add("小刘");
        list.add("小花");
        list.add("阿娇");
        list.add("赵四");
        list.add("阿婷");
        return list;
    }

    public static String ImageSrc(){
        return "http://pic.ali213.net/showbigpic.html?http://images.ali213.net/picfile/pic/2014/04/08/20140408162137547.jpg";
    }

    public static List<ProMsg.ProjectsEntity> getProData(){
        List<ProMsg.ProjectsEntity> projectsEntityList = new ArrayList<>();
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1002","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1004","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1001","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1003","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1004","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1001","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        projectsEntityList.add(new ProMsg.ProjectsEntity("0b03e755-00b2-4107-b220-75f8f2e1041f","郑州市","1002","19.07,19.06,19.08",
                "7","http://www.wo.ni.com","销售行业","手游,3D游戏,网页游戏","5aceb37b-1faf-4998-abf3-a5cd1df85b29","汪海鹏",
                "100","600","游戏广告","2周","河南省"));
        return projectsEntityList;
    }

    public static List<PersonBean.XmzbAccountEntityCustomBean> getUserMessage(){
        List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList = new ArrayList<>();
        xmzbAccountEntityCustomBeanList.add(new PersonBean.XmzbAccountEntityCustomBean(
                "d12035b6-9591-40f8-a433-a1bc659c636f","0","待开发","0",
                "作为国内领先的网络营销软件提供商，不断研发领先的网络营销软件，打造一流的推广产品；更致力于在互联网领域构建全新的OEM合作模式，建立合作更牢固、分工更科学、竞争更有优势的全新合作模式，从而改变现有网络行业效率低下、问题成堆及对渠道商严重不公的渠道体系，与合作伙伴共建网络行业合作新体系。",
                "运营总监","2933929509@qq.com","1001","ec674bad-ce8f-444e-b6a7-450b2a8f9bbd","img/dsdsadsd.jsg","阿娇",
                "c60e4224-319c-40cb-8770-20a9750c14f8","待开发"
        ));
        return xmzbAccountEntityCustomBeanList;
    }

    public static List<ShoppingProp.XmzbToolsEntityCustomBean> getShoppingProp(){
        List<ShoppingProp.XmzbToolsEntityCustomBean> xmzbToolsEntityCustomBeanList = new ArrayList<>();
        xmzbToolsEntityCustomBeanList.add(new ShoppingProp.XmzbToolsEntityCustomBean(
                "开通关注特权,即可享受最高30个关注联系人上限的特权.多人聊天中帮您快速定位重要联系人.关注联系人会在您的消息列表中置顶展示.可进行统一管理.方便快捷.",
                "19牛币","8384161d-18dc-4a6f-bfa9-328e155fbb95","无图2","关注卡","天"
        ));
        xmzbToolsEntityCustomBeanList.add(new ShoppingProp.XmzbToolsEntityCustomBean(
                "开通关注特权,即可享受最高30个关注联系人上限的特权.多人聊天中帮您快速定位重要联系人.关注联系人会在您的消息列表中置顶展示.可进行统一管理.方便快捷.",
                "24牛币","8384161d-18dc-4a6f-bfa9-328e155fbb95","无图2","关注卡","天"
        ));
        return xmzbToolsEntityCustomBeanList;
    }

    public static CardDetail.XmzbToolsEntityCustomBean getCardDetail(){
        return new CardDetail.XmzbToolsEntityCustomBean(
                "开通关注特权,即可享受最高30个关注联系人上限的特权.多人聊天中帮您快速定位重要联系人.关注联系人会在您的消息列表中置顶展示.可进行统一管理.方便快捷.",
                "15牛币,25牛币,35牛币","5人/30天,15人/60天,30人/90天","8384161d-18dc-4a6f-bfa9-328e155fbb95","无图2","关注卡"
        );
    }

    public static List<ProDetail.ProjectsDetailEntity> getProDetail(){
        List<ProDetail.ProjectsDetailEntity> projectsDetailEntityList = new ArrayList<>();
        Random random = new Random();
        int follow = random.nextInt(2);
        LogUtil.i("是否已关注的值："+follow);
        projectsDetailEntityList.add(new ProDetail.ProjectsDetailEntity("b40c3ced-a3a1-46fd-bcf7-7722f8f9c660",String.valueOf(follow),"九江市",
                "开了个便利店找合适的团队定做一个收银后台发PM应该多了解背景和筛选最优方案这两个过程，比如我们应该了解反馈的用户是集中在手机端还是web端？是不是我们的支付路径太长？是否可优化？是否有其他购买方式？反馈的用户有些什么特征呢？等等，即便最后也是通过一键购买解决，但值得肯定的是我们选择的是当前的最优解。\n" +
                        "         为什么要这么做的原因就是两点：\n" +
                        "         1、用户不一定能准确描述自己的需求，或者是用户需求不等于用户的问题。\n" +
                        "         2、用户的解决方案不一定是最优的。PM就是发现背后的用户需求然后很优雅的解决它。\n" +
                        "         这就是经常听到PM要去了解用户需求和场景重要性的原因，另外需要注意的是向团队描述需求时，一定要先描述用户的需求和场景，然后在描述解决方案，避免跳过这步直接描述功能需求。否则结果大家都懂得。顺丰PM应该多了解背景和筛选最优方案这两个过程，比如我们应该了解反馈的用户是集中在手机端还是web端？是不是我们的支付路径太长？是否可优化？是否有其他购买方式？反馈的用户有些什么特征呢？等等，即便最后也是通过一键购买解决，但值得肯定的是我们选择的是当前的最优解。\n" +
                        "         为什么要这么做的原因就是两点：\n" +
                        "         1、用户不一定能准确描述自己的需求，或者是用户需求不等于用户的问题。\n" +
                        "         2、用户的解决方案不一定是最优的。PM就是发现背后的用户需求然后很优雅的解决它。\n" +
                        "         这就是经常听到PM要去了解用户需求和场景重要性的原因，另外需要注意的是向团队描述需求时，一定要先描述用户的需求和场景，然后在描述解决方案，避免跳过这步直接描述功能需求。否则结果大家都懂得。PM应该多了解背景和筛选最优方案这两个过程，比如我们应该了解反馈的用户是集中在手机端还是web端？是不是我们的支付路径太长？是否可优化？是否有其他购买方式？反馈的用户有些什么特征呢？等等，即便最后也是通过一键购买解决，但值得肯定的是我们选择的是当前的最优解。\n" +
                        "         为什么要这么做的原因就是两点：\n" +
                        "         1、用户不一定能准确描述自己的需求，或者是用户需求不等于用户的问题。\n" +
                        "         2、用户的解决方案不一定是最优的。PM就是发现背后的用户需求然后很优雅的解决它。\n" +
                        "         这就是经常听到PM要去了解用户需求和场景重要性的原因，另外需要注意的是向团队描述需求时，一定要先描述用户的需求和场景，然后在描述解决方案，避免跳过这步直接描述功能需求。否则结果大家都懂得。","002","8.01,8.03,8.05","8",
                "网站开发,工具软件,行业解决方案","8人以上","食品销售","40bedc32-0156-42bb-a638-f0d4badbc963","公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对,公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对,公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对,公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对,公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对,公司长期承接各种项目对公司长期承接各种项目公司长期承接各种项目对公司长期承接各种项目对公司长期承接各种项目对对",
                "http://192.168.1.124:8080/XMZB/upload/logos/1497152010829.png","汪雪梅","100","600","软件开发","收银后台系统","30天","江西省"));
        return projectsDetailEntityList;
    }

    public static String getAtvSerProA(){
        return "       在此特别提醒您（用户）在注册成为用户之前，请认真阅读本《用户协议》（以下简称“协议”）";
    }

    public static String getAtvSerProB(){
        return "         1、用户在使用本服务前需要注册一个“项目外包”账号。\n" +
                "        2、“项目外包”系基于全国服务的APP产品\n" +
                "        3、鉴于“项目外包”账号的绑定注册方式，您同意项目外包。\n" +
                "        4、在用户注册及使用本服务时，项目外包需要搜集能识别用户身份的个人信息以便项目直包可以在必要时联系用户";
    }

    public static String getAtvSerProC(){
        return "         1、通知用户处理结果。\n" +
                "         2、因违反用户协议被封禁的用户，可以自行处理\n" +
                "         3、用户理解并同意，对违法违规的任何用户采取适当的法律行动。\n" +
                "         4、用户理解并同意，因用户违反本协议约定，用户应当自行解决。";
    }

    public static String getAtvSerProD(){
        return "      1、本条所述内容是指用户使用“项目外包”的过程中所制作、上载、复制、发布、传播的任何内容。\n" +
                "       2、用户不得利用“项目外包”账号或本服务制作、上载、复制、发布、传播如下法律、法规和政策禁止的内容：\n" +
                "      (1) 反对宪法所确定的基本原则的；\n" +
                "      (2) 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；\n" +
                "      (3) 损害国家荣誉和利益的；\n" +
                "      (4) 煽动民族仇恨、民族歧视，破坏民族团结的；\n" +
                "      (5) 破坏国家宗教政策，宣扬邪教和封建迷信的；\n" +
                "      (6) 散布谣言，扰乱社会秩序，破坏社会稳定的；\n" +
                "      (7) 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；\n" +
                "      (8) 侮辱或者诽谤他人，侵害他人合法权益的；\n" +
                "      (9) 含有法律、行政法规禁止的其他内容的信息。\n" +
                "     3、用户不得利用“项目外包”账号或本服务制作、上载、复制、发布、传播如下干扰“项目外包”正常运营，以及侵犯其他用户或 第三方合法权益的内容：\n" +
                "     (1) 含有任何性或性暗示的；\n" +
                "     (2) 含有辱骂、恐吓、威胁内容的；\n" +
                "     (3) 含有骚扰、垃圾广告、恶意信息、诱骗信息的；\n" +
                "     (4) 涉及他人隐私、个人信息或资料的；\n" +
                "     (5) 侵害他人名誉权、肖像权、知识产权、商业秘密等合法权利的；\n" +
                "     (6) 含有其他干扰本服务正常运营和侵犯其他用户或第三方合法权益内容的信息。";
    }

    public static String getAtvSerProE(){
        return "      1、用户在本服务中或通过本服务所传送、发布的任何内容并不反映或代表，也不得被视为反映、立场或政策。\n" +
                "      2、用户不得利用“项目外包”账号或本服务进行如下行为：\n" +
                "      (1) 提交、发布虚假信息，或盗用他人头像或资料，冒充、利用他人名义的；\n" +
                "      (2) 强制、诱导其他用户关注、点击链接页面或分享信息的；\n" +
                "      (3) 虚构事实、隐瞒真相以误导、欺骗他人的；\n" +
                "      (4) 利用技术手段批量建立虚假账号的；\n" +
                "      (5) 利用“项目外包”账号或本服务从事任何违法犯罪活动的；\n" +
                "      (6) 制作、发布与以上行为相关的方法、工具，或对此类方法、工具进行运营或传播，无论这些行为是否为商业目的；\n" +
                "      (7) 其他违反法律法规规定、侵犯其他用户合法权益、干扰“项目外包”正常运营或项目直包未明示授权的行为。\n" +
                "      3、用户须对利用“项目外包”账号或本服务传送信息的真实性、合法性、无害性、准确性、有效性等 全权负责。\n" +
                "      4、网上可能包括广告。";
    }

    public static String getAtvSerProF(){
        return "       1、郑重提醒。\n" +
                "       2、本协议的效力、解释及纠纷的解决。\n" +
                "       3、本协议的任何条款无论因何种原因无效或不具可执行性，其余条款仍有效，对双方具有约束力。\n" +
                "       4、本协议最终解释权归个人所有。";
    }
}
