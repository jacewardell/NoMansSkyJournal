package com.sadostrich.nomansskyjournal.Models;

import com.sadostrich.nomansskyjournal.Utils.Enums;
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
    private final Enums.DiscoveryType discoveryType = Enums.DiscoveryType.PLANET;
    private User user;
    private String date, commonName, scientificName, description, story, imageUrl;
    private List<String> tags;
    private int timesFlagged;


    public Discovery() {
        this.user = new User();
        date = getCurrentDate();
        commonName = scientificName = description = story = imageUrl = "";
    }

    /**
     * Sets the variables according to the parameters
     *
     * @param user           user
     * @param commonName     common name
     * @param scientificName scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(User user, String commonName, String scientificName, String description, String story, String imageUrl) {
        this.user = user;
        this.date = getCurrentDate();
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.description = description;
        this.story = story;
        this.imageUrl = imageUrl;
    }

    /**
     * Sets the variables according to the parameters
     *
     * @param user           user
     * @param date           date
     * @param commonName     common name
     * @param scientificName scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(User user, String date, String commonName, String scientificName, String description, String story, String imageUrl) {
        this.user = user;
        this.date = date;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.description = description;
        this.story = story;
        this.imageUrl = imageUrl;
    }

    /**
     * s
     * Sets the variables according to the parameters
     *
     * @param user           user
     * @param date           date
     * @param commonName     common name
     * @param scientificName scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(User user, Date date, String commonName, String scientificName, String description, String story, String imageUrl) {
        this.user = user;
        this.date = Formatter.dateFormat.format(date);
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.description = description;
        this.story = story;
        this.imageUrl = imageUrl;
    }

    public Enums.DiscoveryType getDiscoveryType() {
        return discoveryType;
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
    public String getDate() {
        return date;
    }

    /**
     * Sets the date the discovery was made
     *
     * @param date discovered
     */
    public void setDate(String date) {
        this.date = date;
    }

    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return Formatter.dateFormat.format(cal.getTime());
    }

    /**
     * Returns the discovery's scientific name
     *
     * @return scientificName
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Sets the discovery's scientific name
     *
     * @param scientificName assigned by game
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Returns the user-given name
     *
     * @return commonName
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Sets the user-given name,
     *
     * @param commonName given by user
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
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
     * Returns the story of when the discovery was made
     *
     * @return story
     */
    public String getStory() {
        return story;
    }

    /**
     * Sets the story of when the discovery was made
     *
     * @param story when discovered
     */
    public void setStory(String story) {
        this.story = story;
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
