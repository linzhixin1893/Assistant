package com.xgimi.fragments.video.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xgimi.assistant.BaseFragment;
import com.xgimi.assistant.R;
import com.xgimi.util.GlideImageLoader;
import com.xgimi.common.model.IBaseViewModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhixin on 2017/9/9.
 */
//https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505028122264&di=e1bb8a38e1dc987c3c9876eb44a85c55&imgtype=0&src=http%3A%2F%2Fimg.jsqq.net%2Fuploads%2Fallimg%2F140323%2F1_140323190922_23.jpg
//https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505028122264&di=3ad7dd8981bcda9ef367acad789a22b0&imgtype=0&src=http%3A%2F%2Fdynamic-image.yesky.com%2F600x-%2FuploadImages%2Fupload%2F20141120%2Fpxufo4rtr0ajpg.jpg
//https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505028122264&di=810f841c3f4aa378b3f129e4bb3ad077&imgtype=0&src=http%3A%2F%2Fimg.jsqq.net%2Fuploads%2Fallimg%2F141015%2F1_141015172140_12.jpg
//https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505028122264&di=7728a4720ba5695ee195558128accf3c&imgtype=0&src=http%3A%2F%2Fimg2.ooopic.com%2F12%2F13%2F40%2F56bOOOPIC8c_202.jpg

//https://screenapi.xgimi.com/v3/video_subject_list?params=

public class VideoRecommendFragment extends BaseFragment implements IVideoRecommendView {

    private View mView;
    private Banner mBanner;
    private RecyclerView mRcv;
    private SmartRefreshLayout mRefreshLayout;


    private IBaseViewModel mModel;
    private IVideoRecommendPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_video_recommend, container, false);
            initView();
            initPresenter();
            loadData();
        }

        return mView;
    }

    private void loadData() {
        mPresenter.loadData();
    }


    private void initPresenter() {
        mModel = new VideoRecommendViewModel(mCompositeDisposable);
        mPresenter = new VideoRecommendPresenter(this, mModel);
    }

    private void initView() {
        mBanner = (Banner) mView.findViewById(R.id.view_banner);
        mRcv = (RecyclerView) mView.findViewById(R.id.rcv_video_recommend);
        mRefreshLayout = (SmartRefreshLayout) mView.findViewById(R.id.srf_video_recommend);
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.reloadData();
            }
        });
    }


    @Override
    public void initBanner(List<VideoRecommendBean.DataBean.AdBean> adList) {

        List<String> adImgList = new ArrayList<>();
        List<String> adTitleList = new ArrayList<>();
        for (VideoRecommendBean.DataBean.AdBean ad : adList) {
            adImgList.add(ad.getImage());
            adTitleList.add(ad.getTitle());
        }

        mBanner.setImageLoader(new GlideImageLoader());
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(adImgList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(adTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2400);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                mPresenter.onAdClick(position);
            }
        });

    }

    @Override
    public void initVideoData(VideoRecommendBean data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
//                return super.canScrollVertically();
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcv.setLayoutManager(layoutManager);
        mRcv.setAdapter(new VideoRecommendAdapter(data, this));
    }

    @Override
    public void freshAllView(VideoRecommendBean bean) {

        List<VideoRecommendBean.DataBean.AdBean> adList = bean.getData().getAd();
        List<String> adImgList = new ArrayList<>();
        List<String> adTitleList = new ArrayList<>();
        for (VideoRecommendBean.DataBean.AdBean ad : adList) {
            adImgList.add(ad.getImage());
            adTitleList.add(ad.getTitle());
        }

        mBanner.update(adImgList, adTitleList);
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void onRefreshDataError(String reason) {
        showToast(reason);
    }

    @Override
    public void onStart() {
        if (mBanner != null && mModel.getData() != null) {
            mBanner.startAutoPlay();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        mBanner.stopAutoPlay();
        super.onStop();
    }
}
