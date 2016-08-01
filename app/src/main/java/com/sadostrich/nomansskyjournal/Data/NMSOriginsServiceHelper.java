package com.sadostrich.nomansskyjournal.Data;

import android.support.annotation.NonNull;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/28/16.
 */
public class NMSOriginsServiceHelper {

	public static final String SOLAR_SYSTEM = "system";
	public static final String STAR = "star";
	public static final String PLANET = "planet";
	public static final String FAUNA = "fauna";
	public static final String FLORA = "flora";
	public static final String STRUCTURE = "structure";
	public static final String ITEM = "item";
	public static final String SHIP = "ship";

	private static final int PAGE_SIZE = 20;

	@NonNull
	public static HashMap<String, String> getLoginBodyHashMap(String email, String password) {
		HashMap<String, String> loginBody = new HashMap<>();
		loginBody.put("username", email);
		loginBody.put("password", password);
		return loginBody;
	}


	/**
	 * {"query":{},"sort":{"createdAt":-1},"limit":6}
	 *
	 * @return
	 */
	@NonNull
	public static RequestBody getNewDiscoveriesRequestBody() {
		return getDiscoveriesBodyHashMap("", "createdAt", -1, PAGE_SIZE);
	}

	public static RequestBody getPopularDiscoveriesRequestBody() {
		return getDiscoveriesBodyHashMap("", "score", -1, PAGE_SIZE);
	}

	public static RequestBody getDiscoveriesBodyHashMap(String query, String sortKey, int
			sortNum, int limit) {
		String bodyString = "{\"query\":{" + query + "},\"sort\":{\"" + sortKey + "\":" + sortNum
				+ "},\"limit\":" + limit + "}";

		RequestBody body = RequestBody.create(MediaType.parse("text/plain"), bodyString);
		return body;
	}
}
