package com.xgimi.main.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.xgimi.assistant.BaseActivity;
import com.xgimi.assistant.R;
import com.xgimi.fragments.app.AppFragment;
import com.xgimi.fragments.find.FindFragment;
import com.xgimi.fragments.music.MusicFragment;
import com.xgimi.fragments.user.UserFragment;
import com.xgimi.fragments.video.home.VideoFragment;
import com.xgimi.main.widget.BottomTabBar;
import com.xgimi.main.widget.TitleLayoutMain;
import com.xgimi.util.Alog;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements IMainView {

    private TitleLayoutMain mTitleBar;
    private BottomTabBar mBottomBar;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragments();
    }

    private void initFragments() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new VideoFragment());
        mFragmentList.add(new MusicFragment());
        mFragmentList.add(new AppFragment());
        mFragmentList.add(new FindFragment());
        mFragmentList.add(new UserFragment());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_fragment_container, mFragmentList.get(0));
        ft.commit();
    }

    private void initView() {
        mTitleBar = (TitleLayoutMain) findViewById(R.id.view_title_layout_main);
        mBottomBar = (BottomTabBar) findViewById(R.id.view_bottom_tab_bar_main);
        mTitleBar.setMainView(this);
        mBottomBar.setMainView(this);
    }


    @Override
    public void switchFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_fragment_container, mFragmentList.get(index));
        ft.commit();
    }

    @Override
    public void onSearchClick() {
        Alog.d("");
    }


    @Override
    public void onRemoteControlClick() {
        Alog.d("");
    }


    @Override
    public void onMusicPlayClick() {
        Alog.d("");
    }


}
