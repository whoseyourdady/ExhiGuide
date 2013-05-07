package com.scut.exguide.ui;

import java.util.ArrayList;

import com.baidu.oauth2.BaiduOAuth;
import com.baidu.oauth2.BaiduOAuthViaDialog;
import com.baidu.oauth2.BaiduOAuthViaDialog.DialogListener;
import com.baidu.pcs.PcsClient;
import com.scut.exguid.multithread.DownloadImage;
import com.scut.exguide.assist.ExhibitsPageAdapter;
import com.scut.exguide.assist.ExhibitsPageChangeListener;
import com.scut.exguide.assist.ListViewAdapter_exhibition;
import com.scut.exguide.assist.MenuListAdapter;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.assist.PosterPageAdapter;
import com.scut.exguide.assist.PosterPageChangeListener;
import com.scut.exguide.entity.Exhibit;
import com.scut.exguide.entity.Exhibition;
import com.scut.exguide.entity.ExhibitionDetail;
import com.scut.exguide.json.ExGuideJSON;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ExhiHomeActivity extends ActivityGroup implements MyActivity {
	// ����Menu�˵�ѡ���ItemId
	final static int ONE = Menu.FIRST;
	final static int TWO = Menu.FIRST + 1;
	final static int THREE = Menu.FIRST + 2;
	final static int FOUR = Menu.FIRST + 3;
	final static int FIVE = Menu.FIRST + 4;
	/*
	 * �ٶ�PCS��֤
	 */
	final static private String APP_KEY = "GqRQpN9bayVt6yIAzrqsUbnU";
	final static private String APP_ROOT = "/apps/չ�ᵼ�α�";
	private PcsClient mPcsClient = null;// �ͻ��˶���
	private BaiduOAuth mBaiduoauth;// �ٶȷ�װ����֤

	public static ExGuideJSON api = new ExGuideJSON();

	private final static String TAG = "ExhiHomeActivity";
	private LayoutInflater inflater;

	private ViewPager mPoserViewPager; // viewpager�ؼ�
	private ArrayList<View> mPosterPages;// ���ڷ�ҳ��view
	private ArrayList<Bitmap> bitmapsList;

	// �²���Ʒ�����е�view
	private ViewPager mInfoViewPager;
	private ArrayList<View> mInfoPages;

	private ImageView _imageView;// ��ʱ����
	private ImageView[] DotImageViews;// СԲ���view

	// ��Ʒ��ҳ����
	private ViewGroup main;
	// �Ϸ��ĺ���
	private ViewGroup mPosterChannel;
	// �����е�СԲ��
	private ViewGroup mDotgroup;
	// �·��Ĳ�Ʒ��Ϣ
	private ViewGroup mInfoChannel;
	// ͼƬ��progressbar
	private ProgressBar mPosterProgressBar;

	// �²���Ʒ�����е�����ͷ
	private ViewGroup mItemHeader;
	private LinearLayout[] mItem;

	// չ�������
	private TextView mExhibitionName;

	private Button menuBtn;
	private View[] children;

	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private MenuListAdapter menuListAdapter;

	private ArrayList<Exhibition> list;
	public static ExhibitionDetail exhibition;
	public static Exhibit exhibit;

	private Dialog popUpDialog;

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
		// setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
		// this.scrollView = (MenuHorizontalScrollView)
		// findViewById(R.id.mScrollView);
		// this.menuListAdapter = new MenuListAdapter(this, 0);
		// this.menuList = (ListView) findViewById(R.id.menuList);
		// this.menuList.setAdapter(menuListAdapter);
		//
		// this.menuBtn = (Button) this.main.findViewById(R.id.menuBtn);
		// this.menuBtn.setOnClickListener(onClickListener);
		//
		// View leftView = new View(this);
		// leftView.setBackgroundColor(Color.TRANSPARENT);
		// children = new View[] { leftView, main };
		// this.scrollView.initViews(children,
		// new com.scut.exguide.assist.SizeCallBackForMenu(this.menuBtn),
		// this.menuList);
		// this.scrollView.setMenuBtn(this.menuBtn);

		setContentView(main);

		initalLayout();
		inialDialog(this).show();
     
	}

	@SuppressWarnings("null")
	protected Dialog inialDialog(final MyActivity Instance) {

		list = api.getExhibitions();

		Dialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ѡ��᳡");
		ListViewAdapter_exhibition adapter = new ListViewAdapter_exhibition(
				this, list);
		DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int which) {
				Toast.makeText(getApplicationContext(),
						list.get(which).getName() + "", Toast.LENGTH_LONG)
						.show();
				Log.d("dd", list.get(which).getId() + "");
				exhibition = api.getExhibitionInfo(list.get(which).getId());
				initaiTitle(exhibition.getmName());
				initalInfo();
				initalItemHeader();
				if (exhibition.getmPosterurl() != null)
					new DownloadImage(Instance, 1).execute(exhibition
							.getmPosterurl());
			}
		};

	
		
		builder.setAdapter(adapter, listener);
		
		dialog = builder.create();

		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					Toast.makeText(getApplicationContext(),
							"��ѡ�������������ĳ���"+ "", Toast.LENGTH_LONG)
							.show();
					return true;
				} else {
					return false; // Ĭ�Ϸ��� false
				}
			}
		});
		
		return dialog;
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
			Log.d("aaa", MenuHorizontalScrollView.menuOut + "");
			if (!MenuHorizontalScrollView.menuOut) {
				main.setEnabled(false);
			} else
				main.setEnabled(true);

		}
	};

	public MenuHorizontalScrollView getScrollView() {
		return scrollView;
	}

	public void setScrollView(MenuHorizontalScrollView scrollView) {
		this.scrollView = scrollView;
	}

	// ����Menu�˵�
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ONE, 0, "�󶨰ٶ�"); // ����������ͼ��
		menu.add(0, TWO, 0, "����");
		menu.add(0, THREE, 0, "��ѡ�᳡");
		menu.add(0, FOUR, 0, "����");
		menu.add(0, FIVE, 0, "�˳�");
		return super.onCreateOptionsMenu(menu);
	}

	// ���Menu�˵�ѡ����Ӧ�¼�
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case 1:
			this.getBaiduOAuth();
			break;
		/*----------------------------------------------------*/
		case 2:
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "����");
			intent.putExtra(Intent.EXTRA_TEXT,
					"~~~~~~~~~����" + exhibition.getmName());
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, "����"));
			break;
		/*----------------------------------------------------*/
		case 3:
			inialDialog(this).show();
			break;
		case 4:
			intent.setClass(this, ExGuideTutorialsActivity.class);
			startActivity(intent);
			break;
		case 5:
			AlertDialog alertDialog = new AlertDialog.Builder(this)
					.setTitle("��ʾ")
					.setMessage("�Ƿ��˳�������")
					.setPositiveButton(
							"ȷ��",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									return;
								}
							}).create();
			alertDialog.show();
			break;
		default:

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog alertDialog = new AlertDialog.Builder(this)
					.setTitle("��ʾ")
					.setMessage("�Ƿ��˳�������")
					.setPositiveButton(
							"ȷ��",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									return;
								}
							}).create();
			alertDialog.show();
		}
		return false;
	}

	/**
	 * ���������һЩ�ؼ���ʼ��
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

		// //��������ҵ��ҵ���չ������
		ViewGroup title = (ViewGroup) main.findViewById(R.id.title);
		mExhibitionName = (TextView) title.findViewById(R.id.exhibitionname);
	}

	private void initaiTitle(String title) {
		mExhibitionName.setText(title);
	}

	/**
	 * ��ʼ���²���Ʒ����
	 */
	private void initalInfo() {
		// mInfoChannel = (ViewGroup) main.findViewById(R.id.info);

		/*
		 * �������Ƶ��Activity
		 */
		// mInfoViewPager = (ViewPager) mInfoChannel
		// .findViewById(R.id.infoviewpages);

		Intent intent = new Intent();
		intent.setClass(ExhiHomeActivity.this, ExhiAttributeListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("entrance", "home");

		Intent intent2 = new Intent();
		intent2.setClass(ExhiHomeActivity.this,
				ExhiVedioSelectListActivity.class);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent2.putExtra("entrance", "home");

		mInfoPages = new ArrayList<View>();
		mInfoPages.add(getLocalActivityManager().startActivity(
				"ExhiAttributeListActivity", intent).getDecorView());
		((ExhiAttributeListActivity) getLocalActivityManager().getActivity(
				"ExhiAttributeListActivity")).Update(exhibition);

		mInfoPages.add(getLocalActivityManager().startActivity(
				"ExhiVedioSelectListActivity", intent2).getDecorView());
		((ExhiVedioSelectListActivity) getLocalActivityManager().getActivity(
				"ExhiVedioSelectListActivity")).Update(exhibition);

	}

	/*
	 * ��ʼ���ϲ������� �����Ƿ��ص�imageview
	 */
	private void UpdatePoster(ArrayList<View> viewList) {

		DotImageViews = new ImageView[viewList.size()]; // ����СԲ��

		// mPosterChannel = (ViewGroup) main.findViewById(R.id.poster);
		//
		// mDotgroup = (ViewGroup)
		// mPosterChannel.findViewById(R.id.dotviewgroup);
		//
		initalDot();// ��ʼ��СԲ��
		//
		// mPoserViewPager = (ViewPager) mPosterChannel
		// .findViewById(R.id.posterviewpages);

		mPoserViewPager.setAdapter(new PosterPageAdapter(viewList));

		mPoserViewPager.setOnPageChangeListener(new PosterPageChangeListener(
				DotImageViews));
	}

	/**
	 * �����Ϸ�����ͼƬ
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
			final Bitmap poster = bitmaps.get(i);
			_imageView.setImageBitmap(poster);
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
	 * ��ʼ����ҳ��СԲ��
	 */
	private void initalDot() {
		// ��ʼ��СԲ��
		mDotgroup.removeAllViews();
		for (int i = 0; i < DotImageViews.length; i++) {
			_imageView = new ImageView(ExhiHomeActivity.this);
			_imageView.setLayoutParams(new LayoutParams(20, 20));
			_imageView.setPadding(20, 0, 20, 0);
			DotImageViews[i] = _imageView;
			if (i == 0) {
				// Ĭ��ѡ�е�һ��ͼƬ
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
	 * ��ʼ��itemheader
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
	 * ������
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
	 * 1�Ǹ��º���
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

	/*
	 * �ٶ�PCS��֤����
	 */
	public void getBaiduOAuth() {
		mBaiduoauth = new BaiduOAuthViaDialog(APP_KEY);

		mBaiduoauth.startDialogAuth(ExhiHomeActivity.this, new String[] {
				"basic", "netdisk" }, new DialogListener() {

			@Override
			public void onCancel() {
				Toast.makeText(getApplicationContext(), "�û�ȡ���ٶ�PCS��",
						Toast.LENGTH_LONG).show();
			}

			/*
			 * ���access_token (non-Javadoc)
			 * 
			 * @see
			 * com.baidu.oauth2.BaiduOAuthViaDialog.DialogListener#onComplete
			 * (android.os.Bundle)
			 */
			@Override
			public void onComplete(Bundle values) {
				String accessToken = values.getString("access_token");
				Toast.makeText(getApplicationContext(), "��ɰ�",
						Toast.LENGTH_LONG).show();

			}

			@Override
			public void onException(String msg) {
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
						.show();
			}
		});
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
