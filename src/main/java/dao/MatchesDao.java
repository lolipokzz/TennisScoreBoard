package dao;


import model.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class MatchesDao {

    public void createNewMatch(Match match) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(match);
        session.getTransaction().commit();
    }


    public List<Match> getAllMatches() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Match> matches = session.createQuery("from Match").list();
        session.getTransaction().commit();
        return matches;
    }


    public List<Match> findMatchesByPlayersName(String playerName) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Match m where m.player1.name = :playerName or m.player2.name = :playerName");
        query.setParameter("playerName", playerName);
        List<Match> matches = query.list();
        session.getTransaction().commit();
        return matches;
    }
}
