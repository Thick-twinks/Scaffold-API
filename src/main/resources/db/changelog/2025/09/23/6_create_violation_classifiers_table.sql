CREATE TABLE violation_classifiers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category VARCHAR(255) NOT NULL,
    kind VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    name VARCHAR(500) NOT NULL,
    fix_deadline_days INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);