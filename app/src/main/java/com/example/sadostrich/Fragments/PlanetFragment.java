package com.example.sadostrich.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadostrich.Data.DiscoveryRecyclerAdapter;
import com.example.sadostrich.Models.Planet;
import com.example.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PlanetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanetFragment extends Fragment {
	private RecyclerView recyclerView;
	private DiscoveryRecyclerAdapter discoveryRecyclerAdapter;
	private LinearLayoutManager layoutManager;
    private static final String EXTRA = "planet_fragment_extra";
    private ArrayList<Planet> allPlanets;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @return A new instance of fragment PlanetFragment.
	 */
    public static PlanetFragment newInstance(ArrayList<Planet> planets) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA, planets);
        PlanetFragment planetFragment = new PlanetFragment();
        planetFragment.setArguments(bundle);
        return planetFragment;
    }

	public PlanetFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        allPlanets = (ArrayList<Planet>) getArguments().getSerializable(getString(R.string.planet_fragment_extra));
        if (allPlanets == null) {
            allPlanets = new ArrayList<>();
        }
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_planet, container, false);

		recyclerView = (RecyclerView) rootView.findViewById(R.id.planets_recyclerview);

		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

        discoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(getActivity(), allPlanets);
        recyclerView.setAdapter(discoveryRecyclerAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

    public void updateDataSet(ArrayList<Planet> allPlanets) {
        this.allPlanets = allPlanets;
        discoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(getActivity(), allPlanets);
        recyclerView.setAdapter(discoveryRecyclerAdapter);
    }
}
