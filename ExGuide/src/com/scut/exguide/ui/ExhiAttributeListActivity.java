package com.scut.exguide.ui;

import com.scut.exguide.assist.RoundListView;
import com.scut.exguide.assist.RoundListViewAdapter_attribute;
import com.scut.exguide.assist.ScrollExpandListViewUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class ExhiAttributeListActivity extends Activity {

	private ListView listviewAttribute;// ÊôÐÔµÄlistview

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.listview);

		RoundListViewAdapter_attribute attributeAdapter = new RoundListViewAdapter_attribute(
				this);

		listviewAttribute = (RoundListView) findViewById(R.id.roundlistview02);
		listviewAttribute.setAdapter(attributeAdapter);
		ScrollExpandListViewUtil
				.setListViewHeightBasedOnChildren(listviewAttribute);
	}

}
