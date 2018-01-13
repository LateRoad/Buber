package com.lateroad.buber.logic.service;

import com.lateroad.buber.logic.map.DirectionCalculator;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MapService {
    public String calculatePrice(String from, String to) throws IOException, JSONException {
        JSONObject location = DirectionCalculator.calculateRoute(from, to);
        return location.getJSONObject("duration").getString("text");
    }

    public String calculateDistance(String from, String to) throws IOException, JSONException {
        JSONObject location = DirectionCalculator.calculateRoute(from, to);
        return location.getJSONObject("distance").getString("text");
    }
}
