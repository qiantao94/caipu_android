package com.qiantao.caicai.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.qiantao.caicai.R;
import com.qiantao.caicai.activity.CookListActivity;
import com.qiantao.caicai.adapter.CookMenuAdapter;
import com.qiantao.caicai.databinding.FragmentMenuBinding;
import com.qiantao.caicai.entity.CookMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiantao on 2016/10/12.
 */
public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {
    public final static String ITEM_ID = "item_id";
    public static final String TITLE_NAME = "title_name";
    private List<CookMenu> data;
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
        FragmentMenuBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, null, false);
        data = getData();
        CookMenuAdapter adapter = new CookMenuAdapter(data, getActivity());
        binding.gvMenu.setAdapter(adapter);
        binding.gvMenu.setOnItemClickListener(this);
        return binding.getRoot();
    }

    private List<CookMenu> getData() {
        List<CookMenu> data = new ArrayList<>();
        for (int i = 0; i < cookIds.length; i++) {
            CookMenu bean = new CookMenu();
            bean.setId(cookIds[i]);
            bean.setImgId(imgIds[i]);
            bean.setName(imgNames[i]);
            data.add(bean);
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CookListActivity.class);
        intent.putExtra(ITEM_ID, data.get(position).getId());
        intent.putExtra(TITLE_NAME, data.get(position).getName());
        startActivity(intent);
    }

}
