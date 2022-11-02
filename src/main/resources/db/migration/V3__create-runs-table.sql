CREATE TABLE IF NOT EXISTS runs (
    id VARCHAR ( 36 ) PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,
    account_id VARCHAR ( 36 ) NOT NULL,
    status VARCHAR ( 50 ) NOT NULL,
    scheduled_time TIMESTAMP NOT NULL
);

ALTER TABLE runs
    ADD CONSTRAINT fk_runs_accounts
        FOREIGN KEY (account_id)
            REFERENCES accounts (id);
