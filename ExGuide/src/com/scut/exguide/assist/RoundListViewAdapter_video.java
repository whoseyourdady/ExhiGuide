package com.scut.exguide.assist;


import com.scut.exguide.ui.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class RoundListViewAdapter_video extends BaseAdapter implements
		ListAdapter {

	final String[] strs = { "一汽大众新宝来" };
	
	final String[] strs1 = { "《古墓丽影9》" };
	
	final String[] strs2 = { "深圳蔓诺视觉摄影" };
	

	final String[] strs3 = { "2012北京国际汽车展览会" };
	

	final String[] strs4 = { "2012东京游戏展" };
	

	final String[] strs5 = { "2012中国婚博会" };
	
	final int[] images={R.drawable.bei,R.drawable.hun,R.drawable.you,R.drawable.gu};

	private Context context;
	private LayoutInflater inflater;

	public RoundListViewAdapter_video(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.item_video, null);
		final View ll = convertView;
		TextView tv = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist01_textview);
		tv.setText(strs5[position]);
		
		ImageView iv = (ImageView) convertView
				.findViewById(R.id.video_image);
		iv.setImageResource(images[1]);
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				// 颜色
				switch (position) {
				case 0:
					ll.setBackgroundResource(R.drawable.app_list_corner_round_top);
					break;

				default:
					if (position == strs.length - 1) {
						ll.setBackgroundResource(R.drawable.app_list_corner_round_bottom);
					} else {
						ll.setBackgroundResource(R.drawable.app_list_corner_shape);
					}
				}

				try {
					context.startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		return convertView;
	}

}
