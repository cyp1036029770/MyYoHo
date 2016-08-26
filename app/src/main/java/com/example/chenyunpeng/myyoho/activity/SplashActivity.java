package com.example.chenyunpeng.myyoho.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.myyoho.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/8/22.
 */
public class SplashActivity extends AppCompatActivity {
    @Bind(R.id.splash_group)
    RelativeLayout splashGroup;
    private int height;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);
        height = getWindowManager().getDefaultDisplay().getHeight();
        splashGroup.setTranslationY(-height);
        init();
       start();
    }

    @SuppressLint("NewApi")
    private void start() {
        if(animator.isPaused()){
            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
            finish();
        }
    }

    private void init() {
        animator = ObjectAnimator.ofFloat(splashGroup,"translationY",-height,0);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3500);
        animator.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
