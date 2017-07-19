# ProOutSource
一个高仿BOSS直聘,项目发单和接单的APP。
使用了okhttoUtils框架，基于位置的定位服务，集成Mob短信验证码服务，大量自定义组件,viewHolder编程，代码混淆处理等等。
##   viewHolder编程的运用
###  1.使代码更清晰明了。</br>
###  2.界面和逻辑代码的统一</br>
------------------------------------------------------------------------------
##   Activity生命周期的回调
###  1.解决了多个Base继承的问题</br>
###  2.有利于Activity,Fragment生命周期的统一管理</br>
---------------------------------------------------------------------------
##   集成Mob短信验证码
登录时的验证码校验</br>
如果想容易进入个人信息页面，请在登录界面点击游客登录</br>
--------------------------------------------------------------------------
##   自定义View
1.对于顶部布局，长相一致的界面均采用自定义组件开发</br>
2.圆形圆角空间</br>
3.开关按钮</br>
4.推荐角标</br>
-----------------------------------------------------------------------------
##    sugarorm的使用
####  用于数据存储</br>
------------------------------------------------------------------------------
##    沉浸式状态栏
##    Android6.0动态权限
#####  采用第三方支持库</br>
------------------------------------------------------------------------------------
##    图片处理
采用glide</br>
------------------------------------------------------------------------------------
##    数据解析
采用fastjson</br>
--------------------------------------------------------------------------------------
## 指引页
####  可以点击立即体验进入主页面，也可以滑动进入主页面
--------------------------------------------------------------------------------------------------
##  BUG处理
在5.0的手机上覆写onDestory()方法时调用unbindService(conn)并没有出现这个问题，</br>
 但是在4.4的机子上跑的时候，退出发现就报上述错误了。可能有人会想，明明有使用bindService绑定服务呀，</br>
而且查看进程服务也在运行。没错，我一开始就是这么认为的，然后我点进bindService这个方法去看源码，</br>
 发现它返回类型不是void,而是boolean</br>
 解决方案：</br>
   ### 1.定义一个全局变量用来标记 </br>
         private boolean isConnected = false;  
###    2.在bindService时 </br>
        isConnected = bindService（intent）;</br>
  ###  3.onDestroy()</br>
        //预先判断ServiceConnection 是否为空，不空再解绑服务。
        if(isConnected){
            unbindService(conn);//conn表示ServiceConnection 对象
            isConnected = false;
        }
        super.onDestroy();

