package com.lateroad.buber.service;

import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.map.DirectionCalculator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class MapService {
    private static final Logger LOGGER = Logger.getLogger(MapService.class);


    public String calculateDuration(String from, String to) throws BuberLogicException {
        String duration;
        try {
            JSONObject location = DirectionCalculator.calculateRoute(from, to);
            duration = location.getJSONObject("duration").getString("text");
        } catch (JSONException e) {
            LOGGER.error("JSONException was occurred while getting JSON from duration.", e);
            throw new BuberLogicException("Can't calculate your route.");
        }
        return duration;
    }

    public String calculateDistance(String from, String to) throws BuberLogicException {
        String duration;
        try {
            JSONObject location = DirectionCalculator.calculateRoute(from, to);
            duration = location.getJSONObject("distance").getString("text");
        } catch (JSONException e) {
            LOGGER.error("JSONException was occurred while getting JSON from distance.", e);
            throw new BuberLogicException("Can't calculate your route.");
        }
        return duration;
    }
}
