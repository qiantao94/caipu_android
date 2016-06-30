package com.qt.cookinghelp.view;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.qt.cookinghelp.R;
import com.qt.cookinghelp.bean.CookBean;
import com.qt.cookinghelp.callback.ResponseCallback;
import com.qt.cookinghelp.utils.OKHttpUtil;

/**
 * Created by qiantao on 2016/6/28.
 */
public class CookDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String COOK_ID = "cook_id";
    public static final String COOK_NAME = "cook_name";
    private int id;
    private String name;
    private OKHttpUtil httpUtil;
    private CookBean bean;
    private SimpleDraweeView sd_cook_img;
    private LinearLayout ll_details;
    private TextView tv_keywords, tv_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookdetails);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initView();
        initData();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getIntent().getStringExtra(COOK_NAME));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sd_cook_img = (SimpleDraweeView) findViewById(R.id.sd_cook_img);
        ll_details = (LinearLayout) findViewById(R.id.ll_details);
        tv_keywords = (TextView) findViewById(R.id.tv_keywords);
        tv_details = (TextView) findViewById(R.id.tv_details);
    }

    private void initData() {
        id = getIntent().getIntExtra(COOK_ID, 1);
        httpUtil = OKHttpUtil.getInstance();
        String url = httpUtil.addFirstGP("show", "id", id);
        httpUtil.get(url, new ResponseCallback() {
            @Override
            public void onFailed(Exception e) {
                Toast.makeText(CookDetailsActivity.this, "服务器或网络错误", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSucceed(String json) {
                ll_details.setVisibility(View.VISIBLE);
                Log.i("json", json);
                Gson gson = new Gson();
                bean = gson.fromJson(json, CookBean.class);
                sd_cook_img.setImageURI(Uri.parse("http://tnfs.tngou.net/image"+bean.getImg()));
                tv_keywords.setText(bean.getKeywords());
                tv_details.setText(Html.fromHtml(bean.getMessage()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

}
