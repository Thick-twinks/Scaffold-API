CREATE TABLE tasks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(500) NOT NULL,
    description TEXT,
    deadline TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    actual_completion_date TIMESTAMP,
    task_order INTEGER,
    type VARCHAR(50) NOT NULL,

    creator_id UUID NOT NULL,
    executor_id UUID,
    project_id UUID NOT NULL,
    classifier_id UUID,
    creator_geopoint_id UUID,
    executor_geopoint_id UUID,
    confirmation_document_id UUID,

    CONSTRAINT fk_tasks_creator FOREIGN KEY (creator_id) REFERENCES member(id),
    CONSTRAINT fk_tasks_executor FOREIGN KEY (executor_id) REFERENCES member(id),
    CONSTRAINT fk_tasks_project FOREIGN KEY (project_id) REFERENCES projects(id),
    CONSTRAINT fk_tasks_classifier FOREIGN KEY (classifier_id) REFERENCES violation_classifiers(id),
    CONSTRAINT fk_tasks_creator_geopoint FOREIGN KEY (creator_geopoint_id) REFERENCES geopoints(id),
    CONSTRAINT fk_tasks_executor_geopoint FOREIGN KEY (executor_geopoint_id) REFERENCES geopoints(id),
    CONSTRAINT fk_tasks_confirmation_document FOREIGN KEY (confirmation_document_id) REFERENCES documents(id)
);
