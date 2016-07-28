package com.know.you.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.know.you.app.R;
import com.know.you.app.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class MainActivity extends BaseActivity {

@BindView(R.id.btn_zhiren)
    Button btn_zhiren;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e("test","=============="+btn_zhiren);
    }

    @OnClick(R.id.btn_zhiren)
    public void sayHi(Button btn){
        Log.e("test","==============");
        ToastUtils.short_toast(mContext,btn.getText().toString());
    }
}
