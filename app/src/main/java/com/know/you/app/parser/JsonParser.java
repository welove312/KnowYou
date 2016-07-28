package com.know.you.app.parser;

import org.json.JSONException;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public abstract class JsonParser<T> {

	public abstract T parseJSON(String jsonStr) throws JSONException;

}
