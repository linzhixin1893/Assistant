package com.xgimi.fragments.video.recommend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.xgimi.assistant.BaseFragment;
import com.xgimi.assistant.R;
import com.xgimi.common.holder.BaseViewHolder;
import com.xgimi.common.widget.ItemHeaderLayout;
import com.xgimi.util.Alog;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class VideoRecommendAdapter extends RecyclerView.Adapter<BaseViewHolder>{


    private static final int ITEM_TYPE_SUBJECT = 1;
    private static final int ITEM_TYPE_CLASSIFY = 2;


    private VideoRecommendBean mDataBean;
    private IVideoRecommendView mFragmentView;

    public VideoRecommendAdapter(VideoRecommendBean mDataBean, IVideoRecommendView mFragmentView) {
        this.mDataBean = mDataBean;
        this.mFragmentView = mFragmentView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mDataBean.getData().getSubject().size()) {
            return ITEM_TYPE_SUBJECT;
        } else {
            return ITEM_TYPE_CLASSIFY;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_SUBJECT) {
            View view = LayoutInflater.from(((BaseFragment)mFragmentView).getContext())
                    .inflate(R.layout.item_video_recommend_subject, null);
            return new SubjectViewHolder(view);
        } else {
            View view = LayoutInflater.from(((BaseFragment)mFragmentView).getContext())
                    .inflate(R.layout.item_video_recommend_classify, null);
            return new ClassifyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof SubjectViewHolder) {
            ((SubjectViewHolder)holder).mItemHeader.init(
                    mDataBean.getData().getSubject().get(position).getTitle(), null, null
            );
            ((SubjectViewHolder)holder).mGv.setAdapter(
                    new VideoRecommendSubjectAdapter(
                            ((BaseFragment)mFragmentView).getContext(),
                            mDataBean.getData().getSubject().get(position).getContents()
                    )
            );
        } else {
            Alog.d("");
        }
    }

    @Override
    public int getItemCount() {
//        return mDataBean.getData().getSubject().size() + mDataBean.getData().getRecommend().size();
        return mDataBean.getData().getSubject().size();
    }


    public static class SubjectViewHolder extends BaseViewHolder {

        public ItemHeaderLayout mItemHeader;
        private GridView mGv;


        public SubjectViewHolder(View itemView) {
            super(itemView);
            mItemHeader = (ItemHeaderLayout) itemView.findViewById(R.id.view_header_item_video_recommend_subject);
            mGv = (GridView) itemView.findViewById(R.id.gv_item_video_recommend_subject);
        }
    }

    public static class ClassifyViewHolder extends BaseViewHolder {

        public ClassifyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
