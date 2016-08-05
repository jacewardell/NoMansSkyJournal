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
import com.sadostrich.nomansskyjournal.Views.LifeSpinnerItem;

import java.util.List;

/**
 *
 */
public class AddSystemView extends RelativeLayout {
    private Spinner systemType, planetCount;
    private LifeSpinnerItem lifeSpinnerItem;
    private CheckBox dangerous;
    private Button previousButton, submitButton;
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
        systemType = (Spinner) findViewById(R.id.spinner_system_type);
        planetCount = (Spinner) findViewById(R.id.spinner_system_planet_count);
        lifeSpinnerItem = (LifeSpinnerItem) findViewById(R.id.layout_life_dangerous_spinners);
        dangerous = (CheckBox) findViewById(R.id.swt_system_dangerous);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupSystemTypeSpinner();
        setupSystemPlanetCountSpinner();
        setupSystemLifeSpinner();
    }

    private void setupSystemTypeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getSystemTypeOptions();
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        systemType.setAdapter(adapter);

//        systemType.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupSystemPlanetCountSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getSystemPlanetCountOptions();
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        planetCount.setAdapter(adapter);

//        planetCount.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
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
                if(listener != null) {
                    // TODO: setup callbacks for submitting a discovery
                }
            }
        });
    }
}
