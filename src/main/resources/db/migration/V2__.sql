ALTER TABLE game
    ADD has_started bit
    GO

ALTER TABLE game
    ALTER COLUMN has_started bit NOT NULL
    GO

ALTER TABLE game_player
    ADD has_won bit
    GO

ALTER TABLE game_player
    ADD is_done bit
    GO

ALTER TABLE game_player
    ALTER COLUMN has_won bit NOT NULL
    GO

ALTER TABLE game_player
    ALTER COLUMN is_done bit NOT NULL
    GO