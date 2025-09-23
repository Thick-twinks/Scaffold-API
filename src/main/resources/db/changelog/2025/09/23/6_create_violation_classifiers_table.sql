CREATE TABLE violation_classifiers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category VARCHAR(255) NOT NULL,
    kind VARCHAR(50) CHECK (kind IN ('FIXABLE', 'NON_FIXABLE')),
    type VARCHAR(50) CHECK (type IN ('SEVERE', 'SIMPLE')),
    name VARCHAR(500) NOT NULL,
    fix_deadline_days INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);