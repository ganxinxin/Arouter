package com.pospackage.modulelibrary.app;

/**
 * Created by ganjing on 2019/4/19 .
 *
 * 一个组件的代理类 ：与Application的生命周期捆绑
 */

public interface ApplicationDelegate {
    void onCreate();
    void  onTerminate();
    void onLowMemory();
    void  onTrimMemory(int level);
}
