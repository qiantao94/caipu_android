package com.qiantao.caicai.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qiantao.caicai.R;
import com.qiantao.caicai.databinding.ItemGvMenuBinding;
import com.qiantao.caicai.entity.CookMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiantao on 2016/6/27.
 */
public class CookMenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<CookMenu> mBean;
    private LayoutInflater mInflater;
    private ItemGvMenuBinding mBinding;

    public CookMenuAdapter(List<CookMenu> mBean, Context mContext) {
        this.mContext = mContext;
        this.mBean = getData(mBean);
        mInflater = LayoutInflater.from(this.mContext);
    }

    public List<CookMenu> getData(List<CookMenu> bean) {
        if (bean == null) {
            bean = new ArrayList<>();
        }
        return bean;
    }

    @Override
    public int getCount() {
        return mBean.size();
    }

    @Override
    public CookMenu getItem(int position) {
        return mBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CookMenu data = getItem(position);
        if (convertView == null) {
            mBinding = DataBindingUtil.inflate(mInflater, R.layout.item_gv_menu, parent, false);
            convertView = mBinding.getRoot();
            convertView.setTag(mBinding);
        } else {
            mBinding = (ItemGvMenuBinding) convertView.getTag();
        }
        mBinding.tvName.setText(data.getName());
        mBinding.ivIcon.setImageResource(data.getImgId());
        return convertView;
    }

}
