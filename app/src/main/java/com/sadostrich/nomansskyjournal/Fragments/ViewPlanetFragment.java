package com.sadostrich.nomansskyjournal.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Models.Planet;
import com.sadostrich.nomansskyjournal.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jacewardell on 7/25/15.
 */
public class ViewPlanetFragment extends android.app.Fragment {
    private ImageView imageView;
    private TextView commonName, scientificName, solarSystemName, description, story;

    private Planet discovery;

    public ViewPlanetFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            discovery = (Planet) getArguments().get(getResources().getString(R.string.extra_discovery));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_discovery, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.view_discovery_image);
        commonName = (TextView) rootView.findViewById(R.id.view_discovery_common_name);
        scientificName = (TextView) rootView.findViewById(R.id.view_discovery_scientific_name);
        solarSystemName = (TextView) rootView.findViewById(R.id.view_discovery_solar_system_name);
        description = (TextView) rootView.findViewById(R.id.view_discovery_description);
        story = (TextView) rootView.findViewById(R.id.view_discovery_discovery_story);


//        Uri imageUri = MiscUtil.getImageUri(discovery.getImageUrl());
//        if(imageUri != null) {
//            Picasso.with(getActivity()).load(imageUri).fit().placeholder(R.mipmap.add_image).into
//                    (imageView);
//        } else {
//            Picasso.with(getActivity()).load(R.mipmap.add_image).into(imageView);
//        }
        commonName.setText(discovery.getCommonName());
        scientificName.setText(discovery.getScientificName());
        solarSystemName.setText(discovery.getSolarSystemName());
        description.setText(discovery.getDescription());
        story.setText(discovery.getStory());

        return rootView;
    }
}
