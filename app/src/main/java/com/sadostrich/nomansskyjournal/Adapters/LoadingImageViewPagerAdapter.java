package com.sadostrich.nomansskyjournal.Adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.Views.LoadingImageView;

import java.util.List;

/**
 * Simple ViewPager adapter that handles a list of image URLs and displays each
 * in an <i>LoadingImageView</i>.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 4/26/2016.
 */
public class LoadingImageViewPagerAdapter extends PagerAdapter {

	public interface ILoadingImagePagerAdapterListener {
		void onItemClick(String imageUrl, int position, Bitmap image);
	}

	private List<String> mImageUrls;
	private ILoadingImagePagerAdapterListener mListener;

	public LoadingImageViewPagerAdapter(List<String> imageUrls,
			ILoadingImagePagerAdapterListener listener) {
		mImageUrls = imageUrls;
		mListener = listener;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		LoadingImageView iv = new LoadingImageView(container.getContext());
		iv.loadImageFromUrl(mImageUrls.get(position),
				android.R.drawable.stat_notify_error,
				android.R.drawable.stat_notify_error);
		iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Try getting Bitmap from image view
				Bitmap bitmap;
				try {
					LoadingImageView liv = (LoadingImageView) view;
					bitmap = ((BitmapDrawable) liv.getImageView().getDrawable())
							.getBitmap();

				} catch (Exception e) {
					Log.e("LoadingIVPagerAdapter", "Could not cast image to Bitmap :(");
					bitmap = null;
				}

				// notify listener of item click
				if (mListener != null) {
					mListener.onItemClick(mImageUrls.get(position), position, bitmap);
				}
			}
		});
		container.addView(iv);

		return iv;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LoadingImageView) object);
	}

	@Override
	public int getCount() {
		return mImageUrls.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object obj) {
		return view == obj;
	}

}
