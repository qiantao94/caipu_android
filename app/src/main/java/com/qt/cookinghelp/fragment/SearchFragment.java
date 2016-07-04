package com.qt.cookinghelp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qt.cookinghelp.R;
import com.qt.cookinghelp.activity.CookDetailsActivity;
import com.qt.cookinghelp.adapter.CookListAdapter;
import com.qt.cookinghelp.bean.CookBean;
import com.qt.cookinghelp.bean.CookBeanList;
import com.qt.cookinghelp.callback.ResponseCallback;
import com.qt.cookinghelp.utils.OKHttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiantao on 2016/6/27.
 */
public class SearchFragment extends LazyFragment implements AdapterView.OnItemClickListener, TextWatcher {
    private boolean isPrepared;//初始化已完成标志位
    private View view;
    private ListView lv_cook_new;
    private List<CookBean> data;
    private CookBeanList cookBeanList;
    private OKHttpUtil httpUtil;
    private EditText et_search;
    private CookListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        lv_cook_new = (ListView) view.findViewById(R.id.lv_cook_new);
        et_search = (EditText) view.findViewById(R.id.et_search);
        isPrepared = true;
        httpUtil = OKHttpUtil.getInstance();
        lazyLoad();
        lv_cook_new.setOnItemClickListener(this);
        et_search.addTextChangedListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), CookDetailsActivity.class);
        intent.putExtra(CookDetailsActivity.COOK_ID, data.get(position).getId());
        intent.putExtra(CookDetailsActivity.COOK_NAME, data.get(position).getName());
        startActivity(intent);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            getNewCookList();
        }
    }

    private void getNewCookList() {
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
                adapter = new CookListAdapter(data, getActivity());
                lv_cook_new.setAdapter(adapter);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String search = s.toString().trim().replace(" ","");
        if (search == null||"".equals(search)) {
            getNewCookList();
        } else {
            Log.i("edit", search);
            String url = httpUtil.addFirstGP("name", "name", search);
            httpUtil.get(url, new ResponseCallback() {
                @Override
                public void onFailed(Exception e) {
                    Toast.makeText(getActivity(), "服务器或网络错误", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSucceed(String json) {
                    Log.i("json", json);
                    Gson gson = new Gson();
                    cookBeanList = new CookBeanList();
                    cookBeanList = gson.fromJson(json, CookBeanList.class);
                    data = new ArrayList<CookBean>();
                    data = cookBeanList.getTngou();
                    adapter = new CookListAdapter(data, getActivity());
                    lv_cook_new.setAdapter(adapter);
                }
            });
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
