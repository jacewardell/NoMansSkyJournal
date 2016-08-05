package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

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
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;
import com.sadostrich.nomansskyjournal.Views.LifeSpinnerItem;

import java.util.List;

/**
 *
 */
public class AddStructureView extends RelativeLayout {
    private Spinner structureSize;
    private Spinner structureType;
    private LifeSpinnerItem lifeSpinnerItem;
    private Spinner structureTrading;
    private Spinner structureHasSave;
    private Button previousButton, submitButton;
    private IAddDiscoveryListener listener;

    public AddStructureView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddStructureView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddStructureView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddStructureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_structure_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        structureSize = (Spinner) findViewById(R.id.spinner_structure_size);
        structureType = (Spinner) findViewById(R.id.spinner_structure_type);
        lifeSpinnerItem = (LifeSpinnerItem) findViewById(R.id.structure_life_view);
        structureTrading = (Spinner) findViewById(R.id.spinner_structure_trading);
        structureHasSave = (Spinner) findViewById(R.id.spinner_structure_has_save);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupStructureSizeSpinner();
        setupStructureTypeSpinner();
        setupSystemLifeSpinner();
        setupStructureTradingSpinner();
        setupStructureHasSaveSpinner();
    }

    private void setupStructureSizeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoverySizeOptions(getContext(), R.color.structure_green);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        structureSize.setAdapter(adapter);

//        structureSize.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupStructureTypeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getStructureTypeOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        structureType.setAdapter(adapter);

//        structureType.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupSystemLifeSpinner() {
        lifeSpinnerItem.setPageAccentColor(R.color.structure_green);
        lifeSpinnerItem.showLifeOptions(false);
    }

    private void setupStructureTradingSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoveryLifeOptions(getContext(), R.color.structure_green);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        structureTrading.setAdapter(adapter);
    }

    private void setupStructureHasSaveSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoveryLifeOptions(getContext(), R.color.structure_green);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        structureHasSave.setAdapter(adapter);
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
