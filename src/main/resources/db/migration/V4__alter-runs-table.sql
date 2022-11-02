ALTER TABLE runs
    ADD COLUMN github_owner VARCHAR ( 255 ) NOT NULL,
    ADD COLUMN github_repository_name VARCHAR ( 255 ) NOT NULL,
    ADD COLUMN github_workflow_id VARCHAR ( 255 ) NOT NULL,
    ADD COLUMN github_ref VARCHAR ( 255 ) NOT NULL;
