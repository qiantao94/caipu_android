package com.qiantao.caicai.http;

import com.qiantao.caicai.entity.CookDetail;
import com.qiantao.caicai.entity.CookList;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiantao on 2016/10/13.
 * 各个接口下的Http请求方法
 */

public class HttpMethod {
    /**
     * 请求的基础url
     */
    private static final String BASE_URL = "http://www.tngou.net/api/cook/";

    public static final String IMG_URL_HEAD = "http://tnfs.tngou.net/image";

    /**
     * 请求延时 秒
     */
    private static final int DEFAULT_TIMEOUT = 5;

    private HttpService mService;

    //构造方法私有化
    private HttpMethod() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//设置超时时间
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = retrofit.create(HttpService.class);
    }

    private static class SingletonHolder {
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    /**
     * 静态内部类单例模式获取实例对象
     *
     * @return HttpMethod实例
     */
    public static HttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取菜谱列表
     * @param subscriber    由调用者传来的观察者对象
     * @param id    菜谱分类id
     * @param page  列表页数
     */
    public void fetchCookList(Subscriber<CookList> subscriber, int id, int page) {
        mService.fetchCookList(id,page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取菜谱详情
     * @param subscriber 由调用者传来的观察者对象
     * @param id    菜谱id
     */
    public void fetchCookDetail(Subscriber<CookDetail> subscriber, int id) {
        mService.fetchCookDetail(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 搜索食谱
     * @param subscriber    由调用者传来的观察者对象
     * @param name  搜索名称
     */
    public void searchCookByName(Subscriber<CookList> subscriber, String name) {
        mService.searchCookByName(name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
