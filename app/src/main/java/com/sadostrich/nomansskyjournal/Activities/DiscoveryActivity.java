package com.sadostrich.nomansskyjournal.Activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sadostrich.nomansskyjournal.Fragments.AddAnimalFragment;
import com.sadostrich.nomansskyjournal.Fragments.AddPlanetFragment;
import com.sadostrich.nomansskyjournal.Fragments.ViewPlanetFragment;
import com.sadostrich.nomansskyjournal.Models.Animal;
import com.sadostrich.nomansskyjournal.Models.DiscoveriesContract;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.Planet;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;

/**
 * Activity that controls fragments used to add a new discovery
 * Is populated with either a planet, animal, or plant fragment
 */
public class DiscoveryActivity extends FragmentActivity {
    private Enums.DiscoveryType discoveryType = Enums.DiscoveryType.PLANET;
    private boolean addDiscovery = true;
    private Discovery discovery;

    private Fragment fragmentToShow = null;

    private boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discovery);

        Intent intent = getIntent();
        discoveryType = (Enums.DiscoveryType) intent.getSerializableExtra(getString(R.string.discovery_type));
        addDiscovery = intent.getBooleanExtra(getString(R.string.discovery_add), true);

        if (!addDiscovery) {
            discovery = (Discovery) intent.getSerializableExtra(getString(R.string.discovery));
        }

        showFragment();
    }

    /**
     * Shows the current fragment based on the user-chosen discovery type
     */
    private void showFragment() {
        switch (discoveryType) {
            case PLANET:
//                getActionBar().setTitle(getResources().getString(R.string.add_planet));

                // If the user is adding a discovery
                if (addDiscovery) {
                    fragmentToShow = new AddPlanetFragment();
                } else {
                    fragmentToShow = new ViewPlanetFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(getString(R.string.discovery), discovery);
                    fragmentToShow.setArguments(bundle);
                }
                break;
            case ANIMAL:
//                getActionBar().setTitle(getResources().getString(R.string.add_animal));
                fragmentToShow = new AddAnimalFragment();
                break;
//            case PLANT:
//                getActionBar().setTitle(getResources().getString(R.string.add_plant));
//                fragmentToShow = new AddPlantFragment();
//                break;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragmentToShow).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_add_discovery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        if (id == R.id.action_save) {
//            switch (discoveryType) {
//                case PLANET:
//                    new InsertNewDiscoveryAsyncTask().execute(new Planet(((AddPlanetFragment) fragmentToShow)));
//                    break;
//                case ANIMAL:
////                    new InsertNewDiscoveryAsyncTask().execute(new Animal(((AddAnimalFragment)fragmentToShow)));
//                    break;
//                case PLANT:
////                    new InsertNewDiscoveryAsyncTask().execute(new Plant(((AddPlantFragment)fragmentToShow)));
//                    break;
//            }
//        }

        return super.onOptionsItemSelected(item);
    }
}
