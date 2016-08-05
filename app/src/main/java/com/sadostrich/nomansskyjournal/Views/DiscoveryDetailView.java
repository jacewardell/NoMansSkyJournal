package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Adapters.LoadingImageViewPagerAdapter;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.DiscoveryImage;
import com.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jacobus LaFazia on 8/4/2016.
 */
public class DiscoveryDetailView extends RelativeLayout {

	private RelativeLayout mLayout;
	private TextView mTvName, mTvUser, mTvType, mTvTime, mTvNumViews, mTvDesc, mTvCaption,
			mTvBtnReport;
	private ViewPager mViewPager;
	private CirclePageIndicator mPageIndicator;
	private DynamicLinearLayout mDyLayoutTags;

	private Discovery mDiscovery;
	private LoadingImageViewPagerAdapter.ILoadingImagePagerAdapterListener mImageAdapterListener;

	public DiscoveryDetailView(Context context) {
		super(context);
		init(context);
	}

	public DiscoveryDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public DiscoveryDetailView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		inflate(context, R.layout.view_discovery_detail, this);

		mLayout = (RelativeLayout) findViewById(R.id.layout_root_discovery_detail);
		getViewRefs();

		if (!isInEditMode()) {
			// TODO set icons to TVs

		} else {
			// TODO set dummy image
		}

	}

	private void getViewRefs() {
		mTvName = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvUser = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvType = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvTime = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvNumViews = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvDesc = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvCaption = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvBtnReport = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mViewPager = (ViewPager) mLayout.findViewById(R.id.view_pager_images);
		mPageIndicator = (CirclePageIndicator) mLayout
				.findViewById(R.id.indicator_images);
		mDyLayoutTags = (DynamicLinearLayout) mLayout
				.findViewById(R.id.layout_dynamic_tags);
	}

	private void setupViewPager() {
		List<DiscoveryImage> images = mDiscovery.getImage();

		// Create list of image URLs for the adapter
		List<String> urls = new ArrayList<>();
		for (DiscoveryImage i : images) {
			String url = i.getFileUrl().getWinQuadratic();
			if (url != null && !url.isEmpty()) {
				urls.add(url);
			}
		}

		// Create the ViewPager adapter
		LoadingImageViewPagerAdapter adapter = new LoadingImageViewPagerAdapter(urls,
				mImageAdapterListener);
		mViewPager.setAdapter(adapter);
		mPageIndicator.setViewPager(mViewPager);

		// Hide the page indicator if only 1 image
		if (urls.size() > 1) {
			mPageIndicator.setVisibility(View.VISIBLE);
		} else {
			mPageIndicator.setVisibility(View.GONE);
		}
	}

	//////////////////////////////////////////////////////////////////
	// Public Config Methods
	//////////////////////////////////////////////////////////////////

	public void setImageAdapterListener(
			LoadingImageViewPagerAdapter.ILoadingImagePagerAdapterListener listener) {
		mImageAdapterListener = listener;
	}

	public void setDiscovery(@NonNull Discovery discovery) {
		mDiscovery = discovery;
		// TODO set discovery to views!
	}

}
