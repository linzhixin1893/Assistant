package com.xgimi.fragments.video.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xgimi.assistant.BaseFragment;
import com.xgimi.assistant.R;
import com.xgimi.util.Alog;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class VideoClassifyFragment extends BaseFragment {

    public static final int TYPE_MOVIE = 1;//电影
    public static final int TYPE_OPERA = 2;//连续剧
    public static final int TYPE_VARIETY = 3;//综艺
    public static final int TYPE_CARTOOM = 4;//动漫
    public static final int TYPE_DOCUMENTARY = 5;//纪录片

    private int mClassifyType;
    private View mView;
    private RefreshLayout mRefreshLayout;

    public VideoClassifyFragment(int mClassifyType) {
        this.mClassifyType = mClassifyType;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_video_classify, container, false);
            mRefreshLayout = (RefreshLayout) mView.findViewById(R.id.sr_layout_video_classify);
            mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    Alog.d("");
                    mRefreshLayout.finishRefresh(1000);
                }
            });

            mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    Alog.d("");
                    mRefreshLayout.finishLoadmore(1000);
                }
            });
            TextView tv = (TextView) mView.findViewById(R.id.tv_video_classify);
            switch (mClassifyType) {
                case TYPE_MOVIE:
                    tv.setText("电影");
                    break;
                case TYPE_OPERA:
                    tv.setText("连续剧");
                    break;
                case TYPE_VARIETY:
                    tv.setText("综艺");
                    break;
                case TYPE_CARTOOM:
                    tv.setText("动漫");
                    break;
                case TYPE_DOCUMENTARY:
                    tv.setText("纪录片");
                    break;
                default:
                    tv.setText("default");
                    break;
            }
        }

        return mView;
    }


    @Override
    public void onDestroyView() {
        FragmentManager f = getFragmentManager();
        if (f != null && !f.isDestroyed()) {
            final FragmentTransaction ft = f.beginTransaction();
            if (ft != null) {
                ft.remove(this).commit();
            }
        }
        super.onDestroyView();
    }
}

