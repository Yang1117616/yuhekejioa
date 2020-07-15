package com.example.yuhekejioa.Utils;

import android.util.Log;

public class MyLog {
    private static boolean isDebug = true;
    public static void e(String s, String meg){
        if (isDebug){
            Log.e(s,meg);
        }
    }
    public static void e(String meg){
        if (isDebug){
            Log.e(Constant.TAG,meg);
        }
    }
    public static void i(String meg){
        if (isDebug){
            Log.i(Constant.TAG,meg);
        }
    }
}
