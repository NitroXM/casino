ALTER TABLE game
    ADD created_at datetime
    GO

ALTER TABLE game
    ALTER COLUMN created_at datetime NOT NULL
    GO
    exec sp_rename 'game_player.dealer', 'is_dealer', 'COLUMN'
    GO