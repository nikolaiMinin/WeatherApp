package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.AirTemperature;
import org.example.service.AirTemperatureService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Time;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AirTemperatureJsonFileReader {

    private static String URL = "C:\\Nikolai\\Programming\\JavaProjects\\WeatherApp\\src\\main\\resources\\json\\temperature-dwd.json";
    private static File file = new File(URL);
    private static String json;
    private static JsonNode jsonNode;
    public static void readFromFile () {
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
        JsonNode tempNode = jsonNode.at("/hours/0/airTemperature/dwd");
        System.out.println(tempNode);

        JsonNode timeNode = jsonNode.at("/hours/0/time");
        System.out.println(timeNode);

        String dateString = timeNode.asText();

        // Create a DateTimeFormatter for the given date format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        // Parse the string to an OffsetDateTime object
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);

        System.out.println(offsetDateTime);

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Session session = databaseConnection.connect();

        session.beginTransaction();

        AirTemperature weather = AirTemperature
                .builder()
                .airtemperature(tempNode.asInt())
                .offsetDateTime(offsetDateTime)
                .build();



        session.save(weather);
        session.getTransaction().commit();

    }
}
