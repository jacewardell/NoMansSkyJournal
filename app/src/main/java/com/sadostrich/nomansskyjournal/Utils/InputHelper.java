package com.sadostrich.nomansskyjournal.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Provides methods to handle input types including:<br>
 * --SoftKeyboard
 * 
 * <p/>
 * Created by Jacobus LaFazia on 4/29/2015.
 */
public class InputHelper {

	/////////////////////////////////////////////////////////////////////
	// Soft Keyboard Stuff
	/////////////////////////////////////////////////////////////////////

	/**
	 * Hides the soft keyboard.
	 * <p>
	 * NOTE: Will not work if called from a View, DialogFragment etc. because
	 * you'll be passing a reference to the Fragment's host Activity which will
	 * have NO focused control while the Fragment is active!
	 * </p>
	 *
	 * @param activity
	 *            The current Activity.
	 */
	public static void hideSoftKeyboard(Activity activity) {
		if (activity.getCurrentFocus() != null) {
			final InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
		}
	}

	/**
	 * Hides the soft keyboard.
	 * <p>
	 * Use this method if calling from within a View, DialogFragment etc.
	 * </p>
	 * 
	 * @param context
	 *            The Activity Context.
	 * @param view
	 *            The view from which to get the window token.
	 */
	public static void hideSoftKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/**
	 * Shows the soft keyboard.
	 *
	 * @param activity
	 *            The current Activity.
	 */
	public static void showSoftKeyboard(Activity activity) {
		if (activity.getCurrentFocus() != null) {
			final InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(),
					0);
		}
	}

}
