package com.know.you.app.network;

import android.content.Context;

import com.know.you.app.parser.JsonParser;
import com.know.you.app.parser.Parser;

import java.util.HashMap;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public class Request {

    public Context context;
    /** 请求Url */
    public String requestUrl;
    /** 请求参数 */
    public HashMap<String, Object> paramMap=new HashMap<String, Object>();
    /** Json数据解析模版 */
    public Parser<?> jsonParser;
    /** 请求方式；默认为get */
    public int reqMethod = NetConfig.HTTP_GET;
    /** 是否为绝对Url请求 */
    public boolean absUrl;
    /**是否显示进度条*/
    public boolean showProgress;
}
