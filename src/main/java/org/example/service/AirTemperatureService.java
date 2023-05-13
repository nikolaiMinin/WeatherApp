package org.example.service;

import org.example.entity.Weather;
import org.example.util.DatabaseConnection;
import org.hibernate.Session;

public class WeatherService {

    Session session = null;

    public void saveAirTemperature (int airTemperature) {

        session = DatabaseConnection.connect();
        session.beginTransaction();

        Weather weather = Weather
                .builder()
                .watertemperature(20)
                .airtemperature((int)airTemperature)
                .build();

        session.save(weather);
        session.getTransaction().commit();
    }

}
