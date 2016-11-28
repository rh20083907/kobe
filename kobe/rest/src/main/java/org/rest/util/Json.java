package org.rest.util;

import com.google.gson.Gson;

public class Json {
	public static Gson gson = new Gson();

	public static String toJson(Object obj) {
		if (obj == null) {
			return "";
		}
		return gson.toJson(obj);
	}

	public static <T> T getObj(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
}
