package com.sadostrich.nomansskyjournal.Interfaces;

import android.support.v4.view.ViewPager;

/**
 * Defines the basic requirements of a ViewPager Indicator which must provide a
 * visual indication of the current ViewPager page out of the total number of
 * pages.
 */
public interface IPageIndicator extends ViewPager.OnPageChangeListener {
	/**
	 * Binds the indicator to a ViewPager.
	 */
	void setViewPager(ViewPager view);

	/**
	 * Binds the indicator to a ViewPager and sets the initial position of the
	 * bound ViewPager.
	 */
	void setViewPager(ViewPager view, int initialPosition);

	/**
	 * <p>
	 * Sets the current page of both the bound ViewPager and indicator.
	 * </p>
	 * <p>
	 * This <strong>MUST</strong> be used if you need to set the starting page
	 * before the views are drawn on screen (i.e., default start page).
	 * </p>
	 */
	void setCurrentItem(int item);

	/**
	 * Sets a page change listener to the bound ViewPager which will receive
	 * forwarded events.
	 * <p>
	 * NOTE: If using a page indicator and you want to set an
	 * <i>OnPageChangeListener</i> to your ViewPager, you <strong>MUST</strong>
	 * set the listener to this indicator and not the ViewPager directly,
	 * otherwise, the indicator will not function as expected.
	 * </p>
	 */
	void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

	/**
	 * Notifies the indicator that the ViewPager list has changed.
	 */
	void notifyDataSetChanged();
}
