package com.sadostrich.nomansskyjournal.Views;


import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sadostrich.nomansskyjournal.R;

/**
 * A custom {@linkplain ImageView} implementation that provides zooming and
 * panning capabilities and also zooms out completely on double tap.
 * <p>
 * By default the maximum zoom scale is set to 3x but this can be changed by
 * calling the method {@linkplain TouchImageView#setMaxZoom(float)}.
 * </p>
 * 
 * <p/>
 * Created by Jacobus LaFazia on 3/13/2014.
 */
public class TouchImageView extends ImageView {

	// TODO create a loading touch image view!

	private static final String TAG = "TouchImageView";

	/* We can be in one of these 3 states */
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;

	private static final int CLICK = 3;

	private Matrix mMatrix;
	private int mViewWidth;
	private int mViewHeight;
	private float mSaveScale = 1f;
	private float mOrigWidth;
	private float mOrigHeight;
	// private int mOldMeasuredWidth;
	private int mOldMeasuredHeight;
	private int mMode = NONE;
	private ScaleGestureDetector mScaleDetector;

	/* Double Tap to zoom out */
	private GestureDetector mGestureDetector;

	/* Zoom */
	private PointF mLast = new PointF();
	private PointF mStart = new PointF();
	private float mMinScale = 1f;
	private float mMaxScale = 3f;
	private float[] mMatrixArray;

	public TouchImageView(Context context) {
		this(context, null);
	}

	public TouchImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		super.setClickable(true);
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		mMatrix = new Matrix();
		mMatrixArray = new float[9];
		setImageMatrix(mMatrix);
		setScaleType(ScaleType.MATRIX);

