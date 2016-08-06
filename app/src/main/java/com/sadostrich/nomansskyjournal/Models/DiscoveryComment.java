package com.sadostrich.nomansskyjournal.Models;

import java.io.Serializable;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jacobus LaFazia on 8/5/2016.
 */
public class DiscoveryComment implements Serializable {

	private User mUser;
	private String mTimeAgo;
	private String mComment;
	private int mReportsCount;

	/**
	 * Default empty constructor.
	 */
	public DiscoveryComment() {
	}

	public User getUser() {
		return mUser;
	}

	public void setUser(User user) {
		mUser = user;
	}

	public String getTimeAgo() {
		return mTimeAgo;
	}

	public void setTimeAgo(String timeAgo) {
		mTimeAgo = timeAgo;
	}

	public String getComment() {
		return mComment;
	}

	public void setComment(String comment) {
		mComment = comment;
	}

	public int getReportsCount() {
		return mReportsCount;
	}

	public void setReportsCount(int reportsCount) {
		mReportsCount = reportsCount;
	}

}
