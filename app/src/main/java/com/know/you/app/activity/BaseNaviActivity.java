package com.know.you.app.activity;


import com.know.you.app.R;
import com.know.you.app.utils.ToastUtils;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public abstract class BaseNaviActivity extends BaseActivity {
	private static final long DIFF_DEFAULT_BACK_TIME = 2000;
	private long mBackTime = -1;
	
	@Override
	public void onBackPressed() {
		long nowTime = System.currentTimeMillis();
		long diff = nowTime - mBackTime;
		if (diff >= DIFF_DEFAULT_BACK_TIME) {
			mBackTime = nowTime;
			ToastUtils.short_toast(mContext,mContext.getResources().getString(R.string.app_logout_hint));
		} else {
			super.onBackPressed();
		}
	}


}
