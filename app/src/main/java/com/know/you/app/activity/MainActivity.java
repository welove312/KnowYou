package com.know.you.app.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;

import com.know.you.app.R;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class MainActivity extends TabActivity {

    private String TAG = "MainActivity";
    private Context mContext;
    private TabHost mTabHost;
    private int mIndex;
    private int mCurrIndex;// 当前索引

    @BindViews({R.id.btn_tab_home, R.id.btn_tab_ask, R.id.btn_tab_seat, R.id.btn_tab_star, R.id.btn_tab_me})
    Button[] mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // no title.
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);

        initView();// 初始化View

    }

    private void initView() {
        mTabs[0].setSelected(true);
        refreshTabs(mTabs);
        mTabHost = this.getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator("home")
                .setContent(new Intent(this, NaviHomeActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("ask").setIndicator("ask")
                .setContent(new Intent(this, NaviMeActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("seat").setIndicator("seat")
                .setContent(new Intent(this, NaviMeActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("star").setIndicator("star")
                .setContent(new Intent(this, NaviMeActivity.class)));
        mTabHost.addTab(mTabHost.newTabSpec("me").setIndicator("me")
                .setContent(new Intent(this, NaviMeActivity.class)));

    }

    /**
     * 更新选中Tab按钮背景图片
     */
    private void refreshTabs(Button[] mTabs) {
        if (mTabs != null && mTabs.length > 0) {
            for (int i = 0; i < mTabs.length; i++) {
                Button tab = mTabs[i];
                tab.setTextColor(mContext.getResources().getColor(R.color.text_tab_off));
                if (i == 0) {
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_home_off, 0, 0);
                } else if (i == 1) {
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_ask_off, 0, 0);
                } else if (i == 2) {
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_seat_off, 0, 0);
                } else if (i == 3) {
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_star_off, 0, 0);
                } else if (i == 4) {
                    tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_me_off, 0, 0);
                }
                if (i == mIndex) {
                    tab.setTextColor(mContext.getResources().getColor(R.color.text_tab_on));
                    if (i == 0) {
                        tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_home_on, 0, 0);
                    } else if (i == 1) {
                        tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_ask_on, 0, 0);
                    } else if (i == 2) {
                        tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_seat_on, 0, 0);
                    } else if (i == 3) {
                        tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_star_on, 0, 0);
                    } else if (i == 4) {
                        tab.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.btn_tab_me_on, 0, 0);
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.btn_tab_home, R.id.btn_tab_ask, R.id.btn_tab_seat, R.id.btn_tab_star, R.id.btn_tab_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tab_home:
                mIndex = 0;
                mTabHost.setCurrentTabByTag("home");
                break;
            case R.id.btn_tab_ask:
                mIndex = 1;
                mTabHost.setCurrentTabByTag("ask");
                break;
            case R.id.btn_tab_seat:
                mIndex = 2;
                mTabHost.setCurrentTabByTag("seat");
                break;
            case R.id.btn_tab_star:
                mIndex = 3;
                mTabHost.setCurrentTabByTag("star");
                break;
            case R.id.btn_tab_me:
                mIndex = 4;
                mTabHost.setCurrentTabByTag("me");
                break;
        }
        refreshTabs(mTabs);


        mTabs[mCurrIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[mIndex].setSelected(true);
        mCurrIndex = mIndex;
    }
}
