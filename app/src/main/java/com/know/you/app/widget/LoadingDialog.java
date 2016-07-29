package com.know.you.app.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.know.you.app.R;

/**
 * Created by xiaojunzi on 16-7-29.
 */
public class LoadingDialog extends AlertDialog {

	private TextView tips_loading_msg;

	private String message = null;

	public LoadingDialog(Context context) {
		super(context);
		message = getContext().getResources().getString(R.string.dialog_loading);
	}

	public LoadingDialog(Context context, String message) {
		super(context);
		this.message = message;
		this.setCancelable(false);
	}

	public LoadingDialog(Context context, int theme, String message) {
		super(context, theme);
		this.message = message;
		this.setCancelable(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_loading);
		tips_loading_msg = (TextView) findViewById(R.id.dialog_loading_msg);
		tips_loading_msg.setText(this.message);
	}

	public void setText(String message) {
		this.message = message;
		tips_loading_msg.setText(this.message);
	}

	public void setText(int resId) {
		setText(getContext().getResources().getString(resId));
	}

}
