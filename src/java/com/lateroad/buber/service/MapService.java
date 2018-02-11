package com.lateroad.buber.service;

import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.map.DirectionCalculator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class provides common for every operations with map.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class MapService {
    private static final Logger LOGGER = Logger.getLogger(MapService.class);


    /**
     * Calculate duration for route according to origin and destination as latitude and
     * longitude points.
     *
     * @throws BuberLogicException if correct way of the operation was broken.
     */
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

    /**
     * Calculate distance for route according to origin and destination as latitude and
     * longitude points.
     *
     * @throws BuberLogicException if correct way of the operation was broken.
     */
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
