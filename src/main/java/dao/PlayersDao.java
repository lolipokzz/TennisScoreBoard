package dao;

import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Optional;

public class PlayersDao {
    public void createNewPlayers(String name) {
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Player player = new Player(name);
            session.persist(player);
            session.getTransaction().commit();
        }
    }

    public Optional<Player> findPlayer(String name) {
        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Player player =session.createQuery("from Player where name = :name", Player.class).setParameter("name", name).uniqueResult();
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }
}
