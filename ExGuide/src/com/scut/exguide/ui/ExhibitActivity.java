package com.scut.exguide.ui;

import java.io.IOException;
import java.util.ArrayList;
import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;
import com.scut.exguide.entity.Exhibit;
import com.scut.exguide.json.ExGuideJSON;
import com.scut.exguide.utils.TransistionUtil;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.os.StrictMode;

import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ExhibitActivity extends ActivityGroup implements MyActivity {
	// 定义Menu菜单选项的ItemId
	final static int ONE = Menu.FIRST;
	final static int TWO = Menu.FIRST + 1;
	final static int THREE = Menu.FIRST + 2;
	final static int FOUR = Menu.FIRST + 3;
	final static int FIVE = Menu.FIRST + 4;

	private ArrayList<Bitmap> bitmapsList;
	public static ExGuideJSON api = new ExGuideJSON();

	private final static String TAG = "ExhiHomeActivity";
	private LayoutInflater inflater;

	private ViewPager mPoserViewPager; // viewpager控件

	// 下部产品介绍中的view
	private ViewPager mInfoViewPager;
	private ArrayList<View> mInfoPages;

	private ImageView _imageView;// 临时变量
	private ImageView[] DotImageViews;// 小圆点的view

	// 产品主页布局
	private ViewGroup main;
	// 上方的海报
	private ViewGroup mPosterChannel;
	// 海报中的小圆点
	private ViewGroup mDotgroup;
	// 下方的产品信息
	private ViewGroup mInfoChannel;
	// 图片的progressbar
	private ProgressBar mPosterProgressBar;

	// 下部产品介绍中的文字头
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	// 展会的名字
	private TextView mExhibitionName;
	private Dialog popUpDialog;

	public static Exhibit exhibit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());

		inflater = LayoutInflater.from(this);
		main = (ViewGroup) inflater.inflate(R.layout.homeactivity, null);
		setContentView(main);

		initalLayout();
		resolveIntent(getIntent());

	}

	// 创建Menu菜单
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ONE, 0, "分享"); // 设置文字与图标
		menu.add(0, TWO, 0, "帮助");
		return super.onCreateOptionsMenu(menu);
	}

	// 点击Menu菜单选项响应事件
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case 1:
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			intent.putExtra(Intent.EXTRA_TEXT, "我正在"
					+ ExhiHomeActivity.exhibition.getmName() + "\n我正在看"
					+ exhibit.getName());
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, "分享"));
			break;
		/*----------------------------------------------------*/
		case 2:
			intent.setClass(this, ExGuideTutorialsActivity.class);
			intent.putExtra("entrance", "card");
			startActivity(intent);
			break;
		/*----------------------------------------------------*/
		default:

		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 将布局里的一些控件初始化
	 */
	private void initalLayout() {
		mInfoChannel = (ViewGroup) main.findViewById(R.id.info);

		mInfoViewPager = (ViewPager) mInfoChannel
				.findViewById(R.id.infoviewpages);

		mPosterChannel = (ViewGroup) main.findViewById(R.id.poster);

		mDotgroup = (ViewGroup) mPosterChannel.findViewById(R.id.dotviewgroup);

		mPoserViewPager = (ViewPager) mPosterChannel
				.findViewById(R.id.posterviewpages);

		mItemHeader = (ViewGroup) mInfoChannel.findViewById(R.id.item_header);

		mPosterProgressBar = (ProgressBar) mPosterChannel
				.findViewById(R.id.posterloading);

		// //在这里胡乱地找到了展会名称
		ViewGroup title = (ViewGroup) main.findViewById(R.id.title);
		mExhibitionName = (TextView) title.findViewById(R.id.exhibitionname);
	}

	private void initaiTitle(String title) {
		mExhibitionName.setText(title);
	}

	/**
	 * 初始化下部产品介绍
	 */
	private void initalInfo() {
		// mInfoChannel = (ViewGroup) main.findViewById(R.id.info);

		/*
		 * 简介与视频的Activity
		 */
		// mInfoViewPager = (ViewPager) mInfoChannel
		// .findViewById(R.id.infoviewpages);

		Intent intent = new Intent();
		intent.setClass(ExhibitActivity.this, ExhiAttributeListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		intent.putExtra("entrance", "card");

		Intent intent2 = new Intent();
		intent2.setClass(ExhibitActivity.this,
				ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent2.putExtra("entrance", "card");

		mInfoPages = new ArrayList<View>();
		mInfoPages.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());

		mInfoPages.add(getLocalActivityManager().startActivity(
				"ExhiVedioSelectListActivity", intent2).getDecorView());

	}

	/*
	 * 初始化上部海报栏 参数是返回的imageview
	 */
	private void UpdatePoster(ArrayList<View> viewList) {

		DotImageViews = new ImageView[viewList.size()]; // 生成小圆点

		// mPosterChannel = (ViewGroup) main.findViewById(R.id.poster);
		//
		// mDotgroup = (ViewGroup)
		// mPosterChannel.findViewById(R.id.dotviewgroup);
		//
		initalDot();// 初始化小圆点
		//
		// mPoserViewPager = (ViewPager) mPosterChannel
		// .findViewById(R.id.posterviewpages);

		mPoserViewPager.setAdapter(new PosterPageAdapter(viewList));

		mPoserViewPager.setOnPageChangeListener(new PosterPageChangeListener(
				DotImageViews));
	}

	/**
	 * 生成上方海报图片
	 * 
	 * @param bitmaps
	 * @return
	 */
	private ArrayList<View> CreatePosterView(ArrayList<Bitmap> bitmaps) {

		ImageView _imageView;
		ArrayList<View> viewList = new ArrayList<View>();
		for (int i = 0; i < bitmaps.size(); i++) {
			View temp = inflater.inflate(R.layout.poster, null);
			_imageView = (ImageView) temp.findViewById(R.id.show);
			_imageView.setImageBitmap(bitmaps.get(i));
			final Bitmap poster = bitmaps.get(i);
			_imageView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog alertDialog = new AlertDialog.Builder(
							ExhibitActivity.this)
							.setTitle("提示")
							.setMessage("是否上传到云存储？")
							.setPositiveButton(
									"确定",
									new android.content.DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											return;
										}
									}).create();
					alertDialog.show();

					return true;
				}
			});
			_imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					create(poster);
					show();

				}
			});
			viewList.add(temp);
		}
		return viewList;
	}

	/**
	 * 初始化换页的小圆点
	 */
	private void initalDot() {
		// 初始化小圆点
		for (int i = 0; i < DotImageViews.length; i++) {
			_imageView = new ImageView(ExhibitActivity.this);
			_imageView.setLayoutParams(new LayoutParams(20, 20));
			_imageView.setPadding(20, 0, 20, 0);
			DotImageViews[i] = _imageView;
			if (i == 0) {
				// 默认选中第一张图片
				DotImageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				DotImageViews[i]
						.setBackgroundResource(R.drawable.page_indicator);
			}
			mDotgroup.addView(DotImageViews[i]);
		}
	}

	/**
	 * 初始化itemheader
	 */
	private void initalItemHeader() {

		mItem = new LinearLayout[mInfoPages.size()];
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.infol);
				mItem[i].setBackgroundColor(Color.rgb(153, 153, 153));
			} else {
				mItem[i] = (LinearLayout) mItemHeader.findViewById(R.id.medial);
				mItem[i].setBackgroundColor(Color.rgb(51, 51, 51));
			}
		}

		mInfoViewPager.setAdapter(new ExhibitsPageAdapter(mInfoPages));
		mInfoViewPager.setOnPageChangeListener(new ExhibitsPageChangeListener(
				mItem));

	}

	/**
	 * 进度条
	 */
	public void setProgressBar(boolean flag) {
		if (!flag) {
			mPosterProgressBar.setVisibility(View.GONE);
		} else {
			mPosterProgressBar.setVisibility(View.VISIBLE);
		}

	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	/**
	 * 1是更新海报
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub

		switch (((Integer) param[1])) {
		case 1: {
			bitmapsList = (ArrayList<Bitmap>) param[0];
			UpdatePoster(CreatePosterView(bitmapsList));
		}
			break;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("FFFFFF", "aiaiaiaiadestroy");
		if (bitmapsList != null) {
			for (int i = 0; i < bitmapsList.size(); i++) {
				if (!bitmapsList.get(i).isRecycled()) {
					bitmapsList.get(i).recycle();
				}
			}
			bitmapsList=null;
			System.gc();
		}
		super.onDestroy();
	}

	/**
	 * 读卡的方法
	 */

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		resolveIntent(intent);

	}

	void resolveIntent(Intent intent) {
		// Parse the intent
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
			// Get an instance of the TAG from the NfcAdapter
			Tag productTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

			MifareClassic mfc = MifareClassic.get(productTag);

			try {
				// Conncet to card
				mfc.connect();
				boolean auth = false;
				auth = mfc.authenticateSectorWithKeyA(0,
						MifareClassic.KEY_DEFAULT);

				if (auth) {
					byte[] data = mfc.readBlock(1);
					char[] cData = TransistionUtil.getChars(data);
					Integer tid = Integer
							.parseInt(String.valueOf(cData).trim()); // 注意要去掉空格。。
					data = mfc.readBlock(2);
					cData = TransistionUtil.getChars(data);
					Integer id = Integer.parseInt(String.valueOf(cData).trim()); // 注意要去掉空格。。
					exhibit = api.getThingOfTemplate(tid, id);
					initaiTitle(exhibit.getName());
					initalInfo();
					initalItemHeader();
					if (exhibit.getpUrls() != null) {
						new DownloadImage(this, 2).execute(exhibit.getpUrls());
					}

				}
			} catch (IOException ex) {
				ex.printStackTrace();
				Toast.makeText(getApplicationContext(), "读卡失败\n请重新刷取卡片",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	private void show() {
		popUpDialog.show();
	}

	private void create(Bitmap poster) {
		popUpDialog = new Dialog(this);
		popUpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		popUpDialog.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		popUpDialog.setContentView(R.layout.image_dialog);
		initImageView(poster);
	}

	private void initImageView(Bitmap poster) {
		ImageView image = (ImageView) popUpDialog.findViewById(R.id.image);
		image.setImageBitmap(poster);
		image.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (popUpDialog != null) {
					popUpDialog.dismiss();
				}
			}
		});
	}
}
