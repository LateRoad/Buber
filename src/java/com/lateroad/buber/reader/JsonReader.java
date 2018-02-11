package com.lateroad.buber.reader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Class provides sending URL request and reading response out of it.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public class JsonReader {

    private JsonReader() {
    }

    /**
     * Returns a JSONObject made from <code>URL</code> response. Can throw
     * <code>IOException</code> and <code>JSONExceptionif</code> if
     * url param is wrong or response is not in JSON format.
     *
     * @param url represents request for some API.
     * @return <code>JSONObject</code> object.
     * @throws IOException   if url param is wrong.
     * @throws JSONException if url is wrong or when response from API is not in JSON
     *                       format.
     * @see JSONObject
     */
    public static JSONObject read(final String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            final String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    /**
     * Returns a String that has been read by reader.
     *
     * @param reader is Reader.
     * @return <code>String</code> object which has been read by Reader.
     * @throws IOException standard exception of Reader.
     * @see JSONObject
     */
    private static String readAll(final Reader reader) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
