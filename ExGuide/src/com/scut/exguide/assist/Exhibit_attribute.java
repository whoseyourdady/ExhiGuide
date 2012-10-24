package com.scut.exguide.assist;

import java.util.ArrayList;

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

public class Exhibit_attribute extends BaseAdapter implements ListAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<String> names;
	private ArrayList<String> values;

	public Exhibit_attribute(Context context, ArrayList<String> nameList, ArrayList<String> valueList) {
		this.context = context;
		this.names = nameList;
		this.values = valueList;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.size();
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
		convertView = inflater.inflate(R.layout.item_attribute, null);
		final View ll = convertView;
		TextView tv = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist01_textview);
		tv.setText(names.get(position));

		TextView tv2 = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist02_textview);
		tv2.setText(values.get(position));

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				// ÑÕÉ«
				ll.setBackgroundResource(R.drawable.app_list_corner_shape);

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
