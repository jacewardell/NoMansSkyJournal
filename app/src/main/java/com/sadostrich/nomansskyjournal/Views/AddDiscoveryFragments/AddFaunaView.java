package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

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
public class AddFaunaView extends RelativeLayout {
    private Spinner faunaSize, faunaBehavior;
    private Button previousButton, submitButton;
    private IAddDiscoveryListener listener;
    private CheckBox landCheckbox, skyCheckbox, caveCheckbox, oceanCheckbox, riverCheckbox, lakeCheckbox;

    public AddFaunaView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddFaunaView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddFaunaView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddFaunaView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_fauna_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        faunaSize = (Spinner) findViewById(R.id.spinner_fauna_size);
        faunaBehavior = (Spinner) findViewById(R.id.spinner_fauna_behavior);
        landCheckbox = (CheckBox) findViewById(R.id.cb_land);
        skyCheckbox = (CheckBox) findViewById(R.id.cb_sky);
        caveCheckbox = (CheckBox) findViewById(R.id.cb_cave);
        oceanCheckbox = (CheckBox) findViewById(R.id.cb_ocean);
        riverCheckbox = (CheckBox) findViewById(R.id.cb_river);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupFaunaSizeSpinner();
        setupFaunaBehaviorSpinner();
    }

    private void setupFaunaSizeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getFaunaSizeOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        faunaSize.setAdapter(adapter);

//        faunaSize.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupFaunaBehaviorSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getFaunaBehaviorOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        faunaBehavior.setAdapter(adapter);

//        faunaBehavior.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
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
