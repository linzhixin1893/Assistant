package com.xgimi.fragments.video.recommend;

/**
 * Created by linzhixin on 2017/9/10.
 */

public interface IVideoRecommendPresenter {
    void loadData();

    void onAdClick(int position);

    void reloadData();
}
