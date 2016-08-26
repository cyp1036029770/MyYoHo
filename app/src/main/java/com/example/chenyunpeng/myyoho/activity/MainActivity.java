package com.example.chenyunpeng.myyoho.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;

import com.example.chenyunpeng.myyoho.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_choose, R.anim.fade_out_boys);
    }
}
