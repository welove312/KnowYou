package com.know.you.app.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.know.you.app.R;

/**
 * @author:zixiaojun
 * @email:zixiaojun@shepinxiu.com
 * @date:2014-10-28 上午10:03:33
 * @copyright:时尚酷品
 */
public class ToastUtils {
    public static void short_toast(Context context, String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();

        }

    }
}
