package com.lateroad.buber.logic.map;

import com.google.common.collect.Maps;
import com.lateroad.buber.logic.reader.JsonReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;


//AIzaSyB7v8vxtjRrOIvLPAcTuEMC_UDsmshieWU

public class DirectionCalculator {
    public static void calculate(String pointFrom, String pointTo) throws IOException, JSONException {
        final String baseUrl ="http://maps.googleapis.com/maps/api/directions/json";
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "ru");
        params.put("mode", "driving");
        params.put("origin", pointFrom);

        params.put("destination", pointTo);

        final String url = baseUrl + '?' + MapEncoder.encodeParams(params);
        final JSONObject response = JsonReader.read(url);

        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);
        final String distance = location.getJSONObject("distance").getString("text");
        final String duration = location.getJSONObject("duration").getString("text");
        System.out.println(distance + "\n" + duration);
    }
}
