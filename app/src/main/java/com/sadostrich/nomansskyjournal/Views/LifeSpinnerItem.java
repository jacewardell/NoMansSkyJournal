package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Adapters.AddDiscoverySpinnerAdapter;
import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;

import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/31/16.
 */
public class LifeSpinnerItem extends RelativeLayout implements AdapterView.OnItemSelectedListener {
    private Spinner lifeSpinner;
    private TextView lifeLabel, inhabitantsTextView;
    private View viewToHide1, viewToHide2, viewToHide3;
    private CheckBox tradersCheckbox, scavengersCheckbox, scientistsCheckbox, militantCheckbox, huntersCheckbox;
    private int pageAccentColor;
    private boolean initializing = true;
    private List<CustomSpinnerObject> options;

    public LifeSpinnerItem(Context context) {
        super(context);
        init();
    }

    public LifeSpinnerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LifeSpinnerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LifeSpinnerItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_life_spinner_item, this);
        getViewRefs();
        setupLifeSpinner();
    }

    private void getViewRefs() {
        lifeSpinner = (Spinner) findViewById(R.id.spinner_life);
        lifeLabel = (TextView) findViewById(R.id.label_life);
        inhabitantsTextView = (TextView) findViewById(R.id.tv_inhabitants);
        viewToHide1 = findViewById(R.id.life_row1);
        viewToHide2 = findViewById(R.id.life_row2);
        viewToHide3 = findViewById(R.id.life_row3);

        tradersCheckbox = (CheckBox) findViewById(R.id.cb_traders);
        scavengersCheckbox = (CheckBox) findViewById(R.id.cb_scavengers);
        scientistsCheckbox = (CheckBox) findViewById(R.id.cb_scientists);
        militantCheckbox = (CheckBox) findViewById(R.id.cb_militant);
        huntersCheckbox = (CheckBox) findViewById(R.id.cb_hunters);
    }

    public void showLifeOptions(boolean show) {
        inhabitantsTextView.setVisibility(show ? View.VISIBLE : View.GONE);
        viewToHide1.setVisibility(show ? View.VISIBLE : View.GONE);
        viewToHide2.setVisibility(show ? View.VISIBLE : View.GONE);
        viewToHide3.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setupLifeSpinner() {
        lifeSpinner.setOnItemSelectedListener(this);
    }

    private void setAdapters() {
        options = MiscUtil.getDiscoveryLifeOptions(getContext(), pageAccentColor);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        lifeSpinner.setAdapter(adapter);
    }

    public void setPageAccentColor(int pageAccentColor) {
        this.pageAccentColor = pageAccentColor;
        int accentColor = getResources().getColor(pageAccentColor);

        lifeLabel.setTextColor(accentColor);
        inhabitantsTextView.setTextColor(accentColor);

        setCheckboxAccent(tradersCheckbox, accentColor);
        setCheckboxAccent(scavengersCheckbox, accentColor);
        setCheckboxAccent(scientistsCheckbox, accentColor);
        setCheckboxAccent(militantCheckbox, accentColor);
        setCheckboxAccent(huntersCheckbox, accentColor);

        setAdapters();
    }

    private void setCheckboxAccent(CheckBox checkBox, int accentColor) {
//        checkBox.setButtonDrawable(getResources().getDrawable(R.drawable.system_checkbox));

//        checkBox.setTextColor(accentColor);

//        ColorStateList colorStateList = new ColorStateList(
//                new int[][]{
//                        new int[]{android.R.attr.state_enabled} //enabled
//                },
//                new int[]{accentColor}
//        );
//        checkBox.setSupportButtonTintList(colorStateList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (!initializing) {
            CustomSpinnerObject customSpinnerObject = options.get(position);
            if (customSpinnerObject.getObject().equals(getResources().getString(R.string.yes))) {
                showLifeOptions(true);
            } else {
                showLifeOptions(false);
            }
        } else {
            initializing = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
