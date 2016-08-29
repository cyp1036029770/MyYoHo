package com.example.chenyunpeng.myyoho.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chenyunpeng.myyoho.R;

public class GouWuCheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       overridePendingTransition(0,R.anim.gouwuche_out);
    }
}
