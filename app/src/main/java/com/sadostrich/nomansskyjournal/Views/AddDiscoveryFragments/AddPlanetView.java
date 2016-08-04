package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.sadostrich.nomansskyjournal.Adapters.AddDiscoverySpinnerAdapter;
import com.sadostrich.nomansskyjournal.Interfaces.IAddDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;

import java.util.List;

/**
 *
 */
public class AddPlanetView extends RelativeLayout {
    private Spinner planetTemperature, planetResources, planetLife;
    private Switch startingPlanet;
    private Button previousButton, submitButton;
    private IAddDiscoveryListener listener;
    private CheckBox toxicCheckbox, hotCheckbox, coldCheckbox, highPressureCheckbox, lowPressureCheckbox;

    public AddPlanetView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddPlanetView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddPlanetView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddPlanetView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_planet_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        planetTemperature = (Spinner) findViewById(R.id.spinner_planet_temperature);
        planetResources = (Spinner) findViewById(R.id.spinner_planet_resources);
        planetLife = (Spinner) findViewById(R.id.spinner_planet_life);
        startingPlanet = (Switch) findViewById(R.id.swt_starting_planet);
        toxicCheckbox = (CheckBox) findViewById(R.id.cb_toxic);
        hotCheckbox = (CheckBox) findViewById(R.id.cb_hot);
        coldCheckbox = (CheckBox) findViewById(R.id.cb_cold);
        highPressureCheckbox = (CheckBox) findViewById(R.id.cb_high_pressure);
        lowPressureCheckbox = (CheckBox) findViewById(R.id.cb_low_pressure);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupPlanetTemperatureSpinner();
        setupPlanetResourcesSpinner();
        setupPlanetLifeSpinner();
    }

    private void setupPlanetTemperatureSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getPlanetTemperatureOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        planetTemperature.setAdapter(adapter);

        planetTemperature.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupPlanetResourcesSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getPlanetResourcesOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        planetResources.setAdapter(adapter);

        planetResources.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupPlanetLifeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoveryLifeOptions(getContext(), R.color.planet_purple_dark);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        planetLife.setAdapter(adapter);

        planetLife.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupButtons() {
        setupPreviousButton();
        setupSubmitButton();
    }

    private void setupPreviousButton() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.previousSelected();
                }
            }
        });
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    // TODO: setup callbacks for submitting a discovery
                }
            }
        });
    }
}
