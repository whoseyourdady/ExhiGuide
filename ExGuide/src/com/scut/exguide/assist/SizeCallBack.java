package com.scut.exguide.assist;

public interface SizeCallBack {

	public void onGlobalLayout();

	public void getViewSize(int idx, int width, int height, int[] dims);
}
