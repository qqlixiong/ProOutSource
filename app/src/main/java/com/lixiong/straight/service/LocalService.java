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

public class LocalService extends Service {
    private MyBinder mMyBinder;
    private MyConn mConn;

    public LocalService() {
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
        LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), mConn, Context.BIND_IMPORTANT);
    }

    class MyBinder extends IProcessService.Stub {
        @Override
        public String getServiceName() throws RemoteException {
            return "LocalService";
        }
    }

    class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.i("连接远程服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //远程服务被干掉
            UIUtils.toast("远程服务被杀死");
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));
            //绑定远程服务
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), mConn, Context.BIND_IMPORTANT);
        }
    }
}
