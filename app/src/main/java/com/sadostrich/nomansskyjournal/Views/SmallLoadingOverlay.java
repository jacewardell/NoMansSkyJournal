package com.sadostrich.nomansskyjournal.Views;

import android.animation.Animator;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.R;

/**
 * A custom (small) overlay view with a semi-transparent BG, a label TextView
 * and an indeterminant ProgressBar.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/8/2016.
 */
public class SmallLoadingOverlay extends LinearLayout {

	private static final String TAG = "SmallLoadingOverlay";
	private static final long ANIM_DURATION = 400;

	private TextView mTvLabel;

	private Animator.AnimatorListener mViewGoneListener;
	private boolean mIsShowing;

	public SmallLoadingOverlay(Context context) {
		super(context);
		init(context);
	}

	public SmallLoadingOverlay(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public SmallLoadingOverlay(Context context, AttributeSet attrs, int arg) {
		super(context, attrs, arg);
		init(context);
	}

	@SuppressWarnings("deprecation")
	public void init(Context context) {
		inflate(context, R.layout.view_overlay_small, this);

		mTvLabel = (TextView) findViewById(R.id.tv_small_overlay);
		ProgressBar progressBar = (ProgressBar) findViewById(
				R.id.progress_bar_small_overlay);

		Drawable indeterminate = progressBar.getIndeterminateDrawable();
		if (indeterminate != null) {
			indeterminate.mutate();
			indeterminate.setColorFilter(new LightingColorFilter(0xffffffff,
					context.getResources().getColor(R.color.text_primary_light)));
			progressBar.setIndeterminateDrawable(indeterminate);
		}

		setUpAnimatorListener();
	}

	/**
	 * Sets up an animator listener which is used when hiding the view.
	 */
	private void setUpAnimatorListener() {
		mViewGoneListener = new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				setVisibility(GONE);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}
		};
	}

	/////////////////////////////////////////////////////////////////////////
	// Public Config Methods
	/////////////////////////////////////////////////////////////////////////

	public void setLabel(int labelRes) {
		mTvLabel.setText(labelRes);
	}

	public void setLabel(String label) {
		mTvLabel.setText(label);
	}

	public void setToDefault() {
		mTvLabel.setText(R.string.loading_dot_dot_dot);
	}

	/**
	 * Shows the view.
	 *
	 * @return <code>True</code> if the view was shown, <code>false</code> if it
	 *         was already showing.
	 */
	public boolean show() {
		if (isShowing()) {
			Log.d(TAG, "Already showing. Returning false...");
			return false;
		}
		setAlpha(0);
		setVisibility(View.VISIBLE);
		animate().alpha(1).setDuration(ANIM_DURATION).setListener(null).start();
		mIsShowing = true;
		return true;
	}

	/**
	 * Hides the view.
	 *
	 * @return <code>True</code> if the view was hidden, <code>false</code> if
	 *         it was already hidden.
	 */
	public boolean hide() {
		if (!mIsShowing) {
			Log.d(TAG, "Already hidden. Returning false...");
			return false;
		}
		mIsShowing = false;
		animate().alpha(0).setDuration(ANIM_DURATION).setListener(mViewGoneListener)
				.start();
		return true;
	}

	/**
	 * @return <code>True</code> if this view is currently showing and is NOT
	 *         animating.
	 */
	public boolean isShowing() {
		return mIsShowing;
	}

}
