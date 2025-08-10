package org.casino.gameplay;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casino.associativeEntities.GamePlayer;
import org.casino.enums.Rank;
import org.casino.enums.Suit;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long cardId;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Enumerated(EnumType.STRING)
    private Suit suit;

    @ManyToOne
    @JoinColumn(name = "game_player_id", nullable = false)
    private GamePlayer owner;
}


