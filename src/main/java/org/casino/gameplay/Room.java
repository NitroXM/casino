package org.casino.gameplay;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue
    private Long roomId;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id", unique = true)     // FK lives in Room
    private Player dealer;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Player> players = new ArrayList<>();
}

