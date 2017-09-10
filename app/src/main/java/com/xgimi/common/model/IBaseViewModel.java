package com.xgimi.common.model;

import com.xgimi.common.bean.BaseDataBean;

/**
 * Created by linzhixin on 2017/9/10.
 */

public interface IBaseViewModel {

    void initData(LoadDataListener listener);

    void saveToCache();


    BaseDataBean getData();

    void freshData(LoadDataListener mFreshDataListener);


    public interface LoadDataListener {
        void onLoadDataStart();
        void onLoadDataSucceed(BaseDataBean bean);
        void onLoadDataFailed(String reason);
    }

}
