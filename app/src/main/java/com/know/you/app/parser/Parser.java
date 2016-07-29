package com.know.you.app.parser;

import org.json.JSONException;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public abstract class Parser<T> {

    public abstract T parseJSON(String jsonStr) throws JSONException;
}
