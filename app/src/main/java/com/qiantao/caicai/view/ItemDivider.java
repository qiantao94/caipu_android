package com.qiantao.caicai.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qiantao on 2016/10/14.
 * RecyclerView 的分割线
 */

public class ItemDivider extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;
    private int mOrientation;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public ItemDivider(Context context, int mOrientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.mDrawable = a.getDrawable(0);
        a.recycle();
        setOrientation(mOrientation);
    }

    /**
     * 设置recyclerview的方向
     *
     * @param mOrientation 方向
     */
    private void setOrientation(int mOrientation) {
        if (mOrientation != LinearLayoutManager.HORIZONTAL && mOrientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = mOrientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent, state);
        } else {
            drawVertical(c, parent, state);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDrawable.getIntrinsicWidth();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }


    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int botoom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, botoom);
            mDrawable.draw(c);
        }
    }

    /**
     * 由于分割线的长宽高 使得item有偏移
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            // 画竖线 向下偏移
            outRect.set(0,0,0,mDrawable.getIntrinsicHeight());
        } else {
            //画横线 向右偏移
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),0);
        }
    }


}
