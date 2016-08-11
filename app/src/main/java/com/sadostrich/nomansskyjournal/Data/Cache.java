package com.sadostrich.nomansskyjournal.Data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sadostrich.nomansskyjournal.Models.Discovery;

import java.util.ArrayList;
import java.util.List;

/**
 * Caches list of Discoveries.
 * 
 * <p/>
 * Created by Jace Wardell on 7/30/16.
 */
public class Cache {

	private static final String TAG = "Cache";

	/* Data Lists */
	private final List<Discovery> mNewDiscoveries;
	private final List<Discovery> mPopularDiscoveries;

	/**
	 * The <i>singleton</i> instance of this cache.
	 */
	private static Cache sInstance;

	/**
	 * @return The <i>singleton</i> instance of this cache.
	 */
	public static Cache getInstance() {
		if (sInstance == null) {
			sInstance = new Cache();
		}
		return sInstance;
	}

	/**
	 * Default private constructor.
	 */
	private Cache() {
		mNewDiscoveries = new ArrayList<>();
		mPopularDiscoveries = new ArrayList<>();
	}

	/////////////////////////////////////////////////////////
	// New Discoveries
	/////////////////////////////////////////////////////////

	public @NonNull List<Discovery> getNewDiscoveries() {
		return mNewDiscoveries;
	}

	public void setNewDiscoveries(@NonNull List<Discovery> discoveries) {
		Log.d(TAG, "@ setNewDiscoveries(): Setting <" + discoveries.size()
				+ "> new discoveries...");

		mNewDiscoveries.clear();
		mNewDiscoveries.addAll(discoveries);
	}

	public void addNewDiscoveries(@NonNull List<Discovery> discoveries) {
		mNewDiscoveries.addAll(discoveries);
	}

	/////////////////////////////////////////////////////////
	// Popular Discoveries
	/////////////////////////////////////////////////////////

	public @NonNull List<Discovery> getPopularDiscoveries() {
		return mPopularDiscoveries;
	}

	public void setPopularDiscoveries(@NonNull List<Discovery> discoveries) {
		Log.d(TAG, "@ setPopularDiscoveries(): Setting <" + discoveries.size()
				+ "> popular discoveries...");

		mPopularDiscoveries.clear();
		mPopularDiscoveries.addAll(discoveries);
	}

	public void addPopularDiscoveries(@NonNull List<Discovery> discoveries) {
		mPopularDiscoveries.addAll(discoveries);
	}

}
