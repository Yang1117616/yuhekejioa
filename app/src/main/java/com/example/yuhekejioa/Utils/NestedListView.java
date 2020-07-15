package com.example.yuhekejioa.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class NestedListView extends ListView {
    int nowY;
    NestedListView nestedListView;

    public NestedListView(Context context) {
        super(context);
    }

    public NestedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int y = (int) event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                nowY = y;
                intercepted = super.onInterceptTouchEvent(event);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (nestedListView.getFirstVisiblePosition() == 0
                        && y > nowY) {
                    intercepted = true;
                    break;
                } else if (nestedListView.getLastVisiblePosition() == nestedListView.getCount() - 1
                        && y < nowY) {
                    intercepted = true;
                    break;
                }
                intercepted = false;
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }

        return intercepted;
    }
}



