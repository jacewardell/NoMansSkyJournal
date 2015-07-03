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
import com.example.sadostrich.Objects.Discovery;
import com.example.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;
import java.util.List;

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

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @return A new instance of fragment PlanetFragment.
	 */
	public static PlanetFragment newInstance() {
		PlanetFragment fragment = new PlanetFragment();
		return fragment;
	}

	public PlanetFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_planet, container, false);

		recyclerView = (RecyclerView) rootView.findViewById(R.id.planets_recyclerview);

		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		List<Discovery> discoveries = new ArrayList<Discovery>();
		discoveries.add(new Discovery("Common NameCommon NameCommon NameCommon NameCommon NameCommon NameCommon Name", "Scientific Name", "Green and" +
				"red lizardGreen and red lizardGreen and red lizardGreen and red lizardGreen and red lizardGreen and red lizardGreen and red" +
				"lizardGreen and red lizardGreen and red lizardGreen and red lizard", "I was walking on planet and found animal", "image"));
		discoveries.add(new Discovery("Common Name2", "Scientific Name2", "Green and red lizard2", "I was walking on planet and found animal2", "image2"));
		discoveries.add(new Discovery("Common Name3", "Scientific Name3", "Green and red lizard3", "I was walking on planet and found animal3", "image3"));
		discoveries.add(new Discovery("Common Name4", "Scientific Name4", "Green and red lizard4", "I was walking on planet and found animal4", "image4"));

		discoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(discoveries);
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
