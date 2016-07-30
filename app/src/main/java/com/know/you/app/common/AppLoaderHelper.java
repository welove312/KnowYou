package com.know.you.app.common;

import android.content.Context;
import com.know.you.app.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class AppLoaderHelper {

    private static AppLoaderHelper instance;
    private AppLoaderHelper() {
    }

    public static AppLoaderHelper getInstance() {
        if (instance == null)
            instance = new AppLoaderHelper();
        return instance;
    }

    public void appInit(Context context){
        if (!LogUtils.DEBUG) {// no debug handle this function
            CrashHandler.getInstance().init(context); // 程序全局异常处理
        }
        initOkHttp();
    }


    /**
     * 初始化okhttp
     * */
    private void initOkHttp(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }



}
