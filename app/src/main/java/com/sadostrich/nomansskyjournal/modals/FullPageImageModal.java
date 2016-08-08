package com.sadostrich.nomansskyjournal.modals;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.TouchImageView;

/**
 * This class extends {@linkplain DialogFragment} to create a modal view that is
 * full screen and hides the device action bar.
 * <p>
 * Contains a single {@linkplain TouchImageView} for viewing an image full
 * screen with zoom and panning capabilities.
 * </p>
 *
 * <p/>
 * Created by Jacobus LaFazia on 3/13/2014.
 */
public class FullPageImageModal extends DialogFragment {

	private static final String TAG = "FullPageImageModal";
	/**
	 * (Required) Bitmap of image to display.
	 * <p/>
	 * Data Type: <var>Parcelable</var>
	 */
	public static final String BUNDLE_KEY_IMAGE_BITMAP = "bundle_image_bitmap";
	/**
	 * (Optional) Max zoom scale in range [ 0F , 4F ] where 0 = no zoom, 1 = 1x
	 * zoom etc.
	 * <p/>
	 * Data Type: <var>Float</var>
	 */
	public static final String BUNDLE_KEY_MAX_ZOOM = "bundle_max_zoom";
	/**
	 * (Optional) Min zoom scale in range [ 1F , 4F ] where 1 = 1x zoom etc.
	 * <p/>
	 * Data Type: <var>Float</var>
	 */
	public static final String BUNDLE_KEY_MIN_ZOOM = "bundle_min_zoom";

	private Bitmap mBitmap;
	private float mMinScale = -1F;
	private float mMaxScale = -1F;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Allow Portrait and Landscape orientations
		Activity a = getActivity();
		if (a != null) {
			Log.i(TAG, "@ onCreate(): Requesting Orientation -> FULL_SENSOR...");
			a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		}

		// Set style using custom style that defines 'full screen'
		setStyle(DialogFragment.STYLE_NORMAL, R.style.ImageFullScreen);
	}

	@Override
	public void onStart() {
		super.onStart();

		// Set layout params of the (implied) Dialog within the DialogFragment
		// to full screen.
		Dialog d = getDialog();
		if (d != null) {
			int width = ViewGroup.LayoutParams.MATCH_PARENT;
			int height = ViewGroup.LayoutParams.MATCH_PARENT;
			d.getWindow().setLayout(width, height);
		}
	}

	private void handleBundleData(Bundle bundle) {
		mBitmap = bundle.getParcelable(BUNDLE_KEY_IMAGE_BITMAP);
		mMaxScale = bundle.getFloat(BUNDLE_KEY_MAX_ZOOM, -1F);
		mMinScale = bundle.getFloat(BUNDLE_KEY_MIN_ZOOM, -1F);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate layout, get TouchImageView
		View layout = inflater.inflate(R.layout.fragment_image, container, false);
		TouchImageView imageView = (TouchImageView) layout
				.findViewById(R.id.touch_image_view_full_screen);

		// If reloading from savedInstanceState
		if (savedInstanceState != null) {
			// Load bitmap from bundle
			handleBundleData(savedInstanceState);

		} else {
			// Get Bitmap from arguments (if any)
			Bundle bundle = getArguments();
			if (bundle != null) {
				handleBundleData(bundle);
			}
		}

		if (mBitmap == null) {
			throw new IllegalStateException(
					"FullPageImageModal MUST be given a Bitmap as an argument!");
		}

		// Set Bitmap to View
		Log.d(TAG, "@ onCreateView(): Setting Bitmap to TouchImageView...");
		imageView.setImageBitmap(mBitmap);
		if (mMinScale >= 1F && mMinScale <= 4F) {
			Log.d(TAG, "@ onCreateView(): Setting Custom MIN scale to <" + mMinScale
					+ ">...");
			imageView.setMinZoom(mMinScale);
		}
		if (mMaxScale >= 0F && mMaxScale <= 4F) {
			Log.d(TAG, "@ onCreateView(): Setting Custom MAX scale to <" + mMaxScale
					+ ">...");
			imageView.setMaxZoom(mMaxScale);
		}

		return layout;
	}

	/**
	 * Override this method to allow this fragment to support screen orientation
	 * changing and both modes of Portrait and Landscape.<br>
	 * Current testing on API 4.1 shows that this method is not called.<br>
	 * Set orientation in onCreate().
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser) {
			// Allow Portrait and Landscape orientations
			Activity a = getActivity();
			if (a != null) {
				Log.i(TAG,
						"@ setUserVisibleHint(): Requesting Orientation -> FULL_SENSOR...");
				a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save image bitmap & custom zoom scales
		outState.putParcelable(BUNDLE_KEY_IMAGE_BITMAP, mBitmap);
		outState.putFloat(BUNDLE_KEY_MIN_ZOOM, mMinScale);
		outState.putFloat(BUNDLE_KEY_MAX_ZOOM, mMaxScale);
	}

	@Override
	public void onPause() {
		super.onPause();

		// Set orientation of activity back to portrait
		Activity a = getActivity();
		if (a != null) {
			Log.i(TAG, "@ onPause(): Requesting Orientation -> PORTRAIT...");
			a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		// Allow Portrait and Landscape orientations
		Activity a = getActivity();
		if (a != null) {
			Log.i(TAG, "@ onResume(): Requesting Orientation -> FULL_SENSOR...");
			a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
		}
	}

}
