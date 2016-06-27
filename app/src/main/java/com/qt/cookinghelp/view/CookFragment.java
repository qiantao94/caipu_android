package com.qt.cookinghelp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qt.cookinghelp.R;
import com.qt.cookinghelp.adapter.CookItemAdapter;
import com.qt.cookinghelp.bean.CookClassifyBean;
import com.qt.cookinghelp.callback.ResponseCallback;
import com.qt.cookinghelp.utils.OKHttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiantao on 2016/6/27.
 */
public class CookFragment extends Fragment implements AdapterView.OnItemClickListener {
    private OKHttpUtil httpUtil;
    private View view;
    private GridView gv_cook;
    private List<CookClassifyBean> data;
    private CookItemAdapter adapter;
    private int[] cookIds = {1, 10, 15, 52, 62, 68, 75, 82, 98, 112, 147, 161, 218, 166, 182, 188,
            192, 197, 202, 205, 212, 227, 132};
    private String[] imgNames = {"美容", "减肥", "保健养生", "人群", "时节", "餐时", "器官", "调养",
            "肠胃消化", "孕产哺乳", "经期", "女性疾病", "男性", "呼吸道", "血管", "心脏", "肝胆脾胰",
            "神经系统", "口腔", "肌肉骨骼", "皮肤", "癌症", "其他"};
    private int[] imgIds = {R.drawable.meirong, R.drawable.jianfei, R.drawable.jiankangyangsheng,
            R.drawable.renqun, R.drawable.shijie, R.drawable.canshi, R.drawable.qiguan, R.drawable.tiaoyang,
            R.drawable.chagnweixiaohua, R.drawable.yunchanpuru, R.drawable.jingqi, R.drawable.nvxingjibing,
            R.drawable.nanxing, R.drawable.huxidao, R.drawable.xueguan, R.drawable.xinzang, R.drawable.gandanpiy,
            R.drawable.shenjingxitong, R.drawable.kouqiang, R.drawable.jirouguge, R.drawable.pifu,
            R.drawable.aizheng, R.drawable.qita};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        httpUtil = OKHttpUtil.getInstance();
        view = inflater.inflate(R.layout.fragment_cook, null);
        gv_cook = (GridView) view.findViewById(R.id.gv_cook);
        data = getData();
        adapter = new CookItemAdapter(data, getActivity());
        gv_cook.setAdapter(adapter);
        gv_cook.setOnItemClickListener(this);
        return view;
    }

    public List<CookClassifyBean> getData() {
        List<CookClassifyBean> data = new ArrayList<>();
        for (int i = 0; i < cookIds.length; i++) {
            CookClassifyBean bean = new CookClassifyBean();
            bean.setId(cookIds[i]);
            bean.setImgId(imgIds[i]);
            bean.setName(imgNames[i]);
            data.add(bean);
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = httpUtil.addFirstGP("list", "id", data.get(position).getId());
        httpUtil.get(url, new ResponseCallback() {
            @Override
            public void onFailed(Exception e) {
                Log.e("json", e + "");
            }

            @Override
            public void onSucceed(String json) {
                Log.i("json", json);
            }
        });
    }
}
