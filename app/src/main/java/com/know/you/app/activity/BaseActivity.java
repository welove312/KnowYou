package com.know.you.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.view.Window;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public abstract class BaseActivity<T> extends FragmentActivity{

	private static final String TAG = "BaseActivity";
	protected Resources res;
	protected Context mContext;

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		EventBus.getDefault().register(this);//注册EventBus
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		res = mContext.getResources();
		ButterKnife.bind(this);
	}



	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	public void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);//注销EventBus
	}

	@Subscribe
	public void onEventMainThread(T t){

	}

}
