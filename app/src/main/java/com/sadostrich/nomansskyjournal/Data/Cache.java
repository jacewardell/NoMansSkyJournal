package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Discovery;

import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/30/16.
 */
public class Cache {
	private static Cache instance = new Cache();

	private List<Discovery> discoveries;

	public static Cache getInstance() {
		return instance;
	}

	public List<Discovery> getDiscoveries() {
		return discoveries;
	}

	public void setDiscoveries(
			List<Discovery> discoveries) {
		this.discoveries = discoveries;
	}
}
