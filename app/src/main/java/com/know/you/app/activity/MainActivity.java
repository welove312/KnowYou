package com.know.you.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.know.you.app.R;
import com.know.you.app.network.NetConfig;
import com.know.you.app.network.Request;
import com.know.you.app.network.RequestFactory;
import com.know.you.app.network.ResultCallback;
import com.know.you.app.parser.JsonParser;
import com.know.you.app.utils.LogUtils;
import com.know.you.app.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_zhiren)
    TextView tvZhiren;
    @BindView(R.id.btn_zhiren)
    Button btnZhiren;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e("test", "==============" + btnZhiren);
    }

    private void test(){
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
                    if(result!=null){
                        LogUtils.e("test","==============="+result);
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



    @OnClick({R.id.tv_zhiren, R.id.btn_zhiren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_zhiren:
                break;
            case R.id.btn_zhiren:
                Log.e("test","===========>>>>");
                test();
                break;
        }
    }
}
