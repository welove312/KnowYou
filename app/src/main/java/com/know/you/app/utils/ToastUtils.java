package com.know.you.app.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.know.you.app.R;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class ToastUtils {
    public static void short_toast(Context context, String content) {
        if(!TextUtils.isEmpty(content)) {
            new SuperToast(context)
                    .setText(content)
                    .setDuration(Style.DURATION_SHORT)
                    .setFrame(Style.FRAME_KITKAT)
                    .setIconResource(R.drawable.icon_toast_info)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_TEAL))
                    .setAnimations(Style.ANIMATIONS_FLY)
                    .show();
        }

    }
}
