package com.lateroad.buber.map;

import com.lateroad.buber.entity.Location;
import com.lateroad.buber.reader.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

public class Geodecoder {
    public static Location decode(String lat, String lng) throws IOException, JSONException {
        Location newLocation = new Location();
        final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";
//        final Map<String, String> params = Maps.newHashMap();
//        params.put("language", "ru");
//        params.put("sensor", "false");
//        params.put("latlng", "55.735893,37.527420");

        final StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("?sensor");
        builder.append('=');
        builder.append(URLEncoder.encode("false", "utf-8"));
        builder.append("&language");
        builder.append('=');
        builder.append("&latlng");
        builder.append('=');
        builder.append(URLEncoder.encode(lat + "," + lng, "utf-8"));
        System.out.println(builder);
        System.out.println(lat);
        System.out.println(lng);

        final JSONObject response = JsonReader.read(builder.toString());
        final JSONObject location = response.getJSONArray("results").getJSONObject(0);

        newLocation.setLat(lat);
        newLocation.setLng(lng);
        newLocation.setHouseNumber(location.getJSONArray("address_components").getJSONObject(0).getString("short_name"));
        newLocation.setStreet(location.getJSONArray("address_components").getJSONObject(1).getString("short_name"));
        newLocation.setCity(location.getJSONArray("address_components").getJSONObject(5).getString("long_name"));
        newLocation.setCountry(location.getJSONArray("address_components").getJSONObject(6).getString("long_name"));

        System.out.println(newLocation);
        return newLocation;
    }
}
