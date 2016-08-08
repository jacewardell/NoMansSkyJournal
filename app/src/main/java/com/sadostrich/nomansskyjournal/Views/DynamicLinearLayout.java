package com.sadostrich.nomansskyjournal.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.R;

/**
 * A <i>LinearLayout</i> that adds views dynamically in horizontal orientation
 * and any views that do not fit in the horizontal space will be added in a new
 * row below the first row of views.
 * <p/>
 * <p/>
 * Created by Jacobus LaFazia on 11/19/2015.
 */
public class DynamicLinearLayout extends LinearLayout {

	private static final String TAG = "DynamicLinearLayout";

	protected AttributeSet mAttributeSet;

	private LinearLayout mLayoutRoot;
	private TextView[] mViews;
	private int mWidth;
	// private int mDefWidth;

	public DynamicLinearLayout(Context context) {
		super(context);
		init(context);
	}

	public DynamicLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mAttributeSet = attrs;
		init(context);
	}

	public DynamicLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mAttributeSet = attrs;
		init(context);
	}

	private void init(Context context) {
		final LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.linear_layout_dynamic, this);

		mLayoutRoot = (LinearLayout) findViewById(R.id.dynamic_linear_layout_root);
		mLayoutRoot.getViewTreeObserver()
				.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

					@SuppressWarnings("deprecation")
					@SuppressLint("NewApi")
					@Override
					public void onGlobalLayout() {
						mWidth = mLayoutRoot.getMeasuredWidth()
								- mLayoutRoot.getPaddingLeft()
								- mLayoutRoot.getPaddingRight();

						// Add views to layouts
						if (mViews == null) {
							Log.e(TAG, "@ onGlobalLayout(): Views[] is NULL!");
						} else {
							addViews();
						}

						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
							mLayoutRoot.getViewTreeObserver()
									.removeOnGlobalLayoutListener(this);

						} else {
							mLayoutRoot.getViewTreeObserver()
									.removeGlobalOnLayoutListener(this);
						}
					}
				});

	}

	// /**
	// * Sets the array of views that will be added when this view is in layout.
	// * <p>
	// * NOTE: For this layout to work correctly, all views must have a static
	// * width & height set to their layout params.
	// * </p>
	// *
	// * @param views
	// * The array of views to add to this layout.
	// * @param defaultWidth
	// * The default width to use for the views in case there is a
	// * problem getting the view's layout params.
	// */
	// public void setViews(View[] views, int defaultWidth) {
	// mViews = views;
	// mDefWidth = defaultWidth;
	// }

	public void setViews(TextView[] views) {
		mViews = views;
	}

	private void addViews() {
		// Row 1
		LinearLayout row = new LinearLayout(getContext());
		LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		row.setLayoutParams(rowParams);
		row.setGravity(Gravity.START);

		int curWidth = 0;
		Rect bounds = new Rect();
		TextView tv = new TextView(getContext());
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		tv.setLayoutParams(params);
		Paint textPaint = tv.getPaint();

		for (TextView view : mViews) {
			// Check if adding this view to current row will exceed width.

			// Check text bounds for current text
			String text = view.getText().toString();
			textPaint.getTextBounds(text, 0, text.length(), bounds);
			bounds.left -= view.getPaddingStart();
			bounds.right += view.getPaddingEnd();

			if (curWidth + bounds.width() > mWidth) {
				Log.e(TAG, "@ addViews(): row.getWidth() > mWidth");

				// Add current row to layout.
				mLayoutRoot.addView(row);

				// Create new row for this view.
				row = new LinearLayout(getContext());
				row.setLayoutParams(rowParams);
				row.setGravity(Gravity.START);

				// Add view to new row
				row.addView(view);

				// Reset row width
				curWidth = bounds.width();

			} else {
				// Add view to current row layout
				row.addView(view);
				curWidth += bounds.width();
			}
		}

		// Add last row to layout
		mLayoutRoot.addView(row);
	}

	// private void addViews() {
	// // Row 1
	// LinearLayout row = new LinearLayout(getContext());
	// LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
	// LinearLayout.LayoutParams.MATCH_PARENT,
	// LinearLayout.LayoutParams.WRAP_CONTENT);
	// row.setLayoutParams(rowParams);
	// row.setGravity(Gravity.CENTER);
	//
	// int viewsAdded = 0;
	// for (View view : mViews) {
	// // Check if adding this view to current row will exceed width.
	// int count = viewsAdded + 1;
	//
	// int newWidth;
	// if (view.getLayoutParams() != null) {
	// Log.d(TAG, "@ addViews(): Using view's layout params width "
	// + view.getLayoutParams().width);
	// newWidth = count * (view.getLayoutParams().width);
	//
	// } else {
	// Log.d(TAG, "@ addViews(): Using default width " + mDefWidth);
	// // Use default width.
	// newWidth = count * mDefWidth;
	// }
	//
	// if (newWidth > mWidth) {
	// // Add current row to layout.
	// mLayoutRoot.addView(row);
	//
	// // Create new row for this view.
	// row = new LinearLayout(getContext());
	// row.setLayoutParams(rowParams);
	// row.setGravity(Gravity.CENTER);
	//
	// // Add view to new row
	// row.addView(view);
	//
	// // Reset row view count
	// viewsAdded = 1;
	//
	// } else {
	// // Add view to current row layout
	// row.addView(view);
	// viewsAdded++;
	// }
	// }
	//
	// // Add last row to layout
	// mLayoutRoot.addView(row);
	// }

}
