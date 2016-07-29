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
	public static RequestBody getDiscoveriesBodyHashMap() {
		return getDiscoveriesBodyHashMap("", "createdAt", -1, 6);
	}

	public static RequestBody getDiscoveriesBodyHashMap(String query, String sortKey, int
			sortNum, int limit) {
		String bodyString = "{\"query\":{" + query + "},\"sort\":{\"" + sortKey + "\":" + sortNum
	+ "},\"limit\":" + limit + "}";

		RequestBody body = RequestBody.create(MediaType.parse("text/plain"), bodyString);
		return body;
	}
}
