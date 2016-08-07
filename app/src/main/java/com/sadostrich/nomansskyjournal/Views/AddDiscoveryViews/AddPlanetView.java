package com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.sadostrich.nomansskyjournal.Adapters.AddDiscoverySpinnerAdapter;
import com.sadostrich.nomansskyjournal.Interfaces.IAddDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;
import com.sadostrich.nomansskyjournal.Views.LifeSpinnerItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class AddPlanetView extends RelativeLayout {
    private Spinner spnrPlanetTemp, spnrPlanetResources;
    private LifeSpinnerItem lifeSpinnerItem;
    private CheckBox cbStartingPlanet;
    private Button btnPrevious, btnSubmit;
    private CheckBox cbToxic, cbHot, cbCold, cbHighPressure, cbLowPressure;
    private IAddDiscoveryListener listener;

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
        spnrPlanetTemp = (Spinner) findViewById(R.id.spinner_planet_temperature);
        spnrPlanetResources = (Spinner) findViewById(R.id.spinner_planet_resources);
        lifeSpinnerItem = (LifeSpinnerItem) findViewById(R.id.layout_linear2);
        cbStartingPlanet = (CheckBox) findViewById(R.id.cb_starting_planet);
        cbToxic = (CheckBox) findViewById(R.id.cb_toxic);
        cbHot = (CheckBox) findViewById(R.id.cb_hot);
        cbCold = (CheckBox) findViewById(R.id.cb_cold);
        cbHighPressure = (CheckBox) findViewById(R.id.cb_high_pressure);
        cbLowPressure = (CheckBox) findViewById(R.id.cb_low_pressure);
        btnPrevious = (Button) findViewById(R.id.btn_previous_add_discovery);
        btnSubmit = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupPlanetTemperatureSpinner();
        setupPlanetResourcesSpinner();
        setupPlanetLifeSpinner();
    }

    private void setupPlanetTemperatureSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getPlanetTemperatureOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrPlanetTemp.setAdapter(adapter);
    }

    private void setupPlanetResourcesSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getPlanetResourcesOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrPlanetResources.setAdapter(adapter);
    }

    private void setupPlanetLifeSpinner() {
        lifeSpinnerItem.setPageAccentColor(R.color.planet_purple);
        lifeSpinnerItem.showLifeOptions(false);
    }

    private void setupButtons() {
        setupPreviousButton();
        setupSubmitButton();
    }

    private void setupPreviousButton() {
        btnPrevious.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.previousSelected();
                }
            }
        });
    }

    private void setupSubmitButton() {
        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    Map<String, Object> properties = createPlanetPropertiesMap();
                    listener.submitDiscovery(Enums.DiscoveryType.PLANET.getNameString(getContext()), createPlanetPropertiesMap());
                }
            }
        });
    }

    private HashMap<String, Object> createPlanetPropertiesMap() {
        HashMap<String, Object> systemProperties = new HashMap<>();
        systemProperties.put("temperature", getPlanetTempValue());
        systemProperties.put("resources", getPlanetResourcesValue());
        systemProperties.put("life", lifeSpinnerItem.getLifeFound());
        systemProperties.put("inhabitants", lifeSpinnerItem.getInhabitantsString());
        systemProperties.put("dangerous", cbToxic.isChecked());
        systemProperties.put("dangerous", cbHot.isChecked());
        systemProperties.put("dangerous", cbCold.isChecked());
        systemProperties.put("dangerous", cbHighPressure.isChecked());
        systemProperties.put("dangerous", cbLowPressure.isChecked());

        return systemProperties;
    }

    private String getPlanetTempValue() {
        CustomSpinnerObject planetTemp = (CustomSpinnerObject) spnrPlanetTemp.getSelectedItem();
        return (String) planetTemp.getObject();
    }

    private String getPlanetResourcesValue() {
        CustomSpinnerObject planetResources = (CustomSpinnerObject) spnrPlanetResources.getSelectedItem();
        return (String) planetResources.getObject();
    }
}
