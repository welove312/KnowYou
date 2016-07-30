package com.know.you.app.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.know.you.app.R;
import com.know.you.app.adapter.AskContentViewAdapter;
import com.know.you.app.utils.LogUtils;
import com.know.you.app.utils.ThreadUtils;
import com.know.you.app.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by zixiaojun on 16/7/30.
 */
public class HomeViewPagerFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate,BGAOnItemChildClickListener {


    @BindView(R.id.lv_listview)
    ListView lvListview;
    @BindView(R.id.rl_listview_refresh)
    BGARefreshLayout rlListviewRefresh;

    private BGAAdapterViewAdapter mAskContentViewAdapter;
    private List<String> mDataList;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home_refresh);
    }

    @Override
    protected void setListener() {
        rlListviewRefresh.setDelegate(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

        mAskContentViewAdapter=new BGAAdapterViewAdapter<String>(mContext,R.layout.item_ask_content){

            @Override
            protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
                viewHolderHelper.setItemChildClickListener(R.id.iv_item_thumb);
            }

            @Override
            protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, String model) {
                Glide.with(mContext).load("http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/images/staggered23.png").crossFade().into((ImageView) viewHolderHelper.getView(R.id.iv_item_thumb));

            }
        };
        mAskContentViewAdapter.setOnItemChildClickListener(this);
        lvListview.setAdapter(mAskContentViewAdapter);
        
        rlListviewRefresh.setRefreshViewHolder(new BGANormalRefreshViewHolder(mContext, true));



        mDataList=new ArrayList<String>();
        mDataList.add("1");mDataList.add("1");mDataList.add("1");
        mDataList.add("1");mDataList.add("1");mDataList.add("1");
        mDataList.add("1");mDataList.add("1");mDataList.add("1");
        mDataList.add("1");mDataList.add("1");mDataList.add("1");
        mDataList.add("1");mDataList.add("1");mDataList.add("1");
        mDataList.add("1");mDataList.add("1");mDataList.add("1");
        mAskContentViewAdapter.setData(mDataList);
    }

    @Override
    protected void onFirstUserVisible() {


    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        try {

            ThreadUtils.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    if(rlListviewRefresh!=null) {
                        rlListviewRefresh.endRefreshing();
                    }
                    mAskContentViewAdapter.setData(mDataList);

                }
            },2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        try {
            ThreadUtils.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    List<String> addList=new ArrayList<String>();
                    addList.add("1");addList.add("1");addList.add("1");
                    mDataList.add("1");mDataList.add("1");mDataList.add("1");
                    mDataList.add("1");mDataList.add("1");mDataList.add("1");
                    mDataList.add("1");mDataList.add("1");mDataList.add("1");
                    mDataList.add("1");mDataList.add("1");mDataList.add("1");
                    if(rlListviewRefresh!=null) {
                        rlListviewRefresh.endLoadingMore();
                    }
                    mAskContentViewAdapter.addMoreData(addList);

                }
            },2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_item_thumb:

                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        switch (childView.getId()){
            case R.id.iv_item_thumb:
                ToastUtils.short_toast(mContext,"click me");
                break;
        }
    }
}
