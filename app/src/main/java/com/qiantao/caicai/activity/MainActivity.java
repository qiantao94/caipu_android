package com.qiantao.caicai.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.qiantao.caicai.R;
import com.qiantao.caicai.adapter.ViewPagerAdapter;
import com.qiantao.caicai.databinding.ActivityMainBinding;
import com.qiantao.caicai.fragment.DiscoveryFragment;
import com.qiantao.caicai.fragment.MenuFragment;
import com.qiantao.caicai.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private ActivityMainBinding mBinding;
    private static final String TAG = "MainActivity";
    private final String mTitleStrs[] = {"分类", "发现", "搜索"};
    private List<ImageView> mTabs;
    private int mLastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTabs = new ArrayList<>(Arrays.asList(mBinding.ivMenu, mBinding.ivDiscovery, mBinding.ivSearch));
        List<Fragment> fragmentList = new ArrayList<>(Arrays.asList(new MenuFragment(), new DiscoveryFragment(), new SearchFragment()));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mBinding.vpMain.setAdapter(adapter);
        mBinding.vpMain.addOnPageChangeListener(this);
        mBinding.setClickListener(this);//为导航栏底部三个按钮设置监听

        //默认选中第一个fragment
        setCurrentPage(0);
    }

    /**
     * 设置当前页面的位置、标题、底部Tab
     *
     * @param currentIndex 当前的位置
     */
    private void setCurrentPage(int currentIndex) {
        mTabs.get(mLastIndex).setSelected(false);//上个tab选中状态设置为false
        mBinding.vpMain.setCurrentItem(currentIndex);//当前viewpager的位置
        mBinding.setTitle(mTitleStrs[currentIndex]);//当前页面下toolbar的标题
        mTabs.get(currentIndex).setSelected(true);//当前tab选中状态设置为true
        mLastIndex = currentIndex;
    }

    //底部导航栏三个按钮的点击监听
    @Override
    public void onClick(View v) {
        int currentIndex = mTabs.indexOf(v);
        if (mLastIndex != currentIndex) {
            setCurrentPage(currentIndex);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //根据viewpager滑动的位置设置当前页面
        setCurrentPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //按返回键返回桌面
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
