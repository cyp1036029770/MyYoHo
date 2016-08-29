package com.example.chenyunpeng.myyoho.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.SPutils;

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
    }


    private void init() {
        animator = ObjectAnimator.ofFloat(splashGroup,"translationY",-height,0);
        animator.setInterpolator(new BounceInterpolator());

        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                String isFirst = SPutils.get("isFirst");
                if(TextUtils.isEmpty(isFirst)||isFirst.equals("false")){
                    startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }else if(isFirst.equals("true")){
                    startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            };

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            animator.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }
}
