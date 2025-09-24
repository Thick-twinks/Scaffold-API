CREATE TABLE work_scope_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id UUID NOT NULL,
    item_order INTEGER NOT NULL,
    task_description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_work_scope_items_projects FOREIGN KEY (project_id) REFERENCES projects(id)
);