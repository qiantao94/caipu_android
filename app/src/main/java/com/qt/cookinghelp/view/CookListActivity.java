package com.qt.cookinghelp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qt.cookinghelp.R;
import com.qt.cookinghelp.adapter.CookListAdapter;
import com.qt.cookinghelp.bean.CookBean;
import com.qt.cookinghelp.bean.CookBeanList;
import com.qt.cookinghelp.callback.ResponseCallback;
import com.qt.cookinghelp.utils.OKHttpUtil;

import java.util.List;

/**
 * Created by qiantao on 2016/6/28.
 */
public class CookListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String ITEM_ID = "item_id";
    public static final String TITLE_NAME = "title_name";
    private TextView tv_title;
    private ListView lv_cook;
    private ImageButton ib_next, ib_last;
    private CookBeanList cookBeanList;
    private List<CookBean> data;
    private OKHttpUtil httpUtil;
    private int id;
    private int page = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooklist);
        initView();
        initData();
    }

    private void initView() {
        lv_cook = (ListView) findViewById(R.id.lv_cook);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ib_next = (ImageButton) findViewById(R.id.ib_next_index);
        ib_last = (ImageButton) findViewById(R.id.ib_last_index);
        lv_cook.setOnItemClickListener(this);
        ib_next.setOnClickListener(this);
        ib_last.setOnClickListener(this);
    }

    private void initData() {
        id = getIntent().getIntExtra(ITEM_ID, 1);
        String titleName = getIntent().getStringExtra(TITLE_NAME);
        tv_title.setText(titleName);
        httpUtil = OKHttpUtil.getInstance();
        getCookMenu(page);
    }

    private void getCookMenu(int page) {
        String url = httpUtil.addFirstGP("list", "id", id) + httpUtil.addGP("page", page);
        httpUtil.get(url, new ResponseCallback() {
            @Override
            public void onFailed(Exception e) {
                ib_last.setEnabled(true);
                ib_next.setEnabled(true);
                Toast.makeText(CookListActivity.this, "服务器或网络错误", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSucceed(String json) {
                ib_last.setEnabled(true);
                ib_next.setEnabled(true);
                Log.i("json", json);
                Gson gson = new Gson();
                if (cookBeanList != null) {
                    cookBeanList = new CookBeanList();
                }
                cookBeanList = gson.fromJson(json, CookBeanList.class);
                data = cookBeanList.getTngou();
                CookListAdapter adapter = new CookListAdapter(data, CookListActivity.this);
                lv_cook.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_last_index:
                ib_last.setEnabled(false);
                page--;
                if (page < 1) {
                    Toast.makeText(CookListActivity.this, "已经是第一页了", Toast.LENGTH_LONG).show();
                    page = 1;
                } else {
                    getCookMenu(page);
                }
                break;
            case R.id.ib_next_index:
                ib_next.setEnabled(false);
                page++;
                if (page > 5) {
                    Toast.makeText(CookListActivity.this, "已经是最后一页了", Toast.LENGTH_LONG).show();
                    page = 5;
                } else {
                    getCookMenu(page);
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CookDetailsActivity.class);
        intent.putExtra(CookDetailsActivity.COOK_ID, data.get(position).getId());
        intent.putExtra(CookDetailsActivity.COOK_NAME,data.get(position).getName());
        startActivity(intent);
    }
}
