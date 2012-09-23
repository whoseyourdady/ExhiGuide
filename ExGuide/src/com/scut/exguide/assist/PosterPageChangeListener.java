package com.scut.exguide.assist;

import com.scut.exguide.ui.R;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class PosterPageChangeListener implements OnPageChangeListener {

	private ImageView[] mImageViews;// Ð¡Ô²µãµÄview
	
	public PosterPageChangeListener(ImageView[] imageViews ) {
		mImageViews = imageViews;
	}
	
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mImageViews.length; i++) {
			mImageViews[arg0]
					.setBackgroundResource(R.drawable.page_indicator_focused);

			if (arg0 != i) {
				mImageViews[i].setBackgroundResource(R.drawable.page_indicator);
			}
		}
	}

}
