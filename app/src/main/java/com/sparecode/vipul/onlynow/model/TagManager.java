package com.sparecode.vipul.onlynow.model;

import java.util.HashMap;

/**
 * Created by vipul on 22/3/17.
 */

public class TagManager {
    private static String keyLat, keyLong, keyKeyWord, keyCategory, keyPopularQuery;
    private static String value;
    static HashMap<String, String> tags = new HashMap<>();

    public static void setKeyLat(String value) {
        tags.put("lat", value);
    }

    public static void setKeyLong(String value) {
        tags.put("long", value);
    }

    public static void setKeyKeyWord(String value) {
        tags.put("keyword", value);
    }

    public static void setKeyCategory(String value) {
        tags.put("cat_id", value);
    }

    public static void setKeyPopularQuery(String value) {
        tags.put("keyword1", value);
    }

    public static HashMap<String, String> getTagsFromManager() {
        return tags;
    }
}
