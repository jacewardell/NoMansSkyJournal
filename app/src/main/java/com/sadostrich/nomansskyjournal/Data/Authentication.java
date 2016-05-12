package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to authenticate REQUEST queries to get information from the server
 * <p>
 * Created by jacewardell on 5/9/16.
 */
public class Authentication {
    private static Authentication instance;

    private User user;

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        if(user != null) {
            return user.getId();
        }
        return "";
    }

    public String getEmail() {
        if(user != null) {
            return user.getEmail();
        }
        return "";
    }

    public String getUsername() {
        if(user != null) {
            return user.getUsername();
        }
         return "";
    }

    public List<Discovery> getFavorites() {
        if(user != null) {
            return user.getFavorites();
        }
        return new ArrayList<>();
    }

    public List<String> getVotes() {
        if(user != null) {
            return user.getVotes();
        }
        return new ArrayList<>();
    }
}
