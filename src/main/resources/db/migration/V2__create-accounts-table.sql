CREATE TABLE IF NOT EXISTS accounts (
    id VARCHAR ( 36 ) PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,
    client_id VARCHAR ( 36 ) UNIQUE NOT NULL,
    encrypted_token TEXT NOT NULL,
    provider VARCHAR ( 50 ) NOT NULL
);
