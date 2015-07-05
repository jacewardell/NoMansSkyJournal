package com.example.sadostrich.Controllers;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sadostrich.Fragments.AnimalFragment;
import com.example.sadostrich.Fragments.PlanetFragment;
import com.example.sadostrich.Fragments.PlantFragment;
import com.example.sadostrich.Models.DiscoveriesContract;
import com.example.sadostrich.Models.Planet;
import com.example.sadostrich.Utils.Enums;
import com.example.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends Activity implements PlanetFragment.OnFragmentInteractionListener, AnimalFragment.OnFragmentInteractionListener, PlantFragment.OnFragmentInteractionListener {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        // Set up the listener to change the tab when a different fragment is selected
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Set up the action bar tabs
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Listener to change fragment when a different tab is selected
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(sectionsPagerAdapter.getPageTitle(i)).setTabListener(tabListener));
        }

        // Load database planets
        new ReadPlanetDiscoveriesAsyncTask().execute();
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

        Intent intent = new Intent(MainActivity.this, AddDiscoveryActivity.class);
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add_planet) {
            intent.putExtra(getString(R.string.add_discovery_type), Enums.DiscoveryType.PLANET);
            startActivityForResult(intent, Enums.DiscoveryType.PLANET.ordinal());
        } else if (id == R.id.action_add_animal) {
            intent.putExtra(getString(R.string.add_discovery_type), Enums.DiscoveryType.ANIMAL);
            startActivityForResult(intent, Enums.DiscoveryType.ANIMAL.ordinal());
        } else if (id == R.id.action_add_plant) {
            intent.putExtra(getString(R.string.add_discovery_type), Enums.DiscoveryType.PLANT);
            startActivityForResult(intent, Enums.DiscoveryType.PLANT.ordinal());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Enums.DiscoveryType.PLANET.ordinal()) {
            // Reload database discoveries
            new ReadPlanetDiscoveriesAsyncTask().execute();
        } else if (requestCode == Enums.DiscoveryType.ANIMAL.ordinal()) {

        } else {

        }
        // Change to the current fragment
        viewPager.setCurrentItem(requestCode);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PlanetFragment.newInstance(new ArrayList<Planet>());
                case 1:
                    return AnimalFragment.newInstance();
                case 2:
                    return PlantFragment.newInstance();
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.planet_fragment_title).toUpperCase(l);
                case 1:
                    return getString(R.string.animal_fragment_title).toUpperCase(l);
                case 2:
                    return getString(R.string.plant_fragment_title).toUpperCase(l);
            }
            return null;
        }
    }

    public class ReadPlanetDiscoveriesAsyncTask extends AsyncTask<Void, Void, ArrayList<Planet>> {
        DiscoveriesContract.PlanetsTable.DiscoveriesDbHelper dbHelper = new DiscoveriesContract.PlanetsTable.DiscoveriesDbHelper
                (getApplicationContext());

        @Override
        protected ArrayList<Planet> doInBackground(Void... params) {
            return readPlanetDiscoveries();
        }

        private ArrayList<Planet> readPlanetDiscoveries() {
            ArrayList<Planet> allPlanets = new ArrayList<>();

            // Gets the data repository in write mode
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from teh database youw ill actually use after this query
            String[] projection = {DiscoveriesContract.PlanetsTable._ID, DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_DATE, DiscoveriesContract
                    .PlanetsTable.COLUMN_NAME_PLANET_COMMON_NAME, DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SCIENTIFIC_NAME, DiscoveriesContract
                    .PlanetsTable.COLUMN_NAME_PLANET_DESCRIPTION, DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_STORY, DiscoveriesContract
                    .PlanetsTable.COLUMN_NAME_PLANET_IMAGE_URL, DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SOLAR_SYSTEM_NAME,
                    DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SIZE};

            // How you want the results sorted in the resulting Cursor
            String sortOrder = DiscoveriesContract.PlanetsTable._ID + " DESC";

            Cursor cursor = db.query(DiscoveriesContract.PlanetsTable.PLANET_TABLE_NAME, projection, null, null, null, null, sortOrder);

            while (cursor.moveToNext()) {
                allPlanets.add(new Planet(cursor.getString(cursor.getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_COMMON_NAME)), cursor
                        .getString(cursor.getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SCIENTIFIC_NAME)), cursor
                        .getString(cursor.getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_DESCRIPTION)), cursor.getString
                        (cursor.getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_STORY)), cursor.getString(cursor
                        .getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_IMAGE_URL)), cursor.getString(cursor
                        .getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SOLAR_SYSTEM_NAME)), Enums.PlanetSize
                        .getByFriendlyName(cursor.getString(cursor.getColumnIndexOrThrow(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SIZE))
                        )));
            }
            cursor.close();

            return allPlanets;
        }

        @Override
        protected void onPostExecute(ArrayList<Planet> planets) {
            PlanetFragment planetFragment = (PlanetFragment) getFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" +
                    viewPager.getCurrentItem());
            planetFragment.updateDataSet(planets);
        }
    }
}