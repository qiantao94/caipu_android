package com.qt.cookinghelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qt.cookinghelp.R;
import com.qt.cookinghelp.bean.CookClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiantao on 2016/6/27.
 */
public class CookItemAdapter extends BaseAdapter {
    private Context context;
    private List<CookClassifyBean> bean;
    private LayoutInflater inflater;

    public CookItemAdapter(List<CookClassifyBean> bean, Context context) {
        this.context = context;
        this.bean = getData(bean);
        inflater = LayoutInflater.from(this.context);
    }

    public List<CookClassifyBean> getData(List<CookClassifyBean> bean) {
        if (bean == null) {
            bean = new ArrayList<>();
        }
        return bean;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public CookClassifyBean getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        CookClassifyBean data = getItem(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gv_cook,null);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_icon.setImageResource(data.getImgId());
        viewHolder.tv_name.setText(data.getName());
        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
    }
}
