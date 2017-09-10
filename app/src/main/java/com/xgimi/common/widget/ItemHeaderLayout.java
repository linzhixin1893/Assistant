package com.xgimi.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xgimi.assistant.R;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class ItemHeaderLayout extends RelativeLayout {

    private Context mContext;
    private TextView mItemNameTv;
    private TextView mItemMoreTv;
    private FrameLayout mItemMoreFl;

    private OnHeaderMoreClickListener mHeaderMoreClickListener;


    public ItemHeaderLayout(Context context) {
        super(context);
        initView(context);
    }


    public ItemHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ItemHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ItemHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_common_item_header, this, true);
        mItemNameTv = (TextView) this.findViewById(R.id.tv_item_name_common_item_header);
        mItemMoreFl = (FrameLayout) this.findViewById(R.id.fl_more_common_item_header);
        mItemMoreTv = (TextView) this.findViewById(R.id.tv_more_common_item_header);
    }

    public void init(String itemName, String moreText, OnHeaderMoreClickListener listener) {
        mItemNameTv.setText(itemName);
        if (listener != null) {
            mHeaderMoreClickListener = listener;
            mItemMoreFl.setVisibility(VISIBLE);
            mItemMoreTv.setText(moreText);
            mItemMoreFl.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mHeaderMoreClickListener != null) {
                        mHeaderMoreClickListener.onHeaderMoreClick();
                    }
                }
            });
        } else {
            mItemMoreFl.setVisibility(GONE);
        }
    }

    public interface OnHeaderMoreClickListener {
        void onHeaderMoreClick();
    }
}
