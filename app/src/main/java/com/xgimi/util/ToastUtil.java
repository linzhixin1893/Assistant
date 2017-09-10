package com.xgimi.util;

import android.content.Context;
import android.widget.Toast;

import com.xgimi.assistant.BaseApplication;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class ToastUtil {
    private static Toast mToast;
    private static Context mContext;

    public static Toast getToast(String text) {
        if (mContext == null) {
            mContext = BaseApplication.getInstance();
        } else {
            mToast.cancel();
        }
        mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        return mToast;
    }
}
