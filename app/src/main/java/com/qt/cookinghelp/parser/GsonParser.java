package com.qt.cookinghelp.parser;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by qiantao on 2016/6/24.
 */
public class GsonParser<T> implements BaseParser<T>{
    private Class<T> mClass = null;

    public GsonParser(Class<T> mClass) {
        if (mClass == null) {
            throw new IllegalArgumentException("Class cant't be null");
        }
        this.mClass = mClass;
    }

    @Override
    public T parse(Response response) {
        try {
            Gson gson = new Gson();
            String s = response.body().string();
            T t = gson.fromJson(s,mClass);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
