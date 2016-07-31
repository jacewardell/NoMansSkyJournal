package com.sadostrich.nomansskyjournal.Adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.DiscoveryViewHolder;

import java.util.List;

/**
 * Created by jacewardell on 4/7/16.
 */
public class DiscoveryAdapter extends RecyclerView.Adapter {
    private IDiscoveryListener discoveryListener;
    List<Discovery> discoveries;

    public DiscoveryAdapter(List<Discovery> discoveries) {
        this.discoveries = discoveries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discovery_card, parent, false);
        return new DiscoveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscoveryViewHolder discoveryViewHolder = (DiscoveryViewHolder) holder;
        discoveryViewHolder.setData(discoveries.get(position));
//        discoveryViewHolder.setDiscoveryListener(discoveryListener);
    }

    @Override
    public int getItemCount() {
        return discoveries.size();
    }

    public void setDiscoveryListener(IDiscoveryListener discoveryListener) {
        this.discoveryListener = discoveryListener;
    }
}
