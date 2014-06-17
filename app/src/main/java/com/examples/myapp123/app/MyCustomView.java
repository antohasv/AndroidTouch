package com.examples.myapp123.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyCustomView extends ViewGroup {

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        eventInfo(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventInfo(event);
        return true;
    }

    private void eventInfo(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG", "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TAG", "ACTION_CANCEL");
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
