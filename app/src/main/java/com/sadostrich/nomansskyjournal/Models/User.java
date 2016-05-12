package com.sadostrich.nomansskyjournal.Models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user, who will have discoveries, login information, etc.
 * <p>
 * Created by jacewardell on 5/8/16.
 */
public class User {
    @SerializedName("_id")
    private String id;
    private String username, email;
    private List<Discovery> favorites;
    private List<String> votes;
    private boolean admin;

    public User() {
        id = username = email = "";
        favorites = new ArrayList<>();
        votes = new ArrayList<>();
    }

    public User(String id, String username, String email, List<Discovery> favorites, List<String> votes, boolean admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.favorites = favorites;
        this.votes = votes;
        this.admin = admin;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public List<Discovery> getFavorites() {
        return favorites;
    }

    /**
     * @param favorites
     */
    public void setFavorites(List<Discovery> favorites) {
        this.favorites = favorites;
    }

    /**
     * @return
     */
    public List<String> getVotes() {
        return votes;
    }

    /**
     * @param votes
     */
    public void setVotes(List<String> votes) {
        this.votes = votes;
    }

    /**
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static User parseFromJSON(JSONObject userObject) {
        Gson gson = new Gson();
        User user = gson.fromJson(userObject.toString(), User.class);
        return user;
    }
}
