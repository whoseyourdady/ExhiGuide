package com.scut.exguide.entity;

/**
 * 这是属性的实体
 * 
 * @author fatboy
 *
 */
public class Attribute {

	public static final int TAG = 1;
	private String mTitle;
	private String mContent;
	
	public Attribute(String title, String cotent) {
		mTitle = title;
		mContent = cotent;
	}
	
	public String getTtile() {
		return mTitle;
	}
	
	public String getContent() {
		return mContent;
	}
}
