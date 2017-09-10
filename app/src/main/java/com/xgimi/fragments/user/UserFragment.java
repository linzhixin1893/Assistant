package com.xgimi.fragments.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xgimi.assistant.BaseFragment;
import com.xgimi.assistant.R;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class UserFragment extends BaseFragment {

    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_user, container, false);
        }
        return mView;
    }
}
