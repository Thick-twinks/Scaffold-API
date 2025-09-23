CREATE TABLE work_changes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    task_id UUID NOT NULL REFERENCES tasks(id),
    new_deadline TIMESTAMP NOT NULL,
    status VARCHAR(50) CHECK (status IN ('PENDING', 'CANCELED', 'ACCEPTED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);