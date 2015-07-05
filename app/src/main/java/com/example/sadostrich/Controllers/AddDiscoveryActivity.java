package com.example.sadostrich.Controllers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sadostrich.Fragments.AddAnimalFragment;
import com.example.sadostrich.Fragments.AddPlanetFragment;
import com.example.sadostrich.Fragments.AddPlantFragment;
import com.example.sadostrich.Models.Animal;
import com.example.sadostrich.Models.DiscoveriesContract;
import com.example.sadostrich.Models.Discovery;
import com.example.sadostrich.Models.Planet;
import com.example.sadostrich.Utils.Enums;
import com.example.sadostrich.nomansskyjournal.R;

/**
 * Activity that controls fragments used to add a new discovery
 * Is populated with either a planet, animal, or plant fragment
 */
public class AddDiscoveryActivity extends FragmentActivity {
    private Enums.DiscoveryType discoveryType = Enums.DiscoveryType.PLANET;
    private Fragment fragmentToShow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discovery);

        Intent intent = getIntent();
        discoveryType = (Enums.DiscoveryType) intent.getSerializableExtra(getString(R.string.add_discovery_type));
        showFragment(discoveryType);
    }

    /**
     * Shows the current fragment based on the user-chosen discovery type
     *
     * @param discoveryType type of discovery to add
     */
    private void showFragment(Enums.DiscoveryType discoveryType) {
        switch (discoveryType) {
            case PLANET:
                getActionBar().setTitle(getResources().getString(R.string.add_planet));
                fragmentToShow = new AddPlanetFragment();
                break;
            case ANIMAL:
                getActionBar().setTitle(getResources().getString(R.string.add_animal));
                fragmentToShow = new AddAnimalFragment();
                break;
            case PLANT:
                getActionBar().setTitle(getResources().getString(R.string.add_plant));
                fragmentToShow = new AddPlantFragment();
                break;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragmentToShow).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_discovery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_save) {
            DiscoveriesContract.PlanetsTable.DiscoveriesDbHelper dbHelper = new DiscoveriesContract.PlanetsTable.DiscoveriesDbHelper(this);
            switch (discoveryType) {
                case PLANET:
                    new InsertNewDiscoveryAsyncTask().execute(new Planet(((AddPlanetFragment) fragmentToShow)));
                    break;
                case ANIMAL:
//                    new InsertNewDiscoveryAsyncTask().execute(new Animal(((AddAnimalFragment)fragmentToShow)));
                    break;
                case PLANT:
//                    new InsertNewDiscoveryAsyncTask().execute(new Plant(((AddPlantFragment)fragmentToShow)));
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public class InsertNewDiscoveryAsyncTask extends AsyncTask<Discovery, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Discovery... params) {
            return insertNewDiscovery((Discovery) params[0]);
        }

        public boolean insertNewDiscovery(Discovery discovery) {
            long newRowId = -1;

            DiscoveriesContract.PlanetsTable.DiscoveriesDbHelper dbHelper = new DiscoveriesContract.PlanetsTable.DiscoveriesDbHelper
                    (getApplicationContext());
            // Gets the data repository in write mode
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (discovery instanceof Planet) {
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_DATE, discovery.getDate());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_COMMON_NAME, discovery.getCommonName());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SCIENTIFIC_NAME, discovery.getScientificName());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_DESCRIPTION, discovery.getDescription());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_STORY, discovery.getStory());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_IMAGE_URL, discovery.getImageUrl());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SOLAR_SYSTEM_NAME, ((Planet) discovery).getSolarSystemName());
                values.put(DiscoveriesContract.PlanetsTable.COLUMN_NAME_PLANET_SIZE, ((Planet) discovery).getSize().toString());

                // Insert the new row, returning the primary key value of the new row
                newRowId = db.insert(DiscoveriesContract.PlanetsTable.PLANET_TABLE_NAME, DiscoveriesContract.PlanetsTable.COLUMN_NAME_NULLABLE,
                        values);
            } else if (discovery instanceof Animal) {

            } else {

            }

            if (newRowId != -1) {
                Log.d("DiscoveriesContract", "" + newRowId);
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            setResult(discoveryType.ordinal());
            finish();
        }
    }
}
