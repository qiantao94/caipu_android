package com.qt.cookinghelp.parser;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by qiantao on 2016/6/24.
 */
public class StringParser implements BaseParser<String>{
    @Override
    public String parse(Response response) {
        String result = null;
        try {
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
