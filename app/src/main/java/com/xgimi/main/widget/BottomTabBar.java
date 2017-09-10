package com.xgimi.main.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xgimi.assistant.R;
import com.xgimi.main.view.IMainView;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class BottomTabBar extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private IMainView mMainView;

    private LinearLayout mVideoLayout;
    private LinearLayout mMusicLayout;
    private LinearLayout mAppLayout;
    private LinearLayout mFindLayout;
    private LinearLayout mUserLayout;

    private ImageView mVideoIv;
    private ImageView mMusicIv;
    private ImageView mAppIv;
    private ImageView mFindIv;
    private ImageView mUserIv;

    private TextView mVideoTv;
    private TextView mMusicTv;
    private TextView mAppTv;
    private TextView mFindTv;
    private TextView mUserTv;

    private int mColorBlue;
    private int mColorDark;


    public BottomTabBar(Context context) {
        super(context);
        initView(context);
    }

    public BottomTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public BottomTabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_tab_main, this, true);
        mVideoLayout = (LinearLayout) this.findViewById(R.id.ll_video_bottom_bar);
        mVideoIv = (ImageView) this.findViewById(R.id.iv_video_bottom_bar);
        mVideoTv = (TextView) this.findViewById(R.id.tv_video_bottom_bar);
        mVideoLayout.setOnClickListener(this);

        mMusicLayout = (LinearLayout) this.findViewById(R.id.ll_music_bottom_bar);
        mMusicIv = (ImageView) this.findViewById(R.id.iv_music_bottom_bar);
        mMusicTv = (TextView) this.findViewById(R.id.tv_music_bottom_bar);
        mMusicLayout.setOnClickListener(this);

        mAppLayout = (LinearLayout) this.findViewById(R.id.ll_app_bottom_bar);
        mAppIv = (ImageView) this.findViewById(R.id.iv_app_bottom_bar);
        mAppTv = (TextView) this.findViewById(R.id.tv_app_bottom_bar);
        mAppLayout.setOnClickListener(this);

        mFindLayout = (LinearLayout) this.findViewById(R.id.ll_find_bottom_bar);
        mFindIv = (ImageView) this.findViewById(R.id.iv_find_bottom_bar);
        mFindTv = (TextView) this.findViewById(R.id.tv_find_bottom_bar);
        mFindLayout.setOnClickListener(this);

        mUserLayout = (LinearLayout) this.findViewById(R.id.ll_user_center_bottom_bar);
        mUserIv = (ImageView) this.findViewById(R.id.iv_user_center_bottom_bar);
        mUserTv = (TextView) this.findViewById(R.id.tv_user_center_bottom_bar);
        mUserLayout.setOnClickListener(this);

        mColorBlue = mContext.getResources().getColor(R.color.blue);
        mColorDark = mContext.getResources().getColor(R.color.text_color_gray_dark);
    }

    public void setMainView(IMainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_video_bottom_bar:
                if (mMainView != null) {
                    mMainView.switchFragment(0);
                }

                mVideoIv.setImageResource(R.drawable.img_bottom_tab_video_selected);
                mMusicIv.setImageResource(R.drawable.img_bottom_tab_bar_music_not_selected);
                mAppIv.setImageResource(R.drawable.img_bottom_tab_bar_app_not_selected);
                mFindIv.setImageResource(R.drawable.img_bottom_tab_bar_find_not_selected);
                mUserIv.setImageResource(R.drawable.img_bottom_tab_bar_user_center_not_selected);

                mVideoTv.setTextColor(mColorBlue);
                mMusicTv.setTextColor(mColorDark);
                mAppTv.setTextColor(mColorDark);
                mFindTv.setTextColor(mColorDark);
                mUserTv.setTextColor(mColorDark);
                break;
            case R.id.ll_music_bottom_bar:
                if (mMainView != null) {
                    mMainView.switchFragment(1);
                }

                mVideoIv.setImageResource(R.drawable.img_bottom_tab_video_not_selected);
                mMusicIv.setImageResource(R.drawable.img_bottom_tab_bar_music_selected);
                mAppIv.setImageResource(R.drawable.img_bottom_tab_bar_app_not_selected);
                mFindIv.setImageResource(R.drawable.img_bottom_tab_bar_find_not_selected);
                mUserIv.setImageResource(R.drawable.img_bottom_tab_bar_user_center_not_selected);

                mVideoTv.setTextColor(mColorDark);
                mMusicTv.setTextColor(mColorBlue);
                mAppTv.setTextColor(mColorDark);
                mFindTv.setTextColor(mColorDark);
                mUserTv.setTextColor(mColorDark);
                break;
            case R.id.ll_app_bottom_bar:
                if (mMainView != null) {
                    mMainView.switchFragment(2);
                }


                mVideoIv.setImageResource(R.drawable.img_bottom_tab_video_not_selected);
                mMusicIv.setImageResource(R.drawable.img_bottom_tab_bar_music_not_selected);
                mAppIv.setImageResource(R.drawable.img_bottom_tab_bar_app_selected);
                mFindIv.setImageResource(R.drawable.img_bottom_tab_bar_find_not_selected);
                mUserIv.setImageResource(R.drawable.img_bottom_tab_bar_user_center_not_selected);

                mVideoTv.setTextColor(mColorDark);
                mMusicTv.setTextColor(mColorDark);
                mAppTv.setTextColor(mColorBlue);
                mFindTv.setTextColor(mColorDark);
                mUserTv.setTextColor(mColorDark);
                break;
            case R.id.ll_find_bottom_bar:
                if (mMainView != null) {
                    mMainView.switchFragment(3);
                }


                mVideoIv.setImageResource(R.drawable.img_bottom_tab_video_not_selected);
                mMusicIv.setImageResource(R.drawable.img_bottom_tab_bar_music_not_selected);
                mAppIv.setImageResource(R.drawable.img_bottom_tab_bar_app_not_selected);
                mFindIv.setImageResource(R.drawable.img_bottom_tab_bar_find_selected);
                mUserIv.setImageResource(R.drawable.img_bottom_tab_bar_user_center_not_selected);

                mVideoTv.setTextColor(mColorDark);
                mMusicTv.setTextColor(mColorDark);
                mAppTv.setTextColor(mColorDark);
                mFindTv.setTextColor(mColorBlue);
                mUserTv.setTextColor(mColorDark);
                break;
            case R.id.ll_user_center_bottom_bar:
                if (mMainView != null) {
                    mMainView.switchFragment(4);
                }
                mVideoIv.setImageResource(R.drawable.img_bottom_tab_video_not_selected);
                mMusicIv.setImageResource(R.drawable.img_bottom_tab_bar_music_not_selected);
                mAppIv.setImageResource(R.drawable.img_bottom_tab_bar_app_not_selected);
                mFindIv.setImageResource(R.drawable.img_bottom_tab_bar_find_not_selected);
                mUserIv.setImageResource(R.drawable.img_bottom_tab_bar_user_center_selected);

                mVideoTv.setTextColor(mColorDark);
                mMusicTv.setTextColor(mColorDark);
                mAppTv.setTextColor(mColorDark);
                mFindTv.setTextColor(mColorDark);
                mUserTv.setTextColor(mColorBlue);

                break;
        }
        invalidate();
    }
}
