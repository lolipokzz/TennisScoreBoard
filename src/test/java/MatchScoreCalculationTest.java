import junit.framework.Assert;
import model.MatchScore;
import model.Player;
import org.junit.jupiter.api.Test;
import service.CurrentMatchesService;
import service.MatchScoreCalculationService;

import java.util.UUID;

public class MatchScoreCalculationTest {
    MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    CurrentMatchesService currentMatchesService = new CurrentMatchesService();
    UUID uuid = currentMatchesService.addNewMatch(new Player("bob"),new Player("bib"));
    MatchScore matchScore = currentMatchesService.findMatchScoreByUuid(uuid);
    @Test
    public void matchScoreGameShouldNotEnd() {
        matchScore.getFirstPlayerScore().setPoints(40);
        matchScore.getSecondPlayerScore().setPoints(40);
        matchScore = matchScoreCalculationService.calculateMatchScore(1,uuid);
        Assert.assertEquals(40,matchScore.getFirstPlayerScore().getPoints());
        Assert.assertEquals(55,matchScore.getSecondPlayerScore().getPoints());
    }
    @Test
    public void matchScoreGameShouldEnd() {
        matchScore.getFirstPlayerScore().setPoints(40);
        matchScore = matchScoreCalculationService.calculateMatchScore(0,uuid);
        Assert.assertEquals(0,matchScore.getFirstPlayerScore().getPoints());
        Assert.assertEquals(1,matchScore.getFirstPlayerScore().getGames());

    }
    @Test
    public void matchScoreTiebreak() {
        matchScore.getFirstPlayerScore().setGames(6);
        matchScore.getSecondPlayerScore().setGames(6);
        matchScore = matchScoreCalculationService.calculateMatchScore(1,uuid);
        Assert.assertEquals(1,matchScore.getSecondPlayerScore().getPoints());
    }
}
