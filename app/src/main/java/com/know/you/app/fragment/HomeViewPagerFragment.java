package com.know.you.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.know.you.app.R;
import com.know.you.app.adapter.AskContentViewAdapter;
import com.know.you.app.utils.LogUtils;
import com.know.you.app.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by zixiaojun on 16/7/30.
 */
public class HomeViewPagerFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.lv_listview)
    ListView lvListview;
    @BindView(R.id.rl_listview_refresh)
    BGARefreshLayout rlListviewRefresh;

    private AskContentViewAdapter mAskContentViewAdapter;
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
        mAskContentViewAdapter=new AskContentViewAdapter(mContext);
        rlListviewRefresh.setRefreshViewHolder(new BGANormalRefreshViewHolder(mContext, true));
        lvListview.setAdapter(mAskContentViewAdapter);


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
    public void onClick(View view) {

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
}
