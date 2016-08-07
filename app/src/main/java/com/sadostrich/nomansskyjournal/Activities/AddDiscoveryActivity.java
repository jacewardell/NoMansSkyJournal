package com.sadostrich.nomansskyjournal.Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
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
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Interfaces.IAddDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddDiscoveryMainView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddFaunaView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddFloraView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddItemView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddPlanetView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddStarView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddStationView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddStructureView;
import com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews.AddSystemView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private String discoveryName, youtubeUrl, description, discoveredAt;
    private List<String> tags;

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
                viewToShow = new AddItemView(getApplicationContext(), this);
                break;

            case SHIP:
                addDiscoveryMainView = getAddDiscoveryMainView(R.style.ShipDialogTheme, R.color.ship_gray);
                addDiscoveryMainView.setNextButtonAsSubmit(this);
                addOneViewToViewPager(addDiscoveryMainView);
                return;
        }

        addTwoViewsToViewPager(addDiscoveryMainView, viewToShow);
    }

    private AddDiscoveryMainView getAddDiscoveryMainView(@StyleRes int dialogTheme, @ColorRes int pageAccentColor) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(pageAccentColor)));
        }
        return new AddDiscoveryMainView(getApplicationContext(), this, dialogTheme, pageAccentColor);
    }

    private void addOneViewToViewPager(AddDiscoveryMainView mainView) {
        viewpager.removeAllViews();
        List<View> views = new ArrayList<>();
        views.add(mainView);
        AddDiscoveryPagerAdapter adapter = new AddDiscoveryPagerAdapter(views);
        viewpager.setAdapter(adapter);
    }

    private void addTwoViewsToViewPager(AddDiscoveryMainView mainView, View detailsView) {
        viewpager.removeAllViews();
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
    public void nextClicked(String name, String youtubeUrl, String description, List<String> tags, String discoveredAt) {
        discoveryName = name;

        if (discoveryName.isEmpty()) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                Snackbar.make(contentView, R.string.error_add_disc_name_missing, Snackbar.LENGTH_LONG).show();
            }
        } else {
            this.youtubeUrl = youtubeUrl;
            this.description = description;
            this.tags = tags;
            this.discoveredAt = discoveredAt;

            viewpager.setCurrentItem(1);
        }
    }

    @Override
    public void showErrorSnackbar() {
        View contentView = findViewById(android.R.id.content);
        if (contentView != null) {
            Snackbar.make(contentView, R.string.error_add_disc_name_missing, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void previousSelected() {
        viewpager.setCurrentItem(0);
    }

    @Override
    public void submitDiscovery(String type, Map<String, Object> properties) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);
        nmsOriginsService.saveDiscovery(Authentication.getInstance().getCookie(), NMSOriginsServiceHelper.createSaveDiscoveryBodyHashMap(type,
                properties, tags, discoveredAt, discoveryName, youtubeUrl, description)).enqueue(new Callback<Discovery>() {
            @Override
            public void onResponse(Call<Discovery> call, Response<Discovery> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Discovery> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public void submitShipDiscovery(String type, Map<String, Object> properties, ArrayList<String> strings, List<String> tags, String discoveredAt,
                                    String name, String youtubeUrl, String description) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);
        nmsOriginsService.saveDiscovery(Authentication.getInstance().getCookie(), NMSOriginsServiceHelper.createSaveDiscoveryBodyHashMap(type,
                properties, tags, discoveredAt, name, youtubeUrl, description)).enqueue(new Callback<Discovery>() {
            @Override
            public void onResponse(Call<Discovery> call, Response<Discovery> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Discovery> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
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
