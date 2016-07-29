package com.know.you.app.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.know.you.app.R;
import com.know.you.app.utils.LogUtils;
import com.know.you.app.utils.NetworkUtils;
import com.know.you.app.widget.LoadingDialog;

import org.json.JSONObject;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public class OkHttpRequest {

    private static final String TAG = "OkHttpRequest";

    private static OkHttpRequest instance;

    private LoadingDialog dialog;

    private OkHttpRequest() {

    }

    public static OkHttpRequest getInstance() {
        if (instance == null) {
            instance = new OkHttpRequest();
        }
        return instance;
    }

    private class BaseHandler extends Handler {
        private Context context;
        private ResultCallback callBack;
        private Request req;

        public BaseHandler(Context context, ResultCallback callBack, Request request) {
            this.context = context;
            this.callBack = callBack;
            this.req = req;
        }

        public void handleMessage(Message msg) {
            try {
                //LogUtil.e(TAG, "--json-->>jsonStr:" +msg.what);
                if (msg.what == 1) {
                    if (msg.obj == null) {
                        callBack.onFailure(context.getResources().getString(R.string.network_no_responsed));
                    } else {
                        // 结果解析
                        String jsonStr = (String) msg.obj;
                        LogUtils.e(TAG, "response jsonStr:" + jsonStr);
                        JSONObject json = new JSONObject(jsonStr);
                        String code = json.optString("code");
                        if (NetConfig.STATUS_SUCCESS_CODE.equals(code)) {
                            if (req.jsonParser != null) {
                                Object parseJSON = req.jsonParser.parseJSON(jsonStr);
                                callBack.onSuccess(parseJSON);
                            } else {
                                callBack.onSuccess(jsonStr);
                            }
                        } else {
                            String errorMsg = json.optString("message");
                            ;
                            if (!TextUtils.isEmpty(errorMsg)) {
                                callBack.onFailure(errorMsg);
                            }
                        }
                    }
                } else if (msg.what == 2) {
                    callBack.onFailure(context.getResources().getString(R.string.network_no_responsed));
                } else if (msg.what == 0) {
                    callBack.onFailure(context.getResources().getString(R.string.network_no_connected));
                }
                // closeProgressDialog();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        }
    }

    private class BaseTask implements Runnable {
        private Context context;
        private Request req;
        private Handler handler;
        private Message msg = new Message();

        public BaseTask(Context context, Request req, Handler handler) {
            this.context = context;
            this.req = req;
            this.handler = handler;
        }

        private RequestCallback requestCallback=new RequestCallback(){

            @Override
            public void onSuccess(String result) {
                // TODO Auto-generated method stub
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(Throwable e) {
                // TODO Auto-generated method stub
                msg.what = 2;
                msg.obj = null;
                handler.sendMessage(msg);
            }

        };

        @Override
        public void run() {
            Object obj = null;
            if (NetworkUtils.isNetworkAvailable(context)) {
                if(req.reqMethod==NetConfig.HTTP_POST){
                    OkHttpKit.post(req,requestCallback);

                }else{
                    OkHttpKit.get(req,requestCallback);
                }

            } else {
                msg.what = 0;
                msg.obj = obj;
                handler.sendMessage(msg);
            }
        }

    }

    public void request(Request request, ResultCallback callback) {
        ThreadPoolManager threadPoolManager=ThreadPoolManager.getInstance();;
        if(request.showProgress){
            dialog = new LoadingDialog(request.context, request.context.getString(R.string.dialog_loading));
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }
        BaseHandler handler = new BaseHandler(request.context, callback, request);
        BaseTask taskThread = new BaseTask(request.context, request, handler);
        threadPoolManager.addTask(taskThread);
    }
}
