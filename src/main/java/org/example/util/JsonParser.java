package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    /**
     *
     * @param json fetched from Weather provider (AirTemperature should be included)
     * @return Integer value of AirTemperature
     */

    public static int getAirTemperature(String json) {

        JsonNode jsonNode;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(json);
            System.out.println("SUCCESSFUL STRING PARSING TO JSON");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(jsonNode.get("hours"));
        JsonNode timeNode = jsonNode.at("/hours/0/airTemperature/dwd");
        System.out.println(timeNode);

        return timeNode.asInt();
    }

//    public static int getDate(String json) {
//
//        JsonNode jsonNode;
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            jsonNode = objectMapper.readTree(json);
//            System.out.println("SUCCESSFUL STRING PARSING TO JSON");
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(jsonNode.get("hours"));
//        JsonNode timeNode = jsonNode.at("/hours/0/time");
//        System.out.println(timeNode);
//
//        return timeNode.asInt();
//    }
}
