package com.example.chenyunpeng.myyoho.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.Dp2Px;

/**
 * Created by chenyunpeng on 2016/8/29.
 */
public class PullAndREflashListView extends RelativeLayout {

    private ListView lv;
    private ImageView footView;
    private ImageView headview;
    private LayoutParams headparams;
    private LayoutParams footparams;

    public PullAndREflashListView(Context context) {
        this(context, null);
    }


    public PullAndREflashListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullAndREflashListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        addHeadView();
        addFootView();
        lv = new ListView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(ABOVE, R.id.head);
        params.addRule(BELOW, R.id.head);
        lv.setLayoutParams(params);
        headview.measure(0, 0);
        int measuredHeight = headview.getMeasuredHeight();
        headparams.topMargin = -measuredHeight;
        headview.setLayoutParams(headparams);
        Log.e("tag", "" + measuredHeight);
        footparams.bottomMargin = -measuredHeight;
        footView.setLayoutParams(footparams);
        addView(headview);
        addHeaderView(footView);
        addView(lv);
    }

    private void addFootView() {
        footView = new ImageView(getContext());
        footView.setImageResource(R.mipmap.icon_loaing_frame_1);
        footparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(20));
        footparams.addRule(ALIGN_PARENT_BOTTOM);
        footparams.addRule(CENTER_HORIZONTAL);
        footView.setLayoutParams(footparams);
        footView.setId(R.id.foot);

    }

    private void addHeadView() {
        headview = new ImageView(getContext());
        headview.setImageResource(R.mipmap.icon_loaing_frame_1);
        headparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(20));
        headparams.addRule(CENTER_HORIZONTAL);
        headview.setLayoutParams(headparams);
        headview.setId(R.id.head);

    }

    public void addHeaderView(View headview) {
        lv.addHeaderView(headview);
    }

    public void addFooterView(View footView) {
        lv.addFooterView(footView);
    }

    public void setAdapter(BaseAdapter adapter) {
        lv.setAdapter(adapter);
    }
}
