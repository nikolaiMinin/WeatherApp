package org.example.service;

import org.example.entity.AirTemperature;
import org.example.util.DatabaseConnection;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirTemperatureService {

    Session session = null;

    public void saveAirTemperature (int airTemperature) {

        session = DatabaseConnection.connect();
        session.beginTransaction();

        AirTemperature weather = AirTemperature
                .builder()
                .airtemperature((int)airTemperature)
                .build();

        session.save(weather);
        session.getTransaction().commit();
        session.close();
    }

    public void readFromDB () {

        session = DatabaseConnection.connect();
        session.beginTransaction();

        System.out.println(session
                .createQuery("SELECT at.airtemperature FROM AirTemperature at", Integer.class)
                .getResultList());

        session.getTransaction().commit();
        session.close();
    }

}
