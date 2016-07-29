package com.know.you.app.network;

import com.know.you.app.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public class OkHttpKit {

    private static final String TAG = "OkHttpKit";

    public static void get(Request vo, final RequestCallback callback) {
        String url = null;
        if (!vo.absUrl) {
            url = NetConfig.REQUEST_HEADER + vo.requestUrl;
        } else {
            url = vo.requestUrl;
        }

        GetBuilder getBuilder= OkHttpUtils.get();
        getBuilder.url(url);

        /**装载headers,params*/
        //1、装载headers
        if(!vo.absUrl){
            getBuilder.addHeader(NetConfig.DEFAULT_X_CLIENT_K, NetConfig.DEFAULT_X_CLIENT_V);
        }


        //2、装载参数
        HashMap<String, Object> paramMap = vo.paramMap;
        LogUtils.e(TAG,"doGet:"+url);
        LogUtils.e(TAG,"paramMap:"+paramMap);
        if (paramMap != null&&paramMap.size()>0) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if(entry.getValue() instanceof String){
                    getBuilder.addParams(entry.getKey(),(String)entry.getValue());
                }
            }
        }

        getBuilder.build().execute(new StringCallback() {

            public void onError(Call call, Exception e, int id) {
                callback.onError(e);
            }


            public void onResponse(String response,int id) {
                callback.onSuccess(response);
            }
        });

    }



    public static void post(Request vo, final RequestCallback callback) {
        String url = null;
        if (!vo.absUrl) {
            url = NetConfig.REQUEST_HEADER + vo.requestUrl;
        } else {
            url = vo.requestUrl;
        }

        PostFormBuilder postBuilder= OkHttpUtils.post();
        postBuilder.url(url);

        /**装载headers,params*/
        //1、装载headers
        if(!vo.absUrl){
            postBuilder.addHeader(NetConfig.DEFAULT_X_CLIENT_K, NetConfig.DEFAULT_X_CLIENT_V);
        }


        //2、装载参数
        HashMap<String, Object> paramMap = vo.paramMap;
        LogUtils.e(TAG,"doPost:"+url);
        LogUtils.e(TAG,"paramMap:"+paramMap);
        if (paramMap != null&&paramMap.size()>0) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if(entry.getValue() instanceof String){
                    postBuilder.addParams(entry.getKey(),(String)entry.getValue());
                }else if(entry.getValue() instanceof File){
                    File file=(File)entry.getValue();
                    postBuilder.addFile(entry.getKey(),file.getName(),file);
                }else if(entry.getValue() instanceof List){
                    List list=(List)entry.getValue();
                    if(list!=null&&list.size()>0){
                        for(int i=0;i<list.size();i++){
                            Object obj=list.get(i);
                            if(obj instanceof File){
                                File file=(File)obj;
                                postBuilder.addFile(entry.getKey(),file.getName(),file);
                            }else if(obj instanceof String){
                                postBuilder.addParams(entry.getKey(),(String)obj);
                            }
                        }
                    }
                }
            }
        }
        postBuilder.build().execute(new StringCallback() {

                    public void onError(Call call, Exception e, int id) {
                        //e.printStackTrace();
                        callback.onError(e);
                    }


                    public void onResponse(String response,int id) {
                        callback.onSuccess(response);
                    }
                });

    }
}
