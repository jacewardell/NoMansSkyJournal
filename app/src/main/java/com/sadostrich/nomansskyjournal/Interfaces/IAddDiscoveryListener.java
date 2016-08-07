package com.sadostrich.nomansskyjournal.Interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jacewardell on 8/4/16.
 */
public interface IAddDiscoveryListener {
    void previousSelected();

    void submitDiscovery(String type, Map<String, Object> properties);

    void submitShipDiscovery(String type, Map<String, Object> properties, ArrayList<String> strings, List<String> tags, String discoveredAt, String name, String youtubeUrl, String description);
}
