package com.pospackage.modulelibrary.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Administrator on 2019/4/19.
 */

public class BaseApplication  extends Application {
    public static final String TAG = "BaseApplication";
    private static Context mContext;
    private static  BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
        initRouter();
    }

    public static Context getContext() {
        return mContext;
    }

    public static BaseApplication getInstance(){
        return instance;
    }

    private void initRouter(){
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
