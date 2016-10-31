package com.qiantao.caicai.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qiantao.caicai.R;
import com.qiantao.caicai.activity.CookDetailsActivity;
import com.qiantao.caicai.activity.CookListActivity;
import com.qiantao.caicai.adapter.CookListAdapter;
import com.qiantao.caicai.databinding.FragmentDiscoveryBinding;
import com.qiantao.caicai.entity.CookDetail;
import com.qiantao.caicai.entity.CookList;
import com.qiantao.caicai.http.HttpMethod;
import com.qiantao.caicai.http.HttpSubscribe;
import com.qiantao.caicai.util.CommonUtils;
import com.qiantao.caicai.view.ItemDivider;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.qiantao.caicai.activity.CookListActivity.DETAIL;

/**
 * Created by qiantao on 2016/10/12.
 */
public class DiscoveryFragment extends LazyFragment implements CookListAdapter.OnItemClickListener {
    private CookListAdapter mAdapter;
    private List<CookDetail> mList;
    private Intent mIntent;
    private int mItemCount;
    private int mPage = 1;
    private int mTotal;
    private boolean fetchingMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDiscoveryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discovery, null, false);
        mIntent = new Intent(getActivity(), CookDetailsActivity.class);
        mList = new ArrayList<>();
        mAdapter = new CookListAdapter(getActivity(), mList);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        binding.rvList.setLayoutManager(manager);
        binding.rvList.addItemDecoration(new ItemDivider(getActivity(), LinearLayoutManager.VERTICAL));
        binding.rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        binding.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mItemCount = manager.getItemCount();
                int lastVisibleItem = manager.findLastVisibleItemPosition();
                if (!fetchingMore && mItemCount < mTotal && lastVisibleItem + 3 > mItemCount) {
                    Log.i(TAG, "滑动到底部");
                    mPage++;
                    fetchingMore = true;
                    //加载下一页
                    fetchCookList(mPage);
                }
            }
        });
        return binding.getRoot();
    }

    /**
     * 懒加载
     */
    @Override
    protected void lazyLoad() {
        fetchCookList(mPage);
    }

    private void fetchCookList(final int page) {
        HttpSubscribe<CookList> subscribe = new HttpSubscribe<CookList>() {
            @Override
            public void onNext(CookList cookList) {
                fetchingMore = false;
                Log.i(TAG, "网络请求成功");
                mList.addAll(cookList.getTngou());
                Log.i(TAG, mTotal + "");
                mAdapter.notifyItemInserted(mItemCount);
                mTotal = cookList.getTotal();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                fetchingMore = false;
                mPage--;
            }
        };
        HttpMethod.getInstance().getCookList(subscribe, 0, page);
    }

    @Override
    public void onItemClick(View v, ImageView iv, Bitmap bitmap, int position) {
        byte[] bitmapByte = CommonUtils.getInstance().bitmap2byte(bitmap);
        mIntent.putExtra(CookListActivity.BITMAP, bitmapByte);
        mIntent.putExtra(DETAIL, mList.get(position));
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), iv, "shareImg");
        startActivity(mIntent, options.toBundle());
    }
}
