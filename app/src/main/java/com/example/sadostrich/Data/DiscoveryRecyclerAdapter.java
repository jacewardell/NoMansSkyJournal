package com.example.sadostrich.Data;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sadostrich.Models.Discovery;
import com.example.sadostrich.Utils.MiscUtil;
import com.example.sadostrich.nomansskyjournal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter that takes care of binding a user's discovery data to the recycler view
 * <p/>
 * Created by jacewardell on 7/3/15.
 */
public class DiscoveryRecyclerAdapter extends RecyclerView.Adapter<DiscoveryRecyclerAdapter.DiscoveryViewHolder> {
    private Context context;
    private List<Discovery> discoveries;

    DiscoverySelectedCallback discoverySelectedCallback;

    public DiscoveryRecyclerAdapter(Activity context, List<?> discoveries) {
        this.context = context;
        this.discoveries = (List<Discovery>) discoveries;

        discoverySelectedCallback = (DiscoverySelectedCallback) context;
    }

    public static class DiscoveryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
		ImageView image;
		TextView date, commonName, scientificName, description;

        public DiscoverySelectedListener discoverySelectedListener;

        public DiscoveryViewHolder(View itemView, DiscoverySelectedListener discoverySelectedListener) {
            super(itemView);
			cardView = (CardView) itemView.findViewById(R.id.card_cardview);
			image = (ImageView) itemView.findViewById(R.id.card_image);
			date = (TextView) itemView.findViewById(R.id.card_date);
			commonName = (TextView) itemView.findViewById(R.id.card_common_name);
			scientificName = (TextView) itemView.findViewById(R.id.card_scientific_name);
			description = (TextView) itemView.findViewById(R.id.card_description);

            this.discoverySelectedListener = discoverySelectedListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            discoverySelectedListener.discoverySelected(v, getLayoutPosition());
        }

        public static interface DiscoverySelectedListener {
            public void discoverySelected(View caller, int position);
        }
    }

	@Override
	public DiscoveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discovery_card, parent, false);
        final DiscoveryViewHolder viewHolder = new DiscoveryViewHolder(v, new DiscoveryViewHolder.DiscoverySelectedListener() {
            @Override
            public void discoverySelected(View caller, int position) {
                discoverySelectedCallback.discoverySelected(discoveries.get(position));
            }
        });

        return viewHolder;
    }

	@Override
	public void onBindViewHolder(DiscoveryViewHolder discoveryViewHolder, int position) {

        Uri imageUri = MiscUtil.getImageUri(discoveries.get(position).getImageUrl());
        if(imageUri != null) {
            Picasso.with(context).load(imageUri).resize(80, 80).placeholder(R.mipmap.add_image).into
                    (discoveryViewHolder.image);
        } else {
            Picasso.with(context).load(R.mipmap.add_image).resize(80, 80).into(discoveryViewHolder.image);
        }

        discoveryViewHolder.date.setText(discoveries.get(position).getDate());
        discoveryViewHolder.commonName.setText(discoveries.get(position).getCommonName());
		discoveryViewHolder.scientificName.setText(discoveries.get(position).getScientificName());
		discoveryViewHolder.description.setText(discoveries.get(position).getDescription());
	}

	@Override
	public int getItemCount() {
		return discoveries.size();
	}

    public interface DiscoverySelectedCallback {
        public void discoverySelected(Discovery discovery);
    }
}
