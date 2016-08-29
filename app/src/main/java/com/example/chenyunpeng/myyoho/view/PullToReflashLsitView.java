package com.example.chenyunpeng.myyoho.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.HttpUtils;

/**
 * Created by chenyunpeng on 2016/8/29.
 */
public class PullToReflashLsitView extends LinearLayout {

    private ListView lv;

    public PullToReflashLsitView(Context context) {
        this(context,null);
    }

    public PullToReflashLsitView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullToReflashLsitView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.text_item, null);
        lv = (ListView) inflate.findViewById(R.id.lv);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lv.setLayoutParams(params);
        View headview = View.inflate(getContext(), R.layout.text_item2, null);
        lv.addHeaderView(headview);
        View footview = View.inflate(getContext(), R.layout.text_item2, null);
        lv.addFooterView(footview);
    }
    public  void loadData(String path,String body){
      new HttpUtils().post(path,body).DataCallBack(new HttpUtils.DataCallBack() {
          @Override
          public void successful(String data) {

          }

          @Override
          public void failrue(String e) {

          }
      });
    };

    public  void setAdapter(BaseAdapter adapter){
              lv.setAdapter(adapter);
    }
    public  void addHeadView(View headview){
        lv.addHeaderView(headview);
    }

}
