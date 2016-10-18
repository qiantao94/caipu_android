package com.qiantao.caicai.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qiantao.caicai.R;
import com.qiantao.caicai.databinding.ItemRvCooklistBinding;
import com.qiantao.caicai.entity.CookDetail;
import com.qiantao.caicai.http.HttpMethod;

import java.util.List;

/**
 * Created by qiantao on 2016/10/13.
 */

public class CookListAdapter extends RecyclerView.Adapter<CookListAdapter.CookViewHolder> {
    private Context mContext;
    private List<CookDetail> mList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View v, ImageView iv, Bitmap bitmap, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public CookListAdapter(Context mContext, List<CookDetail> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public CookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CookViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_cooklist, parent, false));
    }

    @Override
    public void onBindViewHolder(CookViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CookViewHolder extends RecyclerView.ViewHolder {
        private ItemRvCooklistBinding mBinding;
        private View mItemView;
        private Bitmap mBitmap;

        CookViewHolder(View mItemView) {
            super(mItemView);
            this.mItemView = mItemView;
            mBinding = DataBindingUtil.bind(mItemView);
        }

        private void bind(final int position) {
            CookDetail detail = mList.get(position);
            mBinding.setCookDetail(detail);
            Glide.with(mContext).load(HttpMethod.IMG_URL_HEAD + detail.getImg())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.lodingimgfailed)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            mBinding.ivCook.setImageBitmap(resource);
                            mBitmap = resource;
                        }
                    });
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, mBinding.ivCook, mBitmap, position);
                    }
                }
            });
        }
    }
}
