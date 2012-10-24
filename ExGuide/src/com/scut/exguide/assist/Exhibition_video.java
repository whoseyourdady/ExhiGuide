package com.scut.exguide.assist;

import com.scut.exguide.ui.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class Exhibition_video extends BaseAdapter implements
		ListAdapter {



	private Context context;
	private LayoutInflater inflater;
	private String[] urls;
	private String[] names;

	public Exhibition_video(Context context,String[] urls,String[] names) {
		this.context = context;
		this.urls=urls;
		this.names=names;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return urls.length;
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
		tv.setText(names[position]);

		ImageView iv = (ImageView) convertView.findViewById(R.id.video_image);

		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alertDialog = new AlertDialog.Builder(context)
				.setTitle("提示")
				.setMessage("是否播放视频?")
				.setPositiveButton(
						"确定",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Uri uri = Uri.parse(urls[position]);
								Intent intent = new Intent(Intent.ACTION_VIEW,uri);
								intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								intent.setType("video/*");
								intent.setDataAndType(uri , "video/*");
							    context.startActivity(intent);
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
			    

			}
		});
		ImageView arrow = (ImageView) convertView
				.findViewById(R.id.arrow_image);

		arrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alertDialog = new AlertDialog.Builder(context)
						.setTitle("提示")
						.setMessage("是否上传到云存储？")
						.setPositiveButton(
								"确定",
								new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
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
			}
		});
		// convertView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent();
		//
		// // 颜色
		// ll.setBackgroundResource(R.drawable.app_list_corner_shape);
		//
		// try {
		// context.startActivity(intent);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// });

		return convertView;
	}

}
