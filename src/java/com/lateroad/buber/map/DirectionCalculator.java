package com.lateroad.buber.map;

import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.reader.JsonReader;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Service class for calculate distance from one point to another.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class DirectionCalculator {
    private static final Logger LOGGER = Logger.getLogger(DirectionCalculator.class);


    private DirectionCalculator() {
    }

    /**
     * Calculate route details according to origin and destination as latitude and
     * longitude points.
     *
     * @throws BuberLogicException if correct way of the operation was broken.
     */
    public static JSONObject calculateRoute(String origin, String destination) throws BuberLogicException {
        JSONObject answer;
        final String baseUrl = "http://maps.googleapis.com/maps/api/directions/json";
        try {
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
            builder.append(URLEncoder.encode(origin, "utf-8"));
            builder.append("&destination");
            builder.append('=');
            builder.append(URLEncoder.encode(destination, "utf-8"));

            //final String url = baseUrl + '?' + MapEncoder.encodeParams(params);// генерируем путь с параметрами
            final JSONObject response = JsonReader.read(builder.toString());
            JSONObject location = response.getJSONArray("routes").getJSONObject(0);
            answer = location.getJSONArray("legs").getJSONObject(0);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException was occurred due to bad encoding while making url for Google Map Direction API.", e);
            throw new BuberLogicException("Can't calculate your route.");
        } catch (JSONException | IOException e) {
            LOGGER.error("IOException (JSONException) was occurred while sending url to Google Map Directions API.", e);
            throw new BuberLogicException("Can't calculate your route.");
        }
        return answer;
    }
}
