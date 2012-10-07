package com.scut.exguide.ui;

import java.util.ArrayList;

import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.MenuListAdapter;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.view.ViewPager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

@SuppressWarnings("deprecation")
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
	
	private Button menuBtn;
	private View[] children;
	
	// 下部产品介绍中的view
	private ViewPager mEviewPager;
	private ArrayList<View> mExhibitsPageView;

	// 下部产品介绍中的文字头
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private MenuListAdapter menuListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		inflater = LayoutInflater.from(this);
		main = (ViewGroup) inflater.inflate(R.layout.exhibition, null);

		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
		this.scrollView = (MenuHorizontalScrollView) findViewById(R.id.mScrollView);
		this.menuListAdapter = new MenuListAdapter(this, 0);
		this.menuList = (ListView) findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);

		this.menuBtn = (Button)this.main.findViewById(R.id.menuBtn);
		this.menuBtn.setOnClickListener(onClickListener);
		
		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		children = new View[]{leftView, main};
		this.scrollView.initViews(children, new com.scut.exguide.assist.SizeCallBackForMenu(this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
		
		initalExhibits();

		initalItemHeader();

		String[] url = {
				"http://pic.iresearch.cn/news/0468/20120903/0082@56936.jpg",
				"http://nuomi.xnimg.cn/upload/deal//V_L/34075.jpg" };
		new DownloadImage(this).execute(url);

	}

	private OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
		}
	};
	
	public MenuHorizontalScrollView getScrollView() {
		return scrollView;
	}

	public void setScrollView(MenuHorizontalScrollView scrollView) {
		this.scrollView = scrollView;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(MenuHorizontalScrollView.menuOut == true)
				this.scrollView.clickMenuBtn();
			else
				this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}	
	
	/**
	 * 初始化下部产品介绍
	 */
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
		intent2.setClass(ExhiHomeActivity.this,
				ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		mExhibitsPageView = new ArrayList<View>();
		mExhibitsPageView.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());

		mExhibitsPageView.add(getLocalActivityManager().startActivity(
				"ExhiVedioSelectListActivity", intent2).getDecorView());

		mItem = new LinearLayout[mExhibitsPageView.size()];

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.infol);
				mItem[i].setBackgroundColor(Color.rgb(153, 153, 153));
			} else {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.medial);
				mItem[i].setBackgroundColor(Color.rgb(51, 51, 51));
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
			// _imageView = new ImageView(this);
			View temp = inflater.inflate(R.layout.poster2, null);
			_imageView = (ImageView) temp.findViewById(R.id.imageView1);
			// _imageView.setImageBitmap(bitmaps.get(i));

			_imageView.setImageBitmap(bitmaps.get(i));
			// viewList.add(_imageView);
			viewList.add(temp);
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

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	@Override
	public void UpdateImage(ArrayList<Bitmap> bitmapsList) {
		// TODO Auto-generated method stub
		UpdatePoster(CreateViewList(bitmapsList));

	}

	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub

	}
}
