ALTER TABLE player
    ADD table_id bigint
    GO

ALTER TABLE player
    ADD CONSTRAINT FK_PLAYER_ON_TABLE FOREIGN KEY (table_id) REFERENCES [room] (table_id)
    GO

ALTER TABLE game_player
    ALTER COLUMN bet int NULL
    GO

ALTER TABLE game_player
    ALTER COLUMN can_double_down bit NULL
    GO

ALTER TABLE game_player
    ALTER COLUMN has_won bit NULL
    GO

ALTER TABLE game_player
    ALTER COLUMN seat_number int NULL
    GO