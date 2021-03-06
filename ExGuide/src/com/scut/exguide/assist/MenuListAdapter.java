package com.scut.exguide.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.scut.exguide.ui.ExGuideTutorialsActivity;
import com.scut.exguide.ui.ExhiHomeActivity;
import com.scut.exguide.ui.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * MenuList的�?配器
 * 
 * @author acbuwa
 * 
 */
public class MenuListAdapter extends BaseAdapter {

	private Activity context;
	private List<Map<String, Object>> listItems;
	private int itemCount;
	private LayoutInflater listInflater;
	private boolean isPressed[];
	private int imageId = R.drawable.star_icon;
	private final int COUNT = 6;
	private int pressedId;

	/* �?��menu item中包含一个imageView和一个TextView */
	public final class ListItemsView {
		public ImageView menuIcon;
		public TextView menuText;
	}

	public MenuListAdapter(Activity context, int pressedId) {
		// TODO Auto-generated constructor stub
		// switch(pressedId){
		// case 0:
		// this.context = (ACBUWAPageActivity)context;
		// case 1:
		// this.context = (CAROLPageActivity)context;
		// }
		this.context = context;
		this.pressedId = pressedId;
		this.init();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.itemCount;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int po = position;
		ListItemsView listItemsView;
		if (convertView == null) {
			listItemsView = new ListItemsView();
			convertView = this.listInflater.inflate(R.layout.menu_list_item,
					null);
			listItemsView.menuIcon = (ImageView) convertView
					.findViewById(R.id.menuIcon);
			listItemsView.menuText = (TextView) convertView
					.findViewById(R.id.menuText);
			convertView.setTag(listItemsView);
		} else {
			listItemsView = (ListItemsView) convertView.getTag();
		}

		listItemsView.menuIcon.setBackgroundResource((Integer) listItems.get(
				position).get("menuIcon"));
		listItemsView.menuText.setText((String) listItems.get(position).get(
				"menuText"));

		if (this.isPressed[position] == true)
			convertView.setBackgroundResource(R.drawable.menu_item_bg_sel);
		else
			convertView.setBackgroundColor(Color.TRANSPARENT);

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				changeState(po);
				gotoActivity(po);
				notifyDataSetInvalidated();
				new Handler().post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

					}

				});
			}
		});

		return convertView;
	}

	private void gotoActivity(int position) {
		Intent intent = new Intent();
		switch (position) {
		case 0:
			((ExhiHomeActivity) context).getBaiduOAuth();
			break;
		/*----------------------------------------------------*/
		case 1:
			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			intent.putExtra(Intent.EXTRA_TEXT, "展会宝");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(Intent.createChooser(intent, "分享"));
			break;
		/*----------------------------------------------------*/
		case 4:
			intent.setClass(context, ExGuideTutorialsActivity.class);
			context.startActivity(intent);
			break;
		case 5:
			AlertDialog alertDialog = new AlertDialog.Builder(context)
					.setTitle("提示")
					.setMessage("是否退出本程序")
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									context.finish();
								}
							})
					.setNegativeButton("取消",
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
	}

	private void changeState(int position) {

		for (int i = 0; i < this.itemCount; i++) {
			isPressed[i] = false;
		}
		isPressed[position] = true;
	}

	private void init() {

		this.itemCount = this.COUNT;
		this.listItems = new ArrayList<Map<String, Object>>();
		this.isPressed = new boolean[this.itemCount];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuIcon", imageId);
		map.put("menuText", "绑定百度云");
		this.listItems.add(map);
		map = new HashMap<String, Object>();
		map.put("menuIcon", imageId);
		map.put("menuText", "分享");
		this.listItems.add(map);
		map = new HashMap<String, Object>();
		map.put("menuIcon", imageId);
		map.put("menuText", "收藏");
		this.listItems.add(map);
		map = new HashMap<String, Object>();
		map.put("menuIcon", imageId);
		map.put("menuText", "设置");
		this.listItems.add(map);
		map = new HashMap<String, Object>();
		map.put("menuIcon", imageId);
		map.put("menuText", "帮助");
		this.listItems.add(map);
		map = new HashMap<String, Object>();
		map.put("menuIcon", imageId);
		map.put("menuText", "退出");
		this.listItems.add(map);
		for (int i = 0; i < this.itemCount; i++) {
			this.isPressed[i] = false;
		}

		this.isPressed[this.pressedId] = true;
		this.listInflater = LayoutInflater.from(context);
	}
}
