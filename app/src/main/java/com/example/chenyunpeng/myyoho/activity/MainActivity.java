package com.example.chenyunpeng.myyoho.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.fragment.FeileiFragment;
import com.example.chenyunpeng.myyoho.fragment.GouWuCheFragment;
import com.example.chenyunpeng.myyoho.fragment.GuangFragment;
import com.example.chenyunpeng.myyoho.fragment.MineFragment;
import com.example.chenyunpeng.myyoho.fragment.ShouyeFragment;
import com.example.chenyunpeng.myyoho.view.MyRadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.shouye)
    MyRadioButton shouye;
    @Bind(R.id.feilei)
    MyRadioButton feilei;
    @Bind(R.id.guang)
    MyRadioButton guang;
    @Bind(R.id.gouwuche)
    MyRadioButton gouwuche;
    @Bind(R.id.mine)
    MyRadioButton mine;
    @Bind(R.id.buttonGroup)
    RadioGroup buttonGroup;
    HashMap<String, Fragment> fragmentHashMap = new HashMap<>();
    @Bind(R.id.FragmentGroup)
    FrameLayout FragmentGroup;
    private String fragmentTag = "";
    private String ccurrentTag = "";
    private FragmentManager manager;
    private List<MyRadioButton> radioButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initRadioList();
    }

    private void initRadioList() {
        radioButtonList = new ArrayList<>();


    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        fragmentHashMap.put(ShouyeFragment.class.getSimpleName(), new ShouyeFragment());
        fragmentHashMap.put(FeileiFragment.class.getSimpleName(), new FeileiFragment());
        fragmentHashMap.put(GuangFragment.class.getSimpleName(), new GuangFragment());
        fragmentHashMap.put(MineFragment.class.getSimpleName(), new MineFragment());
        fragmentHashMap.put(GouWuCheFragment.class.getSimpleName(),new GouWuCheFragment());
        replaceFragment(ShouyeFragment.class.getSimpleName());
    }

    private void replaceFragment(String simpleName) {
        Fragment fragment = fragmentHashMap.get(simpleName);
        if (!fragmentTag.equals(simpleName)) {
            ccurrentTag = simpleName;
            manager.beginTransaction().replace(R.id.FragmentGroup, fragment, simpleName).commit();
        }
        fragmentTag = simpleName;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_choose, R.anim.fade_out_boys);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.shouye, R.id.feilei, R.id.guang, R.id.gouwuche, R.id.mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shouye:
                replaceFragment(ShouyeFragment.class.getSimpleName());
                break;
            case R.id.feilei:
                replaceFragment(FeileiFragment.class.getSimpleName());
                break;
            case R.id.guang:
                replaceFragment(GuangFragment.class.getSimpleName());
                break;
            case R.id.gouwuche:
              //此处需要判断是否登陆,如果未登录切换的是fragment,登录后切换的是fragment
                replaceFragment(GouWuCheFragment.class.getSimpleName());
                break;
            case R.id.mine:
                replaceFragment(MineFragment.class.getSimpleName());
                break;
        }
    }

    private void statGouwuChe() {
        startActivity(new Intent(MainActivity.this, GouWuCheActivity.class));
        overridePendingTransition(R.anim.gouwuche_in, 0);
    }
}
