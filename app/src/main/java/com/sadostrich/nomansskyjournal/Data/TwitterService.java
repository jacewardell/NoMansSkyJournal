package com.sadostrich.nomansskyjournal.Data;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * TODO JavaDoc
 * <p>
 * Created by Jace Wardell on 8/24/16.
 */
public interface TwitterService {
	String BASE_URL = "https://api.twitter.com/";

	@POST(TwitterServiceHelper.requestTokenUrl)
	Call<String> login(@Body HashMap<String, String> loginQuery);
}
