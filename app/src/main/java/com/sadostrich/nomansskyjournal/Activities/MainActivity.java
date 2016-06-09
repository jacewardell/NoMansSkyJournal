package com.sadostrich.nomansskyjournal.Activities;

import android.content.Intent;
import android.content.res.Configuration;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ListView;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Fragments.PlanetFragment;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryCardView;
import com.sadostrich.nomansskyjournal.Views.BottomTabsView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PlanetFragment.OnFragmentInteractionListener, BottomTabsView.ITabSelectedListener, View.OnClickListener, IDiscoveryListener {

    private static final String TAG = "MainActivity";
    Retrofit retrofit;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
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
    private FloatingActionButton mainFab;
    private AddDiscoveryCardView addDiscoveryCardView;
    private FloatingActionButton planetFab;
    private FloatingActionButton animalFab;
    private FloatingActionButton plantFab;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
//    private BottomTabsView bottomTabsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewRefs();

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);


        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupFabs();
        setupDrawerLayout();
//        setupBottomTabs();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);

        String requestString = NMSOriginsService.FIND_DISCOVERIES_QUERY;
        Call<List<Discovery>> findDiscoveriesCall = nmsOriginsService.findDiscoveries(NMSOriginsService.FIND_DISCOVERIES_QUERY);
        findDiscoveriesCall.enqueue(new Callback<List<Discovery>>() {
            @Override
            public void onResponse(Call<List<Discovery>> call, Response<List<Discovery>> response) {
                Log.e(TAG, "response");
            }

            @Override
            public void onFailure(Call<List<Discovery>> call, Throwable t) {
                Log.e(TAG, "response");
            }
        });
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void getViewRefs() {
        viewPager = (ViewPager) findViewById(R.id.container);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        bottomTabsView = (BottomTabsView) findViewById(R.id.bottom_tabs_view);

        mainFab = (FloatingActionButton) findViewById(R.id.fab);
        addDiscoveryCardView = (AddDiscoveryCardView) findViewById(R.id.add_discovery_card_view);
        planetFab = (FloatingActionButton) findViewById(R.id.planet_fab);
        animalFab = (FloatingActionButton) findViewById(R.id.animal_fab);
        plantFab = (FloatingActionButton) findViewById(R.id.plant_fab);
    }

    private void setupFabs() {
        if (mainFab != null) {
            mainFab.setOnClickListener(this);
            addDiscoveryCardView.setListener(this);
            planetFab.setOnClickListener(this);
        }
    }

//    private void setupBottomTabs() {
//        bottomTabsView.setTabSelectedListener(this);
//    }

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

        //noinspection SimplifiableIfStatement
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
        switch (v.getId()) {
            case R.id.fab:
                fabExpanded = !fabExpanded;

                float rotationDegrees = fabExpanded ? 135 : 0;
                mainFab.animate().rotation(rotationDegrees).setInterpolator(new AccelerateDecelerateInterpolator()).start();

                addDiscoveryCardView.animate().alpha(fabExpanded ? 1 : 0).setInterpolator(new AccelerateDecelerateInterpolator()).start();


//
//                float yTranslation = (float) (fabExpanded ? -mainFab.getHeight() - 16 : mainFab.getHeight() + 16);
//                planetFab.animate().xBy(0).yBy(yTranslation).setInterpolator(new AccelerateDecelerateInterpolator()).start();
//                animalFab.animate().xBy(0).yBy(yTranslation * 2).setInterpolator(new AccelerateDecelerateInterpolator()).start();
//                plantFab.animate().xBy(0).yBy(yTranslation * 3).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                break;

            case R.id.planet_fab:
                break;
        }
    }

    @Override
    public void onDiscoverySelected(Discovery discovery) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.discovery_type), discovery.getDiscoveryType());
        bundle.putSerializable(getString(R.string.extra_discovery), discovery);
        bundle.putBoolean(getString(R.string.discovery_add), false);

        Intent intent = new Intent(MainActivity.this, DiscoveryActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onAddPlanet() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(getString(R.string.discovery_type), Enums.DiscoveryType.PLANET);
        bundle.putBoolean(getString(R.string.discovery_add), true);

        Intent intent = new Intent(MainActivity.this, DiscoveryActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
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
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlanetFragment.newInstance("", "", listener);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
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
                case 7:
                    return getResources().getString(R.string.plants);
                case 8:
                    return getResources().getString(R.string.plants);
            }
            return null;
        }
    }
}
