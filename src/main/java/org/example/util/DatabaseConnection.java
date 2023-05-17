package org.example.util;


import org.example.entity.AirTemperature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class DatabaseConnection {

    public static Session connect() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(AirTemperature.class);
        configuration.configure();

        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("SUCCESSFUL CONNECTION");


        } catch (Exception ex) {
            if (sessionFactory != null) {
                sessionFactory.close();
            }

            throw new RuntimeException("Failed to connect to database", ex);
        }

        return session;
    }

}
