CREATE TABLE [room]
(
    table_id
    bigint
    NOT
    NULL,
    dealer_keycloak_id
    varchar
(
    255
),
    CONSTRAINT pk_table PRIMARY KEY
(
    table_id
)
    )
    GO

ALTER TABLE game
    ADD table_id bigint
    GO

ALTER TABLE game
    ALTER COLUMN table_id bigint NOT NULL
    GO

ALTER TABLE game
    ADD CONSTRAINT FK_GAME_ON_TABLE FOREIGN KEY (table_id) REFERENCES [room] (table_id)
    GO

ALTER TABLE [room]
    ADD CONSTRAINT FK_TABLE_ON_DEALER_KEYCLOAKID FOREIGN KEY (dealer_keycloak_id) REFERENCES player (keycloak_id)
    GO