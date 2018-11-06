package ru.job4j.cars.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.function.Function;
/**
 * @author Alexander Belov (whiterabbit.nsk@gmail.com)
 * @since 05.10.18
 */
public class HibernateManager {
    private static SessionFactory factory;
    private static HibernateManager INSTANCE = new HibernateManager();
    private static String CONFIG = "/resources/hibernate.cfg.xml";

    private HibernateManager() {

    }


    public static HibernateManager getInstance() {
        openFactory();
        return INSTANCE;
    }

    public static HibernateManager getInstance(final String config) {
        CONFIG = config;
        openFactory();
        return INSTANCE;
    }

    public <T> T transaction(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public static void closeFactory() {
        factory.close();
    }

    public static void openFactory() {
        factory = new Configuration().configure(CONFIG).buildSessionFactory();
    }
}
