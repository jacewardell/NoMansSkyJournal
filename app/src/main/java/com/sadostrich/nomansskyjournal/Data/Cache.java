package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Discovery;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/30/16.
 */
public class Cache {
	private static Cache instance = new Cache();

	private final List<Discovery> newDiscoveries = new ArrayList<>();

	private final List<Discovery> popularDiscoveries = new ArrayList<>();

	public static Cache getInstance() {
		return instance;
	}

	public List<Discovery> getNewDiscoveries() {
		return newDiscoveries;
	}

	public void setNewDiscoveries(
			List<Discovery> discoveries) {
		this.newDiscoveries.clear();
		this.newDiscoveries.addAll(discoveries);
	}

	public List<Discovery> getPopularDiscoveries() {
		return popularDiscoveries;
	}

	public void setPopularDiscoveries(
			List<Discovery> discoveries) {
		this.popularDiscoveries.clear();
		this.popularDiscoveries.addAll(discoveries);
	}
}
