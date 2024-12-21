package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MatchScore {
    private Player player1;

    private Player player2;

    private UUID uuid;

    private PlayerScore firstPlayerScore;

    private PlayerScore secondPlayerScore;

    private boolean isMatchEnded;

    public MatchScore(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.uuid = UUID.randomUUID();
        firstPlayerScore = new PlayerScore();
        secondPlayerScore = new PlayerScore();
        isMatchEnded = false;
    }


}
