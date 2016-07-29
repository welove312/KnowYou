package com.know.you.app;

import android.app.Application;

import com.know.you.app.common.CrashHandler;
import com.know.you.app.common.AppLoaderBuilder;
import com.know.you.app.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

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
        AppLoaderBuilder.getInstance().appInit(this);

    }

    public static MyApplication getInstance() {
        return instance;
    }

    //应用全局函数
    //==========================================================

}
