CREATE TABLE projects (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(500) NOT NULL,
    customer_id UUID NOT NULL,
    contractor_id UUID,
    geopoint_id UUID,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    opening_act VARCHAR(1000),
    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_project_customer FOREIGN KEY (customer_id) REFERENCES member(id),
    CONSTRAINT fk_project_contractor FOREIGN KEY (contractor_id) REFERENCES member(id),
    CONSTRAINT fk_project_geopoint FOREIGN KEY (geopoint_id) REFERENCES geopoints(id)
);