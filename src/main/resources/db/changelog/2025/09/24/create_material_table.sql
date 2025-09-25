CREATE TABLE materials (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status VARCHAR(20) NOT NULL ,
    document_link TEXT,
    project_id UUID NOT NULL,
    created_at TIMESTAMP,

    CONSTRAINT fk_materials_projects FOREIGN KEY (project_id) REFERENCES projects(id)
);