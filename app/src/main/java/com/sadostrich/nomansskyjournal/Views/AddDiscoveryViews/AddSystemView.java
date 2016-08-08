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
public class AddSystemView extends RelativeLayout {
    private Spinner spnrSystemType, spnrPlanetCount;
    private LifeSpinnerItem lifeSpinnerItem;
    private CheckBox cbDangerous;
    private Button btnPrevious, btnSubmit;
    private IAddDiscoveryListener listener;

    public AddSystemView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_system_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        spnrSystemType = (Spinner) findViewById(R.id.spinner_system_type);
        spnrPlanetCount = (Spinner) findViewById(R.id.spinner_system_planet_count);
        lifeSpinnerItem = (LifeSpinnerItem) findViewById(R.id.layout_life_dangerous_spinners);
        cbDangerous = (CheckBox) findViewById(R.id.swt_system_dangerous);
        btnPrevious = (Button) findViewById(R.id.btn_previous_add_discovery);
        btnSubmit = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupSystemTypeSpinner();
        setupSystemPlanetCountSpinner();
        setupSystemLifeSpinner();
    }

    private void setupSystemTypeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getSystemTypeOptions();
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrSystemType.setAdapter(adapter);

//        spnrSystemType.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupSystemPlanetCountSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getSystemPlanetCountOptions();
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrPlanetCount.setAdapter(adapter);

//        spnrPlanetCount.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupSystemLifeSpinner() {
        lifeSpinnerItem.setPageAccentColor(R.color.system_purple);
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
                    Map<String, Object> properties = createSystemPropertiesMap();
                    listener.submitDiscovery(Enums.DiscoveryType.SOLAR_SYSTEM.getNameString(getContext()), createSystemPropertiesMap());
                }
            }
        });
    }

    private HashMap<String, Object> createSystemPropertiesMap() {
        HashMap<String, Object> systemProperties = new HashMap<>();
        systemProperties.put("system-type", getSystemTypeValue());
        systemProperties.put("number-of-planets", getSystemPlanetCountValue());
        systemProperties.put("life", lifeSpinnerItem.getLifeFound());
        systemProperties.put("inhabitants", lifeSpinnerItem.getInhabitantsString());
        systemProperties.put("dangerous", cbDangerous.isChecked());

        return systemProperties;
    }

    private String getSystemTypeValue() {
        CustomSpinnerObject systemType = (CustomSpinnerObject) spnrSystemType.getSelectedItem();
        return "" + ((Enums.SystemType) systemType.getObject()).getNonsensicalWebString() + "";
    }

    private String getSystemPlanetCountValue() {
        CustomSpinnerObject systemType = (CustomSpinnerObject) spnrPlanetCount.getSelectedItem();
        return (String) systemType.getObject();
    }
}
