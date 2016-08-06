package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sadostrich.nomansskyjournal.R;

/**
 * Layout that draws an Octagonal background.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/5/2016.
 */
public class OctagonLayout extends RelativeLayout {

	private AttributeSet mAttributes;
	private final RectF mCanvasBounds = new RectF(0, 0, 0, 0);
	private Path mClipPath;
	private Path mOctPath;
	private int mColor;

	public OctagonLayout(Context context) {
		super(context);
		init(context);
	}

	public OctagonLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mAttributes = attrs;
		init(context);
	}

	public OctagonLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mAttributes = attrs;
		init(context);
	}

	@SuppressWarnings("deprecation")
	private void init(Context context) {
		if (mAttributes != null) {
			TypedArray a = context.obtainStyledAttributes(mAttributes,
					R.styleable.OctagonLayout, 0, 0);
			// Get desired octagon color
			mColor = a.getColor(R.styleable.OctagonLayout_octagon_color,
					context.getResources().getColor(R.color.accent));

			a.recycle();

		} else {
			mColor = context.getResources().getColor(R.color.accent);
		}

		mClipPath = new Path();
		mOctPath = new Path();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		resetCanvasBounds(w, h);
	}

	/**
	 * Resets the bounds of the main canvas back to original size.
	 *
	 * @param canvasWidth
	 *            The width the canvas should be
	 * @param canvasHeight
	 *            The height the canvas should be
	 */
	private void resetCanvasBounds(float canvasWidth, float canvasHeight) {
		mCanvasBounds.set(getPaddingLeft(), getPaddingTop(),
				canvasWidth - getPaddingRight(), canvasHeight - getPaddingBottom());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.rotate(22.0f, mCanvasBounds.centerX(), mCanvasBounds.centerY());
		mClipPath.addPath(Octagon());
		canvas.clipPath(mClipPath);
		canvas.rotate(-22.0f, mCanvasBounds.centerX(), mCanvasBounds.centerY());
		canvas.drawColor(mColor);

		super.onDraw(canvas);
	}

	private Path Octagon() {
		float midX = getWidth() / 2;
		float midY = getHeight() / 2;
		mOctPath.moveTo(midX, midY);
		// mOctPath.lineTo(midX + 300, midY + 120);
		// mOctPath.lineTo(midX + 120, midY + 300);
		// mOctPath.lineTo(midX - 120, midY + 300);
		// mOctPath.lineTo(midX - 300, midY + 120);
		// mOctPath.lineTo(midX - 300, midY - 120);
		// mOctPath.lineTo(midX - 120, midY - 300);
		// mOctPath.lineTo(midX + 120, midY - 300);
		// mOctPath.lineTo(midX + 300, midY - 120);
		// mOctPath.lineTo(midX + 300, midY + 120);

		float large = (getWidth() / 2) * 0.91f;
		float small = (getWidth() / 5) * 0.91f;
		mOctPath.lineTo(midX + large, midY + small);
		mOctPath.lineTo(midX + small, midY + large);
		mOctPath.lineTo(midX - small, midY + large);
		mOctPath.lineTo(midX - large, midY + small);
		mOctPath.lineTo(midX - large, midY - small);
		mOctPath.lineTo(midX - small, midY - large);
		mOctPath.lineTo(midX + small, midY - large);
		mOctPath.lineTo(midX + large, midY - small);
		mOctPath.lineTo(midX + large, midY + small);

		return mOctPath;
	}

}
