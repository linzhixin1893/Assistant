package com.xgimi.fragments.video.recommend;

import java.util.List;

/**
 * Created by linzhixin on 2017/9/10.
 */

public interface IVideoRecommendView {

    void initBanner(List<VideoRecommendBean.DataBean.AdBean> ad);

    void initVideoData(VideoRecommendBean data);

    void freshAllView(VideoRecommendBean bean);

    void onRefreshDataError(String reason);
}
