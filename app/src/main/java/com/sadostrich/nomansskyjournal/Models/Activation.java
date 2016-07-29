package com.sadostrich.nomansskyjournal.Models;

/**
 * TODO JavaDoc
 * <p>
 * Created by Jace Wardell on 7/27/16.
 */
public class Activation {
	private String token;
	private boolean active;

	public Activation(String token, boolean active) {
		this.token = token;
		this.active = active;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
