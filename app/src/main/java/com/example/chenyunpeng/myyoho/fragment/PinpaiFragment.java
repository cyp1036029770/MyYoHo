package com.example.chenyunpeng.myyoho.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.Dp2Px;
import com.example.chenyunpeng.myyoho.Utils.HttpUtils;
import com.example.chenyunpeng.myyoho.adapter.MyExpandableListViewAdapter;
import com.example.chenyunpeng.myyoho.bean.AllBannerBean;
import com.example.chenyunpeng.myyoho.bean.ChildBean;
import com.example.chenyunpeng.myyoho.bean.DataBean;
import com.example.chenyunpeng.myyoho.bean.HttpModel;
import com.example.chenyunpeng.myyoho.bean.LetterBean;
import com.example.chenyunpeng.myyoho.view.MyBanner;
import com.example.chenyunpeng.myyoho.view.MyScollView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by chenyunpeng on 2016/8/25.
 */
public class PinpaiFragment extends BaseFragment {
    protected RadioButton boyPinlei;
    protected RadioButton girlPinlei;
    protected RadioButton lifestylePinlei;
    protected RadioGroup radioGroup;
    protected ExpandableListView expandListView;
    protected TextView pinpaiTv;
    private MyBanner banner;
    private MyScollView scollView;
    private TextView emptyTv;
    private ProgressBar emptyPb;

    public void initData() {
        new HttpUtils().post(HttpModel.ALLBANNER, "parames={\\\"page\\\":\\\"1\\\"}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                AllBannerBean allBannerBean = new Gson().fromJson(data, AllBannerBean.class);
                List<AllBannerBean.BrandBean> brand = allBannerBean.getBrand();
                List<String> letterlist=getLetter(brand);
                Collections.sort(letterlist);
                List<LetterBean> letterBeanList=new ArrayList();
                for(String letter:letterlist){
                  LetterBean letterBean=  getBranBean(letter,brand);
                    if(letterBean!=null){
                        letterBeanList.add(letterBean);
                    }
                }

                MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(letterBeanList,a);
                expandListView.setAdapter(adapter);
                if(adapter.getGroupCount()!=0){
                    for(int i=0;i<adapter.getGroupCount();i++){
                        expandListView.expandGroup(i);
                    }
                }
                expandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        return true;
                    }
                });
            }

            @Override
            public void failrue(String e) {

            }
        });
    }

    private LetterBean getBranBean(String letter, List<AllBannerBean.BrandBean> brand) {
        List<AllBannerBean.BrandBean> list=new ArrayList<>();
        for(AllBannerBean.BrandBean bean:brand){
            boolean equals = bean.getLetter().equals(letter);
            if(equals){
                list.add(bean);
            }
        }
        if(list.size()>0){
            return  new LetterBean(letter,list);
        }
        return null;
    }

    private List<String> getLetter(List<AllBannerBean.BrandBean> brand) {
       List<String> list=new ArrayList<>();
        for(AllBannerBean.BrandBean bean:brand){
            String letter = bean.getLetter();
            boolean contains = list.contains(letter);
            if(!contains){
                list.add(letter);
            }
        }
        return list;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        //chushihuakongjian
        View inflate = init(inflater, container);
        iniHeadView();
        expandListView.setHeaderDividersEnabled(false);// 设置头部的divider为false
        expandListView.setGroupIndicator(null);
        expandListView.setVerticalScrollBarEnabled(false);
        View emptyView = View.inflate(a, R.layout.empty_view, null);
        expandListView.setEmptyView(emptyView);
        emptyTv= (TextView) emptyView.findViewById(R.id.pinpai_tv);
        emptyPb = (ProgressBar) emptyView.findViewById(R.id.pinpai_pb);
        initData();
        return inflate;
    }

    private void iniHeadView() {
        final View searchView = View.inflate(a, R.layout.searchview_pinpai, null);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(40));
        searchView.setLayoutParams(layoutParams);

        //设置无限轮播的viewapger的参数
        banner = new MyBanner(a);
        AbsListView.LayoutParams pagerparams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(150));
        banner.setLayoutParams(pagerparams);
        banner.LoadData(HttpModel.BANNER, null);
        banner.start();
        //设置水平滚动的recycleview
        scollView = new MyScollView(a);
        AbsListView.LayoutParams scollparams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(150));
        scollView.setLayoutParams(scollparams);
        scollView.loadData(HttpModel.HOTBANNER, null);
        //把View作为头部添加给ex  view
        expandListView.addHeaderView(searchView);

        expandListView.addHeaderView(banner);
        expandListView.addHeaderView(scollView);
        expandListView.addHeaderView(View.inflate(a,R.layout.brand_pinpai,null));
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchView.hasFocus()){
                    //tiaozhuan
                }
            }
        });
    }
    private View init(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_pinpai, container, false);
        boyPinlei = (RadioButton) rootView.findViewById(R.id.boy_pinlei);
        boyPinlei.performClick();
        girlPinlei = (RadioButton) rootView.findViewById(R.id.girl_pinlei);
        lifestylePinlei = (RadioButton) rootView.findViewById(R.id.lifestyle_pinlei);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
        expandListView = (ExpandableListView) rootView.findViewById(R.id.expandListView);
        return rootView;
    }

}
