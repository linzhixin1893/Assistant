package com.xgimi.assistant;

import android.support.v4.app.Fragment;

import com.xgimi.util.ToastUtil;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by linzhixin on 2017/9/9.
 */

public class BaseFragment extends Fragment {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroyView() {
        mCompositeDisposable.clear();
        super.onDestroyView();
    }

    public void showProgressDialog() {

    }

    public void dismissProgressDialog() {

    }

    public void showToast(String reason) {
        ToastUtil.getToast(reason).show();
    }
}
