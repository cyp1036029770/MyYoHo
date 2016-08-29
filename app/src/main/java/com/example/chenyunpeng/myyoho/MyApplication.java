package com.example.chenyunpeng.myyoho;

import android.app.Application;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class MyApplication extends Application {
    public  static  MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
