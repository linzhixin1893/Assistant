package com.xgimi.fragments.video.recommend;

import com.xgimi.assistant.BaseFragment;
import com.xgimi.common.bean.BaseDataBean;
import com.xgimi.common.model.IBaseViewModel;
import com.xgimi.util.Alog;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class VideoRecommendPresenter implements IVideoRecommendPresenter {

    private IVideoRecommendView mView;
    private IBaseViewModel mModel;
    private VideoRecommendBean mBean;

    private IBaseViewModel.LoadDataListener mLoadDataListener = new IBaseViewModel.LoadDataListener() {
        @Override
        public void onLoadDataStart() {
            ((BaseFragment)mView).showProgressDialog();
        }

        @Override
        public void onLoadDataSucceed(BaseDataBean bean) {
            ((BaseFragment)mView).dismissProgressDialog();
            Alog.d("获取首页数据成功");
            Alog.longLog(bean);
            mBean = (VideoRecommendBean) bean;
            mView.initBanner(((VideoRecommendBean)bean).getData().getAd());
            mView.initVideoData((VideoRecommendBean)bean);
        }

        @Override
        public void onLoadDataFailed(String reason) {
            ((BaseFragment)mView).dismissProgressDialog();
            ((BaseFragment)mView).showToast(reason);
        }
    };

    private IBaseViewModel.LoadDataListener mFreshDataListener = new IBaseViewModel.LoadDataListener() {
        @Override
        public void onLoadDataStart() {

        }

        @Override
        public void onLoadDataSucceed(BaseDataBean bean) {
            Alog.d("刷新数据成功, 更新UI");
            mView.freshAllView((VideoRecommendBean)bean);
        }

        @Override
        public void onLoadDataFailed(String reason) {
            Alog.d("刷新数据失败 : " + reason);
            mView.onRefreshDataError(reason);
        }
    };

    public VideoRecommendPresenter(IVideoRecommendView view, IBaseViewModel model) {
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void loadData() {
        mModel.initData(mLoadDataListener);
    }

    @Override
    public void onAdClick(int position) {
        if (mBean != null) {
            Alog.d(mBean.getData().getAd().get(position));
        }
    }

    @Override
    public void reloadData() {
        mModel.freshData(mFreshDataListener);
    }

}
