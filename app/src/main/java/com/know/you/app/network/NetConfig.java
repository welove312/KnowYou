package com.know.you.app.network;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class NetConfig {

	public static final String STATUS_SUCCESS_CODE="200";//返回成功标识
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;

	/** 站点域名 */
	public static final String DOMAIN_URL = "http://www.shepinxiu.com/html/download.html";
	/**
	 * 接口请求服务器地址
	 */
	public static final String REQUEST_HEADER = "http://192.168.1.103:9090";
	
	/** 默认客户端key */
	public static final String DEFAULT_X_CLIENT_K = "x-client";
	/** 默认客户端value */
	public static final String DEFAULT_X_CLIENT_V = "android";
	/** 接口版本号 */
	public static final String VERSION = "v1.1";
}
