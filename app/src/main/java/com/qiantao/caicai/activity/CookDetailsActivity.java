package com.qiantao.caicai.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.view.MenuItem;

import com.qiantao.caicai.R;
import com.qiantao.caicai.databinding.ActivityCookdetailsBinding;
import com.qiantao.caicai.entity.CookDetail;
import com.qiantao.caicai.http.HttpMethod;
import com.qiantao.caicai.http.HttpSubscribe;

/**
 * Created by qiantao on 2016/10/14.
 */

public class CookDetailsActivity extends AppCompatActivity {
    private ActivityCookdetailsBinding mBinding;
    private static final String TAG = "CookDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cookdetails);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        CookDetail detail = getIntent().getParcelableExtra(CookListActivity.DETAIL);
        mBinding.setDetail(detail);
        byte[] bis = getIntent().getByteArrayExtra(CookListActivity.BITMAP);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
        mBinding.ivCookImg.setImageBitmap(bitmap);
        // 取图片主色作背景
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getMutedSwatch();
                if (swatch != null) {
                    mBinding.toolbar.setBackgroundColor(swatch.getRgb());
                    mBinding.llHead.setBackgroundColor(swatch.getRgb());
                    getWindow().setStatusBarColor(swatch.getRgb());
                } else {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });
        getMessage(detail.getId());
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
                //设置菜谱详情信息
                mBinding.tvMessage.setText(Html.fromHtml(cookDetail.getMessage()));
            }
        };
        HttpMethod.getInstance().getCookDetail(subscribe, id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
