package com.wan7451.binderpool;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;

public class BinderPoolService extends Service {

    public BinderPoolService() {
    }

    private BinderPool.BinderPoolImpl binder = new BinderPool.BinderPoolImpl();

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.wan7451.binder.pool.permission");
        if (check == PackageManager.PERMISSION_GRANTED) {
            return binder;
        } else {
            return null;
        }
    }
}
