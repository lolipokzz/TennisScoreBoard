package util;

import model.Match;
import model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final Configuration configuration = new Configuration().configure().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
