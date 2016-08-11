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

import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class AddStarView extends RelativeLayout {
    private Spinner spnrStarSize, spnrStarType;
    private Button btnPrevious, btnSubmit;
    private IAddDiscoveryListener listener;

    public AddStarView(Context context, IAddDiscoveryListener listener) {
        super(context);
        init(listener);
    }

    public AddStarView(Context context, AttributeSet attrs, IAddDiscoveryListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddStarView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddStarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryListener listener) {
        inflate(getContext(), R.layout.view_add_star_discovery, this);
        getViewRefs();
        setupSpinners();
        setupButtons();
        this.listener = listener;
    }

    private void getViewRefs() {
        spnrStarSize = (Spinner) findViewById(R.id.spinner_star_size);
        spnrStarType = (Spinner) findViewById(R.id.spinner_star_type);
        btnPrevious = (Button) findViewById(R.id.btn_previous_add_discovery);
        btnSubmit = (Button) findViewById(R.id.btn_submit_discovery);
    }

    private void setupSpinners() {
        setupStarSizeSpinner();
        setupStarTypeSpinner();

    }

    private void setupStarSizeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getDiscoverySizeOptions(getContext(), R.color.star_yellow);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrStarSize.setAdapter(adapter);

//        spnrStarSize.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
    }

    private void setupStarTypeSpinner() {
        List<CustomSpinnerObject> options = MiscUtil.getStarTypeOptions(getContext());
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrStarType.setAdapter(adapter);

//        spnrStarType.getBackground().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
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
                if(listener != null) {
                    listener.submitDiscovery(Enums.DiscoveryType.STAR.getNameString(getContext()), createStarPropertiesMap());
                }
            }
        });
    }

    private HashMap<String, Object> createStarPropertiesMap() {
        HashMap<String, Object> systemProperties = new HashMap<>();
        systemProperties.put("size", getStarSizeValue());
        systemProperties.put("star-type", getStarTypeValue());

        return systemProperties;
    }

    private String getStarSizeValue() {
        CustomSpinnerObject starSize = (CustomSpinnerObject) spnrStarSize.getSelectedItem();
        return (String) starSize.getObject();
    }

    private String getStarTypeValue() {
        CustomSpinnerObject starType = (CustomSpinnerObject) spnrStarType.getSelectedItem();
        return (String) starType.getObject();
    }
}
