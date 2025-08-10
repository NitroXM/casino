CREATE
SEQUENCE card_seq START
WITH 1 INCREMENT BY 50
GO

CREATE
SEQUENCE game_player_seq START
WITH 1 INCREMENT BY 50
GO

CREATE TABLE card
(
    card_id        bigint NOT NULL,
    rank           varchar(255),
    suit           varchar(255),
    game_player_id bigint NOT NULL,
    CONSTRAINT pk_card PRIMARY KEY (card_id)
)
    GO

CREATE TABLE game
(
    game_id           varchar(255) NOT NULL,
    current_player_id bigint,
    is_finished       bit          NOT NULL,
    CONSTRAINT pk_game PRIMARY KEY (game_id)
)
    GO

CREATE TABLE game_player
(
    game_player_id  bigint       NOT NULL,
    game_id         varchar(255) NOT NULL,
    player_id       varchar(255) NOT NULL,
    dealer          bit          NOT NULL,
    seat_number     int          NOT NULL,
    bet             int          NOT NULL,
    can_double_down bit          NOT NULL,
    CONSTRAINT pk_gameplayer PRIMARY KEY (game_player_id)
)
    GO

CREATE TABLE player
(
    keycloak_id varchar(255) NOT NULL,
    email       varchar(255),
    given_name  varchar(255),
    balance     int          NOT NULL,
    CONSTRAINT pk_player PRIMARY KEY (keycloak_id)
)
    GO

ALTER TABLE game
    ADD CONSTRAINT uc_game_current_player UNIQUE (current_player_id)
    GO

ALTER TABLE card
    ADD CONSTRAINT FK_CARD_ON_GAME_PLAYER FOREIGN KEY (game_player_id) REFERENCES game_player (game_player_id)
    GO

ALTER TABLE game_player
    ADD CONSTRAINT FK_GAMEPLAYER_ON_GAME FOREIGN KEY (game_id) REFERENCES game (game_id)
    GO

ALTER TABLE game_player
    ADD CONSTRAINT FK_GAMEPLAYER_ON_PLAYER FOREIGN KEY (player_id) REFERENCES player (keycloak_id)
    GO

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_CURRENT_PLAYER FOREIGN KEY (current_player_id) REFERENCES game_player (game_player_id)
    GO