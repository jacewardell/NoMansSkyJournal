package com.example.sadostrich.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadostrich.Data.DiscoveryRecyclerAdapter;
import com.example.sadostrich.Models.Planet;
import com.example.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlanetFragment.OnFragmentInteractionListener} interface
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

	private OnFragmentInteractionListener mListener;

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

//		List<Discovery> discoveries = new ArrayList<Discovery>();
//		discoveries.add(new Discovery("Common NameCommon NameCommon NameCommon NameCommon NameCommon NameCommon Name", "Scientific Name", "Green and" +
//				"red lizardGreen and red lizardGreen and red lizardGreen and red lizardGreen and red lizardGreen and red lizardGreen and red" +
//				"lizardGreen and red lizardGreen and red lizardGreen and red lizard", "I was walking on planet and found animal", "image"));
//		discoveries.add(new Discovery("Common Name2", "Scientific Name2", "Green and red lizard2", "I was walking on planet and found animal2", "image2"));
//		discoveries.add(new Discovery("Common Name3", "Scientific Name3", "Green and red lizard3", "I was walking on planet and found animal3", "image3"));
//		discoveries.add(new Discovery("Common Name4", "Scientific Name4", "Green and red lizard4", "I was walking on planet and found animal4", "image4"));

        discoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(allPlanets);
        recyclerView.setAdapter(discoveryRecyclerAdapter);

		return rootView;
	}

	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

    public void updateDataSet(ArrayList<Planet> allPlanets) {
        this.allPlanets = allPlanets;
        discoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(allPlanets);
        recyclerView.setAdapter(discoveryRecyclerAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		public void onFragmentInteraction(Uri uri);
	}
}
