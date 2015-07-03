package com.example.sadostrich.Data;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadostrich.Objects.Discovery;
import com.example.sadostrich.nomansskyjournal.R;

import java.util.List;

/**
 * Adapter that takes care of binding a user's discovery data to the recycler view
 * <p/>
 * Created by jacewardell on 7/3/15.
 */
public class DiscoveryRecyclerAdapter extends RecyclerView.Adapter<DiscoveryRecyclerAdapter.DiscoveryViewHolder> {
	private List<Discovery> discoveries;

	public DiscoveryRecyclerAdapter(List<Discovery> discoveries) {
		this.discoveries = discoveries;
	}

	public static class DiscoveryViewHolder extends RecyclerView.ViewHolder {
		CardView cardView;
		ImageView image;
		TextView date, commonName, scientificName, description;

		public DiscoveryViewHolder(View itemView) {
			super(itemView);
			cardView = (CardView) itemView.findViewById(R.id.card_cardview);
			image = (ImageView) itemView.findViewById(R.id.card_image);
			date = (TextView) itemView.findViewById(R.id.card_date);
			commonName = (TextView) itemView.findViewById(R.id.card_common_name);
			scientificName = (TextView) itemView.findViewById(R.id.card_scientific_name);
			description = (TextView) itemView.findViewById(R.id.card_description);
		}
	}

	@Override
	public DiscoveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discovery_card, parent, false);
		return new DiscoveryViewHolder(v);
	}

	@Override
	public void onBindViewHolder(DiscoveryViewHolder discoveryViewHolder, int position) {
//		discoveryViewHolder.date.setText(discoveries.get(position).getCommonName());
		discoveryViewHolder.date.setText(discoveries.get(position).getDateAsString());
		discoveryViewHolder.commonName.setText(discoveries.get(position).getCommonName());
		discoveryViewHolder.scientificName.setText(discoveries.get(position).getScientificName());
		discoveryViewHolder.description.setText(discoveries.get(position).getDescription());
	}

	@Override
	public int getItemCount() {
		return discoveries.size();
	}
}
