package org.casino.gameplay;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casino.associativeEntities.GamePlayer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Game {
    @Id
    private String gameId;

    // whose turn it is right now
    @OneToOne
    @JoinColumn(name = "current_player_id")
    private GamePlayer currentPlayer;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> participants = new ArrayList<>();

    // convenient helper
    public GamePlayer getDealer() {
        return participants.stream()
                .filter(GamePlayer::isDealer)
                .findFirst()
                .orElseThrow();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    private boolean hasStarted;
    private boolean isFinished;
}
