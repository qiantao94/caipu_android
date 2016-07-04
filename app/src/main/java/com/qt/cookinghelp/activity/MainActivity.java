package com.qt.cookinghelp.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.qt.cookinghelp.R;
import com.qt.cookinghelp.adapter.ViewPagerAdapter;
import com.qt.cookinghelp.fragment.CookFragment;
import com.qt.cookinghelp.fragment.SearchFragment;
import com.qt.cookinghelp.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ib_home, ib_search, ib_user;
    private ViewPager vp_main;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        ib_home = (ImageButton) findViewById(R.id.ib_home);
        ib_search = (ImageButton) findViewById(R.id.ib_search);
        ib_user = (ImageButton) findViewById(R.id.ib_user);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        vp_main.setCurrentItem(0);
        ib_home.setSelected(true);
        ib_home.setOnClickListener(this);
        ib_search.setOnClickListener(this);
        ib_user.setOnClickListener(this);
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        CookFragment cookFragment = new CookFragment();
        fragmentList.add(cookFragment);
        SearchFragment searchFragment = new SearchFragment();
        fragmentList.add(searchFragment);
        UserFragment userFragment = new UserFragment();
        fragmentList.add(userFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        vp_main.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_home:
                vp_main.setCurrentItem(0);
                setNotSelected();
                ib_home.setSelected(true);
                break;
            case R.id.ib_search:
                vp_main.setCurrentItem(1);
                setNotSelected();
                ib_search.setSelected(true);
                break;
            case R.id.ib_user:
                vp_main.setCurrentItem(2);
                setNotSelected();
                ib_user.setSelected(true);
                break;
        }
    }

    public void setNotSelected() {
        ib_home.setSelected(false);
        ib_search.setSelected(false);
        ib_user.setSelected(false);
    }
}
