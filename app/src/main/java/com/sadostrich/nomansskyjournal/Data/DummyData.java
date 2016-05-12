package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.Planet;
import com.sadostrich.nomansskyjournal.Models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacewardell on 4/8/16.
 */
public class DummyData {
    private static final int TOTAL_DISCOVERIES = 100;

    public static List<Discovery> generateTestDiscoveries() {
        List<Discovery> generatedDiscoveries = new ArrayList<Discovery>();
        for(int i = 0; i < TOTAL_DISCOVERIES; i++) {
            Discovery newDiscovery = new Discovery(new User(), "common " + i, "scientific " + i, "description " + i, "story " + i, "imageUrl " + i);
            generatedDiscoveries.add(newDiscovery);
        }
        return generatedDiscoveries;
    }
}
