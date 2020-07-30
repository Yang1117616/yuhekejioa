package com.example.yuhekejioa.Base;

import android.os.Bundle;

import com.example.yuhekejioa.R;
import com.gyf.barlibrary.ImmersionBar;

import androidx.fragment.app.FragmentActivity;


public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(true).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
