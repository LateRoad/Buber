package com.lateroad.buber.map;

import com.lateroad.buber.entity.Location;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.reader.JsonReader;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Geodecoder {
    private static final Logger LOGGER = Logger.getLogger(Geodecoder.class);


    public static Location decode(String lat, String lng) throws BuberLogicException {
        Location newLocation = new Location();
        try {
            final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";
            final StringBuilder builder = new StringBuilder(baseUrl);
            builder.append("?sensor");
            builder.append('=');
            builder.append(URLEncoder.encode("false", "utf-8"));
            builder.append("&language");
            builder.append('=');
            builder.append("&latlng");
            builder.append('=');
            builder.append(URLEncoder.encode(lat + "," + lng, "utf-8"));

            final JSONObject response = JsonReader.read(builder.toString());
            final JSONObject location = response.getJSONArray("results").getJSONObject(0);

            newLocation.setLat(lat);
            newLocation.setLng(lng);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException was occurred due to bad encoding while making url for Google Map API.", e);
            throw new BuberLogicException("Can't find route.");
        } catch (JSONException | IOException e) {
            LOGGER.error("IOException (JSONException) was occurred while sending url to Google Map API.", e);
            throw new BuberLogicException("Something went wrong.");
        }
        return newLocation;
    }
}
