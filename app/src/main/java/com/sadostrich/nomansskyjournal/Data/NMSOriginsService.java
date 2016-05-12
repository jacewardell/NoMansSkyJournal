package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Discovery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by jacewardell on 5/10/16.
 */
public interface NMSOriginsService {
    String BASE_URL = "https://www.nmsorigins.com/";

    /////////////////////////////////////////////////////////
    // Request Placeholders
    /////////////////////////////////////////////////////////
    String USERNAME = "{username}";
    String PASSWORD = "{password}";

    String PAGE_NUM = "{page_num}";

    /////////////////////////////////////////////////////////
    // MongoDB body queries
    /////////////////////////////////////////////////////////

    String LOGIN_QUERY = "{\"username\":\"" + USERNAME + "\",\"password\":\"" + PASSWORD + "\",\"remember\":false}";

    String FIND_DISCOVERIES_QUERY = "{\"query\":{\"type\":{\"$in\":[\"system\",\"planet\",\"animal\",\"ship\",\"flora\",\"structure\",\"station\"," +
            "\"star\",\"item\",\"system\"]},\"_images\":{\"$exists\":true,\"$not\":{\"$size\":0}}},\"model\":\"discovery\",\"limit\":6," +
            "\"page\":" + PAGE_NUM + ",\"sort\":{\"score\":-1},\"populate\":[\"_discoveredBy\",\"_images\",\"_parent\",\"_image\"]}";

    @POST("auth/login")
    @Headers({
            "Origin:" + NMSOriginsService.BASE_URL,
            "Accept-Encoding:gzip, deflate",
            "Accept-Language:en-US,en;q=0.8,de;q=0.6",
            "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36",
            "Content-Type:application/json;charset=UTF-8",
            "Accept:application/json, text/plain, */*",
            "Referer:https://www.nmsorigins.com/",
            "Cookie:__unam=dd1ddf7-1548ec2d1ae-4ca325cf-4; nmsexplorer=s%3Ac6sL0H_T1DdYSd9P35R0Uu9DMKJoQscv.9DSp1QKRLvk7JtZ3x9%2BCqxYIZdzUkU0pc7Z7RAikXIM",
            "Connection:keep-alive"
    })
    Call<Authentication> login(@Body String loginQuery);

    @POST("crud/discovery/find")
    Call<List<Discovery>> findDiscoveries(@Body String findDiscoveriesQuery);
}
