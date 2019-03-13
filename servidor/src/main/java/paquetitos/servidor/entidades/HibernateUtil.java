package paquetitos.servidor.entidades;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        SessionFactory sesFactory = null;
        try {
            // Create the SessionFactory from hibernate.cfg.xml

            sesFactory = new Configuration().configure()
                    .buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sesFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
