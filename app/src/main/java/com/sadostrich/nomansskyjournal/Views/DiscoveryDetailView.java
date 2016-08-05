package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Adapters.LoadingImageViewPagerAdapter;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryDetailView;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.DiscoveryImage;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.IconUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays a <i>Discovery</i> in full page with full details.
 * <p>
 * User Interaction:<br>
 * The 'Discovered by' user name is click-able and should navigate to that
 * user's profile page.<br>
 * An image is click-able and should display the image in a full page view with
 * zoom and panning capabilities.<br>
 * There is a button to 'up vote' the Discovery which should 'up vote' on the
 * server when clicked or show the login dialog first if the current user is not
 * logged in.<br>
 * Finally, there is a button on the bottom right of the page to report the
 * Discovery as inappropriate.
 * </p>
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/4/2016.
 */
public class DiscoveryDetailView extends RelativeLayout implements View.OnClickListener,
		LoadingImageViewPagerAdapter.ILoadingImagePagerAdapterListener {

	private static final String TAG = "DiscoveryDetailView";

	private RelativeLayout mLayout;
	private TextView mTvName, mTvUser, mTvType, mTvTime, mTvNumViews, mTvDesc, mTvCaption,
			mTvBtnReport;
	private ViewPager mViewPager;
	private CirclePageIndicator mPageIndicator;
	private DynamicLinearLayout mDyLayoutTags;

	private Discovery mDiscovery;
	private IDiscoveryDetailView mListener;

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
			setClickListeners();
			setIconsToViews(context);
		}
	}

	private void getViewRefs() {
		mTvName = (TextView) mLayout.findViewById(R.id.tv_discovery_name);
		mTvUser = (TextView) mLayout.findViewById(R.id.tv_discovered_by_user);
		mTvType = (TextView) mLayout.findViewById(R.id.tv_type);
		mTvTime = (TextView) mLayout.findViewById(R.id.tv_time);
		mTvNumViews = (TextView) mLayout.findViewById(R.id.tv_num_views);
		mTvDesc = (TextView) mLayout.findViewById(R.id.tv_discovery_desc);
		mTvCaption = (TextView) mLayout.findViewById(R.id.tv_discovery_caption);
		mTvBtnReport = (TextView) mLayout.findViewById(R.id.tv_btn_report);
		mViewPager = (ViewPager) mLayout.findViewById(R.id.view_pager_images);
		mPageIndicator = (CirclePageIndicator) mLayout
				.findViewById(R.id.indicator_images);
		mDyLayoutTags = (DynamicLinearLayout) mLayout
				.findViewById(R.id.layout_dynamic_tags);
	}

	private void setClickListeners() {
		mTvUser.setOnClickListener(this);
		// TODO click listener for up vote!
		mTvBtnReport.setOnClickListener(this);
	}

	private void setIconsToViews(Context context) {
		// TODO bell icon for the report btn
		Drawable icon = IconUtil.getIcon(context, android.R.drawable.alert_dark_frame,
				R.color.accent, R.dimen.d24);
		mTvBtnReport.setCompoundDrawables(null, null, icon, null);
	}

	private void setupViewPager() {
		List<DiscoveryImage> images = mDiscovery.getImage();

		// Create list of image URLs for the adapter
		List<String> urls = new ArrayList<>();
		for (DiscoveryImage i : images) {
			String url = i.getFileUrl().getFullHd();
			if (url != null && !url.isEmpty()) {
				urls.add(url);
			}
		}

		// Create the ViewPager adapter
		LoadingImageViewPagerAdapter adapter = new LoadingImageViewPagerAdapter(urls,
				this);
		mViewPager.setAdapter(adapter);
		mPageIndicator.setViewPager(mViewPager);

		// Hide the page indicator if only 1 image
		if (urls.size() > 1) {
			mPageIndicator.setVisibility(View.VISIBLE);
		} else {
			mPageIndicator.setVisibility(View.GONE);
		}
	}

	private void setDiscoveryType() {
		int colorRes = 0;
		int imageRes = 0;

		switch (mDiscovery.getType().toLowerCase()) {
		case NMSOriginsServiceHelper.SOLAR_SYSTEM:
			colorRes = R.color.system_purple;
			imageRes = R.drawable.ic_system;
			break;

		case NMSOriginsServiceHelper.STAR:
			colorRes = R.color.star_yellow;
			imageRes = R.drawable.ic_star;
			break;

		case NMSOriginsServiceHelper.PLANET:
			colorRes = R.color.planet_purple;
			imageRes = R.drawable.ic_planet;
			break;

		case NMSOriginsServiceHelper.FAUNA:
			colorRes = R.color.fauna_red;
			imageRes = R.drawable.ic_fauna;
			break;

		case NMSOriginsServiceHelper.FLORA:
			colorRes = R.color.flora_blue;
			imageRes = R.drawable.ic_flora;
			break;

		case NMSOriginsServiceHelper.STRUCTURE:
			colorRes = R.color.structure_green;
			imageRes = R.drawable.ic_structure;
			break;

		case NMSOriginsServiceHelper.ITEM:
			colorRes = R.color.item_red;
			imageRes = R.drawable.ic_item;
			break;

		case NMSOriginsServiceHelper.SHIP:
			colorRes = R.color.ship_gray;
			imageRes = R.drawable.ic_ship;
			break;
		}

		// Type name
		mTvType.setText(mDiscovery.getType());

		// Type color
		if (colorRes == 0) {
			colorRes = android.R.color.black;
		}
		mTvType.setBackgroundColor(getContext().getResources().getColor(colorRes));

		// Type icon
		if (imageRes != 0) {
			Drawable icon = IconUtil.getIcon(getContext(), imageRes, R.color.text_icons,
					R.dimen.d24);
			mTvType.setCompoundDrawables(icon, null, null, null);
		}
	}

	private void setupTagViews() {
		// TODO Tags (in dynamic linear layout)

		final LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		List<String> tags = mDiscovery.getTags();
		int count = tags.size();
		TextView[] tagViews = new TextView[count];
		for (int i = 0; i < count; i++) {
			TextView tv = (TextView) inflater.inflate(R.layout.tv_tag, mDyLayoutTags,
					false);
			tv.setText(tags.get(i));
			tagViews[i] = tv;
		}

		int width = (int) (getContext().getResources().getDimension(R.dimen.d96));
		mDyLayoutTags.setViews(tagViews, width);
	}

	private void setDiscoveryToViews() {
		// Name
		mTvName.setText(mDiscovery.getName());

		// User name
		mTvUser.setText(mDiscovery.getUser().getUsername());

		// Type
		setDiscoveryType();

		// TODO Time since added (amount of time elapsed since now)
		mTvTime.setText(mDiscovery.getDiscoveredAt());

		// Number of views
		String numViews = getContext().getResources().getString(R.string.views)
				.toUpperCase();
		numViews = String.valueOf(mDiscovery.getViews()) + " " + numViews;
		mTvNumViews.setText(numViews);

		// TODO Number of up votes

		// Images
		setupViewPager();

		// Tags
		setupTagViews();

		// Description
		mTvDesc.setText(mDiscovery.getDescription());

		// TODO Caption?
		mTvCaption.setVisibility(View.GONE);
	}

	//////////////////////////////////////////////////////////////////
	// Public Config Methods
	//////////////////////////////////////////////////////////////////

	public void setListener(@Nullable IDiscoveryDetailView listener) {
		mListener = listener;
	}

	public void setDiscovery(@NonNull Discovery discovery) {
		mDiscovery = discovery;
		setDiscoveryToViews();
	}

	//////////////////////////////////////////////////////////////////
	// View.OnClickListener
	//////////////////////////////////////////////////////////////////

	@Override
	public void onClick(View v) {
		if (mListener == null) {
			Log.w(TAG, "@ onClick(): No listener set!");
			return;
		}

		switch (v.getId()) {
		case R.id.tv_discovered_by_user:
			Log.i(TAG, "User name clicked!");
			mListener.onUserClicked(mDiscovery.getUser());
			break;

		// TODO click listener to up vote!

		case R.id.tv_btn_report:
			Log.i(TAG, "Report Inappropriate clicked!");
			mListener.onReportInappropriate(mDiscovery);
			break;
		}
	}

	//////////////////////////////////////////////////////////////////
	// ILoadingImagePagerAdapterListener
	//////////////////////////////////////////////////////////////////

	@Override
	public void onItemClick(String imageUrl, int position, Bitmap image) {
		Log.i(TAG, "@ discovery image clicked with URL: " + imageUrl);

		if (mListener != null) {
			mListener.onImageClicked(image);

		} else {
			Log.w(TAG, "Discovery image clicked but no listener has been set!");
		}
	}

}
