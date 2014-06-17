package com.examples.myapp123.app;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class MyViewGroup extends ViewGroup {

    public static final int NUM_VIEWS = 3;
    private Random mRandom;

    private int mScreenWidth;
    private int mScreenHeight;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mRandom = new Random();

        for (int i = 0; i < NUM_VIEWS; i++) {
            addView(generateView());
        }
    }

    private View generateView() {
        MyCustomView view = new MyCustomView(getContext());
        view.setBackgroundColor(getColor(mRandom.nextInt(4)));
        return view;
    }

    private int getColor(int i) {
        switch (i) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GRAY;
        }
        return Color.GREEN;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mScreenWidth = MeasureSpec.getSize(widthMeasureSpec);
        mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width = mScreenWidth / getChildCount();
        int height = mScreenHeight / 3;

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        }
        setMeasuredDimension(mScreenWidth, mScreenHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(left, 0, left + child.getMeasuredWidth(), child.getMeasuredHeight());
            left += child.getMeasuredWidth();
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("TAG", "InterceptTouchEvent");

        ViewGroup child = (MyCustomView) getChildAt(0);
        if (ev.getX() <= child.getMeasuredWidth() && ev.getY() <= child.getMeasuredHeight()) {
            child.requestDisallowInterceptTouchEvent(true);
            Log.d("TAG", "requestDisallowInterceptTouchEvent");
        }
        return false;
    }
}
