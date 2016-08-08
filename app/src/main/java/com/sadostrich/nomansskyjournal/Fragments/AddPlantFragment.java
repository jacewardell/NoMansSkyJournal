package com.sadostrich.nomansskyjournal.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPlantFragment extends Fragment {

    public AddPlantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: change to right layout
        return inflater.inflate(R.layout.discovery_card, container, false);
    }
}
