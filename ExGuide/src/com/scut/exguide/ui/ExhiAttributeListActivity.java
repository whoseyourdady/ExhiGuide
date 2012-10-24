package com.scut.exguide.ui;

import java.util.ArrayList;

import com.scut.exguide.assist.Exhibition_attribute;
import com.scut.exguide.assist.Exhibit_attribute;
import com.scut.exguide.assist.MyActivity;
import com.scut.exguide.entity.Exhibit;
import com.scut.exguide.entity.Exhibition;
import com.scut.exguide.entity.ExhibitionDetail;
import com.scut.exguide.json.ExGuideJSON;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class ExhiAttributeListActivity extends Activity implements MyActivity {

	private ListView listviewAttribute;// 属性的listview
	private ExhibitionDetail exhibition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.listview);
		Intent intent = getIntent();
		String entrance = intent.getStringExtra("entrance");
		listviewAttribute = (ListView) findViewById(R.id.roundlistview02);
		if (entrance.equals("home")) {
			
		}
		if (entrance.equals("card")) {
			String[] values = ExhibitActivity.exhibit.getAttributeValues();
			String[] names = ExhibitActivity.exhibit.getAttributeNames();
			if (names != null && values != null) {
				ArrayList<String> nameList = new ArrayList<String>();
				ArrayList<String> valueList = new ArrayList<String>();
				for (int i = 0; i < values.length; i++) {
					valueList.add(values[i]);
					nameList.add(names[i]);
				}
				if (ExhibitActivity.exhibit.getAttachmenturls().length != 0) {
					String[] attachments = ExhibitActivity.exhibit
							.getAttachmenturls();
					for (int i = 0; i < attachments.length; i++) {
						valueList.add(attachments[i]);
						nameList.add("附件" + (i + 1));
					}
				}

				Exhibit_attribute attributeAdapter = new Exhibit_attribute(
						this, nameList, valueList);
				listviewAttribute.setAdapter(attributeAdapter);
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
		String[] values = new String[1];
		exhibition=(ExhibitionDetail)param[0];
		values[0] = exhibition.getmDescription();

		final String[] keys = { "展会描述" };
		if (keys != null && values != null) {
			Exhibition_attribute attributeAdapter = new Exhibition_attribute(
					this, keys, values);
			listviewAttribute.setAdapter(attributeAdapter);
		}
	}

}
