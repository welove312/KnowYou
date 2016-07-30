package com.know.you.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.know.you.app.R;
import com.know.you.app.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public abstract class BaseActivity<T>  extends AppCompatActivity implements View.OnClickListener{

	protected String TAG;
	protected Resources res;
	protected Context mContext;
	private LoadingDialog mLoadingDialog;

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		TAG = this.getClass().getSimpleName();
		EventBus.getDefault().register(this);//注册EventBus

		mContext = this;
		res = mContext.getResources();
		initView(paramBundle);
		setListener();
		processLogic(paramBundle);
	}

	/**
	 * 查找View
	 *
	 * @param id   控件的id
	 * @param <VT> View类型
	 * @return
	 */
	protected <VT extends View> VT getViewById(@IdRes int id) {
		return (VT) findViewById(id);
	}

	/**loadContextView*/
	protected void loadContextView(@LayoutRes int layoutResID){
		this.setContentView(layoutResID);
		ButterKnife.bind(this);
	}

	/**
	 * 初始化布局以及View控件
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 给View控件添加事件监听器
	 */
	protected abstract void setListener();

	/**
	 * 处理业务逻辑，状态恢复等操作
	 *
	 * @param savedInstanceState
	 */
	protected abstract void processLogic(Bundle savedInstanceState);

	/**
	 * 需要处理点击事件时，重写该方法
	 *
	 * @param v
	 */
	public void onClick(View v) {
	}

	public void showLoadingDialog() {
		if (mLoadingDialog == null) {
			mLoadingDialog = new LoadingDialog(this);
		}
		mLoadingDialog.show();
	}

	public void dismissLoadingDialog() {
		if (mLoadingDialog != null) {
			mLoadingDialog.dismiss();
		}
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
