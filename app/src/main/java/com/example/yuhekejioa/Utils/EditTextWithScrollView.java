package com.example.yuhekejioa.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class EditTextWithScrollView extends EditText {
    public EditTextWithScrollView(Context context) {
        super(context);
    }

    public EditTextWithScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextWithScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        //让父类不不拦截自己的触摸事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
