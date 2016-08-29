package com.example.chenyunpeng.myyoho.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.myyoho.R;
import com.example.chenyunpeng.myyoho.Utils.HttpUtils;
import com.example.chenyunpeng.myyoho.adapter.GuanzhuAdapter;
import com.example.chenyunpeng.myyoho.bean.GuanzhuBean;
import com.example.chenyunpeng.myyoho.bean.HttpModel;
import com.example.chenyunpeng.myyoho.diaglog.LoadDataDiaglog;
import com.google.gson.Gson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenyunpeng on 2016/8/28.
 */
public class GuanzhuFragment extends BaseFragment {
    protected CircleImageView headImageGuanzhu;
    protected TextView tv1;
    protected RelativeLayout head;
    protected ListView lvGuanzhu;
    private TextView tv2;

    @Override
    protected void initData() {
        showLoadDialog();
           new HttpUtils().post(HttpModel.FLLOW,null).DataCallBack(new HttpUtils.DataCallBack() {
               @Override
               public void successful(String data) {
                   GuanzhuBean guanzhuBean = new Gson().fromJson(data, GuanzhuBean.class);
                   List<GuanzhuBean.FollowBean> follow = guanzhuBean.getFollow();
                   GuanzhuAdapter adapter=new GuanzhuAdapter(follow,a);
                   lvGuanzhu.setAdapter(adapter);
                   dismissionLoadDialog();
               }

               @Override
               public void failrue(String e) {
                   dismissionLoadDialog();
               }
           });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_guanzhu, container, false);
        findView(inflate);
        return inflate;

    }

    private void findView(View rootView) {
        headImageGuanzhu = (CircleImageView) rootView.findViewById(R.id.head_image_guanzhu);
        tv1 = (TextView) rootView.findViewById(R.id.tv1);
        tv2 = (TextView) rootView.findViewById(R.id.tv2);
        head = (RelativeLayout) rootView.findViewById(R.id.head);
        lvGuanzhu = (ListView) rootView.findViewById(R.id.lv_guanzhu);
    }
}
