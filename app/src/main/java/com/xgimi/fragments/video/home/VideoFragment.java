package com.xgimi.fragments.video.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xgimi.assistant.BaseFragment;
import com.xgimi.assistant.R;
import com.xgimi.common.adapter.FragmentAdapter;
import com.xgimi.fragments.video.classify.VideoClassifyFragment;
import com.xgimi.fragments.video.recommend.VideoRecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class VideoFragment extends BaseFragment {

    private static final String[] CLASSIFY_ARR = {"精选", "电影", "连续剧", "综艺", "动漫", "纪录片"};
    private View mView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter mVpAdapter;
    private List<Fragment> mVideoFragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_video, container, false);
            initFragments();
            initView();

        }
        return mView;
    }

    private void initFragments() {
        mVideoFragmentList = new ArrayList<>();
        mVideoFragmentList.add(new VideoRecommendFragment());
        mVideoFragmentList.add(new VideoClassifyFragment(VideoClassifyFragment.TYPE_MOVIE));
        mVideoFragmentList.add(new VideoClassifyFragment(VideoClassifyFragment.TYPE_OPERA));
        mVideoFragmentList.add(new VideoClassifyFragment(VideoClassifyFragment.TYPE_VARIETY));
        mVideoFragmentList.add(new VideoClassifyFragment(VideoClassifyFragment.TYPE_CARTOOM));
        mVideoFragmentList.add(new VideoClassifyFragment(VideoClassifyFragment.TYPE_DOCUMENTARY));
    }

    private void initView() {
        mViewPager = (ViewPager) mView.findViewById(R.id.vp_video_main);
        mTabLayout = (TabLayout) mView.findViewById(R.id.tl_video_main);
        for (String tabText : CLASSIFY_ARR) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabText));
        }

        mVpAdapter = new FragmentAdapter(
                getFragmentManager(),
                mVideoFragmentList,
                CLASSIFY_ARR
        );
        mViewPager.setAdapter(mVpAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
