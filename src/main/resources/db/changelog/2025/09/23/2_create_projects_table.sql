CREATE TABLE projects (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(500) NOT NULL,
    customer_id UUID NOT NULL REFERENCES members(id),
    contractor_id UUID REFERENCES members(id),
    geopoint_id UUID REFERENCES geopoints(id),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    opening_act VARCHAR(1000),
    status VARCHAR(50) CHECK (status IN ('REJECTED', 'STARTED', 'PENDING')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);