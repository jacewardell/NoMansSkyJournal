package com.sadostrich.nomansskyjournal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sadostrich.nomansskyjournal.Fragments.AddDiscoveryFragments.AddDiscoveryMainView;
import com.sadostrich.nomansskyjournal.Fragments.AddDiscoveryFragments.AddSystemView;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that controls fragments used to add a new discovery
 * Is populated with either a planet, animal, or plant fragment
 */
public class AddDiscoveryActivity extends AppCompatActivity implements AddDiscoveryMainView.IAddDiscoveryMainViewListener, AddSystemView.IAddSystemViewListener {
    private ViewPager viewpager;

    private Enums.DiscoveryType discoveryType;

    private AddDiscoveryMainView addDiscoveryMainView;
    private AddSystemView addSystemView;

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
        setupNavButtons();

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

    private void setupNavButtons() {
//        nextButton = (Button) findViewById(R.id.btn_next_add_discovery);
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (viewpager.getCurrentItem()) {
//                    // Show the details view
//                    case 0:
//                        viewpager.setCurrentItem(1);
//                        break;
//
//                    // Submit discovery to the server
//                    case 1:
//                        break;
//                }
//            }
//        });

//        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
//        previousButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (viewpager.getCurrentItem()) {
//                    case 1:
//                        viewpager.setCurrentItem(0);
//                        break;
//                }
//            }
//        });
    }

    /**
     * Shows the current fragment based on the user-chosen discovery type
     */
    private void showFragments() {

        switch (discoveryType) {
            case SOLAR_SYSTEM:
                addDiscoveryMainView = new AddDiscoveryMainView(getApplicationContext(), this);
                addSystemView = new AddSystemView(getApplicationContext(), this);
                break;
            case STAR:
                break;
            case STATION:
                break;
            case PLANET:
                break;
            case FAUNA:
                break;
            case FLORA:
                break;
            case STRUCTURE:
                break;
            case ITEM:
                break;
            case SHIP:
                break;
        }

        addViewsToViewPager(addDiscoveryMainView, addSystemView);
    }

    private void addViewsToViewPager(AddDiscoveryMainView mainView, AddSystemView detailsView) {
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
