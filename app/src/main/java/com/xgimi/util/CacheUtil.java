package com.xgimi.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.xgimi.fragments.video.recommend.VideoRecommendBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class CacheUtil {


    private static final String VIDEO_RECOMMEND_KEY = "video_recommend";


    private static CacheUtil mInstance = new CacheUtil();
    private ACache mCache;
    private Observable mSaveObl;
    private Observable<String> mReadObl;

    public static CacheUtil getInstance() {
        return mInstance;
    }

    private CacheUtil() {
    }

    public void init(Context context) {
        mCache = ACache.get(context);
    }

    public void save(@NonNull final String key, @NonNull final String value, final OnSaveToCacheDoneListener listener) {
        mSaveObl = (Observable) Observable.create(new SaveTask(key, value))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mSaveObl.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                if (listener != null) {
                    listener.onSaveCacheDone();
                }
            }
        });
    }

    public void read(String key, final OnGetFromCacheDoneListener listener) {
        mReadObl = Observable.create(new ReadTask(key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        mReadObl.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                listener.onSucceed(value);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailed(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 保存影视首页数据
     *
     * @param mBean
     * @param listener
     */
    public void saveVideoRecommendBean(@NonNull VideoRecommendBean mBean, OnSaveToCacheDoneListener listener) throws Exception {
        save(VIDEO_RECOMMEND_KEY, new Gson().toJson(mBean), listener);
    }

    public void getVideoRecommendBean(OnGetFromCacheDoneListener listener) {
        read(VIDEO_RECOMMEND_KEY, listener);
    }

    public void removeVideoRecommend() {
        new Thread(){
            @Override
            public void run() {
                boolean res = mCache.remove(VIDEO_RECOMMEND_KEY);
                Alog.d("删除VideoRecommend : " + res);
            }
        }.start();
    }

    public interface OnSaveToCacheDoneListener {
        void onSaveCacheDone();
    }

    public interface OnGetFromCacheDoneListener {
        void onSucceed(String data);

        void onFailed(String reason);
    }

    private class SaveTask implements ObservableOnSubscribe {

        private String key;
        private String value;

        public SaveTask(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public void subscribe(ObservableEmitter e) throws Exception {
            mCache.put(key, value);
            e.onComplete();
        }
    }

    private class ReadTask implements ObservableOnSubscribe<String> {

        private String key;

        public ReadTask(String key) {
            this.key = key;
        }

        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            String res = mCache.getAsString(key);
            if (res == null) {
                e.onError(new Exception("cache'value  does not have key = " + key));
            } else {
                e.onNext(res);
            }
            e.onComplete();
        }
    }

}
