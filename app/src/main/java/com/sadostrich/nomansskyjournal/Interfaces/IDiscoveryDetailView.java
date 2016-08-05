package com.sadostrich.nomansskyjournal.Interfaces;

import android.graphics.Bitmap;

import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.User;

/**
 * Listener interface for the {@linkplain IDiscoveryDetailView} actions.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/4/2016.
 */
public interface IDiscoveryDetailView {

	void onUserClicked(User user);

	void onImageClicked(Bitmap image);

	void onUpvoteClicked(Discovery discovery);

	void onReportInappropriate(Discovery discovery);

}
