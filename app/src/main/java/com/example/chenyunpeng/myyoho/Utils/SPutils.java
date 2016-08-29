package com.example.chenyunpeng.myyoho.Utils;

import com.example.chenyunpeng.myyoho.MyApplication;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class SPutils {
    public  static   void save(String key,String values){
        MyApplication.app.getSharedPreferences("config",0).edit().putString(key,values).commit();
    }
    public  static String get(String key){
        return  MyApplication.app.getSharedPreferences("config",0).getString(key,"");
    }
}
