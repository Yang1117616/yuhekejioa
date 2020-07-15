package com.example.yuhekejioa;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

public class ExampleApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        JPushInterface.setDebugMode(true);     // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        String registrationID = JPushInterface.getRegistrationID(this);
        Log.e("TAG", "保存userNo=:2 "+registrationID);
    }
    /**
     * 获取上下文
     *
     * @return Context
     */
    public static Context getContext() {
        return context;
    }
}
