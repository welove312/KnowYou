package com.know.you.app.utils;


import android.content.Context;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.know.you.app.R;

/**
 * @author:zixiaojun
 * @email:zixiaojun@shepinxiu.com
 * @date:2014-10-28 上午10:03:33
 * @copyright:时尚酷品
 */
public class ToastUtils {
	public static void short_toast(Context context, String content) {
		SuperToast superToast=new SuperToast(context);
		superToast.setAnimations(Style.ANIMATIONS_FLY);
		superToast.setIconResource(R.drawable.icon_toast_info);
		superToast.setIconPosition(Style.ICONPOSITION_LEFT);
		superToast.setDuration(Style.DURATION_SHORT);
		superToast.setText(content);
		superToast.setTextSize(Style.TEXTSIZE_SMALL);
		superToast.setColor(context.getResources().getColor(R.color.bg_toast_black));
		superToast.show();
	}

}
