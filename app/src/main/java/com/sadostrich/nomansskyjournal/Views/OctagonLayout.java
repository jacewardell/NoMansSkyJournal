package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.sadostrich.nomansskyjournal.R;

/**
 * Layout that draws an Octagonal background.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/5/2016.
 */
public class OctagonLayout extends RelativeLayout {

	private final RectF mCanvasBounds = new RectF(0, 0, 0, 0);
	private Path mClipPath;
	private Path mOctPath;

	// Settings
	private AttributeSet mAttributes;
	private boolean mAsBtn;
	private int mColor;
	private int mColorMain;
	private int mColorPressed;

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
			mColor = mColorMain = a.getColor(R.styleable.OctagonLayout_octagon_color,
					context.getResources().getColor(R.color.accent));

			// Get pressed color (if any)
			mColorPressed = a.getColor(R.styleable.OctagonLayout_octagon_color_pressed,
					context.getResources().getColor(R.color.accent));

			// Check if desired as btn (change color on pressed)
			mAsBtn = a.getBoolean(R.styleable.OctagonLayout_asButton, false);

			a.recycle();

		} else {
			mColor = mColorMain = mColorPressed = context.getResources()
					.getColor(R.color.accent);
		}

		mClipPath = new Path();
		mOctPath = new Path();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		resetCanvasBounds(w, h);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Change colors if is in button mode
		if (mAsBtn) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mColor = mColorPressed;
				invalidate();
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				mColor = mColorMain;
				invalidate();
				break;
			}
		}

		return super.onTouchEvent(event);
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
