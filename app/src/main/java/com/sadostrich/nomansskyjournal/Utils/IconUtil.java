package com.sadostrich.nomansskyjournal.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * Contains helper methods for working with icons.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/4/2016.
 */
public class IconUtil {

	/**
	 * Gets the icon from resources and sets the given size and color filter to
	 * it.
	 *
	 * @param context
	 *            The context from which to get the resources.
	 * @param iconRes
	 *            The drawable resource ID for the desired icon.
	 * @param colorRes
	 *            The resource ID for the color filter to apply to the icon.
	 * @param sizeRes
	 *            The dimen resource ID for the desired icon size i.e. 'R.dimen
	 *            .icon_size_2'.
	 *
	 * @return The drawable with the size and color filter set.
	 */
	@SuppressWarnings("deprecation")
	public static Drawable getIcon(@NonNull Context context, int iconRes, int colorRes,
			int sizeRes) {
		Resources res = context.getResources();

		int iconSize = (int) res.getDimension(sizeRes);
		Drawable icon = res.getDrawable(iconRes);
		if (icon != null) {
			icon.setBounds(0, 0, iconSize, iconSize);
			icon.setColorFilter(res.getColor(colorRes), PorterDuff.Mode.SRC_IN);
		}

		return icon;
	}

	/**
	 * Gets the icon and sets the given size and color state list to it.
	 * <p>
	 * NOTE: On APIs pre-21, vector assets are not supported and thus we cannot
	 * set a color state tint list to them, in this case, the icon will have a
	 * color filter of <var>colorRes</var> set to it.
	 * </p>
	 *
	 * @param context
	 *            The context from which to get the resources.
	 * @param iconRes
	 *            The drawable resource ID for the desired icon.
	 * @param iconSizeRes
	 *            The dimen resource ID for the desired icon size i.e. 'R.dimen
	 *            .icon_size_2'.
	 * @param colorStateRes
	 *            The resource ID for the color state list to apply.
	 * @param colorRes
	 *            The resource ID for the color to apply for APIs pre-21.
	 *
	 *
	 * @return The drawable with the size and color state list set.
	 */
	@SuppressWarnings("deprecation")
	public static Drawable getIconWithTintList(@NonNull Context context, int iconRes,
			int iconSizeRes, int colorStateRes, int colorRes) {

		Resources res = context.getResources();

		// Get the image size.
		int imageSize = (int) res.getDimension(iconSizeRes);

		// Get color state list.
		ColorStateList colorState = res.getColorStateList(colorStateRes);

		// Get the icon.
		Drawable icon = res.getDrawable(iconRes);
		if (icon != null) {
			icon.setBounds(0, 0, imageSize, imageSize);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				icon.setTintList(colorState);
			} else {
				icon.setColorFilter(res.getColor(colorRes), PorterDuff.Mode.SRC_IN);
			}
		}

		return icon;
	}

}
