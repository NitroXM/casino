---------------------------------------------------------------------
-- V8__rename_table_to_room.sql
---------------------------------------------------------------------
SET XACT_ABORT ON;
GO

/* 1️⃣  Rename the table [table] → room */
IF OBJECT_ID('dbo.[table]', 'U') IS NOT NULL
    EXEC sp_rename 'dbo.[table]', 'room';
GO

/* 2️⃣  Rename column table_id → room_id in dbo.game  */
IF COL_LENGTH('dbo.game', 'table_id') IS NOT NULL
BEGIN
    -- drop FK if it exists
    IF OBJECT_ID('FK_GAME_ON_TABLE', 'F') IS NOT NULL
ALTER TABLE dbo.game DROP CONSTRAINT FK_GAME_ON_TABLE;
EXEC sp_rename 'dbo.game.table_id', 'room_id', 'COLUMN';
END
GO

/* 3️⃣  Rename column table_id → room_id in dbo.player */
IF COL_LENGTH('dbo.player', 'table_id') IS NOT NULL
BEGIN
    -- drop FK to [table] first (name may differ in your DB)
    IF OBJECT_ID('FK_PLAYER_ON_TABLE', 'F') IS NOT NULL
ALTER TABLE dbo.player DROP CONSTRAINT FK_PLAYER_ON_TABLE;

EXEC sp_rename 'dbo.player.table_id', 'room_id', 'COLUMN';
END
GO

/* 4️⃣  Re-create FK from player(room_id) → room(room_id) */
IF OBJECT_ID('FK_PLAYER_ON_ROOM', 'F') IS NULL
ALTER TABLE dbo.player
    ADD CONSTRAINT FK_PLAYER_ON_ROOM
        FOREIGN KEY (room_id) REFERENCES dbo.room(table_id)
            ON DELETE SET NULL;
GO
