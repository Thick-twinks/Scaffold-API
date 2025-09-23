CREATE TABLE tasks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(500) NOT NULL,
    description TEXT,
    deadline TIMESTAMP,
    status VARCHAR(50) CHECK (status IN ('IN_PROGRESS', 'REQUIRES_CONFIRMATION', 'COMPLETED')),
    actual_completion_date TIMESTAMP,
    task_order INTEGER,
    task_type VARCHAR(50) CHECK (task_type IN ('REMARK', 'WORK', 'VIOLATION')),

    creator_id UUID NOT NULL REFERENCES members(id),
    executor_id UUID REFERENCES members(id),
    project_id UUID NOT NULL REFERENCES projects(id),
    classifier_id UUID REFERENCES violation_classifiers(id),
    creator_geopoint_id UUID REFERENCES geopoints(id),
    executor_geopoint_id UUID REFERENCES geopoints(id),
    confirmation_document_id UUID REFERENCES documents(id),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);