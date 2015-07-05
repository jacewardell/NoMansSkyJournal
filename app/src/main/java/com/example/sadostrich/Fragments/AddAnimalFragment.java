package com.example.sadostrich.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadostrich.nomansskyjournal.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddAnimalFragment extends Fragment {

    public AddAnimalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_animal, container, false);
    }
}
