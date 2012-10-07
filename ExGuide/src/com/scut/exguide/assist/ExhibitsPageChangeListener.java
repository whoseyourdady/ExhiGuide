package com.scut.exguide.assist;

import android.graphics.Color;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.widget.LinearLayout;

public class ExhibitsPageChangeListener implements OnPageChangeListener {

	private LinearLayout[] mLinearLayout;// Ð¡Ô²µãµÄview

	public ExhibitsPageChangeListener(LinearLayout[] linearLayout) {
		mLinearLayout = linearLayout;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

//		mLinearLayout[arg0]
//				.setBackgroundResource(R.drawable.page_focus);
		mLinearLayout[arg0]
			.setBackgroundColor(Color.rgb(153, 153, 153));
		for (int i = 0; i < mLinearLayout.length; i++) {			

			if (arg0 != i) {
//				mLinearLayout[i].setBackgroundResource(R.drawable.page_not_focus);
				mLinearLayout[i]
						.setBackgroundColor(Color.rgb(51, 51, 51));
			}
		}
	}

}
