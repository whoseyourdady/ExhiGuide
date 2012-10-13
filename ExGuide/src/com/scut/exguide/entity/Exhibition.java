package com.scut.exguide.entity;

/**
 * 这是展会的实体
 * @author fatboy
 *
 */
public class Exhibition {
	
	public static final int TAG = 2;

	private String mName;//展会的名称
	private String mProvince;//所在省
	private String mCity;//所在城市
	private String mHall;//所在展馆
	private int mID;//对应的ID
	
	public Exhibition(){
		
	}
	
	public Exhibition(String title, String province, String city, String hall, int id) {
		mName = title;
		mProvince = province;
		mCity = city;
		mHall = hall;
		mID = id;
	}
	
	public String getName() {
		return mName;
	}
	
	public String getProvince() {
		return mProvince;
	}
	
	public String getCity() {
		return mCity;
	}
	
	public String getHall() {
		return mHall;
	}
	
	public int getId() {
		return mID;
	}
}
