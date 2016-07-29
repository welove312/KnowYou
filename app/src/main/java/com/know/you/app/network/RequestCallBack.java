package com.know.you.app.network;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public interface RequestCallback {
    public void onSuccess(String result);
    public void onError(Throwable e);
}
