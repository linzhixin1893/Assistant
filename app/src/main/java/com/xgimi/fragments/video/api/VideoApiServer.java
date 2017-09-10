package com.xgimi.fragments.video.api;

import com.xgimi.common.bean.EncodedDataBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by linzhixin on 2017/9/10.
 */

public interface VideoApiServer {

    //检查是否收藏影视 NEW
    @GET("video_collection_check")
    Observable<EncodedDataBean> getcheckCollect(@Query("param") String param);


    //添加影视收藏 NEW
    @FormUrlEncoded
    @POST("video_collection_add")
    Observable<EncodedDataBean> getMovieCollect(@Field("param") String param);

    //取消收藏 NEW
    @FormUrlEncoded
    @POST("video_collection_remove")
    Observable<EncodedDataBean> CancleMovieCollect(@Field("param") String param);


    //获取收藏影视列表 NEW
    @GET("video_collection_list")
    Observable<EncodedDataBean> getMovieCollectList(@Query("param") String param);


    // 影视筛选条件 NEW
    @GET("video_screen_condition")
    Observable<EncodedDataBean> getMovieByCategory(@Query("param") String param);


    //推荐列表以及筛选列表 NEW
//    @FormUrlEncoded
    @GET("video_screen_list")
    Observable<EncodedDataBean> getAllMovie(@Query("param") String param);



    //搜索影视 NEW
    @GET("video_search_list")
    Observable<EncodedDataBean> getSearchMovie(@Query("param") String param);




    //影视推荐(精选) new
    @GET("video_subject_list")
    Observable<EncodedDataBean> getVideoRecommend(@Query("param") String param);


    //获取电影详情 NEW
    @GET("video_detail")
    Observable<EncodedDataBean> getMovieDetail(@Query("param") String param);


    //获取影视专题列表 NEW
    @GET("video_subject_content")
    Observable<EncodedDataBean> getFilmZhuanti(@Query("param") String param);


    //影视搜索关键字
    @GET("video_search_keyword")
    Observable<EncodedDataBean> getFilmKeyWord(@Query("param") String param);


}
