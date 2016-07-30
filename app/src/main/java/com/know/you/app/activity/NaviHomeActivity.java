package com.know.you.app.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.know.you.app.R;
import com.know.you.app.adapter.HomeViewPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojunzi on 16-7-30.
 */
public class NaviHomeActivity extends BaseNaviActivity {

    String[] channels=new String[]{"北京","上海","广州","深圳","石家庄","天津","成都","西安","郑州","黑龙江","内蒙古","南宁"};

    @BindView(R.id.indicator_home)
    MagicIndicator indicatorHome;
    @BindView(R.id.vpager_home)
    ViewPager vpagerHome;

    private List<String> mDataList=new ArrayList<String>();

    @Override
    protected void initView(Bundle savedInstanceState) {
          this.loadContextView(R.layout.activity_navi_home);
    }

    @Override
    protected void setListener() {
        vpagerHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorHome.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorHome.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicatorHome.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mDataList= Arrays.asList(channels);
        vpagerHome.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager(), mContext,mDataList));

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(mContext);
                commonPagerTitleView.setContentView(R.layout.inflater_indicator_zoom_title);

                // 初始化
                final TextView titleText = (TextView) commonPagerTitleView.findViewById(R.id.tv_indicator_title);
                titleText.setText(mDataList.get(index));

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(Color.WHITE);
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Color.WHITE);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                        float zoomX=0.8f + (0.8f - 1.0f) * leavePercent;
                        float zoomY=0.8f + (0.8f - 1.0f) * leavePercent;
                        titleText.setScaleX(zoomX);
                        titleText.setScaleY(zoomY);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        float zoomX=0.6f + (1.0f - 0.8f) * enterPercent;
                        float zoomY=0.6f + (1.0f - 0.8f) * enterPercent;
                        titleText.setScaleX(zoomX);
                        titleText.setScaleY(zoomY);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vpagerHome.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        indicatorHome.setNavigator(commonNavigator);
    }

}
