package com.scut.exguide.ui;

import com.scut.exguide.assist.Exhibit_attribute;
import com.scut.exguide.assist.Exhibit_video;
import com.scut.exguide.assist.Exhibition_attribute;
import com.scut.exguide.assist.Exhibition_video;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.entity.ExhibitionDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ListView;

public class ExhiVedioSelectListActivity extends Activity implements MyActivity {

	private ListView listviewVideo;// ÊÓÆµµÄlistview
	private ExhibitionDetail exhibition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.listview);

		listviewVideo = (ListView) findViewById(R.id.roundlistview02);
		Intent intent = getIntent();
		String entrance = intent.getStringExtra("entrance");
		listviewVideo = (ListView) findViewById(R.id.roundlistview02);
		if (entrance.equals("home")) {
			
		}
		if (entrance.equals("card")) {
			String[] names = ExhibitActivity.exhibit.getvNames();
			String[] urls = ExhibitActivity.exhibit.getvUrls();
			if (names != null && urls != null) {
				Exhibit_video videoAdapter = new Exhibit_video(this, names,
						urls);
				listviewVideo.setAdapter(videoAdapter);
			}
		}
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Object... param) {
		// TODO Auto-generated method stub
		exhibition=(ExhibitionDetail)param[0];
		String[] names = exhibition.getmVedioname();
		String[] urls = exhibition.getmVediourl();
		if (names != null && urls != null) {
			Exhibition_video videoAdapter = new Exhibition_video(this, urls,
					names);
			listviewVideo.setAdapter(videoAdapter);
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
	
		return false;
	}

}
