package com.qt.cookinghelp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by qiantao on 2016/6/24.
 */
public class Params<T> {
    private List<Map<String, T>> mapList;
    private List<String> keyList;
    private List<T> valueList;

    public Params() {
        mapList = new ArrayList<>();
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
    }

    public void add(String key, T value) {
        Map<String, T> map = new HashMap<>();
        map.put(key, value);
        mapList.add(map);
    }

    public void parseMap(){
        for (Map map:mapList) {
            Iterator i = map.entrySet().iterator();
            while (i.hasNext()){
                Map.Entry<String,T> entry = (Map.Entry) i.next();
                keyList.add(entry.getKey());
                valueList.add((T) entry.getValue());
            }
        }
    }

    public String getKey(int index) {
        return keyList.get(index);
    }

    public T getValue(int index){
        return valueList.get(index);
    }

    public int size(){
        return mapList.size();
    }
}
