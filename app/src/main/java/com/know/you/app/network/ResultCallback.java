package com.know.you.app.network;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public interface ResultCallback<T> {

    public void onSuccess(T result);

    public void onFailure(String errCode);
}
