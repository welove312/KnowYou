package com.know.you.app.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.know.you.app.R;
import com.know.you.app.transform.GlideCircleTransform;
import com.know.you.app.transform.GlideRoundTransform;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by zixiaojun on 16/7/30.
 */
public class AskContentViewAdapter extends BGAAdapterViewAdapter<String> {

    public AskContentViewAdapter(Context context) {
        super(context, R.layout.item_ask_content);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.iv_item_thumb);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, String model) {;

        Glide.with(mContext).load("http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/images/staggered23.png").crossFade().into((ImageView) viewHolderHelper.getView(R.id.iv_item_thumb));
        //viewHolderHelper.setImageResource(R.id.iv_item_thumb,R.drawable.demo_1);
    }
}
