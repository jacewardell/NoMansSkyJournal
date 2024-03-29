package com.sadostrich.nomansskyjournal.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

/**
 * Used to authenticate REQUEST queries to get information from the server
 * <p/>
 * Created by jacewardell on 5/9/16.
 */
public class Authentication {
    private static final String TAG = "Authentication";

    private static Authentication instance;

    private String id;
    private String cookie;
    private String username, email;
    private int totalScore;
    private Activation activation;
    private boolean banned;
    private List<Discovery> favorites;
    private List<String> votes;
    private boolean mod, admin;
    private Avatar avatar;

    public Authentication() {
        id = cookie = username = email = "";
        favorites = new ArrayList<>();
        votes = new ArrayList<>();
    }

    public Authentication(String id, String cookie, String username, String email, List<Discovery> favorites, List<String> votes, boolean admin) {
        this.id = id;
        this.cookie = cookie;
        this.username = username;
        this.email = email;
        this.favorites = favorites;
        this.votes = votes;
        this.admin = admin;
    }

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }

    public static void setInstance(Authentication inst) {
        instance = inst;
    }

    public static boolean isValidUsername(Authentication auth) {
        return auth != null && auth.getUsername() != null && !auth.getUsername().isEmpty();
    }

    public static boolean isValidUserAvatar(Authentication auth) {
        return auth != null && auth.getAvatar() != null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Activation getActivation() {
        return activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    public List<Discovery> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Discovery> favorites) {
        this.favorites = favorites;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<String> getVotes() {
        return votes;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setCookie(Headers headers) {
        String tempCookie = headers.get("set-cookie");
        this.cookie = tempCookie.substring(0, tempCookie.indexOf(';'));
        Log.d(TAG, "setCookie: cookie " + cookie);
    }

    public String getCookie() {
        return cookie;
    }

    public User getAsUser() {
        return new User(getId(), getUsername(), "", isMod(), isAdmin(), getAvatar());
    }
}
