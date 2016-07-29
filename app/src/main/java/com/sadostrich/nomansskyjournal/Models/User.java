package com.sadostrich.nomansskyjournal.Models;

import java.io.Serializable;

/**
 * Represents a user, who will have discoveries, login information, etc.
 * <p>
 * Created by jacewardell on 5/8/16.
 */
public class User implements Serializable {
    private String id, username, createdAt;
	private boolean mod, admin;

	public User(String id, String username, String createdAt, boolean mod, boolean admin) {
		this.id = id;
		this.username = username;
		this.createdAt = createdAt;
		this.mod = mod;
		this.admin = admin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isMod() {
		return mod;
	}

	public void setMod(boolean mod) {
		this.mod = mod;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
