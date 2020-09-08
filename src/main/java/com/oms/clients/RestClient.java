package com.oms.clients;

import org.springframework.web.client.RestTemplate;

public class RestClient {
    private static final String URL = "https://nominatim.openstreetmap.org/";
    private static RestTemplate restTemplate = new RestTemplate();

    public static String getJsonAsString(String state, String country) {
            return restTemplate.getForObject(URL +
                            "search?state=" + state +
                            "&country=" + country +
                            "&format=json&polygon_geojson=1",
                    String.class);

    }
}
