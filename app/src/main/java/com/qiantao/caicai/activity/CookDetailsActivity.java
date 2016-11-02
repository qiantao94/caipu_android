package com.qiantao.caicai.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;

import com.qiantao.caicai.R;
import com.qiantao.caicai.databinding.ActivityCookdetailsBinding;
import com.qiantao.caicai.entity.CookDetail;
import com.qiantao.caicai.http.HttpMethod;
import com.qiantao.caicai.http.HttpSubscribe;

/**
 * Created by qiantao on 2016/10/14.
 * 菜谱详情页面
 */

public class CookDetailsActivity extends AppCompatActivity {
    private ActivityCookdetailsBinding mBinding;
    private static final String TAG = "CookDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cookdetails);
        setSupportActionBar(mBinding.tbMain);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        CookDetail detail = getIntent().getParcelableExtra(CookListActivity.DETAIL);
        mBinding.setDetail(detail);
        byte[] bis = getIntent().getByteArrayExtra(CookListActivity.BITMAP);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
        if (bitmap != null) {
            mBinding.ivCookImg.setImageBitmap(bitmap);
            // 取图片主色作背景
            Palette.Builder builder = Palette.from(bitmap);
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch swatch = palette.getMutedSwatch();
                    if (swatch != null) {
                        mBinding.llHead.setBackgroundColor(swatch.getRgb());
                        getWindow().setStatusBarColor(swatch.getRgb());
                    } else {
                        getWindow().setStatusBarColor(ContextCompat.getColor(CookDetailsActivity.this, R.color.colorPrimary));
                    }
                }
            });
        }
        getMessage(detail.getId());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().getId();
            }
        },3000);

    }

    /**
     * 获取菜谱详情
     *
     * @param id 菜谱id
     */
    private void getMessage(int id) {
        HttpSubscribe<CookDetail> subscribe = new HttpSubscribe<CookDetail>() {
            @Override
            public void onNext(CookDetail cookDetail) {
                //设置菜谱详情信息，解析html格式的字符串
                Spanned message;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    message = Html.fromHtml(cookDetail.getMessage(), Html.FROM_HTML_MODE_LEGACY);
                } else {
                    message = Html.fromHtml(cookDetail.getMessage());
                }
                mBinding.tvMessage.setText(message);
            }
        };
        HttpMethod.getInstance().fetchCookDetail(subscribe, id);
    }

    //返回键监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
