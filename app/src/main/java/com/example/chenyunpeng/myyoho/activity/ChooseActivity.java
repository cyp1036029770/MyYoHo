package com.example.chenyunpeng.myyoho.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.chenyunpeng.myyoho.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseActivity extends BaseActivity {

    @Bind(R.id.boys)
    ImageButton boys;
    @Bind(R.id.girls)
    ImageButton girls;
    @Bind(R.id.kids)
    ImageButton kids;
    @Bind(R.id.lifestyle)
    ImageButton lifestyle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.boys, R.id.girls, R.id.kids, R.id.lifestyle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.boys:
            case R.id.girls:
            case R.id.kids:
            case R.id.lifestyle:
                startActivity(new Intent(ChooseActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.fade_in_boys,R.anim.fade_out_choose);
                break;
        }
    }


}
