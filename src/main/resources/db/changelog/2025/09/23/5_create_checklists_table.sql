CREATE TABLE checklists (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id UUID NOT NULL,
    type VARCHAR(50) NOT NULL,
    document TEXT,
    created_at TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_checklist_project FOREIGN KEY (project_id) REFERENCES projects(id)
);