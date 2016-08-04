package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

import android.content.Context;
import android.graphics.PorterDuff;
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

import java.util.List;

/**
 *
 */
public class AddStationView extends RelativeLayout {
    private Spinner stationSize, life;
    private Button previousButton, submitButton;
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
        stationSize = (Spinner) findViewById(R.id.spinner_station_size);
        life = (Spinner) findViewById(R.id.spinner_station_life);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupSizeSpinner();
        setupLifeSpinner();

    }

    private void setupSizeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoverySizeOptions(getContext(), R.color.station_green_dark);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        stationSize.setAdapter(adapter);

        stationSize.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupLifeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoveryLifeOptions(getContext(), R.color.station_green_dark);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        life.setAdapter(adapter);

        life.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
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
