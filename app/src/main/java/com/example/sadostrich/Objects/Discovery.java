package com.example.sadostrich.Objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class that will be inherited and that represents any one of the user's discoveries
 * <p>
 * Created by jacewardell on 7/2/15.
 */
public class Discovery {
    private Date date;
	private String commonName, scientificName, description, story, imageUrl;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Discovery() {
		date = getCurrentDate();
		commonName = scientificName = description = story = imageUrl = "";
	}

    /**
     * Sets the variables according to the parameters
     *
	 * @param commonName     common name
	 * @param scientificName scientific name
     * @param description    description
     * @param story          story
     * @param imageUrl       image url
	 */
	public Discovery(String commonName, String scientificName, String description, String story, String imageUrl) {
		date = getCurrentDate();
		this.scientificName = scientificName;
        this.commonName = commonName;
        this.description = description;
        this.story = story;
        this.imageUrl = imageUrl;
    }

    /**
	 * Sets the variables according to the parameters
	 *
	 * @param date           date
	 * @param commonName     common name
	 * @param scientificName scientific name
	 * @param description    description
	 * @param story          story
	 * @param imageUrl       image url
	 */
	public Discovery(Date date, String commonName, String scientificName, String description, String story, String imageUrl) {
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

	public String getDateAsString() {
		return dateFormat.format(date);
	}

	public Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime(); //2014/08/06 16:00:22
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
