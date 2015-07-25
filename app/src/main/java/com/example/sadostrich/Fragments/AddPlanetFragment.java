package com.example.sadostrich.Fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sadostrich.Utils.Enums;
import com.example.sadostrich.Utils.Formatter;
import com.example.sadostrich.nomansskyjournal.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlanetFragment extends Fragment {
    private ImageView imageView;
    private Button selectDateButton;
    private TextView commonNameTextView, scientificNameTextView, descriptionTextView, storyTextView, solarSystemNameTextView;
    private Spinner sizeSpinner;

    private static final int SELECT_IMAGE = 1;

    private String imagePath;

    public AddPlanetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_planet, container, false);

//        ListView addPlanetListView = (ListView) rootView.findViewById(R.id.add_planet_listview);
//        addPlanetListView.setAdapter(new ArrayAdapter<Enums.PlanetSize>(getActivity(), android.R.layout.simple_spinner_item, Enums.PlanetSize.values()));
//        RelativeLayout planetHeaderView = (RelativeLayout) inflater.inflate(R.layout.fragment_add_planet, null);
//        addPlanetListView.addHeaderView(planetHeaderView, null, false);

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        selectDateButton = (Button) rootView.findViewById(R.id.planet_date_button);
        selectDateButton.setText(Formatter.dateFormat.format(new Date(c.getTimeInMillis())));
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectDateButton.setText(Formatter.dateFormat.format(new Date(year - 1900, monthOfYear, dayOfMonth)));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        // Create the imageview and allow the user to choose an image from their gallery when touched
        imageView = (ImageView) rootView.findViewById(R.id.planet_imageview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_IMAGE);
            }
        });

        commonNameTextView = (TextView) rootView.findViewById(R.id.planet_common_name_field);
        scientificNameTextView = (TextView) rootView.findViewById(R.id.planet_scientific_name_field);
        descriptionTextView = (TextView) rootView.findViewById(R.id.planet_description_field);
        storyTextView = (TextView) rootView.findViewById(R.id.planet_story_field);
        solarSystemNameTextView = (TextView) rootView.findViewById(R.id.planet_solar_system_name_field);
        sizeSpinner = (Spinner) rootView.findViewById(R.id.planet_size_spinner);
        sizeSpinner.setAdapter(new ArrayAdapter<Enums.PlanetSize>(getActivity(), android.R.layout.simple_spinner_item, Enums.PlanetSize.values()));

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.getActivity().RESULT_OK) {
            if (requestCode == SELECT_IMAGE) {
                Uri imageUri = data.getData();
                imagePath = imageUri.toString();
                Picasso.with(getActivity()).load(imageUri).resize(80, 80).into(imageView);
            }
        }
    }

    public String getImageUrl() {
        return imagePath;
    }

    public String getDate() {
        return selectDateButton.getText().toString();
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
