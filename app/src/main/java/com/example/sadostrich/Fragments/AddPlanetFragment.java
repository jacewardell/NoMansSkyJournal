package com.example.sadostrich.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sadostrich.Utils.Enums;
import com.example.sadostrich.nomansskyjournal.R;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlanetFragment extends Fragment {
    private DatePicker datePicker;
    private TextView commonNameTextView, scientificNameTextView, descriptionTextView, storyTextView, solarSystemNameTextView;
    private Spinner sizeSpinner;

    public AddPlanetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_planet, container, false);

        ListView addPlanetListView = (ListView) rootView.findViewById(R.id.add_planet_listview);
        addPlanetListView.setAdapter(new ArrayAdapter<Enums.PlanetSize>(getActivity(), android.R.layout.simple_spinner_item, Enums.PlanetSize.values()));
        RelativeLayout planetHeaderView = (RelativeLayout) inflater.inflate(R.layout.planet_header, null);
        addPlanetListView.addHeaderView(planetHeaderView, null, false);

        datePicker = (DatePicker) planetHeaderView.findViewById(R.id.planet_date_picker);
        commonNameTextView = (TextView) planetHeaderView.findViewById(R.id.planet_common_name_field);
        scientificNameTextView = (TextView) planetHeaderView.findViewById(R.id.planet_scientific_name_field);
        descriptionTextView = (TextView) planetHeaderView.findViewById(R.id.planet_description_field);
        storyTextView = (TextView) planetHeaderView.findViewById(R.id.planet_story_field);
        solarSystemNameTextView = (TextView) planetHeaderView.findViewById(R.id.planet_solar_system_name_field);
        sizeSpinner = (Spinner) planetHeaderView.findViewById(R.id.planet_size_spinner);
        sizeSpinner.setAdapter(new ArrayAdapter<Enums.PlanetSize>(getActivity(), android.R.layout.simple_spinner_item, Enums.PlanetSize.values()));

        return rootView;
    }

    public Date getDate() {
        return new Date(datePicker.getCalendarView().getDate());
    }

    public String getCommonNameText() {
        return commonNameTextView.getText().toString();
    }

    public String getScientificNameText() {
        return scientificNameTextView.getText().toString();
    }

    public String getDescriptionText() {
        return descriptionTextView.getText().toString();
    }

    public String getStoryText() {
        return storyTextView.getText().toString();
    }

    public String getSolarSystemNameText() {
        return solarSystemNameTextView.getText().toString();
    }

    public Enums.PlanetSize getSizeSpinnerSelection() {
        return (Enums.PlanetSize) sizeSpinner.getSelectedItem();
    }
}
