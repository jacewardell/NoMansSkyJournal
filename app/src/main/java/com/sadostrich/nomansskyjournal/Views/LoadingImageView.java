package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sadostrich.nomansskyjournal.Interfaces.ILoadImageFromUrl;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.RoundedTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * An ImageView that provides a 'loading' state for fetching images from a URL
 * and loads the image once retrieved (Uses Picasso).<br>
 * Also includes methods to load image from Resources (Using Picasso).
 * <p>
 * Contains similar methods for making a 'Rounded' transformation on the images
 * once fetched (Useful for profile avatars).
 * </p>
 * 
 * <p/>
 * Created by Jacobus LaFazia on 1/12/2014.
 */
public class LoadingImageView extends RelativeLayout implements ILoadImageFromUrl {

	private static final String TAG = "LoadingImageView";

	protected RelativeLayout mIndicator;
	protected ImageView mImage;
	protected boolean mHideOnError;

	public LoadingImageView(Context context) {
		super(context);
		start(context);
	}

	public LoadingImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		start(context);
	}

	public LoadingImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		start(context);
	}

	protected void start(Context context) {
		mIndicator = createLoadingIndicator(context);
		addView(mIndicator);
		mImage = createImageView(context);
		addView(mImage);
	}

	@SuppressWarnings("deprecation")
	protected RelativeLayout createLoadingIndicator(Context context) {
		final StretchyLoadingIndicator indicator = new StretchyLoadingIndicator(context);
		final LayoutParams lyp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		indicator.setLayoutParams(lyp);
		indicator.setProgressBarColor(context.getResources().getColor(R.color.primary),
				false);
		return indicator;
	}

	protected ImageView createImageView(Context context) {
		final ImageView image = new ImageView(context);
		final LayoutParams lyp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		image.setLayoutParams(lyp);
		return image;
	}

	/**
	 * Setting the state of this view to 'loading' shows the ProgressBar view in
	 * place of any image.
	 */
	public void setLoading() {
		mIndicator.setVisibility(View.VISIBLE);
		mImage.setVisibility(View.GONE);
	}

	/**
	 * Setting the state of this view to 'loaded' hides the ProgressBar and
	 * shows any image set to this ImageView.
	 */
	public void setLoaded() {
		mIndicator.setVisibility(View.GONE);
		mImage.setVisibility(View.VISIBLE);
	}

	public ImageView getImageView() {
		return mImage;
	}

	public void setImage(Drawable d) {
		final ImageView image = getImageView();
		image.setImageDrawable(d);
		setLoaded();
	}

	public void setImage(int resId) {
		final ImageView image = getImageView();
		image.setImageResource(resId);
		setLoaded();
	}

	public void setImage(Bitmap bmp) {
		final ImageView image = getImageView();
		image.setImageBitmap(bmp);
		setLoaded();
	}

	//////////////////////////////////////////////////////////////////////
	// ILoadImageFromUrl
	//////////////////////////////////////////////////////////////////////

	@Override
	public void hideOnLoadingError() {
		mHideOnError = true;
	}

	@Override
	public void loadImageFromUrl(final String url, final int placeholderRes,
			final int errorRes) {
		final Context context = getContext();
		final ImageView image = getImageView();
		setLoading();

		// Using Picasso
		Picasso.with(context).load(url).placeholder(placeholderRes).error(errorRes)
				.into(image, new Callback() {
					final LoadingImageView self = LoadingImageView.this;

					@Override
					public void onError() {
						Log.e(TAG, "Error loading image at URL: " + url);
						self.setLoaded();

						if (mHideOnError) {
							Log.d(TAG, "@ onError(): Hiding the ImageView...");
							LoadingImageView.this.setVisibility(View.INVISIBLE);
						}
					}

					@Override
					public void onSuccess() {
						self.setLoaded();
					}
				});
	}

	@Override
	public void loadImageFromUrlRounded(final String url, final int placeholderRes,
			final int errorRes) {
		final Context context = getContext();
		final ImageView image = getImageView();
		setLoading();

		// Using Picasso
		Picasso.with(context).load(url).placeholder(placeholderRes).error(errorRes)
				.transform(new RoundedTransformation()).into(image, new Callback() {
					final LoadingImageView self = LoadingImageView.this;

					@Override
					public void onError() {
						Log.e(TAG, "Error loading image at URL: " + url);
						// Load rounded error image
						Picasso.with(context).load(errorRes)
								.transform(new RoundedTransformation()).into(image);
						self.setLoaded();

						if (mHideOnError) {
							Log.d(TAG, "@ onError(): Hiding the ImageView...");
							LoadingImageView.this.setVisibility(View.INVISIBLE);
						}
					}

					@Override
					public void onSuccess() {
						self.setLoaded();
					}
				});
	}

	@Override
	public void loadResourceImage(int imageRes) {
		final Context context = getContext();
		final ImageView image = getImageView();
		Picasso.with(context).load(imageRes).into(image);
	}

	@Override
	public void loadResourceImageRounded(int imageRes) {
		final Context context = getContext();
		final ImageView image = getImageView();
		Picasso.with(context).load(imageRes).transform(new RoundedTransformation())
				.into(image);
	}

	@Override
	public void loadResourceErrorImage(int errorRes) {
		final Context context = getContext();
		final ImageView image = getImageView();
		Picasso.with(context).load(errorRes).into(image);
	}

	@Override
	public void loadResourceErrorImageRounded(int errorRes) {
		final Context context = getContext();
		final ImageView image = getImageView();
		Picasso.with(context).load(errorRes).transform(new RoundedTransformation())
				.into(image);
	}

}