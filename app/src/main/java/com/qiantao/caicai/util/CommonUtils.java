package com.qiantao.caicai.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.io.ByteArrayOutputStream;

/**
 * Created by qiantao on 2016/10/18.
 * 通用工具类
 */

public class CommonUtils {
    private static class SingletonHolder {
        private static CommonUtils INSTANCE = new CommonUtils();
    }

    public static CommonUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * textview文字颜色设置
     *
     * @param builder textview的文字
     * @param start   文字起始位置 (
     * @param end     文字结束位置 ]
     * @param color   颜色
     */
    public void setTextColor(SpannableStringBuilder builder, int start, int end, String color) {
        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor(color));
        builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * textview文字颜色设置
     *
     * @param string textview的文字
     * @param start  文字起始位置 [
     * @param end    文字结束位置 )
     * @param color  颜色
     */
    public void setTextColor(SpannableString string, int start, int end, int color) {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        string.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * bitmap转字节数组
     *
     * @param bitmap bitmap对象
     * @return 字节数组
     */
    public byte[] bitmap2byte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        }
        return baos.toByteArray();
    }
}
