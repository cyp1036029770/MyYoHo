package com.example.chenyunpeng.myyoho.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class BaseActivity extends FragmentActivity {

    public int screenwidth;
    public int screenheight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenwidth = this.getWindowManager().getDefaultDisplay().getWidth();
        screenheight = this.getWindowManager().getDefaultDisplay().getHeight();
    }
}
