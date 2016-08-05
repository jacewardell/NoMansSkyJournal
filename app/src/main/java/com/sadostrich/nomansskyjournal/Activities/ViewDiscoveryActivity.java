package com.sadostrich.nomansskyjournal.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryDetailView;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.User;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.DiscoveryDetailView;

/**
 * Activity that controls fragments related to the viewing of a discovery in
 * full detail.<br>
 * Clicking the picture goes to full screen view of the pic.<br>
 * Maybe a button to view 'related discoveries' in a full page fashion?
 *
 * <p/>
 * Created by Jacobus LaFazia on 8/2/2016.
 */
public class ViewDiscoveryActivity extends AppCompatActivity
		implements IDiscoveryDetailView {

	private static final String TAG = "ViewDiscoveryActivity";
	public static final String INTENT_EXTRA_DISCOVERY = "INTENT_EXTRA_DISCOVERY";

	private DiscoveryDetailView mDetailView;
	private CollapsingToolbarLayout mCollapsingToolbarLayout;
	private RecyclerView mRvComments;

	private Discovery mDiscovery;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_discovery);

		// Get discovery from Intent
		mDiscovery = (Discovery) getIntent().getSerializableExtra(INTENT_EXTRA_DISCOVERY);
		if (mDiscovery == null) {
			throw new IllegalArgumentException(
					"You must pass a Discovery object to this " + "activity!");
		}

		// Init views
		initViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// TODO create menu options

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO handle action bar menu item clicked

		return super.onOptionsItemSelected(item);
	}

	private void initViews() {
		mDetailView = (DiscoveryDetailView) findViewById(R.id.detail_view);
		if (mDetailView != null) {
			mDetailView.setListener(this);
			mDetailView.setDiscovery(mDiscovery);

		} else {
			Log.e(TAG, "@ initViews(): Discovery Detail View from XML is NULL!");
		}

		mRvComments = (RecyclerView) findViewById(R.id.rv_comments);
		mRvComments.setItemAnimator(new DefaultItemAnimator());

		// RecyclerView layout manager.
		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.VERTICAL);
		mRvComments.setLayoutManager(manager);

		// The CollapsingToolbarLayout that will act as the comments header.
		mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(
				R.id.collapsing_toolbar);
		mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.comments));

		// TODO add a FAB for the 'add comment' btn!
	}

	//////////////////////////////////////////////////////////////////
	// IDiscoveryDetailView
	//////////////////////////////////////////////////////////////////

	@Override
	public void onUserClicked(User user) {
		Log.d(TAG, "@ onUserClicked(): User Name = " + user.getUsername());

		// TODO go to view user profile in main activity
	}

	@Override
	public void onImageClicked(Bitmap image) {
		Log.d(TAG, "@ onImageClicked(): ");
		// TODO display image in full page image view thingy
	}

	@Override
	public void onUpVoteClicked(Discovery discovery) {
		Log.d(TAG, "@ onUpVoteClicked(): Discovery = " + discovery.getName());
		// TODO up vote the given discovery
	}

	@Override
	public void onReportInappropriate(Discovery discovery) {
		Log.d(TAG, "@ onReportInappropriate(): Discovery = " + discovery.getName());
		// TODO report discovery as inappropriate
	}

}
