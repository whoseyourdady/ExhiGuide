package com.scut.exguide.ui;

import com.scut.exguide.assist.RoundListView;
import com.scut.exguide.assist.RoundListViewAdapter_video;
import com.scut.exguide.assist.ScrollExpandListViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class ExhiVedioSelectListActivity extends Activity {

	private ListView listviewVideo;// ÊÓÆµµÄlistview

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.listview);
		
		RoundListViewAdapter_video viedoAdapter = new RoundListViewAdapter_video(
				this);

		listviewVideo = (RoundListView) findViewById(R.id.roundlistview02);
		listviewVideo.setAdapter(viedoAdapter);
		ScrollExpandListViewUtil
				.setListViewHeightBasedOnChildren(listviewVideo);
	}

}
