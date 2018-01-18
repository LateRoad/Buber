package com.lateroad.buber.map;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class MapEncoder {
    private MapEncoder() {
    }

    public static String encodeParams(final Map<String, String> params) {
        return Joiner.on('&').join(
                params.entrySet().stream().map((Map.Entry<String, String> input) -> {
                    try {
                        final StringBuilder builder = new StringBuilder();
                        builder.append(input.getKey());
                        builder.append('=');
                        builder.append(URLEncoder.encode(input.getValue(), "utf-8"));
                        return builder.toString();
                    } catch (final UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()));
    }
}
