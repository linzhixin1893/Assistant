package com.xgimi.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xgimi.assistant.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by linzhixin on 2017/9/10.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).placeholder(R.drawable.img_loading).crossFade().error(R.drawable.img_error).into(imageView);
    }
}
