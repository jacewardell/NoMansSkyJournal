package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Adapters.AddDiscoverySpinnerAdapter;
import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;

import java.util.List;

/**
 *
 */
public class AddSystemView extends RelativeLayout {
    private Spinner systemType, planetCount, life;
    private Switch dangerous;
    private Button previousButton;
    private IAddSystemViewListener listener;

    public AddSystemView(Context context, IAddSystemViewListener listener) {
        super(context);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, IAddSystemViewListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, int defStyleAttr, IAddSystemViewListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddSystemViewListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddSystemViewListener listener) {
        inflate(getContext(), R.layout.view_add_system_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        systemType = (Spinner) findViewById(R.id.spinner_system_type);
        planetCount = (Spinner) findViewById(R.id.spinner_system_planet_count);
        life = (Spinner) findViewById(R.id.spinner_system_life);
        dangerous = (Switch) findViewById(R.id.swt_system_dangerous);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
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

        systemType.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupSystemPlanetCountSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getSystemPlanetCountOptions();
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        planetCount.setAdapter(adapter);

        planetCount.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupSystemLifeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getSystemLifeOptions();
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        life.setAdapter(adapter);

        life.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupButtons() {
        setupPreviousButton();
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

    public interface IAddSystemViewListener {
        void previousSelected();
    }
}
