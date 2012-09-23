package com.scut.exguide.assist;

import java.util.ArrayList;

import android.graphics.Bitmap;

public interface MyActivity {

	public String getTag();
	
	public void UpdateImage(ArrayList<Bitmap> bitmapsList);
	
	public void Update(Object ...param);
}
