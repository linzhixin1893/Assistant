package com.xgimi.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] mTabTitleArr;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String ... tabTitles) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.mTabTitleArr = tabTitles;
    }

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTabTitleArr != null) {
            return mTabTitleArr[position];
        } else {
            return super.getPageTitle(position);
        }

    }
}

