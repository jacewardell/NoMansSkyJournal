package com.example.sadostrich.Objects;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class that will be inherited and that represents any one of the user's discoveries
 * <p>
 * Created by jacewardell on 7/2/15.
 */
public class Discovery {
    private Date date;
    private String scientificName, commonName, description, story, imageUrl;

    public Discovery() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        date = c.getTime();

        scientificName = commonName = description = story = imageUrl = "";
    }

    /**
     * Sets the variables according to the parameters
     *
     * @param date           date
     * @param scientificName scientific name
     * @param commonName     common name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
     */
    public Discovery(Date date, String scientificName, String commonName, String description, String story, String imageUrl) {
        this.date = date;
        this.scientificName = scientificName;
        this.commonName = commonName;
        this.description = description;
        this.story = story;
        this.imageUrl = imageUrl;
    }

    /**
     * Returns the date the discovery was made
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date the discovery was made
     *
     * @param date discovered
     */
    public void setDate(Date date) {
        this.date = date;
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
