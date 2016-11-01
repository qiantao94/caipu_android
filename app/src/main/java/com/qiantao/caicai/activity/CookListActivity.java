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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by qiantao on 2016/6/28.
 * 菜单列表Activity
 */

public class CookListActivity extends AppCompatActivity implements CookListAdapter.OnItemClickListener {
    private static final String TAG = "CookListActivity";
    public static final String DETAIL = "detail";
    public static final String BITMAP = "bitmap";

    private Intent mIntent;

    private ActivityCooklistBinding mBinding;

    private ArrayList<CookDetail> mListCooks;
    private CookListAdapter mAdapter;
    private int mCookSize;
    private int mFecthedCount;
    private boolean isFetchingMore;
    private int mFecthPage = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cooklist);
        initData();
    }

    private void initData() {
        mIntent = new Intent(this, CookDetailsActivity.class);
        mListCooks = new ArrayList<>();
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rvList.setLayoutManager(manager);
        mBinding.rvList.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvList.addItemDecoration(new ItemDivider(this, LinearLayoutManager.VERTICAL));
        Intent intent = getIntent();
        List<CookDetail> listResults = intent.getParcelableArrayListExtra(SearchFragment.RESULT);
        if (listResults != null) {
            mBinding.setTitle("搜索结果");
            mListCooks.addAll(listResults);
        } else {
            mBinding.setTitle(intent.getStringExtra(MenuFragment.TITLE_NAME));
            final int menuId = intent.getIntExtra(MenuFragment.ITEM_ID, 1);
            mBinding.rvList.addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    mFecthedCount = manager.getItemCount();
                    int lastVisibleItem = manager.findLastVisibleItemPosition();
                    if (!isFetchingMore && mFecthedCount < mCookSize && lastVisibleItem + 3 > mFecthedCount) {
                        Log.i(TAG, "滑动到底部");
                        isFetchingMore = true;
                        //加载下一页
                        fetchCookList(++mFecthPage, menuId);
                    }
                }
            });
            fetchCookList(mFecthPage, menuId);
        }
        mAdapter = new CookListAdapter(this, mListCooks);
        mBinding.rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        setSupportActionBar(mBinding.tbMain);
    }

    /**
     * 进行网络请求获取菜谱数据列表
     *
     * @param page   页码
     * @param menuId 菜单分类的id
     */
    private void fetchCookList(final int page, int menuId) {
        Log.i(TAG, "menu-id: " + menuId);
        HttpSubscribe<CookList> subscribe = new HttpSubscribe<CookList>() {
            @Override
            public void onNext(CookList cookList) {
                isFetchingMore = false;
                Log.i(TAG, "网络请求成功");
                mListCooks.addAll(cookList.getTngou());
                Log.i(TAG, mCookSize + "");
                mAdapter.notifyItemInserted(mFecthedCount);
                mCookSize = cookList.getTotal();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                isFetchingMore = false;
                mFecthPage--;//请求失败，页码恢复
            }
        };
        HttpMethod.getInstance().fetchCookList(subscribe, menuId, page);
    }

    //返回按钮的监听
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
        mIntent.putExtra(DETAIL, mListCooks.get(position));
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, iv,
                getResources().getString(R.string.share_name));
        startActivity(mIntent, options.toBundle());
    }

}
