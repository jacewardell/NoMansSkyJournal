package com.sadostrich.nomansskyjournal.Views.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jacobus LaFazia on 8/6/2016.
 */
public class AddCommentFABBehavior extends FloatingActionButton.Behavior {

	private static final String TAG = "AddCommentFABBehavior";

	private static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR =
			new FastOutSlowInInterpolator();
	private static final long ANIM_DURATION = 200L;

	private int mDependencyHeight;
	private float mFabStart;
	private boolean mIsFading;

	public AddCommentFABBehavior(Context context, AttributeSet attrs) {
		super();
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child,
			View dependency) {
		return dependency instanceof AppBarLayout;
	}


	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent,
			FloatingActionButton fab, View dependency) {
		Log.e(TAG, "@ onDependentViewChanged(): ");
		if (dependency instanceof AppBarLayout) {

			Log.w(TAG, "@ onDependentViewChanged(): dependency bottom = " + dependency.getBottom
					());
			Log.w(TAG, "@ onDependentViewChanged(): dependency trans Y = " + dependency
					.getTranslationY());

			Log.w(TAG, "@ onDependentViewChanged(): dependency Y = " + dependency.getY());

			CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab
					.getLayoutParams();
			int fabBottomMargin = lp.bottomMargin;
			int distanceToScroll = fab.getHeight() + fabBottomMargin;
			fab.setY(dependency.getBottom() - fab.getHeight() + (2 * fabBottomMargin));

			return true;
		}

		return false;
	}

}
