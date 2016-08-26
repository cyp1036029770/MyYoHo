package com.example.chenyunpeng.myyoho.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.SPutils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.delay_image)
    ImageView delayImage;
    @Bind(R.id.delay_image2)
    ImageView delayImage2;
    @Bind(R.id.start)
    Button start;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);
        ButterKnife.bind(this);
        SPutils.save("isFirst","true");
        delayImage.setScaleY(1.5f);
        delayImage.setScaleX(1.5f);
        init();
    }

    private void init() {
        delayImage.post(new Runnable() {
            @Override
            public void run() {
                animator = ValueAnimator.ofFloat(1.5f,1.0f);
                animator.setDuration(2000);
                AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
                alphaAnimation.setDuration(3000);
                alphaAnimation.setFillAfter(true);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                      delayImage2.clearAnimation();
                        delayImage.setVisibility(View.GONE);
                        animator.start();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
               animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                   @Override
                   public void onAnimationUpdate(ValueAnimator animation) {
                       float animatedValue = (float) animation.getAnimatedValue();
                       delayImage.setScaleX(animatedValue);
                       delayImage.setScaleY(animatedValue);
                   }
               });
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               delayImage2.clearAnimation();
                                animator.removeAllListeners();
                                startActivity(new Intent(WelcomeActivity.this,ChooseActivity.class));
                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                            }
                        },300);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });
    }

    @OnClick(R.id.start)
    public void onClick() {
        //启动Activity
    }
}
