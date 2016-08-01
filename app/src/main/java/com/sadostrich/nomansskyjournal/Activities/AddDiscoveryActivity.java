package com.sadostrich.nomansskyjournal.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;

/**
 * Activity that controls fragments used to add a new discovery
 * Is populated with either a planet, animal, or plant fragment
 */
public class AddDiscoveryActivity extends AppCompatActivity {
//    private Enums.DiscoveryType discoveryType = Enums.DiscoveryType.PLANET;
    private Discovery discovery;

    private Fragment fragmentToShow = null;

    private boolean editing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discovery);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setTitle(R.string.add_fauna);
		}

        Intent intent = getIntent();
//        discoveryType = (Enums.DiscoveryType) intent.getSerializableExtra(getString(R.string.discovery_type));

        showFragment();
    }

    /**
     * Shows the current fragment based on the user-chosen discovery type
     */
    private void showFragment() {
//        switch (discoveryType) {
//            case PLANET:
////                getActionBar().setTitle(getResources().getString(R.string.add_planet));
//
//                // If the user is adding a discovery
//                if (addDiscovery) {
//                    fragmentToShow = new AddPlanetFragment();
//                } else {
//                    fragmentToShow = new ViewPlanetFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(getString(R.string.discovery), discovery);
//                    fragmentToShow.setArguments(bundle);
//                }
//                break;
//            case ANIMAL:
////                getActionBar().setTitle(getResources().getString(R.string.add_animal));
//                fragmentToShow = new AddAnimalFragment();
//                break;
////            case PLANT:
////                getActionBar().setTitle(getResources().getString(R.string.add_plant));
////                fragmentToShow = new AddPlantFragment();
////                break;
//        }
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.add_discovery_viewpager, fragmentToShow).commit();
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

		switch (id) {
			case android.R.id.home:
				this.finish();
				break;
		}

        return super.onOptionsItemSelected(item);
    }
}
