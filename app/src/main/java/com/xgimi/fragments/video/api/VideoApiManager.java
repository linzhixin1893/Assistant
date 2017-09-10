package com.xgimi.fragments.video.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xgimi.util.Alog;
import com.xgimi.util.EasyAES;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class VideoApiManager {
    private static VideoApiServer mVideoApi;

    private static OkHttpClient mHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS).build();

    public static VideoApiServer getVideoApi() {
        if (mVideoApi == null) {
            mVideoApi = new Retrofit.Builder()
                    .baseUrl("https://screenapi.xgimi.com/v3/")
//                        .baseUrl("http://screenapi.t.xgimi.com/v3/")
                    .client(mHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(VideoApiServer.class);
        }
        return mVideoApi;
    }

    public static String getEncodeParam(String[] keys, String[] values) throws Exception {
        if (keys.length != values.length) {
            throw new Exception("keySize != valueSize");
        }
        JSONObject j = new JSONObject();
        try {
            for (int i = 0; i < keys.length; i++) {
                if ("page".equals(keys[i])) {
                    j.put("page", Integer.valueOf(values[i]));
                } else if ("category_id".equals(keys[i])) {
                    j.put("category_id", Integer.valueOf(values[i]));
                } else if ("id".equals(keys[i])) {
                    j.put("id", Integer.valueOf(values[i]));
                } else {
                    j.put(keys[i], values[i]);
                }
            }
            j.put("time", String.valueOf(System.currentTimeMillis() / 1000));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String res = URLEncoder.encode(EasyAES.getInstance().encrypt(j.toString()), "UTF-8");
        Alog.d("未加密参数 : " + j.toString()
                + ", 加密参数 : " + EasyAES.getInstance().encrypt(j.toString()).trim()
                + ",urlEncode : " + res
        );
        return EasyAES.getInstance().encrypt(j.toString().trim());
    }

}
