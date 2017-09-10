package com.xgimi.util;

import android.util.Log;

import com.google.gson.Gson;
import com.xgimi.assistant.BuildConfig;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class Alog {

    private static final int LOG_MAXLENGTH = 2000;
    private static boolean mLogOn;
    private static String TAG;

    public static void init(String tag) {
        if (BuildConfig.DEBUG) {
            mLogOn = true;
        } else {
            mLogOn = false;
        }
        TAG = tag;
    }

    public static void d(String text) {
        Log.d(TAG, build(text));
    }

    public static void d(Object object) {
        Log.d(TAG, build(new Gson().toJson(object)));
    }

    public static void e(String text) {
        Log.e(TAG, build(text));
    }

    public static void e(Object object) {
        Log.e(TAG, build(new Gson().toJson(object)));
    }

    public static void longLog(String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            if (strLength > end) {
                d(msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                d(msg.substring(start, strLength));
                break;
            }
        }
    }

    public static void longLog(Object object) {
        String msg = "";
        if (object != null) {
            msg = new Gson().toJson(object);
        }
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            if (strLength > end) {
                d(msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                d(msg.substring(start, strLength));
                break;
            }
        }
    }

    private static String build(String log) {
        if (log == null) {
            log = "null";
        }
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("(")
                .append(caller.getFileName())
                .append(":")
                .append(caller.getLineNumber())
                .append(").")
                .append(caller.getMethodName())
                .append("():")
                .append(log);
        return stringBuilder.toString();
    }
}
