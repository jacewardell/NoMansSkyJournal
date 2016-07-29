package com.sadostrich.nomansskyjournal.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;

/**
 * Created by jacewardell on 4/7/16.
 */
public class DiscoveryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView dateTextView, commonNameTextView, scientificNameTextView, descriptionTextView;
    private ImageView thumbnailImageView;
    private IDiscoveryListener discoveryListener;
    private Discovery discovery;

    public DiscoveryViewHolder(View itemView) {
        super(itemView);

        getViewRefs(itemView);

        itemView.setOnClickListener(this);
    }

    private void getViewRefs(View itemView) {
        dateTextView = (TextView) itemView.findViewById(R.id.card_date);
        commonNameTextView = (TextView) itemView.findViewById(R.id.card_common_name);
        scientificNameTextView = (TextView) itemView.findViewById(R.id.card_scientific_name);
        descriptionTextView = (TextView) itemView.findViewById(R.id.card_description);
        thumbnailImageView = (ImageView) itemView.findViewById(R.id.card_image);
    }

    public void setData(Discovery discovery) {
        this.discovery = discovery;
//        dateTextView.setText(discovery.getDate());
//        commonNameTextView.setText(discovery.getCommonName());
        scientificNameTextView.setText(discovery.getName());
        descriptionTextView.setText(discovery.getDescription());
        // TODO: get the image of the discovery
    }

    public void setDiscoveryListener(IDiscoveryListener discoveryListener) {
        this.discoveryListener = discoveryListener;
    }

    @Override
    public void onClick(View v) {
        discoveryListener.onDiscoverySelected(discovery);
    }
}
