package br.com.pjbank.sdk.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JSONUtils {
    public static Map<String, Object> toMap(JSONObject object) {
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isBlank(object.toString()))
            return map;

        Iterator<String> keysItr = object.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray)
                value = toList((JSONArray) value);
            else if(value instanceof JSONObject)
                value = toMap((JSONObject) value);

            map.put(key, value);
        }

        return map;
    }

    public static List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);

            if(value instanceof JSONArray)
                value = toList((JSONArray) value);
            else if(value instanceof JSONObject)
                value = toMap((JSONObject) value);

            list.add(value);
        }

        return list;
    }
}
