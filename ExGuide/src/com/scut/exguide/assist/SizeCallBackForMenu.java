package com.scut.exguide.assist;

import android.util.Log;
import android.widget.Button;

public class SizeCallBackForMenu implements SizeCallBack {

	private Button menu;
	private int menuWidth;

	public SizeCallBackForMenu(Button menu) {
		super();
		this.menu = menu;
	}

	@Override
	public void onGlobalLayout() {
		// TODO Auto-generated method stub
		this.menuWidth = this.menu.getMeasuredWidth() + 240;
	}

	@Override
	public void getViewSize(int idx, int width, int height, int[] dims) {
		// TODO Auto-generated method stub
		dims[0] = width;
		dims[1] = height;

		Log.d("daffdsaf", "fasfdasdfafsdf");
		/* 视图不是中间视图 */
		if (idx != 1) {
			dims[0] = width - this.menuWidth;
		}
	}

}
