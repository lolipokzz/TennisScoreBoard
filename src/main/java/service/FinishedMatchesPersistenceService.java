package service;

import dao.MatchesDao;
import model.Match;
import model.MatchScore;
import model.Player;

public class FinishedMatchesPersistenceService {
    public void addFinishedMatch(MatchScore matchScore) {
        Player winner;
        if(matchScore.getFirstPlayerScore().getSet() == 2) {
            winner = matchScore.getPlayer1();
        }else {
            winner = matchScore.getPlayer2();
        }
        Match match = new Match(matchScore.getPlayer1(),matchScore.getPlayer2(),winner);
        MatchesDao matchesDao = new MatchesDao();
        matchesDao.createNewMatch(match);
    }
}
