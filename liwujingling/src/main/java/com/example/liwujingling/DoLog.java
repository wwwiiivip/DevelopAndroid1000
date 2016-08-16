package com.example.liwujingling;

/**
 * Created by ZhengQian on 2016/8/15.
 */
public class DoLog {
    public static final String TAG = "android1000";
    public static void logd(String str){
        android.util.Log.d(TAG, ": " + str);
    }
}
