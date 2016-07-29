package com.know.you.app.activity;

import com.know.you.app.R;
import com.know.you.app.network.NetConfig;
import com.know.you.app.network.Request;
import com.know.you.app.network.RequestFactory;
import com.know.you.app.network.ResultCallback;
import com.know.you.app.utils.LogUtils;
import com.know.you.app.utils.ToastUtils;

import java.util.HashMap;

/**
 * Created by xiaojunzi on 16-7-30.
 */
public class NaviHomeActivity extends BaseNaviActivity{

    @Override
    protected void loadViewLayout() {
        this.setContentView(R.layout.activity_navi_home);
    }

    @Override
    protected void processLogic() {

    }

    private void test() {
        Request vo = new Request();
        vo.requestUrl = res.getString(R.string.post_user_sign);
        vo.context = mContext;
        vo.reqMethod = NetConfig.HTTP_POST;
        vo.showProgress = true;
        vo.paramMap = new HashMap<String, Object>();
        vo.paramMap.put("mobile", "15010392162");
        vo.paramMap.put("pwd", "111111");

        RequestFactory.request(vo, new ResultCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // TODO Auto-generated method stub
                try {
                    if (result != null) {
                        LogUtils.e("test", "===============" + result);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String errCode) {
                // TODO Auto-generated method stub
                ToastUtils.short_toast(mContext, errCode);

            }
        });
    }
}
