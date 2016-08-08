package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.Models.Discovery;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by jacewardell on 5/10/16.
 */
public interface NMSOriginsService {
//	String BASE_URL = "https://www.nmsorigins.com/";
	String BASE_URL = "https://www.dev.nmsorigins.com";

	/////////////////////////////////////////////////////////
	// Request Placeholders
	/////////////////////////////////////////////////////////
	String USERNAME = "{username}";
	String PASSWORD = "{password}";

	String PAGE_NUM = "{page_num}";

	@Headers("Content-Type:application/json;charset=UTF-8")
	@POST("auth/login")
	Call<Authentication> login(@Body HashMap<String, String> loginQuery);

	@Headers("Content-Type:application/json;charset=UTF-8")
	@POST("discoveries/find")
	Call<List<Discovery>> findDiscoveries(@Body RequestBody findDiscoveriesQuery);
}
