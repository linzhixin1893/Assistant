package com.xgimi.fragments.video.recommend;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.xgimi.common.bean.BaseDataBean;
import com.xgimi.common.bean.EncodedDataBean;
import com.xgimi.common.model.IBaseViewModel;
import com.xgimi.fragments.video.api.VideoApiManager;
import com.xgimi.util.Alog;
import com.xgimi.util.CacheUtil;
import com.xgimi.util.EasyAES;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class VideoRecommendViewModel implements IBaseViewModel {

    private VideoRecommendBean mBean;
    private LoadDataListener mLoadDataListener;
    private CompositeDisposable mCompositeDisposable;


    public VideoRecommendViewModel(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    /**
     * 加载数据
     *
     * @param listener
     */
    @Override
    public void initData(@NonNull final LoadDataListener listener) {
        this.mLoadDataListener = listener;
        listener.onLoadDataStart();
        CacheUtil.getInstance().getVideoRecommendBean(new LoadCacheDoneListener(listener));
    }

    /**
     * 写入缓存
     */
    @Override
    public void saveToCache() {
        try {
            CacheUtil.getInstance().saveVideoRecommendBean(mBean, new CacheUtil.OnSaveToCacheDoneListener() {
                @Override
                public void onSaveCacheDone() {
                    Alog.d("保存缓存成功");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BaseDataBean getData() {
        return mBean;
    }

    /**
     * 刷新数据
     * @param mFreshDataListener
     */
    @Override
    public void freshData(LoadDataListener mFreshDataListener) {
        CacheUtil.getInstance().removeVideoRecommend();
        try {
            VideoApiManager.getVideoApi().getVideoRecommend(
                    VideoApiManager.getEncodeParam(
                            new String[]{"mac"}, new String[]{""}))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new LoadDataFromServerObs(mFreshDataListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class LoadCacheDoneListener implements CacheUtil.OnGetFromCacheDoneListener {

        private LoadDataListener loadDataListener;

        public LoadCacheDoneListener(LoadDataListener loadDataListener) {
            this.loadDataListener = loadDataListener;
        }

        @Override
        public void onSucceed(String data) {//从缓存读出的是String
            Alog.d("读取缓存成功");
            mBean = new Gson().fromJson(data, VideoRecommendBean.class);
            loadDataListener.onLoadDataSucceed(mBean);
        }

        @Override
        public void onFailed(String reason) {
            Alog.d("读取缓存失败 : " + reason);
            try {
                VideoApiManager.getVideoApi().getVideoRecommend(
                        VideoApiManager.getEncodeParam(
                                new String[]{"mac"}, new String[]{""}))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new LoadDataFromServerObs(loadDataListener));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class LoadDataFromServerObs implements Observer<EncodedDataBean> {

        private LoadDataListener loadDataListener;

        public LoadDataFromServerObs(LoadDataListener loadDataListener) {
            this.loadDataListener = loadDataListener;
        }

        @Override
        public void onSubscribe(Disposable d) {
            mCompositeDisposable.add(d);
        }

        @Override
        public void onNext(EncodedDataBean value) {
            Alog.d("获取影视首页后台数据成功");
            if (value.getCode() == 200) {
                mBean = new VideoRecommendBean();
                mBean.setCode(value.getCode());
                mBean.setMsg(value.getMsg());
                mBean.setData(new Gson().fromJson(EasyAES.getInstance().decrypt(value.getData()), VideoRecommendBean.DataBean.class));
                loadDataListener.onLoadDataSucceed(mBean);
                saveToCache();
            } else {
                loadDataListener.onLoadDataFailed(value.getMsg());
            }
        }

        @Override
        public void onError(Throwable e) {
            loadDataListener.onLoadDataFailed(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }

}
