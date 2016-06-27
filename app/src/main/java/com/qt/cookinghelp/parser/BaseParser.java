package com.qt.cookinghelp.parser;

import okhttp3.Response;

/**
 * Created by qiantao on 2016/6/24.
 */
public interface BaseParser<T> {
    T parse(Response response);
}
