package com.sadostrich.nomansskyjournal.Models;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.sadostrich.nomansskyjournal.Utils.Enums;

import java.util.ArrayList;
import java.util.Objects;

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
    @ColorRes
    private int backgroundColorRes;
    @StringRes
    private int stringRes;
    private Object object;

    public CustomSpinnerObject(Object object, @ColorRes int backgroundColorRes) {
        this.drawableRes = -1;
        this.drawableColorRes = -1;
        this.backgroundColorRes = backgroundColorRes;
        this.stringRes = -1;
        this.object = object;
    }

    public CustomSpinnerObject(@DrawableRes int drawableRes, @ColorRes int drawableColorRes,
                               @StringRes int stringRes) {
        this.drawableRes = drawableRes;
        this.drawableColorRes = drawableColorRes;
        this.backgroundColorRes = -1;
        this.stringRes = stringRes;
    }

    public CustomSpinnerObject(@DrawableRes int drawableRes, @ColorRes int drawableColorRes, int backgroundColorRes,
                               @StringRes int stringRes) {
        this.drawableRes = drawableRes;
        this.drawableColorRes = drawableColorRes;
        this.backgroundColorRes = backgroundColorRes;
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

    public Object getObject() {
        return object;
    }

    public int getBackgroundColorRes() {
        return backgroundColorRes;
    }
}
