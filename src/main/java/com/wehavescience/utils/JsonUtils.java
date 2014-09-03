package com.wehavescience.utils;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class JsonUtils {
    private JsonUtils(){
    }

    public static <T> T asObject(String json, Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <T> T asObject(InputStreamReader json, Type type){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static <T> String asJson(T object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}