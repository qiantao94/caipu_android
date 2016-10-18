package com.qiantao.caicai.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.qiantao.caicai.R;
import com.qiantao.caicai.adapter.CookListAdapter;
import com.qiantao.caicai.databinding.ActivityCooklistBinding;
import com.qiantao.caicai.entity.CookDetail;
import com.qiantao.caicai.entity.CookList;
import com.qiantao.caicai.fragment.MenuFragment;
import com.qiantao.caicai.fragment.SearchFragment;
import com.qiantao.caicai.http.HttpMethod;
import com.qiantao.caicai.http.HttpSubscribe;
import com.qiantao.caicai.util.CommonUtils;
import com.qiantao.caicai.view.ItemDivider;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by qiantao on 2016/6/28.
 */
public class CookListActivity extends AppCompatActivity implements CookListAdapter.OnItemClickListener {
    private static final String TAG = "CookListActivity";
    private ActivityCooklistBinding mBinding;
    private int mMenuId;
    private ArrayList<CookDetail> mList;
    private CookListAdapter mAdapter;
    private int mTotal;
    private int mItemCount;
    private Intent mIntent;
    private int mPage = 1;
    private boolean isGetMore;
    public static final String DETAIL = "detail";
    public static final String BITMAP = "bitmap";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cooklist);
        initData();
    }

    private void initData() {
        mIntent = new Intent(this, CookDetailsActivity.class);
        mList = new ArrayList<>();
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rvList.setLayoutManager(manager);
        mBinding.rvList.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvList.addItemDecoration(new ItemDivider(this, LinearLayoutManager.VERTICAL));
        Intent intent = getIntent();
        List<CookDetail> list = intent.getParcelableArrayListExtra(SearchFragment.RESULT);
        if (list != null) {
            mBinding.setTitle("搜索结果");
            mList.addAll(list);
        } else {
            mBinding.setTitle(intent.getStringExtra(MenuFragment.TITLE_NAME));
            mMenuId = intent.getIntExtra(MenuFragment.ITEM_ID, 1);
            mBinding.rvList.addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    mItemCount = manager.getItemCount();
                    int lastVisibleItem = manager.findLastVisibleItemPosition();
                    if (!isGetMore && mItemCount < mTotal && lastVisibleItem + 3 > mItemCount) {
                        Log.i(TAG, "滑动到底部");
                        mPage++;
                        isGetMore = true;
                        //加载下一页
                        getCookList(mPage);
                    }
                }
            });
            getCookList(mPage);
        }
        mAdapter = new CookListAdapter(this, mList);
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        setSupportActionBar(mBinding.toolBar);
    }

    private void getCookList(final int page) {
        Log.i(TAG, "menu-id: " + mMenuId);
        HttpSubscribe<CookList> subscribe = new HttpSubscribe<CookList>() {
            @Override
            public void onNext(CookList cookList) {
                isGetMore = false;
                Log.i(TAG, "网络请求成功");
                mList.addAll(cookList.getTngou());
                Log.i(TAG, mTotal + "");
                mAdapter.notifyItemInserted(mItemCount);
                mTotal = cookList.getTotal();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                isGetMore = false;
                mPage--;
            }
        };
        HttpMethod.getInstance().getCookList(subscribe, mMenuId, page);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View v, ImageView iv, Bitmap bitmap, int position) {
        byte[] bitmapByte = CommonUtils.getInstance().bitmap2byte(bitmap);
        mIntent.putExtra(BITMAP, bitmapByte);
        mIntent.putExtra(DETAIL, mList.get(position));
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, iv, "shareImg");
        startActivity(mIntent, options.toBundle());
    }

}
