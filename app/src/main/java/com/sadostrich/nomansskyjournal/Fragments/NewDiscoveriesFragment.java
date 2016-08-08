package com.sadostrich.nomansskyjournal.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.Adapters.MyDiscoveryRecyclerViewAdapter;
import com.sadostrich.nomansskyjournal.Data.Cache;
import com.sadostrich.nomansskyjournal.Utils.GridSpacingItemDecoration;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IDiscoveryListener} interface.
 */
public class NewDiscoveriesFragment extends Fragment implements
		SwipeRefreshLayout.OnRefreshListener {
	private static final String TAG = "NewDiscoveriesFragment";

	// TODO: Customize parameter argument names
	private static final String ARG_COLUMN_COUNT = "column-count";
	// TODO: Customize parameters
	private int mColumnCount = 2;
	private IDiscoveryListener mListener;
	private MyDiscoveryRecyclerViewAdapter adapter;
	private RecyclerView recyclerView;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
	 * screen orientation changes).
	 */
	public NewDiscoveriesFragment() {
	}

	// TODO: Customize parameter initialization
	@SuppressWarnings("unused")
	public static NewDiscoveriesFragment newInstance(int columnCount) {
		NewDiscoveriesFragment fragment = new NewDiscoveriesFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_COLUMN_COUNT, columnCount);
		fragment.setArguments(args);
		Log.d(TAG, "@ newInstance: " + fragment);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof IDiscoveryListener) {
			mListener = (IDiscoveryListener) context;
		} else {
			throw new RuntimeException(context.toString()
											   + " must implement "
											   + "OnListFragmentInteractionListener");

		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null) {
			mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_discoveries_list, container, false);

		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			recyclerView = (RecyclerView) view;
			recyclerView.addItemDecoration(new GridSpacingItemDecoration());
			if (mColumnCount <= 1) {
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
			} else {
				recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
			}

			Log.d(TAG, "@ onCreateView: adapter initialized");
			adapter =
					new MyDiscoveryRecyclerViewAdapter(Cache.getInstance().getNewDiscoveries(),
													   mListener);

			Log.d(TAG, "@ onCreateView: recyclerView.setAdapter()");
			recyclerView.setAdapter(adapter);
		}
		return view;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public void notifyDataSetChanged() {
		if (adapter == null) {
			Log.d(TAG, "@ notifyDataSetChanged: adapter initialized");
			adapter = new MyDiscoveryRecyclerViewAdapter(Cache.getInstance().getNewDiscoveries(),
														 mListener);
		}
		Log.d(TAG, "@ notifyDataSetChanged: adapter.notifyDataSetChanged()");
		Log.d(TAG, "@ notifyDataSetChanged: " + Cache.getInstance().getNewDiscoveries().size());
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onRefresh() {
		Log.d(TAG, "@ onRefresh: ");
	}
}
