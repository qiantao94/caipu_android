package com.qiantao.caicai.http;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by qiantao on 2016/10/13.
 * 进行Http请求的Subscribe
 */

public abstract class HttpSubscribe<T> extends Subscriber<T> {
    private String TAG = "HttpSubscribe";

    /**
     * 请求成功
     */
    @Override
    public void onCompleted() {
        Log.i(TAG, "subscribe 成功");
    }

    /**
     * 请求失败
     *
     * @param e 错误信息
     */
    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "subscribe 失败\n" + e.getMessage());
    }

    /**
     * 请求结果
     *
     * @param t 请求返回的数据
     */
    @Override
    public abstract void onNext(T t);
}
