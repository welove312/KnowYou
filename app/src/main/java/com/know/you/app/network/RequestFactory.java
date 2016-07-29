package com.know.you.app.network;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public class RequestFactory {


    public static void request(Request req, ResultCallback callback){
        OkHttpRequest.getInstance().request(req,callback);
    }
}
