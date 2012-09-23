package com.scut.exguide.entity;

import android.graphics.Bitmap;

/**
 * 这是Vedio的实体
 * @author fatboy
 *
 */
public class Vedio {
	
	public static final int TAG = 4;
	
	private Bitmap mThumbnail;
	private String mTitle;
	private int mID;
	
	public Vedio(Bitmap bitmap, String string, int id) {
		mThumbnail = bitmap;
		mTitle = string;
		mID = id;
	}
	
	public Bitmap getBitmap() {
		return mThumbnail;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public int getId() {
		return mID;
	}

 }
