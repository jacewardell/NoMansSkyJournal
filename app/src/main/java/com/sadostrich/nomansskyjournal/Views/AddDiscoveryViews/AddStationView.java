package com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
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
public class AddStationView extends RelativeLayout {
    private Spinner spnrStationSize;
    private LifeSpinnerItem lifeSpinnerItem;
    private Button btnPrevious, btnSubmit;
    private IAddDiscoveryListener listener;

    public AddStationView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddStationView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddStationView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddStationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_station_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        spnrStationSize = (Spinner) findViewById(R.id.spinner_station_size);
        lifeSpinnerItem = (LifeSpinnerItem) findViewById(R.id.station_life_spinner_item);
        btnPrevious = (Button) findViewById(R.id.btn_previous_add_discovery);
        btnSubmit = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupSizeSpinner();
        setupStationLifeSpinner();

    }

    private void setupSizeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoverySizeOptions(getContext(), R.color.station_green);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrStationSize.setAdapter(adapter);
    }

    private void setupStationLifeSpinner() {
        lifeSpinnerItem.setPageAccentColor(R.color.station_green);
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
                    Map<String, Object> properties = createStationPropertiesMap();
                    listener.submitDiscovery(Enums.DiscoveryType.STATION.getNameString(getContext()), createStationPropertiesMap());
                }
            }
        });
    }

    private HashMap<String, Object> createStationPropertiesMap() {
        HashMap<String, Object> systemProperties = new HashMap<>();
        systemProperties.put("size", getStationSizeValue());
        systemProperties.put("life", lifeSpinnerItem.getLifeFound());

        return systemProperties;
    }

    private String getStationSizeValue() {
        CustomSpinnerObject starSize = (CustomSpinnerObject) spnrStationSize.getSelectedItem();
        return (String) starSize.getObject();
    }
}
