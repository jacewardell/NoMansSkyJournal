package com.sadostrich.nomansskyjournal.Models;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 8/1/16.
 */
public class CustomSpinnerObject {
	@DrawableRes
	private int drawableRes;
	@ColorRes
	private int drawableColorRes;
	@StringRes
	private int stringRes;

	public CustomSpinnerObject(@DrawableRes int drawableRes, @ColorRes int drawableColorRes,
			@StringRes int stringRes) {
		this.drawableRes = drawableRes;
		this.drawableColorRes = drawableColorRes;
		this.stringRes = stringRes;
	}

	public int getDrawableRes() {
		return drawableRes;
	}

	public void setDrawableRes(@DrawableRes int drawableRes) {
		this.drawableRes = drawableRes;
	}

	public int getDrawableColorRes() {
		return drawableColorRes;
	}

	public void setDrawableColorRes(@ColorRes int drawableColorRes) {
		this.drawableColorRes = drawableColorRes;
	}

	public int getStringRes() {
		return stringRes;
	}

	public void setStringRes(@StringRes int stringRes) {
		this.stringRes = stringRes;
	}
}
