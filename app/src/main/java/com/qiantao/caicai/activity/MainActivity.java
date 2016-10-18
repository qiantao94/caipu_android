package com.qiantao.caicai.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiantao.caicai.R;
import com.qiantao.caicai.adapter.ViewPagerAdapter;
import com.qiantao.caicai.databinding.ActivityMainBinding;
import com.qiantao.caicai.fragment.DiscoveryFragment;
import com.qiantao.caicai.fragment.MenuFragment;
import com.qiantao.caicai.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mBinding;
    private static final String TAG = "MainActivity";
    private int mLastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        MenuFragment cookFragment = new MenuFragment();
        fragmentList.add(cookFragment);
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        fragmentList.add(discoveryFragment);
        SearchFragment searchFragment = new SearchFragment();
        fragmentList.add(searchFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mBinding.vpMain.setAdapter(adapter);

        //默认选中第一个fragment
        mBinding.vpMain.setCurrentItem(0);
        mLastId = R.id.iv_menu;
        mBinding.setTitle("分类");
        mBinding.ivMenu.setSelected(true);
        mBinding.setClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                if (v.getId() != mLastId) {
                    setLastTab(mLastId);
                    mBinding.vpMain.setCurrentItem(0);
                    mBinding.ivMenu.setSelected(true);
                    mBinding.setTitle("分类");
                    mLastId = v.getId();
                }
                break;
            case R.id.iv_discovery:
                if (v.getId() != mLastId) {
                    setLastTab(mLastId);
                    mBinding.vpMain.setCurrentItem(1);
                    mBinding.ivDiscovery.setSelected(true);
                    mBinding.setTitle("发现");
                    mLastId = v.getId();
                }
                break;
            case R.id.iv_search:
                if (v.getId() != mLastId) {
                    setLastTab(mLastId);
                    mBinding.vpMain.setCurrentItem(2);
                    mBinding.ivSearch.setSelected(true);
                    mBinding.setTitle("搜索");
                    mLastId = v.getId();
                }
                break;
        }
    }

    //将上次点击的tab取消选中
    private void setLastTab(int lastId) {
        switch (lastId) {
            case R.id.iv_menu:
                mBinding.ivMenu.setSelected(false);
                break;
            case R.id.iv_discovery:
                mBinding.ivDiscovery.setSelected(false);
                break;
            case R.id.iv_search:
                mBinding.ivSearch.setSelected(false);
                break;
        }
    }
}
