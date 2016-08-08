package com.sadostrich.nomansskyjournal.Data;

import android.support.annotation.NonNull;

import com.sadostrich.nomansskyjournal.Models.Discovery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static RequestBody createGetNewDiscoveriesRequestBody() {
        return createGetDiscoveriesBodyHashMap("", "createdAt", -1, PAGE_SIZE);
    }

    public static RequestBody createGetPopularDiscoveriesRequestBody() {
        return createGetDiscoveriesBodyHashMap("", "score", -1, PAGE_SIZE);
    }

    public static RequestBody createGetDiscoveriesBodyHashMap(String query, String sortKey, int sortNum, int limit) {
        String bodyString = "{\"query\":{" + query + "},\"sort\":{\"" + sortKey + "\":" + sortNum + "},\"limit\":" + limit + "}";

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), bodyString);
        return body;
    }

    public static RequestBody createSaveDiscoveryBodyHashMap(String type, Map<String, Object> properties, List<String> tags, String discoveredAt,
                                                             String name, String youtubeUrl, String description) {
        String bodyString = "{\"type\":\"" + type + "\",\"_images\":[]," +
                " \"properties\":{" + createSaveDiscoveryPropertyString(properties) + "},\"tags\":[" + createSaveDiscoveryTagString(tags) + "]," +
                " \"discoveredAt\":\"" + discoveredAt + "\",\"name\":\"" + name + "\"," +
                " \"youtube\":\"" + youtubeUrl + "\"," +
                " \"description\":\"" + description + "\"}";

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), bodyString);
        return body;
    }

    /**
     * Format: "system-type":"Triple","number-of-planets":13,"life":true,"dangerous":false,
     * "inhabitants":["scientists","traders","militant"]
     *
     * @param propertiesMap
     * @return
     */
    private static String createSaveDiscoveryPropertyString(Map<String, Object> propertiesMap) {
        String propertiesString = "";
        int i = 0;
        for (Map.Entry<String, Object> property : propertiesMap.entrySet()) {
            if (property.getKey().equalsIgnoreCase("inhabitants")) {
                propertiesString += "\"" + property.getKey() + "\":[" + property.getValue() + "]";
            } else {
                propertiesString += "\"" + property.getKey() + "\":\"" + property.getValue() + "\"";
            }
            if (i != propertiesMap.size() - 1) {
                propertiesString += ",";
            }
            i++;
        }

        return propertiesString;
    }

    // Format: "traders","lots of planets"
    private static String createSaveDiscoveryTagString(List<String> tagsList) {
        String tagsString = "";
        int i = 0;
        for (String tag : tagsList) {
            tagsString += "\"" + tag + "\"";

            if (i != tagsList.size() - 1) {
                tagsString += ",";
            }
            i++;
        }

        return tagsString;
    }

    public static RequestBody createGetCommentsRequestBody(Discovery discovery) {
        String bodyString = "{\"query\":{\"_discovery\":\"" + discovery.getId() + "\"},\"limit\":50,\"page\":0}";

        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), bodyString);
        return body;
    }
}
