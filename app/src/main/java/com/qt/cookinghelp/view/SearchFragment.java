package com.qt.cookinghelp.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qt.cookinghelp.R;
import com.qt.cookinghelp.adapter.CookListAdapter;
import com.qt.cookinghelp.bean.CookBean;
import com.qt.cookinghelp.bean.CookBeanList;
import com.qt.cookinghelp.callback.ResponseCallback;
import com.qt.cookinghelp.utils.OKHttpUtil;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by qiantao on 2016/6/27.
 */
public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener{
    private View view;
    private ListView lv_cook_new;
    private List<CookBean> data;
    private CookBeanList cookBeanList;
    private OKHttpUtil httpUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,null);
        lv_cook_new = (ListView) view.findViewById(R.id.lv_cook_new);
        httpUtil = OKHttpUtil.getInstance();
        httpUtil.get("list", new ResponseCallback() {
            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getActivity(), "服务器或网络错误", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSucceed(String json) {
                Log.i("json", json);
                Gson gson = new Gson();
                if (cookBeanList != null) {
                    cookBeanList = new CookBeanList();
                }
                cookBeanList = gson.fromJson(json, CookBeanList.class);
                data = cookBeanList.getTngou();
                CookListAdapter adapter = new CookListAdapter(data, getActivity());
                lv_cook_new.setAdapter(adapter);
            }
        });
        lv_cook_new.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CookDetailsActivity.class);
        intent.putExtra(CookDetailsActivity.COOK_ID, data.get(position).getId());
        intent.putExtra(CookDetailsActivity.COOK_NAME,data.get(position).getName());
        startActivity(intent);
    }
}
