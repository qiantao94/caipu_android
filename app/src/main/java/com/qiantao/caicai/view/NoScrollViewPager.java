package com.qiantao.caicai.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by qiantao on 2016/6/27.
 * 不滚动的ViewPager
 */

public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        //拦截方法，返回false  不拦截
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        //处理方法，返回false  不处理      （不拦截不处理，viewpager就不能实现滑动）
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        //设置false表示取消viewpager切换页面时长
        super.setCurrentItem(item, false);
    }
}
