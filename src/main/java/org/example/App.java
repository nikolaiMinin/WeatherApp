package org.example;

import org.example.config.ApplicationConfiguration;
import org.example.service.AirTemperatureService;
import org.example.util.AirTemperatureURLFetch;
import org.example.util.DatabaseConnection;
import org.example.util.AirTemperatureJsonFileReader;
import org.example.util.JsonParser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

//    String response = null;
//
//    try {
//        response = AirTemperatureURLFetch.fetchAirTemperature();
//    } catch (IOException e) {
//        System.out.println("IO Exception");
//    }
//
//    int temp = JsonParser.getAirTemperature(response);
//
//    DatabaseConnection databaseConnection = new DatabaseConnection();
//    databaseConnection.connect();
//
//    AirTemperatureService airTemperatureService = new AirTemperatureService();
//    airTemperatureService.saveAirTemperature(temp);
//
//        AirTemperatureJsonFileReader.readFromFile();

        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        AirTemperatureService airTemperatureService = new AirTemperatureService();
        airTemperatureService.readFromDB();
    }
}
