package com.scut.exguide.assist;

import java.util.ArrayList;

import com.scut.exguide.entity.Exhibition;
import com.scut.exguide.ui.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListViewAdapter_exhibition extends BaseAdapter implements
		ListAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Exhibition> list;

	public ListViewAdapter_exhibition(Context context,
			ArrayList<Exhibition> list) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		convertView = inflater.inflate(R.layout.item_exhibition, null);
		final View ll = convertView;
		TextView exhibition = (TextView) convertView
				.findViewById(R.id.exhibition_name_text);
		TextView province = (TextView) convertView
				.findViewById(R.id.province_text);
		TextView city = (TextView) convertView.findViewById(R.id.city_text);
		TextView hall = (TextView) convertView.findViewById(R.id.hall_text);

		exhibition.setText(list.get(position).getName());
		province.setText(list.get(position).getProvince());
		city.setText(list.get(position).getCity());
		hall.setText(list.get(position).getHall());

//		convertView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//
//				// ÑÕÉ«
//				ll.setBackgroundResource(R.drawable.app_list_corner_shape);
//
//				try {
//					context.startActivity(intent);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//		});

		return convertView;
	}
}
