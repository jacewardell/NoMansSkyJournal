package com.sadostrich.nomansskyjournal.Interfaces;

import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Activities.MainActivity;

/**
 * Listener to be used to receive events in teh {@linkplain MainActivity}
 *
 * Created by jacewardell on 5/11/16.
 */
public interface IDiscoveryListener {
    void onDiscoverySelected(Discovery discovery);

    void onLoadMoreDiscoveries();
}
