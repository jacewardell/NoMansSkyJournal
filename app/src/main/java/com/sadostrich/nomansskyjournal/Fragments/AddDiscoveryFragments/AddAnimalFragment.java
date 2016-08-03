package com.sadostrich.nomansskyjournal.Fragments.AddDiscoveryFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddAnimalFragment extends Fragment {

    public AddAnimalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: Change to right layout
        return inflater.inflate(R.layout.discovery_card, container, false);
    }
}
