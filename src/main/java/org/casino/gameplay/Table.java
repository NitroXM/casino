package org.casino.gameplay;

import jakarta.persistence.*;
import org.casino.gameplay.Game;
import org.casino.gameplay.Player;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long tableId;

    @OneToOne
    Player dealer;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games = new ArrayList<>();
}

