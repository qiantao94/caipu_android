package com.qt.cookinghelp.utils;

import com.qt.cookinghelp.base.Config;
import com.qt.cookinghelp.callback.ResponseCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by qiantao on 2016/6/23.
 */
public class OKHttpUtil {
    private static OkHttpClient client;
    private static OKHttpUtil instance;

    public static OKHttpUtil getInstance() {
        if (instance == null) {
            instance = new OKHttpUtil();
            client = new OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS).build();
        }
        return instance;
    }


    /**
     * GET
     * 开启异步访问网络
     *
     * @param url
     *          访问地址
     * @param callback
     *          回调
     */
    public void get(String url, ResponseCallback callback) {
        Request request = new Request.Builder().url(Config.DEFAULTURL+url).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 为HttpGet 的 url 添加第一个参数
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public <T> String addFirstGP(String url, String name, T value) {
        return url + "?" + name + "=" + value;
    }

    /**
     * 为httpGet添加一个参数
     *
     * @param name
     * @param value
     * @return
     */
    public <T> String addGP(String name, T value) {
        return "&" + name + "=" + value;
    }
}
