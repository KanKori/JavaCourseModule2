package com.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactoryUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);
    public static SessionFactory sessionFactory;
    private static Session session;
    private static HibernateSessionFactoryUtil instance;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                LOGGER.error(String.valueOf(e));
                StandardServiceRegistryBuilder.destroy(registry);
                throw e;
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static HibernateSessionFactoryUtil getInstance() {
        if (instance == null) {
            instance = new HibernateSessionFactoryUtil();
        }
        return instance;
    }
}