		mGestureDetector = new GestureDetector(context,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onDoubleTap(MotionEvent e) {
						// Zoom out
						Log.d(TAG, "Gesture Detector handled double tap!");
						zoomOutFull();

						return true;
					}

					@Override
					public boolean onSingleTapConfirmed(MotionEvent e) {
						// Show toast msg
						Log.d(TAG, "Gesture Detector handled single tap!");
						if (mSaveScale > mMinScale) {
							// Tell user quick tip to zoom out
							showToastMsg();
						}

						return true;
					}
				});

		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Pass event to gesture detector
				if (mGestureDetector.onTouchEvent(event)) {
					return true;
				}

				// Pass event to scale detector
				mScaleDetector.onTouchEvent(event);
				PointF curr = new PointF(event.getX(), event.getY());

				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					mLast.set(curr);
					mStart.set(mLast);
					mMode = DRAG;
					break;

				case MotionEvent.ACTION_MOVE:
					if (mMode == DRAG) {
						float deltaX = curr.x - mLast.x;
						float deltaY = curr.y - mLast.y;
						float fixTransX = getFixedDragTranslation(deltaX, mViewWidth,
								mOrigWidth * mSaveScale);
						float fixTransY = getFixedDragTranslation(deltaY, mViewHeight,
								mOrigHeight * mSaveScale);
						mMatrix.postTranslate(fixTransX, fixTransY);
						fixTranslation();
						mLast.set(curr.x, curr.y);
					}
					break;

				case MotionEvent.ACTION_UP:
					mMode = NONE;
					int xDiff = (int) Math.abs(curr.x - mStart.x);
					int yDiff = (int) Math.abs(curr.y - mStart.y);
					if (xDiff < CLICK && yDiff < CLICK) {
						performClick();
					}
					break;

				case MotionEvent.ACTION_POINTER_UP:
					mMode = NONE;
					break;
				}

				setImageMatrix(mMatrix);
				invalidate();
				return true; // indicate event was handled
			}

		});
	}

	private void showToastMsg() {
		Toast.makeText(getContext(), R.string.prompt_double_tap_zoom_out,
					   Toast.LENGTH_SHORT).show();
	}

	//////////////////////////////////////////////////////////////////////
	// View Methods
	//////////////////////////////////////////////////////////////////////

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
		mViewHeight = MeasureSpec.getSize(heightMeasureSpec);

		// Rescales image on rotation
		if (mOldMeasuredHeight == mViewWidth && mOldMeasuredHeight == mViewHeight
				|| mViewWidth == 0 || mViewHeight == 0) {
			return;
		}

		mOldMeasuredHeight = mViewHeight;
		// mOldMeasuredWidth = mViewWidth;

		if (mSaveScale == 1) {
			// Fit to screen.
			float scale;

			Drawable drawable = getDrawable();
			if (drawable == null || drawable.getIntrinsicWidth() == 0
					|| drawable.getIntrinsicHeight() == 0) {
				return;
			}
			int bmWidth = drawable.getIntrinsicWidth();
			int bmHeight = drawable.getIntrinsicHeight();

			Log.d(TAG, "Bitmap Size: bmWidth = " + bmWidth + ", bmHeight = " + bmHeight);

			float scaleX = (float) mViewWidth / (float) bmWidth;
			float scaleY = (float) mViewHeight / (float) bmHeight;
			scale = Math.min(scaleX, scaleY);
			mMatrix.setScale(scale, scale);

			// Center the image
			float redundantYSpace = (float) mViewHeight - (scale * (float) bmHeight);
			float redundantXSpace = (float) mViewWidth - (scale * (float) bmWidth);
			redundantYSpace /= (float) 2;
			redundantXSpace /= (float) 2;

			mMatrix.postTranslate(redundantXSpace, redundantYSpace);

			mOrigWidth = mViewWidth - 2 * redundantXSpace;
			mOrigHeight = mViewHeight - 2 * redundantYSpace;
			setImageMatrix(mMatrix);
		}
		fixTranslation();
	}

	private void fixTranslation() {
		mMatrix.getValues(mMatrixArray);
		float transX = mMatrixArray[Matrix.MTRANS_X];
		float transY = mMatrixArray[Matrix.MTRANS_Y];

		float fixedTransX = getFixedTranslation(transX, mViewWidth,
				mOrigWidth * mSaveScale);
		float fixedTransY = getFixedTranslation(transY, mViewHeight,
				mOrigHeight * mSaveScale);

		if (fixedTransX != 0 || fixedTransY != 0) {
			mMatrix.postTranslate(fixedTransX, fixedTransY);
		}
	}

	private float getFixedTranslation(float trans, float viewSize, float contentSize) {
		float minTrans, maxTrans;

		if (contentSize <= viewSize) {
			minTrans = 0;
			maxTrans = viewSize - contentSize;

		} else {
			minTrans = viewSize - contentSize;
			maxTrans = 0;
		}

		if (trans < minTrans) {
			return -trans + minTrans;

		} else if (trans > maxTrans) {
			return -trans + maxTrans;

		} else {
			return 0;
		}
	}

	private float getFixedDragTranslation(float delta, float viewSize,
			float contentSize) {
		if (contentSize <= viewSize) {
			return 0;
		}
		return delta;
	}

	private void zoomOutFull() {
		if (mSaveScale > mMinScale) {
			// We are zoomed in some, zoom out!
			float origScale = mSaveScale;
			mSaveScale = mMinScale;
			float scaleFactor = mMinScale / origScale;

			mMatrix.postScale(scaleFactor, scaleFactor, mViewWidth / 2, mViewHeight / 2);
			fixTranslation();
			setImageMatrix(mMatrix);
			invalidate();
		}
	}

	//////////////////////////////////////////////////////////////////////
	// Public Config Methods
	//////////////////////////////////////////////////////////////////////

	/**
	 * Sets the maximum scale for zooming in. Default is 3 (3x).
	 * <p>
	 * Typically this value should be set in the range [0,4].
	 * </p>
	 *
	 * @param x
	 *            The Max zoom scale. 0 = no zoom, 1 = 1x zoom and so on.
	 */
	public void setMaxZoom(float x) {
		mMaxScale = x;
	}

	/**
	 * Sets the minimum scale for zooming out. Default is 1 (1x).
	 * <p>
	 * Typically this value should be set in the range [1,3].
	 * </p>
	 *
	 * @param x
	 *            The Min zoom scale. 1 = 1x zoom and so on.
	 */
	public void setMinZoom(float x) {
		mMinScale = x;
	}

	//////////////////////////////////////////////////////////////////////
	// ScaleListener Class
	//////////////////////////////////////////////////////////////////////

	private class ScaleListener
			extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			mMode = ZOOM;
			return true;
		}

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			float scaleFactor = detector.getScaleFactor();
			float origScale = mSaveScale;
			mSaveScale *= scaleFactor;
			if (mSaveScale > mMaxScale) {
				mSaveScale = mMaxScale;
				scaleFactor = mMaxScale / origScale;

			} else if (mSaveScale < mMinScale) {
				mSaveScale = mMinScale;
				scaleFactor = mMinScale / origScale;
			}

			if (mOrigWidth * mSaveScale <= mViewWidth
					|| mOrigHeight * mSaveScale <= mViewHeight) {
				mMatrix.postScale(scaleFactor, scaleFactor, mViewWidth / 2,
						mViewHeight / 2);
			} else {
				mMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(),
						detector.getFocusY());
			}

			fixTranslation();
			return true;
		}
	}

}
