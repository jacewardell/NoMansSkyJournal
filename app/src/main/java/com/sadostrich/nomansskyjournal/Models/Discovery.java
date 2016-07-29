package com.sadostrich.nomansskyjournal.Models;

import com.google.gson.annotations.SerializedName;
import com.sadostrich.nomansskyjournal.Utils.Formatter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class that will be inherited and that represents any one of the user's discoveries
 * <p/>
 * Created by jacewardell on 7/2/15.
 */
public class Discovery implements Serializable {
	@SerializedName("_id")
	private String id;
	@SerializedName("_discoveredBy")
	private User user;
	private String type;
	private DiscoveryProperties properties;
    private String description, name, discoveredAt, imageUrl;
    private List<String> tags;
    private int timesFlagged, commentCount, score, views;


    public Discovery() {
//        this.user = new User();
//		date = getCurrentDate();
//        commonName = name = description = story = imageUrl = "";
    }

    /**
     * Sets the variables according to the parameters
     *
     * @param user           user
     * @param commonName     common name
     * @param name scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(User user, String commonName, String name, String description, String story, String imageUrl) {
//        this.user = user;
//        this.date = getCurrentDate();
//        this.commonName = commonName;
//        this.name = name;
//        this.description = description;
//        this.story = story;
//        this.imageUrl = imageUrl;
    }

    /**
     * Sets the variables according to the parameters
     *
     * @param user           user
     * @param date           date
     * @param commonName     common name
     * @param name scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(User user, String date, String commonName, String name, String description, String story, String imageUrl) {
//        this.user = user;
//        this.date = date;
//        this.commonName = commonName;
//        this.name = name;
//        this.description = description;
//        this.story = story;
//        this.imageUrl = imageUrl;
    }

    /**
     * s
     * Sets the variables according to the parameters
     *
     * @param user           user
     * @param date           date
     * @param commonName     common name
     * @param name scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(User user, Date date, String commonName, String name, String description, String story, String imageUrl) {
//        this.user = user;
//        this.date = Formatter.dateFormat.format(date);
//        this.commonName = commonName;
//        this.name = name;
//        this.description = description;
//        this.story = story;
//        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getTimesFlagged() {
        return timesFlagged;
    }

    public void setTimesFlagged(int timesFlagged) {
        this.timesFlagged = timesFlagged;
    }

    /**
     * Returns the date the discovery was made
     *
     * @return date
     */
    public String getDiscoveredAt() {
        return discoveredAt;
    }

    /**
     * Sets the date the discovery was made
     *
     * @param date discovered
     */
    public void setDiscoveredAt(String date) {
        this.discoveredAt = date;
    }

    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return Formatter.dateFormat.format(cal.getTime());
    }

    /**
     * Returns the discovery's scientific name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the discovery's scientific name
     *
     * @param name assigned by game
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the discovery's description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the discovery's description
     *
     * @param description of discovery
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the url of the discovery's image
     *
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the url of the discovery's image
     *
     * @param imageUrl of discovery
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
