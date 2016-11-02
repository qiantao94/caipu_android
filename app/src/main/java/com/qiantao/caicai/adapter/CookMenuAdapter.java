package com.qiantao.caicai.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qiantao.caicai.R;
import com.qiantao.caicai.databinding.ItemMenuGvBinding;
import com.qiantao.caicai.entity.CookMenu;

import java.util.List;

/**
 * Created by qiantao on 2016/6/27.
 * 菜单分类列表的Adapter
 */
public class CookMenuAdapter extends BaseAdapter {
    private List<CookMenu> mListMenus;
    private LayoutInflater mInflater;
    private Context mContext;

    public CookMenuAdapter(List<CookMenu> mListMenus, Context mContext) {
        this.mListMenus = mListMenus;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListMenus.size();
    }

    @Override
    public CookMenu getItem(int position) {
        return mListMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CookMenu data = getItem(position);
        ItemMenuGvBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(mInflater, R.layout.item_menu_gv, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemMenuGvBinding) convertView.getTag();
        }
        binding.tvName.setText(data.getName());
        binding.tvName.setCompoundDrawablesWithIntrinsicBounds(null,
                ContextCompat.getDrawable(mContext, data.getImgId()), null, null);
        return convertView;
    }

}
