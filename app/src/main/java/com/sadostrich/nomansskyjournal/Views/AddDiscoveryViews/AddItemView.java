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
public class AddItemView extends RelativeLayout {
    private Spinner itemTypeSpinner, itemRaritySpinner;
    private CheckBox itemRequiresBlueprintCheckbox;
    private Button previousButton, submitButton;
    private IAddDiscoveryListener listener;

    public AddItemView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddItemView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddItemView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_item_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        itemTypeSpinner = (Spinner) findViewById(R.id.spinner_item_type);
        itemRaritySpinner = (Spinner) findViewById(R.id.spinner_item_rarity);
        itemRequiresBlueprintCheckbox = (CheckBox) findViewById(R.id.cb_item_requires_blueprint);
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        submitButton = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupItemTypeSpinner();
        setupItemRaritySpinner();
    }

    private void setupItemTypeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getItemTypeOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        itemTypeSpinner.setAdapter(adapter);
    }

    private void setupItemRaritySpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getItemRarityOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        itemRaritySpinner.setAdapter(adapter);
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
