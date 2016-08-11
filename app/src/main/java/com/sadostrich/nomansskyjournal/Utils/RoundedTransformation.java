package com.sadostrich.nomansskyjournal.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Picasso;

/**
 * Transformation class to use with {@link Picasso} when loading images from
 * URL, creates a 'Round' image from the original.
 *
 * <p/>
 * Created by Jacobus LaFazia on 4/10/2014.
 */
public class RoundedTransformation implements com.squareup.picasso.Transformation {

	/**
	 * Default empty constructor.
	 */
	public RoundedTransformation() {
	}

	@Override
	public Bitmap transform(final Bitmap source) {
		int size = Math.min(source.getWidth(), source.getHeight());

		int x = (source.getWidth() - size) / 2;
		int y = (source.getHeight() - size) / 2;

		Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
		if (squaredBitmap != source) {
			source.recycle();
		}

		Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

		Canvas canvas = new Canvas(bitmap);

		Paint paint = new Paint();

		BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP,
				BitmapShader.TileMode.CLAMP);
		paint.setShader(shader);
		paint.setAntiAlias(true);

		// TODO Add Feature: Add image color filter
		// PorterDuff.Mode mMode = PorterDuff.Mode.OVERLAY;
		// paint.setColorFilter(new PorterDuffColorFilter(Color.GREEN, mMode));

		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);

		squaredBitmap.recycle();
		return bitmap;
	}

	@Override
	public String key() {
		return "rounded";
	}

}