package com.know.you.app;

import android.app.Application;
import android.text.TextUtils;

import com.know.you.app.common.CrashHandler;
import com.know.you.app.common.ImageLoaderBuilder;
import com.know.you.app.utils.FileUtils;
import com.know.you.app.utils.LogUtils;

import java.io.File;

/**
 * Created by xiaojunzi on 16-7-28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderBuilder.getInstance().initImageLoader(getApplicationContext());//初始化ImageLoader
        if (!LogUtils.DEBUG) {// no debug handle this function
            CrashHandler.getInstance().init(getApplicationContext()); // 程序全局异常处理
        }

        instance = this;


    }

    public static MyApplication getInstance() {
        return instance;
    }

    //应用全局函数
    //==========================================================


}
