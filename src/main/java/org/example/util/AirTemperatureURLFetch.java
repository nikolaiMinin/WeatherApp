package org.example.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AirTemperatureURLFetch {

    private static final String PARAMS = "&params=airTemperature";
    private static final String START_TIME = "&start=2023-05-15T12:00:00";
    private static final String END_TIME = "&end=2023-05-15T12:00:00";
    private static final String SOURCE = "&source=dwd";
    private static final String API_URL = System.getenv("STORMGLASS_API_URL") + PARAMS + START_TIME + END_TIME + SOURCE;
    private static final String TOKEN = System.getenv("STORMGLASS_TOKEN");

    public static String fetchAirTemperature () throws IOException {
        System.out.println(API_URL);

        URL url = null;
        try {
            url = new URL(API_URL);
        } catch (MalformedURLException e) {
            System.out.println("EXCEPTION: MALFORMED URL");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("EXCEPTION: CONNECTION FAILURE");
        }

        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", TOKEN);
        } catch (ProtocolException e) {
            System.out.println("EXCEPTION: WRONG PROTOCOL");
        }

        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            System.out.println("EXCEPTION: RESPONSE CODE FAILURE");
        }
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();
            try (java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
                String line = null;
                while (true) {
                    try {
                        if (!((line = in.readLine()) != null)) break;
                    } catch (IOException e) {
                        System.out.println("EXCEPTION: CAN'T READ LINE");
                    }
                    response.append(line);
                }
            }
            System.out.println(response);
            return response.toString();

        } else {
            System.out.println("Failed to fetch data from API. Response code: " + responseCode);
            return null;
        }
    }
}
