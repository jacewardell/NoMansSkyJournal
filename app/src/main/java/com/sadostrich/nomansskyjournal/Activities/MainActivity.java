package com.sadostrich.nomansskyjournal.Activities;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sadostrich.nomansskyjournal.Data.Cache;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Fragments.PlanetFragment;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Views.BottomTabsView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
	// private AddDiscoveryCardView addDiscoveryCardView;
	private FloatingActionButton mainFab, solarSystemFab, planetFab, animalFab, plantFab,
			structureFab, toolFab, shipFab;
	private Toolbar toolbar;
	private ActionBarDrawerToggle drawerToggle;
	private ImageView drawerUserAvatar;
	private TextView drawerUsername;
	private ArrayList<FloatingActionButton> subFabs;
	private View progressBarContainer;

	private double circleRadius;
	private Point screenCenter;
	// private BottomTabsView bottomTabsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getViewRefs();

		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
														 this);

		// Set up the ViewPager with the sections adapter.
		viewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

		setupFabs();
		setupDrawerLayout();
		// setupBottomTabs();

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

		Call<List<Discovery>> findDiscoveriesCall = nmsOriginsService
				.findDiscoveries(NMSOriginsServiceHelper.getDiscoveriesBodyHashMap());
		findDiscoveriesCall.enqueue(new Callback<List<Discovery>>() {
			@Override
			public void onResponse(Call<List<Discovery>> call,
					Response<List<Discovery>> response) {
				showProgressBar(View.INVISIBLE);

				Cache.getInstance().setDiscoveries(response.body());
			}

			@Override
			public void onFailure(Call<List<Discovery>> call, Throwable t) {
				Log.e(TAG, "response");
			}
		});
	}

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
												 toolbar, /* nav drawer icon to replace 'Up'
												 caret */
												 R.string.drawer_open, /* "open drawer"
												 description */
												 R.string.drawer_close /* "close drawer"
												 description */
		) {

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

		// Set the drawer toggle as the DrawerListener
		drawerLayout.setDrawerListener(drawerToggle);
		drawerListView.setOnItemClickListener(this);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	// private void setupBottomTabs() {
	// bottomTabsView.setTabSelectedListener(this);
	// }

	private void getViewRefs() {
		viewPager = (ViewPager) findViewById(R.id.container);

		drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
		drawerListView = (ListView) findViewById(R.id.drawer_listview);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		// bottomTabsView = (BottomTabsView)
		// findViewById(R.id.bottom_tabs_view);

		mainFab = (FloatingActionButton) findViewById(R.id.fab);
		// addDiscoveryCardViewz = (AddDiscoveryCardView) findViewById(R.id
		// .add_discovery_card_view);
		solarSystemFab = (FloatingActionButton) findViewById(R.id.solar_systems_fab);
		planetFab = (FloatingActionButton) findViewById(R.id.planet_fab);
		animalFab = (FloatingActionButton) findViewById(R.id.animal_fab);
		plantFab = (FloatingActionButton) findViewById(R.id.plant_fab);
		structureFab = (FloatingActionButton) findViewById(R.id.structure_fab);
		toolFab = (FloatingActionButton) findViewById(R.id.tool_fab);
		shipFab = (FloatingActionButton) findViewById(R.id.ship_fab);

		progressBarContainer = findViewById(R.id.layout_progress_bar_container);
	}

	private void setupFabs() {
		if (mainFab != null) {
			// addDiscoveryCardView.setListener(this);
			mainFab.setOnClickListener(this);
			planetFab.setOnClickListener(this);
			animalFab.setOnClickListener(this);
			plantFab.setOnClickListener(this);
			solarSystemFab.setOnClickListener(this);
			structureFab.setOnClickListener(this);
			toolFab.setOnClickListener(this);
			shipFab.setOnClickListener(this);

			subFabs = new ArrayList<FloatingActionButton>();
			subFabs.add(solarSystemFab);
			subFabs.add(planetFab);
			subFabs.add(animalFab);
			subFabs.add(plantFab);
			subFabs.add(structureFab);
			subFabs.add(toolFab);
			subFabs.add(shipFab);

			mainFab.setEnabled(false);
		}
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

	@Override
	public void onFragmentInteraction(String id) {

	}

	@Override
	public void onTabSelected(int index) {
		Log.d(TAG, "tab index: " + index);
	}

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
				// startAddDiscoveryActivity();
				Toast.makeText(MainActivity.this, "solar system", Toast.LENGTH_SHORT).show();
				break;

			case R.id.planet_fab:
				collapseFabs();
				Toast.makeText(MainActivity.this, "planet", Toast.LENGTH_SHORT).show();
				break;

			case R.id.animal_fab:
				collapseFabs();
				Toast.makeText(MainActivity.this, "animal", Toast.LENGTH_SHORT).show();
				break;

			case R.id.plant_fab:
				collapseFabs();
				Toast.makeText(MainActivity.this, "plant", Toast.LENGTH_SHORT).show();
				break;

			case R.id.structure_fab:
				collapseFabs();
				Toast.makeText(MainActivity.this, "structure", Toast.LENGTH_SHORT).show();
				break;

			case R.id.tool_fab:
				collapseFabs();
				Toast.makeText(MainActivity.this, "tool", Toast.LENGTH_SHORT).show();
				break;

			case R.id.ship_fab:
				collapseFabs();
				Toast.makeText(MainActivity.this, "ship", Toast.LENGTH_SHORT).show();
				break;

			default:
				if (!fabExpanded) {
					collapseFabs();
					break;
				}
		}
	}

	private void expandFabs() {
		AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

		mainFab.animate().rotation(135).setInterpolator(interpolator).start();

		animateFabs();

		dimBackground(true);
	}

	private void dimBackground(boolean dim) {
		// drawerLayout.setAlpha(dim ? (float) 0.5 : 1);
	}

	private void collapseFabs() {
		AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

		mainFab.animate().rotation(0).setInterpolator(interpolator);

		for (FloatingActionButton subFab : subFabs) {
			animateFab(subFab, -1);
		}

		dimBackground(false);
	}

	private void animateFabs() {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int screenWidth = metrics.widthPixels;
		int screenHeight = viewPager.getHeight();
		circleRadius = (screenWidth * 0.40) / 2;

		// screenCenter = new Point((int) (viewPager.getX() +
		// viewPager.getWidth() / 2),
		// (int) (viewPager.getY() + viewPager.getHeight() / 2));
		screenCenter = new Point(metrics.widthPixels / 2, metrics.heightPixels / 2);

		for (FloatingActionButton subFab : subFabs) {
			switch (subFab.getId()) {
				case R.id.solar_systems_fab:
					animateFab(solarSystemFab, 5);
					break;

				case R.id.planet_fab:
					animateFab(planetFab, 6);
					break;

				case R.id.animal_fab:
					animateFab(animalFab, 0);
					break;

				case R.id.plant_fab:
					animateFab(plantFab, 1);
					break;

				case R.id.structure_fab:
					animateFab(structureFab, 2);
					break;

				case R.id.tool_fab:
					animateFab(toolFab, 3);
					break;

				case R.id.ship_fab:
					animateFab(shipFab, 4);
					break;
			}
		}
	}

	private void animateFab(final FloatingActionButton fab, final float position) {
		float xPos = 0;
		float yPos = 0;
		// Animate back to the mainFab
		if (position == -1) {
			xPos = mainFab.getX() + mainFab.getWidth() / 6;
			yPos = mainFab.getY() + mainFab.getHeight() / 6;
		} else {
			xPos = (float) (circleRadius
					* Math.cos((2 * Math.PI / subFabs.size()) * position) + screenCenter.x
					- solarSystemFab.getWidth() / 2);
			yPos = (float) (circleRadius
					* Math.sin((2 * Math.PI / subFabs.size()) * position)
					+ screenCenter.y);
		}

		fab.animate().x(xPos).y(yPos)
				.setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250)
				.setListener(new Animator.AnimatorListener() {
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

	@Override
	public void onDiscoverySelected(Discovery discovery) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(getString(R.string.discovery_type), discovery.getType());
		bundle.putSerializable(getString(R.string.extra_discovery), discovery);
		bundle.putBoolean(getString(R.string.discovery_add), false);

		Intent intent = new Intent(MainActivity.this, DiscoveryActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onAddPlanet() {
		Bundle bundle = new Bundle();
		bundle.putSerializable(getString(R.string.discovery_type),
							   Enums.DiscoveryType.PLANET);
		bundle.putBoolean(getString(R.string.discovery_add), true);

		Intent intent = new Intent(MainActivity.this, DiscoveryActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

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
		getSupportActionBar().setTitle(getResources().getString(titleRes));
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the
	 * sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		private IDiscoveryListener listener;

		public SectionsPagerAdapter(FragmentManager fm, IDiscoveryListener listener) {
			super(fm);
			this.listener = listener;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlanetFragment.newInstance("", "", listener);
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 7;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return getResources().getString(R.string.solar_systems);
				case 1:
					return getResources().getString(R.string.planets);
				case 2:
					return getResources().getString(R.string.animals);
				case 3:
					return getResources().getString(R.string.plants);
				case 4:
					return getResources().getString(R.string.structure);
				case 5:
					return getResources().getString(R.string.tool);
				case 6:
					return getResources().getString(R.string.ship);
			}
			return null;
		}
	}
}
