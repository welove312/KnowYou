package com.know.you.app.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.know.you.app.R;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class ToastUtils {
    public static void short_toast(Context context, String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();

        }

    }
}
