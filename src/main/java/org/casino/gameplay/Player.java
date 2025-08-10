package org.casino.gameplay;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casino.associativeEntities.GamePlayer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Player {
    @Id
    private String keycloakId;
    private String email;
    private String givenName;
    private int balance;

    @OneToMany(mappedBy = "player")
    private List<GamePlayer> gamePlayer = new ArrayList<>();

    public Player(String keycloakId, String email, String givenName) {
        this.keycloakId = keycloakId;
        this.email = email;
        this.givenName = givenName;
        this.balance = 1000;
    }
}
