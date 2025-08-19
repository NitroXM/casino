---------------------------------------------------------------------
-- V6__dealer_column_cleanup.sql
---------------------------------------------------------------------
SET XACT_ABORT ON;  -- let SQL Server roll back automatically on error
GO

/* 1️⃣  Ensure the column we want is present
       If dealer_id already exists, skip; else:
       - rename dealer_keycloak_id if present
       - otherwise add a fresh dealer_id column
*/
IF COL_LENGTH('dbo.[room]', 'dealer_id') IS NULL
BEGIN
    IF COL_LENGTH('dbo.[room]', 'dealer_keycloak_id') IS NOT NULL
        EXEC sp_rename 'dbo.[room].dealer_keycloak_id', 'dealer_id', 'COLUMN';
ELSE
ALTER TABLE dbo.[room] ADD dealer_id VARCHAR(255) NULL;
END
GO

/* 2️⃣  Drop old constraints if they exist */
IF OBJECT_ID('FK_TABLE_ON_DEALER_KEYCLOAKID', 'F') IS NOT NULL
ALTER TABLE dbo.[room] DROP CONSTRAINT FK_TABLE_ON_DEALER_KEYCLOAKID;
GO
IF OBJECT_ID('uc_table_dealer', 'UQ') IS NOT NULL
ALTER TABLE dbo.[room] DROP CONSTRAINT uc_table_dealer;
GO

/* 3️⃣  Add the new constraints (idempotent) */
IF OBJECT_ID('uc_table_dealer', 'UQ') IS NULL
ALTER TABLE dbo.[room]
    ADD CONSTRAINT uc_table_dealer UNIQUE (dealer_id);
GO

IF OBJECT_ID('FK_TABLE_ON_DEALER', 'F') IS NULL
ALTER TABLE dbo.[room]
    ADD CONSTRAINT FK_TABLE_ON_DEALER
    FOREIGN KEY (dealer_id) REFERENCES dbo.player(keycloak_id)
    ON DELETE SET NULL;
GO

/* 4️⃣  Drop the old column if it’s still around */
IF COL_LENGTH('dbo.[room]', 'dealer_keycloak_id') IS NOT NULL
ALTER TABLE dbo.[room] DROP COLUMN dealer_keycloak_id;
GO