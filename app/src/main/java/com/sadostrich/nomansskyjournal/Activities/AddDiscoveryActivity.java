package com.sadostrich.nomansskyjournal.Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Interfaces.IAddDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.ConfigObjects.ConfigBaseObject;
import com.sadostrich.nomansskyjournal.Models.ConfigObjects.ConfigObject;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddDiscoveryMainView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddFaunaView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddFloraView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddPlanetView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddStarView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddStationView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddStructureView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments.AddSystemView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity that controls fragments used to add a new discovery
 * Is populated with either a planet, animal, or plant fragment
 */
public class AddDiscoveryActivity extends AppCompatActivity implements AddDiscoveryMainView.IAddDiscoveryMainViewListener, IAddDiscoveryListener {
    private static final String TAG = "AddDiscoveryActivity";

    private ViewPager viewpager;

    private Enums.DiscoveryType discoveryType;

    private AddDiscoveryMainView addDiscoveryMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discovery);

        Intent intent = getIntent();
        discoveryType = (Enums.DiscoveryType) intent.getSerializableExtra(getString(R.string.extra_discovery_type));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getDiscoveryTitle());
        }

        setupViewPager();

        showFragments();

//        Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);
//        nmsOriginsService.getAddDiscoveryFields().enqueue(new Callback<ConfigBaseObject>() {
//            @Override
//            public void onResponse(Call<ConfigBaseObject> call, Response<ConfigBaseObject> response) {
//                Log.d(TAG, "onResponse: ");
//            }
//
//            @Override
//            public void onFailure(Call<ConfigBaseObject> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });

    }

    private void setupViewPager() {
        viewpager = (ViewPager) findViewById(R.id.add_discovery_viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        previousButton.setVisibility(View.INVISIBLE);
//                        nextButton.setText(getString(R.string.next));
                        break;

                    // Submit discovery to the server
                    case 1:
//                        previousButton.setVisibility(View.VISIBLE);
//                        nextButton.setText(getString(R.string.submit));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Shows the current fragment based on the user-chosen discovery type
     */
    private void showFragments() {
        View viewToShow = null;
        switch (discoveryType) {
            case SOLAR_SYSTEM:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.SystemDialogTheme, R.color.system_purple);
                viewToShow = new AddSystemView(getApplicationContext(), this);
                break;

            case STAR:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.StarDialogTheme, R.color.star_yellow);
                viewToShow = new AddStarView(getApplicationContext(), this);
                break;

            case STATION:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.StationDialogTheme, R.color.station_green);
                viewToShow = new AddStationView(getApplicationContext(), this);
                break;

            case PLANET:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.PlanetDialogTheme, R.color.planet_purple);
                viewToShow = new AddPlanetView(getApplicationContext(), this);
                break;

            case FAUNA:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.FaunaDialogTheme, R.color.fauna_red);
                viewToShow = new AddFaunaView(getApplicationContext(), this);
                break;

            case FLORA:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.FloraDialogTheme, R.color.flora_blue);
                viewToShow = new AddFloraView(getApplicationContext(), this);
                break;

            case STRUCTURE:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.StructureDialogTheme, R.color.structure_green);
                viewToShow = new AddStructureView(getApplicationContext(), this);
                break;

            case ITEM:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.ItemDialogTheme, R.color.item_red);
                viewToShow = new AddSystemView(getApplicationContext(), this);
                break;

            case SHIP:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.ShipDialogTheme, R.color.ship_gray);
                viewToShow = new AddSystemView(getApplicationContext(), this);
                break;
        }

        addViewsToViewPager(addDiscoveryMainView, viewToShow);
    }

    private AddDiscoveryMainView getAddDiscoveryMainView(@StyleRes int dialogTheme, @ColorRes int pageAccentColor) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(pageAccentColor)));
        }
        return new AddDiscoveryMainView(getApplicationContext(), this, dialogTheme, pageAccentColor);
    }

    private void addViewsToViewPager(AddDiscoveryMainView mainView, View detailsView) {
        viewpager.removeAllViews();
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        viewpager.addView(mainView, 0, params);
//        viewpager.addView(detailsView, 0, params);
        List<View> views = new ArrayList<>();
        views.add(mainView);
        views.add(detailsView);
        AddDiscoveryPagerAdapter adapter = new AddDiscoveryPagerAdapter(views);
        viewpager.setAdapter(adapter);
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

    public int getDiscoveryTitle() {
        int title = R.string.add_solar_system;
        switch (discoveryType) {
            case SOLAR_SYSTEM:
                title = R.string.add_solar_system;
                break;
            case STAR:
                title = R.string.add_star;
                break;
            case STATION:
                title = R.string.add_station;
                break;
            case PLANET:
                title = R.string.add_planet;
                break;
            case FAUNA:
                title = R.string.add_fauna;
                break;
            case FLORA:
                title = R.string.add_flora;
                break;
            case STRUCTURE:
                title = R.string.add_structure;
                break;
            case ITEM:
                title = R.string.add_item;
                break;
            case SHIP:
                title = R.string.add_ship;
                break;
        }
        return title;
    }

    @Override
    public void nextClicked() {
        viewpager.setCurrentItem(1);
    }

    @Override
    public void previousSelected() {
        viewpager.setCurrentItem(0);
    }

    public class AddDiscoveryPagerAdapter extends PagerAdapter {

        private final List<View> views;

        public AddDiscoveryPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(views.get(position));
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
