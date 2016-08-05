package com.sadostrich.nomansskyjournal.Interfaces;

/**
 * Interface for use with the custom Loading ImageViews for loading an image
 * from a URL.
 * <p>
 * Provides means of setting a placeholder and error image while/after loading.
 * (For use with Picasso)
 * </p>
 *
 * <p/>
 * Created by Jacobus LaFazia on 1/12/2014.
 */
public interface ILoadImageFromUrl {

	/**
	 * If called, hides the loading ImageView if there is an error loading from
	 * a URL.
	 */
	void hideOnLoadingError();

	void loadImageFromUrl(final String url, final int placeholderRes, final int errorRes);

	void loadImageFromUrlRounded(final String url, final int placeholderRes,
			final int errorRes);

	void loadResourceImage(final int imageRes);

	void loadResourceImageRounded(final int imageRes);

	void loadResourceErrorImage(final int errorRes);

	void loadResourceErrorImageRounded(final int errorRes);

}