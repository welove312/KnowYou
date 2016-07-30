package com.know.you.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.know.you.app.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {

	protected String TAG;
	protected View mContentView;
	protected Context mContext;

	protected boolean mIsPrepared = false;
	protected boolean mIsFirstResume = true;
	protected boolean mIsFirstVisible = true;
	protected boolean mIsFirstInvisible = true;

	private LoadingDialog mLoadingDialog;
	private Unbinder unbinder;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		TAG = this.getClass().getSimpleName();
		mContext=getActivity();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initPrepare();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mIsFirstResume) {
			mIsFirstResume = false;
			return;
		}
		if (getUserVisibleHint()) {
			onUserVisible();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (getUserVisibleHint()) {
			onUserInvisible();
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		//unbinder.unbind();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// 避免多次从xml中加载布局文件
		if (mContentView == null) {
			initView(savedInstanceState);
			setListener();
			processLogic(savedInstanceState);
		} else {
			ViewGroup parent = (ViewGroup) mContentView.getParent();
			if (parent != null) {
				parent.removeView(mContentView);
			}
		}
		return mContentView;
	}


	protected void setContentView(@LayoutRes int layoutResID) {
		mContentView = LayoutInflater.from(mContext).inflate(layoutResID, null);
		unbinder=ButterKnife.bind(this,mContentView);
	}

	protected View getmContentView(){
		return mContentView;
	}

	/**
	 * 初始化View控件
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


	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (mIsFirstVisible) {
				mIsFirstVisible = false;
				initPrepare();
			} else {
				onUserVisible();
			}
		} else {
			if (mIsFirstInvisible) {
				mIsFirstInvisible = false;
				onFirstUserInvisible();
			} else {
				onUserInvisible();
			}
		}
	}

	public synchronized void initPrepare() {
		if (mIsPrepared) {
			onFirstUserVisible();
		} else {
			mIsPrepared = true;
		}
	}

	/**
	 * 第一次对用户可见时会调用该方法
	 */
	protected abstract void onFirstUserVisible();

	/**
	 * 对用户可见时会调用该方法，除了第一次
	 */
	public void onUserVisible() {
	}

	/**
	 * 第一次对用户不可见时会调用该方法
	 */
	public void onFirstUserInvisible() {
	}

	/**
	 * 对用户不可见时会调用该方法，除了第一次
	 */
	public void onUserInvisible() {
	}


	/**
	 * 查找View
	 *
	 * @param id   控件的id
	 * @param <VT> View类型
	 * @return
	 */
	protected <VT extends View> VT getViewById(@IdRes int id) {
		return (VT) mContentView.findViewById(id);
	}

	public void showLoadingDialog() {
		if (mLoadingDialog == null) {
			mLoadingDialog = new LoadingDialog(mContext);
		}
		mLoadingDialog.show();
	}

	public void dismissLoadingDialog() {
		if (mLoadingDialog != null) {
			mLoadingDialog.dismiss();
		}
	}



	/**通过类名启动Activity*/
	protected void openActivity(Class<?> pClass){
		openActivity(pClass,null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(mContext, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
	}
}
