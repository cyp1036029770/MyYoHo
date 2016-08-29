package com.example.chenyunpeng.myyoho.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.Dp2Px;
import com.example.chenyunpeng.myyoho.Utils.HttpUtils;
import com.example.chenyunpeng.myyoho.adapter.HomeGridviewAdapter;
import com.example.chenyunpeng.myyoho.bean.GridBean;
import com.example.chenyunpeng.myyoho.bean.HttpModel;
import com.example.chenyunpeng.myyoho.view.MyBanner;
import com.example.chenyunpeng.myyoho.view.MyGridView;
import com.example.chenyunpeng.myyoho.view.PullAndREflashListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class ShouyeFragment extends BaseFragment {
    protected ImageButton toolbarMenu;
    protected ImageButton toolbarSearch;
    protected ImageButton toolbarProductBacklook;
    private MyGridView gv;
    private List<String> strlist;
    private PullAndREflashListView lv;


    @Override
    protected void initData() {
         new HttpUtils().post(HttpModel.RECOMMEND,"parames={\"page\":\"1\"}").DataCallBack(new HttpUtils.DataCallBack() {
             @Override
             public void successful(String data) {
                 Log.e("tag22",""+data);
             }

             @Override
             public void failrue(String e) {
                 Log.e("tag22",""+e);
             }
         });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_shouye, container, false);
        init(inflate);
        return inflate;


    }

    private void init(View rootView) {
        toolbarMenu = (ImageButton) rootView.findViewById(R.id.toolbar_menu);
        toolbarSearch = (ImageButton) rootView.findViewById(R.id.toolbar_search);
        toolbarProductBacklook = (ImageButton) rootView.findViewById(R.id.toolbar_product_backlook);
        lv = (PullAndREflashListView) rootView.findViewById(R.id.lv);
        MyBanner banner=new MyBanner(a);
        banner.LoadData(HttpModel.ALLBANNER,null);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(200));
        banner.setLayoutParams(params);
        View inflate = View.inflate(a, R.layout.fragment_gridview_home, null);
        gv = (MyGridView) inflate.findViewById(R.id.gv);
        AbsListView.LayoutParams gvparams=new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(200));
        banner.setLayoutParams(gvparams);
        initgridviewData();
        lv.addHeaderView(banner);
        lv.addHeaderView(gv);

    }

    private void initgridviewData() {
        List<GridBean> list=new ArrayList<>();
        list.add(new GridBean("新品到站",R.mipmap.btn_xpdz_n));
        list.add(new GridBean("国际优选",R.mipmap.btn_qqyx_n));
        list.add(new GridBean("明星原创",R.mipmap.btn_qxsc_n));
        list.add(new GridBean("全部原创",R.mipmap.btn_qbpl_n));
        list.add(new GridBean("潮流话题",R.mipmap.btn_mxcp_n));
        list.add(new GridBean("新年推荐",R.mipmap.btn_cptj));
        list.add(new GridBean("潮人街拍",R.mipmap.btn_dpzn_n));
        list.add(new GridBean("折扣区",R.mipmap.btn_zkjx_n));
        HomeGridviewAdapter adapter=new HomeGridviewAdapter(list,a);
        gv.setAdapter(adapter);

    }

}
