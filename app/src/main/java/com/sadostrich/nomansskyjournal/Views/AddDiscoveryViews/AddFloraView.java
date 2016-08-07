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
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;

import java.util.List;

/**
 *
 */
public class AddFloraView extends RelativeLayout {
    private Spinner floraSize;
    private Button previousButton, submitButton;
    private IAddDiscoveryListener listener;
    private CheckBox poisonousCheckbox, resourcesCheckbox, carnivorousCheckbox;

    public AddFloraView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddFloraView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddFloraView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddFloraView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_flora_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        floraSize = (Spinner) findViewById(R.id.spinner_flora_size);
        poisonousCheckbox = (CheckBox) findViewById(R.id.cb_poisonous);
        resourcesCheckbox = (CheckBox) findViewById(R.id.cb_resources);
        carnivorousCheckbox = (CheckBox) findViewById(R.id.cb_carnivorous);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupFloraSizeSpinner();
    }

    private void setupFloraSizeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoverySizeOptions(getContext(), R.color.flora_blue);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        floraSize.setAdapter(adapter);

//        floraSize.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
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
