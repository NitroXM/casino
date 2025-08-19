package org.casino.associativeEntities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.casino.gameplay.Card;
import org.casino.gameplay.Game;
import org.casino.gameplay.Player;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
public class GamePlayer {
    @Id @GeneratedValue
    private Long gamePlayerId;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @OneToMany(mappedBy="owner", cascade=ALL, orphanRemoval=true)
    private List<Card> hand;

    private boolean  isDealer;

    private Integer seatNumber;

    @Column(nullable = true)
    private Integer bet;

    @Column(nullable = true)
    private Boolean canDoubleDown;

    private boolean isDone;

    @Column(nullable = true)
    private Boolean hasWon;
}
