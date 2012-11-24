package com.scut.exguide.assist;

import java.util.ArrayList;

import com.scut.exguide.ui.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class Exhibit_attribute extends BaseAdapter implements ListAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<String> names;
	private ArrayList<String> values;

	public Exhibit_attribute(Context context, ArrayList<String> nameList,
			ArrayList<String> valueList) {
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

		TextView tv2 = (TextView) convertView
				.findViewById(R.id.navigation_more_checklist02_textview);

		tv.setText(names.get(position));
		if (names.get(position).equals("附件")) {
			String[] data = values.get(position).split("\n");
			SpannableString ss = new SpannableString(data[0]);
			ss.setSpan(new URLSpan(data[1]), 0, data[0].length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv2.setText(ss);
			// 设置TextView可点击
			tv2.setMovementMethod(LinkMovementMethod.getInstance());
			tv2.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					AlertDialog alertDialog = new AlertDialog.Builder(context)
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

					return false;
				}
			});
		} else
			tv2.setText(values.get(position));

		return convertView;
	}

}
