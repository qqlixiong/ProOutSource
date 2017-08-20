package com.lixiong.straight.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.lixiong.straight.IProcessService;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.UIUtils;

public class RemoteService extends Service {
    private MyBinder mMyBinder;
    private MyConn mConn;

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMyBinder = new MyBinder();
        if (mConn == null) {
            mConn = new MyConn();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), mConn, Context.BIND_IMPORTANT);
    }

    class MyBinder extends IProcessService.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return "RemoteService";
        }
    }

    class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.i("连接本地服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //本地服务被干掉
            UIUtils.toast("本地服务被杀死");
            RemoteService.this.startService(new Intent(RemoteService.this, LocalService.class));
            //绑定本地服务
            RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), mConn, Context.BIND_IMPORTANT);
        }
    }
}
