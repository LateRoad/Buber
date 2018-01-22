package com.lateroad.buber.map;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.lateroad.buber.reader.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.common.collect.Maps;

//AIzaSyB7v8vxtjRrOIvLPAcTuEMC_UDsmshieWU

public class DirectionCalculator {
    private DirectionCalculator() {
    }

    public static JSONObject calculateRoute(String pointFrom, String pointTo) throws IOException, JSONException {

        final String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";

//        final Map<String, String> params = new HashMap<>();
//        params.put("sensor", "false");
//        params.put("language", "ru");
//        params.put("mode", "driving");
//        params.put("origin", "Беларусь, Минск, проезд Сморговский, 29");
//        params.put("destination", "Беларусь, Минск, проспект Пушкина, 81");

        final StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("?sensor");
        builder.append('=');
        builder.append(URLEncoder.encode("false", "utf-8"));
        builder.append("&language");
        builder.append('=');
        builder.append(URLEncoder.encode("ru", "utf-8"));
        builder.append("&mode");
        builder.append('=');
        builder.append(URLEncoder.encode("driving", "utf-8"));
        builder.append("&origin");
        builder.append('=');
        builder.append(URLEncoder.encode(pointFrom, "utf-8"));
        builder.append("&destination");
        builder.append('=');
        builder.append(URLEncoder.encode(pointTo, "utf-8"));

        //final String url = baseUrl + '?' + MapEncoder.encodeParams(params);// генерируем путь с параметрами
        final JSONObject response = JsonReader.read(builder.toString());

        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        return  location.getJSONArray("legs").getJSONObject(0);
    }
}
