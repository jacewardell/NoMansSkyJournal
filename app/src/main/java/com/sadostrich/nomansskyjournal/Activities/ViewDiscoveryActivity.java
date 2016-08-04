package com.sadostrich.nomansskyjournal.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;

/**
 * Activity that controls fragments related to the viewing of a discovery in
 * full detail.<br>
 * Clicking the picture goes to full screen view of the pic.<br>
 * Maybe a button to view 'related discoveries' in a full page fashion?
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/2/2016.
 */
public class ViewDiscoveryActivity extends AppCompatActivity {

	private Discovery mDiscovery;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_discovery);

		// TODO Get discovery from Intent

		// TODO Init views
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

}
