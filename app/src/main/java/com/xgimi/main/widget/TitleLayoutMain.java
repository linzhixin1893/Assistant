package com.xgimi.main.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xgimi.assistant.R;
import com.xgimi.main.view.IMainView;

/**
 * Created by linzhixin on 2017/9/9.
 * 首页顶部标题栏控件
 */

public class TitleLayoutMain extends LinearLayout {

    private Context mContext;


    private ImageView mMusicPlayIv;
    private ImageView mRemoteControlIv;
    private EditText mSearchEdt;
    private IMainView mMainView;

    public TitleLayoutMain(Context context) {
        super(context);
        initView(context);
    }


    public TitleLayoutMain(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleLayoutMain(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public TitleLayoutMain(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_title_main, this, true);
        mMusicPlayIv = (ImageView) this.findViewById(R.id.iv_music_play_main_title);
        mRemoteControlIv = (ImageView) this.findViewById(R.id.iv_remote_control_main_title);
        mSearchEdt = (EditText) this.findViewById(R.id.edt_search_main_title);
        mMusicPlayIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMainView != null) {
                    mMainView.onMusicPlayClick();
                }
            }
        });
        mSearchEdt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMainView != null) {
                    mMainView.onSearchClick();
                }
            }
        });

        mRemoteControlIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMainView != null) {
                    mMainView.onRemoteControlClick();
                }
            }
        });
    }

    public void setMainView(@NonNull IMainView mainView) {
        mMainView = mainView;
    }


}
