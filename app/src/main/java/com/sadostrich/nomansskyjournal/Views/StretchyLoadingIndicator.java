package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.sadostrich.nomansskyjournal.R;

/**
 * Simple View with a transparent background and a <i>ProgressBar</i> in the
 * center.
 *
 * <p/>
 * Created by Jacobus LaFazia on 1/12/2014.
 */
public class StretchyLoadingIndicator extends RelativeLayout {

	public StretchyLoadingIndicator(Context context) {
		super(context);
		init(context);
	}

	public StretchyLoadingIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public StretchyLoadingIndicator(Context context, AttributeSet attrs, int arg) {
		super(context, attrs, arg);
		init(context);
	}

	private void init(Context context) {
		inflate(context, R.layout.view_progress_bar, this);
	}

	/**
	 * Set the background color of this view. Default is transparent.
	 */
	public void setBackgroundColor(int color) {
		(findViewById(R.id.loadingPanel)).setBackgroundColor(color);
	}

	/**
	 * Set/Change the color of the indeterminate progress bar.
	 *
	 * @param color
	 *            The primary color to set to the indeterminate
	 *            <i>ProgressBars</i>.
	 * @param dark
	 *            Pass <code>true</code> if the BG is dark.
	 */
	public void setProgressBarColor(int color, boolean dark) {
		// Set the indeterminate colors
		ProgressBar pb = (ProgressBar) findViewById(R.id.progress_bar);
		Drawable indeterminate = pb.getIndeterminateDrawable();
		if (indeterminate != null) {
			indeterminate.mutate();
			if (dark) {
				// With dark BG, use lighter colors
				indeterminate.setColorFilter(new LightingColorFilter(0xffffffff, color));

			} else {
				// With light BG, use darker colors
				indeterminate.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
			}
			pb.setIndeterminateDrawable(indeterminate);
		}
	}

	// The code below was screwing up the spinner, made it not center
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	// }

}