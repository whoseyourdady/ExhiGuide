package com.scut.exguide.entity;

public class ExhibitionDetail {

	private String mDescription;
	private String mName;
	private String[] mVedioname;
	private String[] mPosterurl;
	private String[] mVediourl;

	public ExhibitionDetail() {
	}

	public ExhibitionDetail(String description, String name, String[] vNames,
			String[] pUrls, String[] vUrls) {
		this.mDescription = description;
		this.mName = name;
		this.mPosterurl = pUrls;
		this.mVedioname = vNames;
		this.mVediourl = vUrls;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String[] getmVedioname() {
		return mVedioname;
	}

	public void setmVedioname(String[] mVedioname) {
		this.mVedioname = mVedioname;
	}

	public String[] getmPosterurl() {
		return mPosterurl;
	}

	public void setmPosterurl(String[] mPosterurl) {
		this.mPosterurl = mPosterurl;
	}

	public String[] getmVediourl() {
		return mVediourl;
	}

	public void setmVediourl(String[] mVediourl) {
		this.mVediourl = mVediourl;
	}

}
