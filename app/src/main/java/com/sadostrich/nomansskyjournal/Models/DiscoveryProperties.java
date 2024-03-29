package com.sadostrich.nomansskyjournal.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/28/16.
 */
public class DiscoveryProperties implements Serializable {

	@SerializedName("system-type")
	private String systemType;
	@SerializedName("number-of-planets")
	private String planetCount;
	private String life;
	private boolean dangerous;
	private String rarity;
}
