package com.sadostrich.nomansskyjournal.Data;

import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.Models.ConfigObjects.ConfigBaseObject;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.DiscoveryComment;
import com.sadostrich.nomansskyjournal.Models.Report;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by jacewardell on 5/10/16.
 */
public interface NMSOriginsService {
    String BASE_URL = "https://www.nmsorigins.com/";
//    String BASE_URL = "https://dev.nmsorigins.com/";

    /////////////////////////////////////////////////////////
    // Constants
    /////////////////////////////////////////////////////////

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
    @POST("auth/register")
    Call<Authentication> register(@Body RequestBody loginQuery);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("discoveries/find")
    Call<List<Discovery>> findDiscoveries(@Body RequestBody findDiscoveriesQuery);

    @GET("config.json")
    Call<ConfigBaseObject> getAddDiscoveryFields();

//	{"type":"system","_images":[{"_user":"572cfe824202ce392e70d8f8","_id":"57a5d1bcddd3727d49575a45",
// "fileurl":{"winQuadratic":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-winQuadratic.jpeg",
// "carousel":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-carousel.jpeg",
// "carouselThumb":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-carouselThumb.jpeg",
// "thumb":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-thumb.jpeg",
// "fullhd":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-fullhd.jpeg",
// "uploadthumb":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-uploadthumb.jpeg",
// "adminthumb":"https://www.nmsorigins.com/image/57a5d1bcddd3727d49575a45-adminthumb.jpeg"}}],
// "properties":{"system-type":"Triple","number-of-planets":13,"life":true,"dangerous":false,
// "inhabitants":["scientists","traders","militant"]},"tags":["traders","lots of planets"],
// "discoveredAt":"2016-08-10T06:00:00.000Z","name":"The cool system",
// "youtube":"https://www.youtube.com/watch?v=dQw4w9WgXcQQ&usg=AFQjCNEPGa2VKuL0GefK_nkQoh9csTD8OA&sig2=glLfRr0gUeD8pxyz82CtIA",
// "description":"<p>It's actually not a system, but a planet, but oh well.</p>"}

    @POST("discoveries/save")
    Call<Discovery> saveDiscovery(@Header("Cookie") String cookie, @Body RequestBody saveDiscoveriesQuery);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("discoveries/vote")
    Call<Void> likeDiscovery(@Header("Cookie") String cookie, @Body Discovery discovery);

    Call<Void> reportDiscovery(@Body Report report);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("discoveries/comments")
    Call<List<DiscoveryComment>> getDiscoveryComments(@Body RequestBody getCommentsQuery);

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("discoveries/comment")
    Call<Void> submitDiscoveryComment(@Header("Cookie") String cookie, @Body RequestBody submitCommentQuery);
}
