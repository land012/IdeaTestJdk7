package com.umbrella.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by xudazhou on 2015/10/21.
 */
public class GsonUtil {
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson.fromJson(json, clazz);
    }
}
