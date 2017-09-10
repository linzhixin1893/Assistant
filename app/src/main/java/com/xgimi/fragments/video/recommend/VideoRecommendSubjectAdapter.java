package com.xgimi.fragments.video.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xgimi.assistant.R;
import com.xgimi.util.Alog;

import java.util.List;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class VideoRecommendSubjectAdapter extends BaseAdapter {

    private List<VideoRecommendBean.DataBean.SubjectBean.ContentsBean> mDataList;
    private Context mContext;

    public VideoRecommendSubjectAdapter(Context mContext, List<VideoRecommendBean.DataBean.SubjectBean.ContentsBean> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        Alog.d(mDataList.size());
    }

    @Override
    public int getCount() {

        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_video_recommend_subject_video_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivCover = (ImageView) convertView
                    .findViewById(R.id.iv_cover_recommend_subject_video_item);
            viewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tv_name_recommend_subject_video_item);
            viewHolder.tvDescribe = (TextView) convertView
                    .findViewById(R.id.tv_describe_recommend_subject_video_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(mDataList.get(position).getName());
        viewHolder.tvDescribe.setText(mDataList.get(position).getDescription());
        Glide.with(mContext).load(mDataList.get(position).getImg()).
                placeholder(R.drawable.img_loading2)
                .crossFade()
                .error(R.drawable.img_loading2)
                .into(viewHolder.ivCover);
        return convertView;
    }

    class ViewHolder {
        ImageView ivCover;
        TextView tvName;
        TextView tvDescribe;
    }
}
