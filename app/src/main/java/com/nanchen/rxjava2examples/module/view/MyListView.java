package com.nanchen.rxjava2examples.module.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.OverScroller;

/**
 * Created by lorienzhang on 2017/5/23.
 */

public class MyListView extends ListView {

    private FlingRunnable mFlingRunnable;

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mFlingRunnable = new FlingRunnable();
    }

    public void flingY(int velocityY) {
        // 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fling(velocityY);
        }
        // 4.4
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mFlingRunnable.startFling(velocityY);
        }
        // 4.4 以下
        else {
            // TODO
        }
    }

    private class FlingRunnable implements Runnable {

        private final OverScroller mScroller;

        private int mLastFlingY;

        private FlingRunnable() {
            mScroller = new OverScroller(getContext());
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            if (getChildCount() == 0) {
                endFling();
                return;
            }

            boolean finish = !mScroller.computeScrollOffset()
                    || mScroller.isFinished();
            final int y = mScroller.getCurrY();
            int delta = y - mLastFlingY;
            if (!finish) {
                mLastFlingY = y;
                // ListView滚动一段距离
                MyListView.this.scrollListBy(delta);
                post(this);
            } else {
                endFling();
            }
        }

        void startFling(int initialVelocity) {
            int initialY = 0;
            mLastFlingY = initialY;
            mScroller.fling(0, initialY, 0, initialVelocity,
                    0, Integer.MAX_VALUE,
                    0, Integer.MAX_VALUE);
            post(this);
        }

        void endFling() {
            removeCallbacks(this);

            mScroller.abortAnimation();
        }
    }
}
