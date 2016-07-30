package com.know.you.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.know.you.app.fragment.HomeViewPagerFragment;
import com.know.you.app.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zixiaojun on 16/7/30.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    List<Fragment> mFragments=new ArrayList<Fragment>();

    public HomeViewPagerAdapter(FragmentManager fm, Context context,List<String> mDataList) {
        super(fm);
        mContext = context;
        HomeViewPagerFragment fragment=null;
        for(String item:mDataList){
            fragment=new HomeViewPagerFragment();
            mFragments.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
