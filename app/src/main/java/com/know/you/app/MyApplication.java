package com.know.you.app;

import android.app.Application;

import com.know.you.app.common.AppLoaderHelper;

import okhttp3.OkHttpClient;

/**
 * Created by xiaojunzi on 16-7-28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppLoaderHelper.getInstance().appInit(this);

    }

    public static MyApplication getInstance() {
        return instance;
    }

    //应用全局函数
    //==========================================================

}
