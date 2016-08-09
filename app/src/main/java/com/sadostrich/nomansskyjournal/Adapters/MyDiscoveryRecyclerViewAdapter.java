package com.sadostrich.nomansskyjournal.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.DiscoveryViewHolder;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Discovery} and makes a
 * call to the specified {@link IDiscoveryListener}. TODO: Replace the
 * implementation with code for your data type.
 */
public class MyDiscoveryRecyclerViewAdapter
		extends RecyclerView.Adapter<DiscoveryViewHolder> {
	private static final String TAG = "DiscoveryRecViewAdapter";

	private final List<Discovery> mValues;
	private IDiscoveryListener mListener;
	protected boolean mIsLoading;
	protected boolean mMoreItemsToLoad;
	protected int mPageSize;

	public MyDiscoveryRecyclerViewAdapter(List<Discovery> items,
			IDiscoveryListener listener) {
		mValues = items;
		mListener = listener;

		// Default page size is 20
		mPageSize = 20;

		// Initially loading and has more items to load
		mIsLoading = true;
		mMoreItemsToLoad = true;
	}

	public void releaseAllListeners() {
		mListener = null;
	}

	@Override
	public DiscoveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.discovery_grid_item, parent, false);
		DiscoveryViewHolder dvh = new DiscoveryViewHolder(view);
		dvh.setListener(mListener);
		return dvh;
	}

	@Override
	public void onBindViewHolder(final DiscoveryViewHolder holder, int position) {
		// First check if need to load more items
		if (shouldLoadMoreItems(position)) {
			loadMoreItems();
			Log.i(TAG, "@ onBindViewHolder(): Fetching more items...");
		}

		holder.setData(mValues.get(position));
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}

	////////////////////////////////////////////////////////////////
	// Loading Methods
	////////////////////////////////////////////////////////////////

	private boolean shouldLoadMoreItems(int position) {
		boolean scrollRangeReached;
		if (mValues.size() < 30) {
			// Initially when list is small, check if halfway through list
			scrollRangeReached = (position > mValues.size() / 2);
		} else {
			// When list is larger, check if position within last 20 items
			scrollRangeReached = (position > mValues.size() - 20);
		}

		return (scrollRangeReached && !mIsLoading && mMoreItemsToLoad);
	}

	private void loadMoreItems() {
		mIsLoading = true;
		if (mListener != null) {
			mListener.onLoadMoreDiscoveries();
		} else {
			Log.w(TAG, "Need to load more items but no listener has been set!");
		}
	}

	public void setPageSize(int pageSize) {
		mPageSize = pageSize;
	}

	public void setMoreItemsToLoad(boolean more) {
		mMoreItemsToLoad = more;
	}

	public void notifyError() {
		mIsLoading = false;
		// Server error, stop trying to load more items
		mMoreItemsToLoad = false;
	}

	public void notifyItemsAdded(int numItems) {
		mIsLoading = false;
		if (numItems <= 0) {
			// No more items to load
			mMoreItemsToLoad = false;
			Log.i(TAG, "Reached the end of List with <" + getItemCount() + "> items");

		} else if (numItems < mPageSize) {
			// Check num items added against page size, if smaller than
			// page size, flag there are no more items to load.
			mMoreItemsToLoad = false;
			Log.i(TAG, "Reached the end of List with <" + getItemCount() + "> items");
			notifyDataSetChanged();

		} else {
			// More items received equal to page size, there might be more!
			Log.i(TAG, "Items updated: Got <" + numItems + "> more items");
			mMoreItemsToLoad = true;
			notifyDataSetChanged();
		}
	}

}
