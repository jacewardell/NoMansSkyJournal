package com.sadostrich.nomansskyjournal.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Fragments.PlanetFragment;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.BottomTabsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PlanetFragment.OnFragmentInteractionListener, BottomTabsView.ITabSelectedListener {

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
    private ViewPager mViewPager;
    private boolean fabExpanded = false;
    private FloatingActionButton mainFab;
    private FloatingActionButton planetFab;
    private FloatingActionButton animalFab;
    private FloatingActionButton plantFab;
    private Toolbar toolbar;
    private BottomTabsView bottomTabsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewRefs();

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setupFabs();
        setupBottomTabs();

        retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);

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

    private void getViewRefs() {
        mViewPager = (ViewPager) findViewById(R.id.container);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomTabsView = (BottomTabsView) findViewById(R.id.bottom_tabs_view);

        mainFab = (FloatingActionButton) findViewById(R.id.fab);
        planetFab = (FloatingActionButton) findViewById(R.id.planet_fab);
        animalFab = (FloatingActionButton) findViewById(R.id.animal_fab);
        plantFab = (FloatingActionButton) findViewById(R.id.plant_fab);
    }

    private void setupFabs() {
        if (mainFab != null) {
            mainFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fabExpanded = !fabExpanded;

                    float rotationDegrees = fabExpanded ? 135 : 0;
                    mainFab.animate().rotation(rotationDegrees).setInterpolator(new AccelerateDecelerateInterpolator()).start();

                    float yTranslation = (float) (fabExpanded ? -mainFab.getHeight() - 16 : mainFab.getHeight() + 16);
                    planetFab.animate().xBy(0).yBy(yTranslation).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    animalFab.animate().xBy(0).yBy(yTranslation * 2).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    plantFab.animate().xBy(0).yBy(yTranslation * 3).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                }
            });
        }
    }

    private void setupBottomTabs() {
        bottomTabsView.setTabSelectedListener(this);
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlanetFragment.newInstance("", "");
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
