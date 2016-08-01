package com.sadostrich.nomansskyjournal.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
	private String description, name, discoveredAt;
	private List<String> tags;
	private int timesFlagged, commentCount, score, views;
	@SerializedName("_images")
	private List<DiscoveryImage> image;


	public Discovery() {
		//        this.user = new User();
		//		date = getCurrentDate();
		//        commonName = name = description = story = imageUrl = "";
	}

	/**
	 * Sets the variables according to the parameters
	 *
	 * @param user
	 * 		user
	 * @param commonName
	 * 		common name
	 * @param name
	 * 		scientific name
	 * @param description
	 * 		description
	 * @param story
	 * 		story
	 * @param imageUrl
	 * 		image url
	 */
	public Discovery(User user, String commonName, String name, String description, String story,
			String imageUrl) {
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
	 * @param user
	 * 		user
	 * @param date
	 * 		date
	 * @param commonName
	 * 		common name
	 * @param name
	 * 		scientific name
	 * @param description
	 * 		description
	 * @param story
	 * 		story
	 * @param imageUrl
	 * 		image url
	 */
	public Discovery(User user, String date, String commonName, String name, String description,
			String story, String imageUrl) {
		//        this.user = user;
		//        this.date = date;
		//        this.commonName = commonName;
		//        this.name = name;
		//        this.description = description;
		//        this.story = story;
		//        this.imageUrl = imageUrl;
	}

	/**
	 * s Sets the variables according to the parameters
	 *
	 * @param user
	 * 		user
	 * @param date
	 * 		date
	 * @param commonName
	 * 		common name
	 * @param name
	 * 		scientific name
	 * @param description
	 * 		description
	 * @param story
	 * 		story
	 * @param imageUrl
	 * 		image url
	 */
	public Discovery(User user, Date date, String commonName, String name, String description,
			String story, String imageUrl) {
		//        this.user = user;
		//        this.date = Formatter.dateFormat.format(date);
		//        this.commonName = commonName;
		//        this.name = name;
		//        this.description = description;
		//        this.story = story;
		//        this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DiscoveryProperties getProperties() {
		return properties;
	}

	public void setProperties(DiscoveryProperties properties) {
		this.properties = properties;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscoveredAt() {
		return discoveredAt;
	}

	public void setDiscoveredAt(String discoveredAt) {
		this.discoveredAt = discoveredAt;
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

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<DiscoveryImage> getImage() {
		return image;
	}

	public void setImage(List<DiscoveryImage> image) {
		this.image = image;
	}
}
