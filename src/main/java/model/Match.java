package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "matches")
public class Match {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1",referencedColumnName = "id")
    private Player player1;


    @ManyToOne
    @JoinColumn(name = "player2",referencedColumnName = "id")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner",referencedColumnName = "id")
    private Player winner;

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;

    }
}
