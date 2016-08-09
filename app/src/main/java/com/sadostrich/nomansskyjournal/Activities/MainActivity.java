package com.sadostrich.nomansskyjournal.Activities;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Data.Cache;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Fragments.NewDiscoveriesFragment;
import com.sadostrich.nomansskyjournal.Fragments.PlanetFragment;
import com.sadostrich.nomansskyjournal.Fragments.PopularDiscoveriesFragment;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Utils.SharedPreferencesHelper;
import com.sadostrich.nomansskyjournal.Views.BottomTabsView;
import com.sadostrich.nomansskyjournal.Views.SmallLoadingOverlay;
import com.sadostrich.nomansskyjournal.Views.SpinnerBarView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements
		PlanetFragment.OnFragmentInteractionListener, BottomTabsView.ITabSelectedListener,
		View.OnClickListener, IDiscoveryListener, AdapterView.OnItemClickListener {

	private static final String TAG = "MainActivity";
	private static final int NEW_DISCOVERIES_FRAG_INDEX = 0;
	private static final int POPULAR_DISCOVERIES_FRAG_INDEX = 1;

	Retrofit retrofit;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
	 * sections. We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded
	 * fragment in memory. If this becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager viewPager;
	private boolean fabExpanded = false;
	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private SpinnerBarView spinnerBarView;
	private FloatingActionButton mainFab, solarSystemFab, starFab, stationFab, planetFab,
			animalFab, plantFab, structureFab, toolFab, shipFab;
	private Toolbar toolbar;
	private ActionBarDrawerToggle drawerToggle;
	private ImageView drawerUserAvatar;
	private TextView drawerUsername;
	private ArrayList<FloatingActionButton> subFabs;
	private View progressBarContainer;
	private SmallLoadingOverlay smallLoadingOverlay;

	private double circleRadius;
	private Point screenCenter;
	private TextView btnSettings, btnLogout;
	// private BottomTabsView bottomTabsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getViewRefs();
		setSupportActionBar(toolbar);
		ViewCompat.setElevation(smallLoadingOverlay,
								getResources().getDimension(R.dimen.d24));

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		viewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		if (tabLayout != null) {
			tabLayout.setupWithViewPager(viewPager);
		}

		setupFabs();
		setupDrawerLayout();
		setupSpinnerBarView();

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		// set your desired log level
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		// add your other interceptors …
		// add logging as last interceptor
		httpClient.addInterceptor(logging); // <-- this is the important line!

		retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create()).build();
		NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);

		getNewDiscoveries(nmsOriginsService);
		getPopularDiscoveries(nmsOriginsService);
	}

	private void getViewRefs() {
		viewPager = (ViewPager) findViewById(R.id.main_activity_viewpager);

		drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
		drawerListView = (ListView) findViewById(R.id.drawer_listview);
		btnSettings = (TextView) findViewById(R.id.tv_settings);
		btnLogout = (TextView) findViewById(R.id.tv_logout);
		toolbar = (Toolbar) findViewById(R.id.toolbar);

		mainFab = (FloatingActionButton) findViewById(R.id.fab);
		solarSystemFab = (FloatingActionButton) findViewById(R.id.solar_systems_fab);
		starFab = (FloatingActionButton) findViewById(R.id.star_fab);
		stationFab = (FloatingActionButton) findViewById(R.id.station_fab);
		planetFab = (FloatingActionButton) findViewById(R.id.planet_fab);
		animalFab = (FloatingActionButton) findViewById(R.id.fauna_fab);
		plantFab = (FloatingActionButton) findViewById(R.id.flora_fab);
		structureFab = (FloatingActionButton) findViewById(R.id.structure_fab);
		toolFab = (FloatingActionButton) findViewById(R.id.item_fab);
		shipFab = (FloatingActionButton) findViewById(R.id.ship_fab);

		smallLoadingOverlay = (SmallLoadingOverlay) findViewById(
				R.id.small_loading_overlay_bottom);
		progressBarContainer = findViewById(R.id.layout_progress_bar_container);
	}

	private void setupFabs() {
		if (mainFab != null) {
			// addDiscoveryCardView.setListener(this);
			mainFab.setOnClickListener(this);
			solarSystemFab.setOnClickListener(this);
			starFab.setOnClickListener(this);
			stationFab.setOnClickListener(this);
			planetFab.setOnClickListener(this);
			animalFab.setOnClickListener(this);
			plantFab.setOnClickListener(this);
			structureFab.setOnClickListener(this);
			toolFab.setOnClickListener(this);
			shipFab.setOnClickListener(this);

			subFabs = new ArrayList<>();
			subFabs.add(solarSystemFab);
			subFabs.add(starFab);
			subFabs.add(stationFab);
			subFabs.add(planetFab);
			subFabs.add(animalFab);
			subFabs.add(plantFab);
			subFabs.add(structureFab);
			subFabs.add(toolFab);
			subFabs.add(shipFab);

			mainFab.setEnabled(false);
		}
	}

	private void setupDrawerLayout() {
		drawerUserAvatar = (ImageView) findViewById(R.id.drawer_user_avatar);
		drawerUsername = (TextView) findViewById(R.id.drawer_username);
		if (Authentication.isValidUsername(Authentication.getInstance())) {
			drawerUsername.setText(Authentication.getInstance().getUsername());
		}

		if (Authentication.isValidUserAvatar(Authentication.getInstance())) {
			Picasso.with(getApplicationContext())
					.load(Authentication.getInstance().getAvatar().getSmall())
					.into(drawerUserAvatar);
		}

		drawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
												 drawerLayout, /* DrawerLayout object */
												 toolbar, /*
							 * nav drawer icon to replace 'Up' caret
							 */
												 R.string.drawer_open, /*
										 * "open drawer" description
										 */
												 R.string.drawer_close /*
										 * "close drawer" description
										 */) {

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				// getSupportActionBar().setTitle(getResources().getString(R.string
				// .app_name));
			}

			/**
			 * Called when a drawer has settled in a completely closed state.
			 */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				// getSupportActionBar().setTitle(getResources().getString(R.string
				// .app_name));
			}
		};

		setupFooterOptions();

		// Set the drawer toggle as the DrawerListener
		drawerLayout.addDrawerListener(drawerToggle);
		drawerListView.setOnItemClickListener(this);

		ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setDisplayHomeAsUpEnabled(true);
			ab.setHomeButtonEnabled(true);
		}
	}

	private void setupFooterOptions() {
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferencesHelper.logout(MainActivity.this);
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				intent.addFlags(
						Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		});
	}

	private void setupSpinnerBarView() {
		// TODO setup spinner bar view!
	}

	/////////////////////////////////////////////////////////
	// LifeCycle Methods
	/////////////////////////////////////////////////////////

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.action_settings);
		item.setVisible(false);
		super.onPrepareOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/////////////////////////////////////////////////////////
	// PlanetFragment.OnFragmentInteractionListener
	/////////////////////////////////////////////////////////

	@Override
	public void onFragmentInteraction(String id) {
		// TODO handle onFragmentInteraction()
	}

	/////////////////////////////////////////////////////////
	// API Methods
	/////////////////////////////////////////////////////////

	private void getNewDiscoveries(NMSOriginsService nmsOriginsService) {
		// Show loading
		showProgressBar(View.VISIBLE);

		Call<List<Discovery>> findDiscoveriesCall = nmsOriginsService.findDiscoveries(
				NMSOriginsServiceHelper.createGetNewDiscoveriesRequestBody());
		findDiscoveriesCall.enqueue(new Callback<List<Discovery>>() {
			@Override
			public void onResponse(Call<List<Discovery>> call,
					Response<List<Discovery>> response) {
				if (response != null && response.body() != null
						&& response.code() == 200) {
					// TODO add only the new discoveries to cache
					// do not allow duplicates

					// TODO notify frag the # of new items added to cache (for the adapter)

					// Set new discoveries to cache
					Cache.getInstance().setNewDiscoveries(response.body());

					// Notify NewDiscoveriesFragment in adapter
					mSectionsPagerAdapter.notifyFragment(NEW_DISCOVERIES_FRAG_INDEX);

					// Hide loading
					showProgressBar(View.INVISIBLE);
				}
			}

			@Override
			public void onFailure(Call<List<Discovery>> call, Throwable t) {
				Log.e(TAG, "@ onFailure(): EXCEPTION = " + t.toString());

				// TODO notify frag adapter of error

				// Notify user of error fetching 'new' discoveries
				CoordinatorLayout cl = (CoordinatorLayout) findViewById(
						R.id.main_content);
				if (cl != null) {
					Snackbar.make(cl, R.string.error_get_discoveries,
								  Snackbar.LENGTH_LONG).show();
				}
			}
		});
	}

	private void getPopularDiscoveries(NMSOriginsService nmsOriginsService) {
		// Show loading
		showProgressBar(View.VISIBLE);

		Call<List<Discovery>> findDiscoveriesCall = nmsOriginsService.findDiscoveries(
				NMSOriginsServiceHelper.createGetPopularDiscoveriesRequestBody());
		findDiscoveriesCall.enqueue(new Callback<List<Discovery>>() {
			@Override
			public void onResponse(Call<List<Discovery>> call,
					Response<List<Discovery>> response) {
				if (response != null && response.body() != null
						&& response.code() == 200) {
					// TODO add only the new discoveries to cache
					// do not allow duplicates

					// TODO notify frag the # of new items added to cache (for the adapter)

					// Set popular discoveries to cache
					Cache.getInstance().setPopularDiscoveries(response.body());

					// Notify PopularDiscoveriesFragment in adapter
					mSectionsPagerAdapter.notifyFragment(POPULAR_DISCOVERIES_FRAG_INDEX);

					// Hide loading
					showProgressBar(View.INVISIBLE);
				}
			}

			@Override
			public void onFailure(Call<List<Discovery>> call, Throwable t) {
				Log.e(TAG, "@ onFailure(): EXCEPTION = " + t.toString());

				// TODO notify frag adapter of error

				// Notify user of error fetching 'popular' discoveries
				CoordinatorLayout cl = (CoordinatorLayout) findViewById(
						R.id.main_content);
				if (cl != null) {
					Snackbar.make(cl, R.string.error_get_discoveries,
								  Snackbar.LENGTH_LONG).show();
				}
			}
		});
	}

	/////////////////////////////////////////////////////////
	// BottomTabsView.ITabSelectedListener
	/////////////////////////////////////////////////////////

	@Override
	public void onTabSelected(int index) {
		Log.d(TAG, "tab index: " + index);
		// TODO handle bottom tab selection here!
	}

	/////////////////////////////////////////////////////////
	// View.OnClickListener
	/////////////////////////////////////////////////////////

	@Override
	public void onClick(View v) {
		fabExpanded = !fabExpanded;
		switch (v.getId()) {
			case R.id.fab:
				if (fabExpanded) {
					expandFabs();
				} else {
					collapseFabs();
				}
				break;

			case R.id.solar_systems_fab:
				collapseFabs();
				startAddDiscoveryActivity(Enums.DiscoveryType.SOLAR_SYSTEM);
				break;

			case R.id.star_fab:
				collapseFabs();
				startAddDiscoveryActivity(Enums.DiscoveryType.STAR);
				break;

			case R.id.station_fab:
				collapseFabs();
				startAddDiscoveryActivity(Enums.DiscoveryType.STATION);
				break;

			case R.id.planet_fab:
				collapseFabs();
				startAddDiscoveryActivity(Enums.DiscoveryType.PLANET);
				break;

			case R.id.fauna_fab:
				collapseFabs();
				startAddDiscoveryActivity(Enums.DiscoveryType.FAUNA);
				break;

			case R.id.flora_fab:
				startAddDiscoveryActivity(Enums.DiscoveryType.FLORA);
				collapseFabs();
				break;

			case R.id.structure_fab:
				startAddDiscoveryActivity(Enums.DiscoveryType.STRUCTURE);
				collapseFabs();
				break;

			case R.id.item_fab:
				startAddDiscoveryActivity(Enums.DiscoveryType.ITEM);
				collapseFabs();
				break;

			case R.id.ship_fab:
				startAddDiscoveryActivity(Enums.DiscoveryType.SHIP);
				collapseFabs();
				break;

			default:
				if (!fabExpanded) {
					collapseFabs();
					break;
				}
		}
	}

	/////////////////////////////////////////////////////////
	// View Animation & Update Methods
	/////////////////////////////////////////////////////////

	public void showSmallOverlay() {
		smallLoadingOverlay.show();
	}

	public void hideSmallOverlay() {
		smallLoadingOverlay.hide();
	}

	private void startAddDiscoveryActivity(@StringRes Enums.DiscoveryType discoveryType) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(getString(R.string.extra_discovery_type), discoveryType);
		Intent intent = new Intent(MainActivity.this, AddDiscoveryActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	private void dimBackground(boolean dim) {
		// TODO finish dimBackground() or discard
		// drawerLayout.setAlpha(dim ? (float) 0.5 : 1);
	}

	private void expandFabs() {
		AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

		mainFab.animate().rotation(135).setInterpolator(interpolator).start();

		animateFabs(true);

		dimBackground(true);
	}

	private void collapseFabs() {
		AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

		mainFab.animate().rotation(0).setInterpolator(interpolator);

		animateFabs(false);

		dimBackground(false);
	}

	private void animateFabs(boolean expand) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int screenWidth = metrics.widthPixels;
		circleRadius = (screenWidth * 0.50) / 2;

		// screenCenter = new Point((int) (viewPager.getX() +
		// viewPager.getWidth() / 2),
		// (int) (viewPager.getY() + viewPager.getHeight() / 2));
		screenCenter = new Point(metrics.widthPixels / 2, metrics.heightPixels / 2);

		for (FloatingActionButton subFab : subFabs) {
			switch (subFab.getId()) {
				case R.id.solar_systems_fab:
					animateFab(solarSystemFab, 0, expand);
					break;

				case R.id.star_fab:
					animateFab(starFab, 1, expand);
					break;

				case R.id.station_fab:
					animateFab(stationFab, 2, expand);

				case R.id.planet_fab:
					animateFab(planetFab, 3, expand);
					break;

				case R.id.fauna_fab:
					animateFab(animalFab, 4, expand);
					break;

				case R.id.flora_fab:
					animateFab(plantFab, 5, expand);
					break;

				case R.id.structure_fab:
					animateFab(structureFab, 6, expand);
					break;

				case R.id.item_fab:
					animateFab(toolFab, 7, expand);
					break;

				case R.id.ship_fab:
					animateFab(shipFab, 8, expand);
					break;
			}
		}
	}

	private void animateFab(final FloatingActionButton fab, final float position,
			boolean expand) {
		float xPos = mainFab.getX() + mainFab.getWidth() / 6;
		float yPos = mainFab.getY() + mainFab.getHeight() / 6;
		float alpha = 0;
		long duration = 250;
		// Animate to the expanded position
		if (expand) {
			// Angle between each fab
			double angleIncrement = 2 * Math.PI / subFabs.size();
			// Calculate the angle based off the fab's position
			// Need to adjust each fab so position 0 is at the top instead of
			// the right side like a unit circle
			double fabAngle = (angleIncrement * position) - (2 * angleIncrement);
			xPos = (float) (circleRadius * Math.cos(fabAngle) + screenCenter.x)
					- solarSystemFab.getWidth() / 2;
			yPos = (float) (circleRadius * Math.sin(fabAngle) + screenCenter.y);

			alpha = 1;
		}
		duration += (expand ? position : -position) * 20;

		fab.animate().x(xPos).y(yPos).alpha(alpha)
				.setInterpolator(new AccelerateDecelerateInterpolator())
				.setDuration(duration).setListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				if (position != -1) {
					fab.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (position == -1) {
					fab.setVisibility(View.GONE);
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
	}

	private void showProgressBar(int visibility) {
		progressBarContainer.setVisibility(visibility);
		mainFab.setEnabled(visibility == View.INVISIBLE);
	}

	/////////////////////////////////////////////////////////
	// IDiscoveryListener
	/////////////////////////////////////////////////////////

	@Override
	public void onDiscoverySelected(Discovery discovery) {
		Log.d(TAG, "@ onDiscoverySelected(): Discovery = " + discovery.getName());
		Log.d(TAG, "@ onDiscoverySelected(): Going to the ViewDiscoveryActivity...");

		// Go to the ViewDiscoveryActivity with the given Discovery!
		Bundle bundle = new Bundle();
		bundle.putSerializable(ViewDiscoveryActivity.INTENT_EXTRA_DISCOVERY, discovery);

		Intent intent = new Intent(MainActivity.this, ViewDiscoveryActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onLoadMoreDiscoveries() {
		// TODO load more discoveries!

		Log.w(TAG, "@ onLoadMoreDiscoveries(): Load more foo!");
	}

	// @Override
	// public void onAddPlanet() {
	// Bundle bundle = new Bundle();
	// bundle.putSerializable(getString(R.string.discovery_type),
	// Enums.DiscoveryType.PLANET);
	// bundle.putBoolean(getString(R.string.discovery_add), true);
	//
	// Intent intent = new Intent(MainActivity.this,
	// AddDiscoveryActivity.class);
	// intent.putExtras(bundle);
	// startActivity(intent);
	// }

	/////////////////////////////////////////////////////////
	// Drawer ListView OnItemClickListener
	/////////////////////////////////////////////////////////

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int titleRes = R.string.community;
		switch (position) {
			case 0:
				// Show the community discoveries
				titleRes = R.string.discoveries;
				break;

			case 1:
				// Show friend's discoveries
				titleRes = R.string.friends;
				break;

			case 2:
				// Show your discoveries
				titleRes = R.string.profile;
				break;
		}

		drawerLayout.closeDrawers();

		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(getResources().getString(titleRes));
		}
	}

	/////////////////////////////////////////////////////////
	// Fragment ViewPager Adapter
	/////////////////////////////////////////////////////////

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the
	 * sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		private final Map<Integer, Fragment> mFrags;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			mFrags = new HashMap<>();
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			switch (position) {
				case NEW_DISCOVERIES_FRAG_INDEX:
					Fragment newDisFrag = NewDiscoveriesFragment.newInstance(2);
					mFrags.put(position, newDisFrag);
					return newDisFrag;

				case POPULAR_DISCOVERIES_FRAG_INDEX:
					Fragment popDisFrag = PopularDiscoveriesFragment.newInstance(2);
					mFrags.put(position, popDisFrag);
					return popDisFrag;

				default:
					Fragment newDisFragDef = NewDiscoveriesFragment.newInstance(2);
					mFrags.put(position, newDisFragDef);
					return newDisFragDef;
			}
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
			mFrags.remove(position);
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case NEW_DISCOVERIES_FRAG_INDEX:
					return getResources().getString(R.string.newest);
				case POPULAR_DISCOVERIES_FRAG_INDEX:
					return getResources().getString(R.string.popular);
			}
			return null;
		}

		/**
		 * @param position
		 * 		Insure this is the position of the current fragment only!
		 */
		public void notifyFragment(int position) {
			Fragment frag = mFrags.get(position);
			if (frag == null) {
				Log.w(TAG, "@ notifyFragment(): Frag at position <" + position
						+ "> is NULL!");

			} else {
				Log.i(TAG, "@ notifyFragment(): Notifying frag at position: " + position);

				switch (position) {
					case NEW_DISCOVERIES_FRAG_INDEX:
						((NewDiscoveriesFragment) frag).notifyDataSetChanged();
						break;

					case POPULAR_DISCOVERIES_FRAG_INDEX:
						((PopularDiscoveriesFragment) frag).notifyDataSetChanged();
						break;
				}
			}
		}

	}

}
