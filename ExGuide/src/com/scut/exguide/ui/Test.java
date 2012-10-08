package com.scut.exguide.ui;

import com.baidu.oauth2.BaiduOAuth;
import com.baidu.oauth2.BaiduOAuthViaDialog;
import com.baidu.oauth2.BaiduOAuthViaDialog.DialogListener;
import com.baidu.pcs.PcsClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Test extends Activity {

	final static private String APP_KEY = "GqRQpN9bayVt6yIAzrqsUbnU";
	final static private String APP_ROOT = "/apps/展会导游宝";
	private PcsClient mPcsClient = null;// 客户端对象
	private BaiduOAuth mBaiduoauth;// 百度封装的验证
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		Button mb = (Button) findViewById(R.id.testB);
		mb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getBaiduOAuth();
			}
		});
		
	}
	
	private void getBaiduOAuth() {
		mBaiduoauth = new BaiduOAuthViaDialog(APP_KEY);

				mBaiduoauth.startDialogAuth(Test.this, new String[] {
						"basic", "netdisk" }, new DialogListener() {

					@Override
					public void onCancel() {
						Toast.makeText(getApplicationContext(),
								"User  cancel the request", Toast.LENGTH_LONG)
								.show();
					}

					/*
					 * 获得access_token
					 * (non-Javadoc)
					 * @see com.baidu.oauth2.BaiduOAuthViaDialog.DialogListener#onComplete(android.os.Bundle)
					 */
					@Override
					public void onComplete(Bundle values) {
						String accessToken = values.getString("access_token");
						Toast.makeText(getApplicationContext(), accessToken,
								Toast.LENGTH_LONG).show();						
		
					}

					@Override
					public void onException(String msg) {
						Toast.makeText(getApplicationContext(), msg,
								Toast.LENGTH_LONG).show();
					}
				});
			}

}
