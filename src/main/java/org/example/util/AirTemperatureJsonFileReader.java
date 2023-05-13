package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.AirTemperatureService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonFileReader {

    String URL = "C:\\Nikolai\\Programming\\JavaProjects\\WeatherApp\\src\\main\\resources\\json\\temperature-dwd.json";
    File file = new File(URL);
    String json;

    JsonNode jsonNode = null;
    JsonNode timeNode = null;

    {
        try {
            json = new String(Files.readAllBytes(file.toPath()));
            System.out.println("SUCCESSFUL JSON READING FROM FILE ");
            System.out.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Parse the JSON string into a JsonNode object
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

        AirTemperatureService airTemperatureService = new AirTemperatureService();
        airTemperatureService.saveAirTemperature(timeNode.asInt());
    }
}
