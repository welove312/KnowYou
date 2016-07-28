package com.know.you.app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.know.you.app.R;
import com.know.you.app.utils.ToastUtils;

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



    @OnClick({R.id.tv_zhiren, R.id.btn_zhiren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_zhiren:
                break;
            case R.id.btn_zhiren:
                break;
        }
    }
}
