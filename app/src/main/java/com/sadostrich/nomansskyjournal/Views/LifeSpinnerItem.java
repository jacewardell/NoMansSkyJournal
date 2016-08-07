package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/31/16.
 */
public class LifeSpinnerItem extends RelativeLayout implements AdapterView.OnItemSelectedListener {
    private Spinner spnrLife;
    private TextView tvLife, tvInhabitants;
    private View viewToHide1, viewToHide2, viewToHide3;
    private CheckBox cbTraders, cbScavengers, cbScientists, cbMilitant, cbHunters;
    private int pageAccentColor;
    private boolean initializing = true;
    private List<CustomSpinnerObject> options;
    private ArrayList<CheckBox> checkboxes;

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
        spnrLife = (Spinner) findViewById(R.id.spinner_life);
        tvLife = (TextView) findViewById(R.id.label_life);
        tvInhabitants = (TextView) findViewById(R.id.tv_inhabitants);
        viewToHide1 = findViewById(R.id.life_row1);
        viewToHide2 = findViewById(R.id.life_row2);
        viewToHide3 = findViewById(R.id.life_row3);

        cbTraders = (CheckBox) findViewById(R.id.cb_traders);
        cbScavengers = (CheckBox) findViewById(R.id.cb_scavengers);
        cbScientists = (CheckBox) findViewById(R.id.cb_scientists);
        cbMilitant = (CheckBox) findViewById(R.id.cb_militant);
        cbHunters = (CheckBox) findViewById(R.id.cb_hunters);

        saveCheckboxes();
    }

    private void saveCheckboxes() {
        checkboxes = new ArrayList<CheckBox>();
        checkboxes.add(cbTraders);
        checkboxes.add(cbScavengers);
        checkboxes.add(cbScientists);
        checkboxes.add(cbMilitant);
        checkboxes.add(cbHunters);
    }

    public void showLifeOptions(boolean show) {
        tvInhabitants.setVisibility(show ? View.VISIBLE : View.GONE);
        viewToHide1.setVisibility(show ? View.VISIBLE : View.GONE);
        viewToHide2.setVisibility(show ? View.VISIBLE : View.GONE);
        viewToHide3.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setupLifeSpinner() {
        spnrLife.setOnItemSelectedListener(this);
    }

    private void setAdapters() {
        options = MiscUtil.getDiscoveryLifeOptions(getContext(), pageAccentColor);
        AddDiscoverySpinnerAdapter adapter = new AddDiscoverySpinnerAdapter(getContext(), options);
        spnrLife.setAdapter(adapter);
    }

    public void setPageAccentColor(int pageAccentColor) {
        this.pageAccentColor = pageAccentColor;
        int accentColor = getResources().getColor(pageAccentColor);

        tvLife.setTextColor(accentColor);
        tvInhabitants.setTextColor(accentColor);

        setCheckboxAccent(cbTraders, accentColor);
        setCheckboxAccent(cbScavengers, accentColor);
        setCheckboxAccent(cbScientists, accentColor);
        setCheckboxAccent(cbMilitant, accentColor);
        setCheckboxAccent(cbHunters, accentColor);

        setAdapters();
    }

    private void setCheckboxAccent(CheckBox checkBox, int accentColor) {
        checkBox.setTextColor(accentColor);
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

    @NonNull
    public String getInhabitantsString() {
        String inhabitantString = "";
        int i = 0;
        for (CheckBox checkbox : checkboxes) {
            if (!checkbox.isChecked()) {
                continue;
            }

            if (i > 0) {
                inhabitantString += ",";
            }
            i++;

            inhabitantString += "\"" + checkbox.getText() + "\"";
        }


        return inhabitantString;
    }

    public boolean getLifeFound() {
        String lifeOption = (String) ((CustomSpinnerObject) spnrLife.getSelectedItem()).getObject();
        return lifeOption.equalsIgnoreCase("yes");
    }
}
