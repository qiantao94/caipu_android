package com.qt.cookinghelp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qt.cookinghelp.R;
import com.qt.cookinghelp.bean.CookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiantao on 2016/6/27.
 */
public class CookListAdapter extends BaseAdapter {
    private Context context;
    private List<CookBean> bean;
    private LayoutInflater inflater;

    public CookListAdapter(List<CookBean> bean, Context context) {
        this.context = context;
        this.bean = getData(bean);
        inflater = LayoutInflater.from(this.context);
    }

    public List<CookBean> getData(List<CookBean> bean) {
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
    public CookBean getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        CookBean data = getItem(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_lv_cook,null);
            viewHolder.iv_cook = (SimpleDraweeView) convertView.findViewById(R.id.iv_cook);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_cook.setImageURI(Uri.parse("http://tnfs.tngou.net/image"+data.getImg()));
        viewHolder.tv_name.setText(data.getName());
        viewHolder.tv_description.setText(data.getDescription());
        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView iv_cook;
        TextView tv_name;
        TextView tv_description;
    }
}
