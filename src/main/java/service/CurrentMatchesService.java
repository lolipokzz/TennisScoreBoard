package service;

import model.MatchScore;
import model.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchesService {
    private static final Map<UUID, MatchScore> currentMatchesMap = new ConcurrentHashMap<>();
    public UUID addNewMatch(Player player1, Player player2) {
        MatchScore matchScore = new MatchScore(player1, player2);
        currentMatchesMap.put(matchScore.getUuid(), matchScore);
        return matchScore.getUuid();
    }

    public String findFirstPlayerNameByUuid(UUID uuid) {
        MatchScore matchScore = currentMatchesMap.get(uuid);
        return matchScore.getPlayer1().getName();
    }

    public String findSecondPlayerNameByUuid(UUID uuid) {
        MatchScore matchScore = currentMatchesMap.get(uuid);
        return matchScore.getPlayer2().getName();
    }

    public MatchScore findMatchScoreByUuid(UUID uuid) {
        return currentMatchesMap.get(uuid);
    }
    public  void deleteMatchScoreByUuid(UUID uuid) {
        currentMatchesMap.remove(uuid);
    }
}
