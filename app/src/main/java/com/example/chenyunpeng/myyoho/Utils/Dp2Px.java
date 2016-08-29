package com.example.chenyunpeng.myyoho.Utils;

import com.example.chenyunpeng.myyoho.MyApplication;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class Dp2Px {
    public   static  int dp2px(int values){
        return (int) (MyApplication.app.getResources().getDisplayMetrics().density*values+0.5f);
    }

}
