package com.scut.exguide.ui;

import java.util.ArrayList;

import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;

import android.R.drawable;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import android.os.Bundle;

import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.ViewGroup.LayoutParams;

import android.widget.ImageView;
import android.widget.LinearLayout;

public class ExhiHomeActivity extends ActivityGroup implements MyActivity {

	private final static String TAG = "ExhiHomeActivity";
	private LayoutInflater inflater;

	private ViewPager mPviewPager; // viewpager控件
	private ArrayList<View> mPosterPageViews;// 用于翻页的view

	private ImageView _imageView;// 临时变量
	private ImageView[] imageViews;// 小圆点的view
	// 产品主页布局
	private ViewGroup main;
	// 上方的海报
	private ViewGroup mPosterChannel;
	// 海报中的小圆点
	private ViewGroup mDotgroup;
	// 下方的产品信息
	private ViewGroup mExhibitsChannel;

	// 下部产品介绍中的view
	private ViewPager mEviewPager;
	private ArrayList<View> mExhibitsPageView;

	// 下部产品介绍中的文字头
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		inflater = getLayoutInflater();

		main = (ViewGroup) inflater.inflate(R.layout.exhibition, null);

		initalExhibits();

		initalItemHeader();

		setContentView(main);

		String[] url = {
				"http://auto.ce.cn/zt/2009/SHAutoShow/wgcz/E4/E4logo/200904/23/W020090423825631031078.jpg",
				"http://auto.msn.com.cn/images/auto_international/eu/2009/2/10/20092105da1cc54f1d247e0b1729deb544ce25e.jpg",
				"http://www.soccergaming.com/wp-content/uploads/2009/04/wcg-usa-2009.jpg" };
		new DownloadImage(this).execute(url);
		

	}

	/**
	 * 初始化下部产品介绍
	 */
	@SuppressWarnings("deprecation")
	private void initalExhibits() {
		mExhibitsChannel = (ViewGroup) main.findViewById(R.id.exhibits);

		mEviewPager = (ViewPager) mExhibitsChannel
				.findViewById(R.id.ExhibitsguidePages);

		mItemHeader = (ViewGroup) mExhibitsChannel
				.findViewById(R.id.item_header);

		Intent intent = new Intent();
		intent.setClass(ExhiHomeActivity.this, ExhiAttributeListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		Intent intent2 = new Intent();
		intent2.setClass(ExhiHomeActivity.this, ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		mExhibitsPageView = new ArrayList<View>();
		mExhibitsPageView.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());

		mExhibitsPageView.add(getLocalActivityManager().startActivity("ExhiVedioSelectListActivity",
				intent2).getDecorView());
		
		

		mItem = new LinearLayout[mExhibitsPageView.size()];

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.infol);
				mItem[i].setBackgroundColor(Color.rgb(204, 204, 204));
			} else {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.medial);
				mItem[i].setBackgroundColor(Color.rgb(255, 255, 255));
			}

		}

		mEviewPager.setAdapter(new ExhibitsPageAdapter(mExhibitsPageView));

		mEviewPager.setOnPageChangeListener(new ExhibitsPageChangeListener(
				mItem));
	}

	/*
	 * 初始化上部海报栏
	 */
	private void UpdatePoster(ArrayList<View> viewList) {

		// mPosterPageViews = new ArrayList<View>(); // 生成链表
		// mPosterPageViews.add(inflater.inflate(R.layout.poster1, null)); //
		// 将要那个展示的页面加入链表中
		// mPosterPageViews.add(inflater.inflate(R.layout.poster2, null));

		imageViews = new ImageView[viewList.size()]; // 生成小圆点

		mPosterChannel = (ViewGroup) main.findViewById(R.id.main);

		mDotgroup = (ViewGroup) mPosterChannel.findViewById(R.id.viewGroup);

		initalDot();// 初始化小圆点

		mPviewPager = (ViewPager) mPosterChannel.findViewById(R.id.guidePages);

		mPviewPager.setAdapter(new PosterPageAdapter(viewList));

		mPviewPager.setOnPageChangeListener(new PosterPageChangeListener(
				imageViews));
	}

	/**
	 * 生成上方海报图片
	 * 
	 * @param bitmaps
	 * @return
	 */
	private ArrayList<View> CreateViewList(ArrayList<Bitmap> bitmaps) {
		ImageView _imageView;
		ArrayList<View> viewList = new ArrayList<View>();
		for (int i = 0; i < bitmaps.size(); i++) {
			_imageView = new ImageView(this);
			_imageView.setImageBitmap(bitmaps.get(i));
			viewList.add(_imageView);
		}
		return viewList;
	}

	/**
	 * 初始化换页的小圆点
	 */
	private void initalDot() {
		// 初始化小圆点
		for (int i = 0; i < imageViews.length; i++) {
			_imageView = new ImageView(ExhiHomeActivity.this);
			_imageView.setLayoutParams(new LayoutParams(20, 20));
			_imageView.setPadding(20, 0, 20, 0);
			imageViews[i] = _imageView;
			if (i == 0) {
				// 默认选中第一张图片
				imageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.page_indicator);
			}

			mDotgroup.addView(imageViews[i]);
		}
	}

	/**
	 * 初始化itemheader
	 */
	private void initalItemHeader() {

	}

	public String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	public void UpdateImage(ArrayList<Bitmap> bitmapsList) {
		// TODO Auto-generated method stub
		UpdatePoster(CreateViewList(bitmapsList));

	}

	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub
		
	}
}
