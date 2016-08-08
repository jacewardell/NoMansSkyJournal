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
 * {@link RecyclerView.Adapter} that can display a {@link Discovery} and makes a call to the
 * specified {@link IDiscoveryListener}. TODO:
 * Replace the implementation with code for your data type.
 */
public class MyDiscoveryRecyclerViewAdapter extends
		RecyclerView.Adapter<DiscoveryViewHolder> {
	private static final String TAG = "DiscoveryRecViewAdapter";

	private final List<Discovery> mValues;
	private final IDiscoveryListener mListener;

	public MyDiscoveryRecyclerViewAdapter(List<Discovery> items,
			IDiscoveryListener listener) {
		mValues = items;
		mListener = listener;
	}

	@Override
	public DiscoveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.discovery_grid_item, parent, false);
		return new DiscoveryViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final DiscoveryViewHolder holder, int position) {
		holder.setData(mValues.get(position));
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}
}
