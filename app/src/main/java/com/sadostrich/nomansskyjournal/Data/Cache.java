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

	private final List<Discovery> discoveries = new ArrayList<>();

	public static Cache getInstance() {
		return instance;
	}

	public List<Discovery> getDiscoveries() {
		return discoveries;
	}

	public void setDiscoveries(
			List<Discovery> discoveries) {
		this.discoveries.clear();
		this.discoveries.addAll(discoveries);
	}
}
